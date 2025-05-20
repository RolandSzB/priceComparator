package com.example.priceComparator.controller;

import com.example.priceComparator.model.ProductModel;
import com.example.priceComparator.service.ProductService;
import com.example.priceComparator.util.CsvProductOfferReader;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ProductSearchRestController {

    private final ProductService productService;

    public ProductSearchRestController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/search")
    public List<ProductModel> searchProducts(@RequestParam("query") String query) {
        List<ProductModel> allOffers = CsvProductOfferReader.readAllOffersFromFolder("/offers");
        return allOffers.stream()
                .filter(offer -> offer.getProductName().toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());
    }

    @PostMapping("/basket/optimize")
    public ResponseEntity<List<ProductModel>> optimizeBasket(@RequestBody List<String> productQueries) {
        List<ProductModel> optimized = productService.optimizeBasket(productQueries);
        return ResponseEntity.ok(optimized);
    }

    @GetMapping("/top-discounts")
    public List<ProductModel> getTopDiscounts() {
        return productService.getTopDiscountedOffers(10);
    }

    @GetMapping("/latest-discounts")
    public List<ProductModel> getLatestDiscounts() {
        return CsvProductOfferReader.getLatestDiscountOffers();
    }

}
