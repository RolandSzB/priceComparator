package com.example.priceComparator.util;


import com.example.priceComparator.model.ProductModel;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvProductOfferReader {
    public static List<ProductModel> readAllOffersFromFolder() {
        String offersRootFolder = "src/main/resources/offers";
        return readAllOffersFromFolder(offersRootFolder);
    }

    public static List<ProductModel> readAllOffersFromFolder(String offersRootFolder) {
        List<ProductModel> allOffers = new ArrayList<>();
        File rootDir = new File(offersRootFolder);

        if (rootDir.exists() && rootDir.isDirectory()) {
            File[] storeDirs = rootDir.listFiles(File::isDirectory);
            if (storeDirs != null) {
                for (File storeDir : storeDirs) {
                    String storeName = storeDir.getName().replace("_offers", "");
                    File[] csvFiles = storeDir.listFiles((dir, name) -> name.endsWith(".csv"));

                    if (csvFiles != null) {
                        for (File csvFile : csvFiles) {
                            String date = extractDateFromFilename(csvFile.getName());
                            allOffers.addAll(readOffersFromCsv(csvFile, storeName, date));
                        }
                    }
                }
            }
        }

        return allOffers;
    }

    private static List<ProductModel> readOffersFromCsv(File csvFile, String storeName, String date) {
        List<ProductModel> offers = new ArrayList<>();
        try (CSVReader csvReader = new CSVReader(new FileReader(csvFile))) {
            String[] line;
            csvReader.readNext(); // skip header
            while ((line = csvReader.readNext()) != null) {
                ProductModel offer = new ProductModel();
                offer.setProductId(line[0]);
                offer.setProductName(line[1]);
                offer.setProductCategory(line[2]);
                offer.setBrand(line[3]);
                offer.setPackageQuantity(Double.parseDouble(line[4]));
                offer.setPackageUnit(line[5]);
                offer.setPrice(Double.parseDouble(line[6]));
                offer.setCurrency(line[7]);
                offers.add(offer);
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
        return offers;
    }

    private static String extractDateFromFilename(String filename) {
        String[] parts = filename.split("_");
        if (parts.length >= 2) {
            return parts[1].replace(".csv", "");
        }
        return "unknown";
    }


}
