package com.myretail.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.myretail.model.Price;
import com.myretail.model.ProductName;
import com.myretail.repo.PriceRepo;

@RestController
public class DataProviderController {

	@Autowired
	PriceRepo priceRepo;

	@RequestMapping(value = "/api/productname/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProductName> getProductName(@PathVariable("id") long id) {

		for (ProductName productName : productNames) {
			if (id == productName.getId()) {
				return new ResponseEntity<ProductName>(productName, HttpStatus.OK);
			}
		}

		return new ResponseEntity<ProductName>(HttpStatus.NOT_FOUND);
	}

	@RequestMapping(value = "/api/productname", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProductName>> getProductNames() {

		return new ResponseEntity<List<ProductName>>(productNames, HttpStatus.OK);
	}

	private static List<ProductName> productNames;

	static {
		productNames = populateDummyProductNames();

	}

	private static List<ProductName> populateDummyProductNames() {
		List<ProductName> productNames = new ArrayList<ProductName>();
		productNames.add(new ProductName(13860428, "The Big Lebowski (Blu-ray) (Widescreen)"));
		productNames.add(new ProductName(15117729, "The Godfather I"));
		productNames.add(new ProductName(16483589, "The Godfather II"));
		productNames.add(new ProductName(16696652, "The Godfather III"));
		productNames.add(new ProductName(16752456, "Lord of The Rings"));
		productNames.add(new ProductName(15643793, "The Dark Knight"));
		return productNames;
	}

	@PostConstruct
	public void populatePrices() {

		List<Price> prices = new ArrayList<Price>();
		prices.add(new Price(null, 13860428, 13.49, "USD"));
		prices.add(new Price(null, 15117729, 23.49, "USD"));
		prices.add(new Price(null, 16483589, 11.51, "USD"));
		prices.add(new Price(null, 16696652, 73.49, "USD"));
		prices.add(new Price(null, 16752456, 99.89, "USD"));
		prices.add(new Price(null, 15643793, 43.33, "USD"));
		priceRepo.save(prices);

	}

	@PreDestroy
	public void deletePrices(){
		priceRepo.deleteAll();
	}
}
