package com.myretail.service;

import java.util.List;

import com.myretail.model.ProductName;



public interface ProductNameService {
	
	ProductName findById(long id);
	List<ProductName> findAll();
}
