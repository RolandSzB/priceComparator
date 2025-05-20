package com.example.priceComparator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BasketPageController {

    @GetMapping("/basket")
    public String getBasketPage() {
        return "basket";
    }
}
