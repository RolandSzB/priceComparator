package com.example.priceComparator.controller;

import com.example.priceComparator.model.ProductModel;
import com.example.priceComparator.util.CsvProductOfferReader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class ProductController {

    @GetMapping("/offers")
    public String getAllOffers(Model model) {
        List<ProductModel> offers = CsvProductOfferReader.readAllOffersFromFolder("src/main/resources/csv");
        model.addAttribute("offers", offers);
        return "offers";
    }
    @GetMapping("/offers/grouped")
    public String getGroupedOffers(Model model) {
        List<ProductModel> offers = CsvProductOfferReader.readAllOffersFromFolder("src/main/resources/csv");
        Map<String, List<ProductModel>> groupedOffers = offers.stream()
                .collect(Collectors.groupingBy(ProductModel::getStoreName));

        model.addAttribute("groupedOffers", groupedOffers);
        return "offers-grouped";
    }
}
