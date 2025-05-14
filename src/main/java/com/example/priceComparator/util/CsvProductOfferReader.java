package com.example.priceComparator.util;


import com.example.priceComparator.model.ProductModel;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CsvProductOfferReader {
    public static List<ProductModel> readAllOffersFromFolder(String offersRootFolder) {
        List<ProductModel> allOffers = new ArrayList<>();
        File rootDir = new File(offersRootFolder);

        return allOffers;
    }
}
