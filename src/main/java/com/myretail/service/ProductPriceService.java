package com.myretail.service;

import java.util.List;

import com.myretail.model.Price;



public interface ProductPriceService {
	
	Price findById(long id);
	List<Price> findAll();
	Price updatePrice(Price current_price);
}
