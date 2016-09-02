package com.myretail.service;

import java.util.List;
import java.util.Map;

import com.myretail.exception.SystemException;
import com.myretail.model.Price;



public interface ProductPriceService {
	
	Price findById(long id) throws SystemException;
	List<Price> findAll() throws SystemException;
	Price updatePrice(Price current_price) throws SystemException;
	Map<Long,Price> findAllAsMap() throws SystemException;
}
