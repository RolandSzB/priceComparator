package com.example.priceComparator.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class ProductModel {
    private String productId;
    private String productName;
    private String productCategory;
    private String brand;
    private Double packageQuantity;
    private String packageUnit;
    private Double price;
    private String currency;
    private String storeName;

    private Double discountedPrice;
    private double discountPercentage = 0;
    private LocalDate DiscountFromDate;
    private LocalDate DiscountToDate;

}
