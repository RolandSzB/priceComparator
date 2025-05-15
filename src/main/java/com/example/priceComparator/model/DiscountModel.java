package com.example.priceComparator.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiscountModel {
    private String productId;
    private String productName;
    private String brand;
    private double packageQuantity;
    private String packageUnit;
    private String productCategory;
    private LocalDate fromDate;
    private LocalDate toDate;
    private double percentageOfDiscount;
}
