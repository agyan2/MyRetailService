package com.myretail.service;

import java.util.List;

import com.myretail.model.Price;
import com.myretail.model.Product;



public interface ProductService {
	
	Product findById(long id);
	
	List<Product> findAllProducts(); 
	
	Product updateProduct(Product product);
	
}
