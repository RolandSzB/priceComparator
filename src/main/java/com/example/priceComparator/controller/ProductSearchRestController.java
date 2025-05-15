package com.example.priceComparator.controller;

import com.example.priceComparator.model.ProductModel;
import com.example.priceComparator.util.CsvProductOfferReader;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ProductSearchRestController {

    @GetMapping("/api/search")
    public List<ProductModel> searchProducts(@RequestParam("query") String query) {
        List<ProductModel> allOffers = CsvProductOfferReader.readAllOffersFromFolder();
        return allOffers.stream()
                .filter(offer -> offer.getProductName().toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());
    }

    @PostMapping("/basket/optimize")
    public ResponseEntity<List<ProductModel>> optimizeBasket(@RequestBody List<String> productQueries) {
        List<ProductModel> optimized = productService.optimizeBasket(productQueries);
        return ResponseEntity.ok(optimized);
    }
}
