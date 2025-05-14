package com.example.priceComparator.util;


import com.example.priceComparator.model.ProductModel;

import java.io.File;
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

                        }
                    }
                }
            }
        }

        return allOffers;
    }
    private static String extractDateFromFilename(String filename) {
        String[] parts = filename.split("_");
        if (parts.length >= 2) {
            return parts[1].replace(".csv", "");
        }
        return "unknown";
    }


}
