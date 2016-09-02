package com.myretail.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myretail.model.Price;
import com.myretail.repo.PriceRepo;
import com.myretail.service.ProductPriceService;

@Service("productPriceService")
@Transactional
public class ProductPriceServiceImpl implements ProductPriceService {
	private static final Logger log = LogManager.getLogger();

	@Autowired
	PriceRepo priceRepo;

	public Price findById(long id) {
		log.info("Entering Price findById(long id) " + id);
		Price price = priceRepo.searchById(id);
		if (null == price) {
			log.info("Price not found for id " + id);
			return null;
		}

		log.info("Exiting Price findById(long id) " + price);

		return price;
	}

	public List<Price> findAll() {
		log.info("Entering List<Price> findAll()");
		List<Price> prices = priceRepo.findAll();
		log.info("Exiting List<Price> findAll() " + prices);
		return prices;
	}
	
	public Map<Long,Price> findAllAsMap() {
		log.info("Map<Long,Price> findAllAsMap()");
		Map<Long,Price> priceMap = new HashMap<Long,Price>();		
		for(Price price:findAll())
		{
			priceMap.put(price.getId(), price);
		}
		log.info("Exiting Map<Long,Price> findAllAsMap() " + priceMap);
		return priceMap;
	}

	public Price updatePrice(Price current_price) {
		log.info("Entering Price updatePrice(Price price)");
		
		Price price = findById(current_price.getId());
		if(null==price)
		{
			log.info("Price not found for id )" + current_price.getId());
			return null;
		}
		price.setValue(current_price.getValue());
		price.setCurrency_code(current_price.getCurrency_code());
		Price updatedPrice = priceRepo.save(price);

		log.info("Exiting Price updatePrice(Price price)" +updatedPrice);

		return updatedPrice;
	}

}
