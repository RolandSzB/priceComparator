package com.example.priceComparator.controller;

import com.example.priceComparator.model.ProductModel;
import com.example.priceComparator.util.CsvProductOfferReader;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @GetMapping
    public List<ProductModel> getAllProducts() {
        return CsvProductOfferReader.readAllOffersFromFolder();
    }
}
