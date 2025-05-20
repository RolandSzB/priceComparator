package com.example.priceComparator.util;

import com.example.priceComparator.model.DiscountModel;
import com.example.priceComparator.model.ProductModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CsvProductOfferReader {

    public static List<ProductModel> readAllOffersFromFolder(String s) {
        List<ProductModel> offers = new ArrayList<>();

        String[] stores = {"kaufland", "lidl", "penny"};
        String todayDate = LocalDate.now().toString().replace("-", "");

        for (String store : stores) {
            String offersPath = String.format("/offers/%s_offers/%s_%s.csv", store, store, LocalDate.now().toString().replace("-", ""));
            String discountsPath = String.format("/offers/%s_offers/%s_discount_%s.csv", store, store, todayDate);

            List<DiscountModel> discounts = readDiscountsFromCsv(discountsPath);

            try (InputStream is = CsvProductOfferReader.class.getResourceAsStream(offersPath);
                 BufferedReader br = new BufferedReader(new InputStreamReader(is))) {

                String line;
                br.readLine();

                while ((line = br.readLine()) != null) {
                    String[] parts = line.split(",");

                    ProductModel product = new ProductModel();
                    product.setProductId(parts[0]);
                    product.setProductName(parts[1]);
                    product.setProductCategory(parts[2]);
                    product.setBrand(parts[3]);
                    product.setPackageQuantity(Double.parseDouble(parts[4]));
                    product.setPackageUnit(parts[5]);
                    product.setPrice(Double.parseDouble(parts[6]));
                    product.setCurrency(parts[7]);
                    product.setStoreName(store);


                    for (DiscountModel discount : discounts) {
                        if (discount.getProductId().equals(product.getProductId()) &&
                                isTodayBetween(discount.getFromDate(), discount.getToDate())) {
                            product.setDiscountPercentage(discount.getPercentageOfDiscount());
                            double discountedPrice = product.getPrice() * (1 - discount.getPercentageOfDiscount() / 100);
                            product.setDiscountedPrice(Math.round(discountedPrice * 100.0) / 100.0);
                            break;
                        }
                    }

                    offers.add(product);
                }

            } catch (Exception e) {
                System.err.println("Could not read file: " + offersPath + " - " + e.getMessage());
            }
        }

        return offers;
    }

    public static List<DiscountModel> readDiscountsFromCsv(String resourcePath) {
        List<DiscountModel> discounts = new ArrayList<>();

        try (InputStream is = CsvProductOfferReader.class.getResourceAsStream(resourcePath);
             BufferedReader br = new BufferedReader(new InputStreamReader(is))) {

            String line;
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");

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

        } catch (IOException | NullPointerException e) {
            System.err.println("Could not read discount file: " + resourcePath + " - " + e.getMessage());
        }

        return discounts;
    }

    private static boolean isTodayBetween(LocalDate from, LocalDate to) {
        LocalDate today = LocalDate.now();
        return (today.isEqual(from) || today.isAfter(from)) && (today.isEqual(to) || today.isBefore(to));
    }

    public static List<ProductModel> getLatestDiscountOffers() {
        List<ProductModel> latestDiscounts = new ArrayList<>();

        String[] stores = {"kaufland", "lidl", "penny"};
        LocalDate today = LocalDate.now();
        LocalDate yesterday = today.minusDays(1);
        String todayStr = today.toString().replace("-", "");

        for (String store : stores) {
            String offersPath = String.format("/offers/%s_offers/%s_%s.csv", store, store, todayStr);
            String discountsPath = String.format("/offers/%s_offers/%s_discount_%s.csv", store, store, todayStr);

            List<DiscountModel> discounts = readDiscountsFromCsv(discountsPath);

            for (DiscountModel discount : discounts) {
                if (discount.getFromDate().isEqual(today) || discount.getFromDate().isEqual(yesterday)) {
                    ProductModel product = new ProductModel();
                    product.setProductId(discount.getProductId());
                    product.setProductName(discount.getProductName());
                    product.setBrand(discount.getBrand());
                    product.setProductCategory(discount.getProductCategory());
                    product.setPackageQuantity(discount.getPackageQuantity());
                    product.setPackageUnit(discount.getPackageUnit());
                    product.setDiscountPercentage(discount.getPercentageOfDiscount());
                    product.setDiscountFromDate(discount.getFromDate());
                    product.setDiscountToDate(discount.getToDate());
                    product.setStoreName(store);

                    latestDiscounts.add(product);
                }
            }
        }

        return latestDiscounts;
    }


}
