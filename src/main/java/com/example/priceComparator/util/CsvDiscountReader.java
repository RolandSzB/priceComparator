package com.example.priceComparator.util;

import com.example.priceComparator.model.DiscountModel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CsvDiscountReader {

    public static List<DiscountModel> readDiscountsFromFolder(String folderPath) {
        List<DiscountModel> discounts = new ArrayList<>();
        File folder = new File(folderPath);

        for (File file : folder.listFiles()) {
            if (file.getName().endsWith(".csv") && file.getName().contains("_discount_")) {
                try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                    String line = br.readLine(); // skip header
                    while ((line = br.readLine()) != null) {
                        String[] parts = line.split(";");
                        if (parts.length >= 9) {
                            DiscountModel discount = new DiscountModel();
                            discount.setProductId(parts[0]);
                            discount.setProductName(parts[1]);
                            discount.setBrand(parts[2]);
                            discount.setPackageQuantity(Double.parseDouble(parts[3]));
                            discount.setPackageUnit(parts[4]);
                            discount.setProductCategory(parts[5]);
                            discount.setFromDate(LocalDate.parse(parts[6]));
                            discount.setToDate(LocalDate.parse(parts[7]));
                            discount.setPercentageOfDiscount(Double.parseDouble(parts[8]));
                            discounts.add(discount);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return discounts;
    }
}
