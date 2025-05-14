package com.example.priceComparator.model;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class ProductModel {
    private String productId;
    private String productName;
    private String productCategory;
    private String brand;
    private Number packageQuantity;
    private String packageUnit;
    private Number price;
    private String currency;
    private String storeName;
}
