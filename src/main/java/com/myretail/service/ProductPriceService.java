package com.myretail.service;

import java.util.List;
import java.util.Map;

import com.myretail.model.Price;



public interface ProductPriceService {
	
	Price findById(long id);
	List<Price> findAll();
	Price updatePrice(Price current_price);
	Map<Long,Price> findAllAsMap();
}
