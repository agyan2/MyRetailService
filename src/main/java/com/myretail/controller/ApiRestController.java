package com.myretail.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.myretail.model.ProductName;

@RestController
public class ApiRestController {


	@RequestMapping(value = "/api/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProductName> getProductName(@PathVariable("id") long id) {
		ProductName productName = new ProductName(id,"Movie From API");
		
		return new ResponseEntity<ProductName>(productName
				, HttpStatus.OK);
	}

}
