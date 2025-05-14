package com.example.priceComparator;

import com.example.priceComparator.model.ProductModel;
import com.example.priceComparator.util.CsvProductOfferReader;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class PriceComparatorApplication {

	public static void main(String[] args) {
		List<ProductModel> offers = CsvProductOfferReader.readAllOffersFromFolder();

		for (ProductModel offer : offers) {
			System.out.println(offer);
		}
	}
}
