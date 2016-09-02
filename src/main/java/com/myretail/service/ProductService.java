package com.myretail.service;

import java.util.List;

import com.myretail.exception.SystemException;
import com.myretail.model.Product;



public interface ProductService {
	
	Product findById(long id) throws SystemException;
	
	List<Product> findAllProducts() throws SystemException; 
	
	Product updateProduct(Product product) throws SystemException;
	
}
