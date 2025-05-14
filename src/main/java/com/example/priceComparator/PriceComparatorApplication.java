package com.example.priceComparator;

import com.example.priceComparator.model.ProductModel;
import com.example.priceComparator.util.CsvProductOfferReader;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class PriceComparatorApplication {

	public static void main(String[] args) {
		List<ProductModel> offers = CsvProductOfferReader.readAllOffersFromFolder();

		for (ProductModel product : offers) {
			System.out.println("Magazin: " + product.getStoreName());
			System.out.println("ID: " + product.getProductId());
			System.out.println("Nume: " + product.getProductName());
			System.out.println("Categorie: " + product.getProductCategory());
			System.out.println("Brand: " + product.getBrand());
			System.out.println("Cantitate: " + product.getPackageQuantity() + " " + product.getPackageUnit());
			System.out.println("Preț: " + product.getPrice() + " " + product.getCurrency());
			System.out.println("-----------");
		}
	}
}
