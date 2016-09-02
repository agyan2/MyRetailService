package com.myretail.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.myretail.model.Product;
import com.myretail.service.ProductService;

@RestController
public class ProductsRestController {
	private static final Logger log = LogManager.getLogger();
	@Autowired
	ProductService productService;

	@RequestMapping(value = "/product", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Product>> listAllProducts() {
		log.info("Entering ResponseEntity<List<Product>> listAllProducts");
		List<Product> products = productService.findAllProducts();
		if (products.isEmpty()) {
			log.info("Product List not found");
			return new ResponseEntity<List<Product>>(HttpStatus.NO_CONTENT); // HttpStatus.NOT_FOUND
		}
		log.info("Exiting ResponseEntity<List<Product>> listAllProducts");
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}

	@RequestMapping(value = "/product/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Product> getProductById(@PathVariable("id") long id) {
		log.info("Entering method ResponseEntity<Product> getProductById(@PathVariable(\"id\") long id) " + id);
		Product product = productService.findById(id);
		if (product == null) {
			log.info("Product with id " + id + " not found");
			return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
		}
		log.info("Exiting method ResponseEntity<Product> getProductById(@PathVariable(\"id\") long id) " + id);
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}

	@RequestMapping(value = "/product/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Product> updatePriceById(@PathVariable("id") long id, @RequestBody Product product) {
		log.info("Entering method ResponseEntity<Product> updatePriceById(@PathVariable(\"id\") long id, @RequestBody Product product) "
				+ id);

		Product currentProduct = productService.findById(id);

		if (currentProduct == null) {
			log.info("Product with id " + id + " not found");
			return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
		}

		currentProduct.setCurrent_price(product.getCurrent_price());
		log.info("Updating Product with id " + id);
		Product updatedProduct = productService.updateProduct(currentProduct);
		log.info("Exiting method ResponseEntity<Product> updatePriceById(@PathVariable(\"id\") long id, @RequestBody Product product) "
				+ id);
		return new ResponseEntity<Product>(updatedProduct, HttpStatus.OK);
	}

}
