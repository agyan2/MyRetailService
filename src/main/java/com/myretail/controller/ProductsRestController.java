package com.myretail.controller;

import java.util.List;

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

	@Autowired
	ProductService productService; // Service which will do all the data
									// retireval/manipulation work for products

	// -------------------Retrieve All
	// Products--------------------------------------------------------

	@RequestMapping(value = "/product/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Product>> listAllProducts() {
		List<Product> products = productService.findAllProducts();
		if (products.isEmpty()) {
			return new ResponseEntity<List<Product>>(HttpStatus.NO_CONTENT);// You
																			// may
																			// decide
																			// to
																			// return
																			// HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}

	// -------------------Retrieve Single Product By
	// Id--------------------------------------------------------

	@RequestMapping(value = "/product/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Product> getProductById(@PathVariable("id") long id) {
		System.out.println("Fetching Product with id " + id);
		Product product = productService.findById(id);
		if (product == null) {
			System.out.println("Product with id " + id + " not found");
			return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}

	/*
	 * //-------------------Create a
	 * User--------------------------------------------------------
	 * 
	 * @RequestMapping(value = "/user/", method = RequestMethod.POST) public
	 * ResponseEntity<Void> createUser(@RequestBody User user,
	 * UriComponentsBuilder ucBuilder) { System.out.println("Creating User " +
	 * user.getName());
	 * 
	 * if (userService.isUserExist(user)) {
	 * System.out.println("A User with name " + user.getName() +
	 * " already exist"); return new ResponseEntity<Void>(HttpStatus.CONFLICT);
	 * }
	 * 
	 * userService.saveUser(user);
	 * 
	 * HttpHeaders headers = new HttpHeaders();
	 * headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(user.
	 * getId()).toUri()); return new ResponseEntity<Void>(headers,
	 * HttpStatus.CREATED); }
	 */

	// ------------------- Update a Product
	// --------------------------------------------------------

	@RequestMapping(value = "/product/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Product> updatePriceById(@PathVariable("id") long id, @RequestBody Product product) {
		System.out.println("Updating Product " + id);

		Product currentProduct = productService.findById(id);

		if (currentProduct == null) {
			System.out.println("Product with id " + id + " not found");
			return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
		}

		currentProduct.setCurrent_price(product.getCurrent_price());

		Product updatedProduct = productService.updateProduct(currentProduct);
		return new ResponseEntity<Product>(updatedProduct, HttpStatus.OK);
	}

}
