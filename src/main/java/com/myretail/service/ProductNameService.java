package com.myretail.service;

import java.util.List;

import com.myretail.exception.SystemException;
import com.myretail.model.ProductName;



public interface ProductNameService {
	
	ProductName findById(long id) throws SystemException;
	List<ProductName> findAll() throws SystemException;
}
