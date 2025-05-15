package com.example.priceComparator.util;

import com.example.priceComparator.model.DiscountModel;
import com.example.priceComparator.model.ProductModel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CsvProductOfferReader {

    public static List<ProductModel> readAllOffersFromFolder() {
        List<ProductModel> offers = new ArrayList<>();
        File folder = new File(folderPath);

        for (File file : folder.listFiles()) {
            if (file.getName().endsWith("_offers.csv")) {
                String storeName = file.getName().split("_")[0]; // kaufland, lidl, penny
                try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                    String line = br.readLine(); // skip header
                    while ((line = br.readLine()) != null) {
                        String[] parts = line.split(",");
                        if (parts.length >= 8) {
                            ProductModel offer = new ProductModel();
                            offer.setProductId(parts[0]);
                            offer.setProductName(parts[1]);
                            offer.setProductCategory(parts[2]);
                            offer.setBrand(parts[3]);
                            offer.setPackageQuantity(Double.valueOf(parts[4]));
                            offer.setPackageUnit(parts[5]);
                            offer.setPrice(Double.parseDouble(parts[6]));
                            offer.setCurrency(parts[7]);
                            offer.setStoreName(storeName);
                            offers.add(offer);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }


        List<DiscountModel> discounts = CsvDiscountReader.readDiscountsFromFolder(folderPath);
        applyDiscounts(offers, discounts);

        return offers;
    }

    private static void applyDiscounts(List<ProductModel> offers, List<DiscountModel> discounts) {
        LocalDate today = LocalDate.now();

        for (ProductModel offer : offers) {
            discounts.stream()
                    .filter(d -> d.getProductId().equals(offer.getProductId()))
                    .filter(d -> !today.isBefore(d.getFromDate()) && !today.isAfter(d.getToDate()))
                    .findFirst()
                    .ifPresent(d -> {
                        double discountAmount = offer.getPrice() * d.getPercentageOfDiscount() / 100;
                        offer.setDiscountedPrice(offer.getPrice() - discountAmount);
                    });
        }
    }
}
