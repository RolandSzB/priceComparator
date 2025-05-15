package com.example.priceComparator.service;

import com.example.priceComparator.model.ProductModel;
import com.example.priceComparator.util.CsvProductOfferReader;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    public List<ProductModel> searchProducts(String query) {
        List<ProductModel> allOffers = CsvProductOfferReader.readAllOffersFromFolder();
        return allOffers.stream()
                .filter(p -> p.getProductName().toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<ProductModel> optimizeBasket(List<String> productQueries) {
        List<ProductModel> allOffers = CsvProductOfferReader.readAllOffersFromFolder();
        List<ProductModel> optimizedBasket = new ArrayList<>();

        for (String query : productQueries) {
            List<ProductModel> matchingProducts = allOffers.stream()
                    .filter(p -> p.getProductName().toLowerCase().contains(query.toLowerCase()))
                    .collect(Collectors.toList());

            Optional<ProductModel> bestOffer = matchingProducts.stream()
                    .min(Comparator.comparingDouble(p -> {
                        double finalPrice = p.getPrice();
                        if (p.getDiscountPercentage() > 0) {
                            finalPrice = finalPrice * (1 - p.getDiscountPercentage() / 100);
                        }
                        return finalPrice;
                    }));

            bestOffer.ifPresent(optimizedBasket::add);
        }

        return optimizedBasket;
    }
}
