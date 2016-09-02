package com.myretail.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myretail.exception.SystemException;
import com.myretail.exception.SystemExceptionEnum;
import com.myretail.model.Price;
import com.myretail.repo.PriceRepo;
import com.myretail.service.ProductPriceService;

@Service("productPriceService")

public class ProductPriceServiceImpl implements ProductPriceService {
	private static final Logger log = LogManager.getLogger();

	@Autowired
	PriceRepo priceRepo;

	public Price findById(long id) throws SystemException {
		log.info("Entering Price findById(long id) " + id);
		Price price = null;

		try {
			price = priceRepo.searchById(id);
		} catch (Throwable t) {
			log.error("Database Access Exception " + t.getStackTrace());
			throw new SystemException(SystemExceptionEnum.PRODUCTPRICE_RETRIEVE_ERROR);
		}
		if (null == price)
			throw new SystemException(SystemExceptionEnum.PRODUCTPRICE_NOT_FOUND);

		log.info("Exiting Price findById(long id) " + price);

		return price;
	}

	public List<Price> findAll() throws SystemException {
		log.info("Entering List<Price> findAll()");
		List<Price> prices = null;

		try {

			prices = priceRepo.findAll();
		} catch (Throwable t) {
			log.error("Database Access Exception " + t.getStackTrace());
			throw new SystemException(SystemExceptionEnum.PRODUCTPRICE_RETRIEVE_ERROR);
		}

		if (null == prices) 
			throw new SystemException(SystemExceptionEnum.PRODUCTPRICE_NOT_FOUND);
		

		log.info("Exiting List<Price> findAll() " + prices);
		return prices;
	}

	public Map<Long, Price> findAllAsMap() throws SystemException {
		log.info("Map<Long,Price> findAllAsMap()");
		Map<Long, Price> priceMap = new HashMap<Long, Price>();
		for (Price price : findAll()) {
			priceMap.put(price.getId(), price);
		}
		log.info("Exiting Map<Long,Price> findAllAsMap() " + priceMap);
		return priceMap;
	}

	public Price updatePrice(Price current_price) throws SystemException {
		log.info("Entering Price updatePrice(Price price)");

		Price price = findById(current_price.getId());

		price.setValue(current_price.getValue());
		price.setCurrency_code(current_price.getCurrency_code());
		Price updatedPrice = null;

		try {
			updatedPrice = priceRepo.save(price);
		} catch (Throwable t) {
			log.error("Database Access Exception " + t.getStackTrace());
			throw new SystemException(SystemExceptionEnum.PRODUCTPRICE_UPDATE_ERROR);
		}
		if (null == updatedPrice)
			throw new SystemException(SystemExceptionEnum.PRODUCTPRICE_NOT_UPDATED);
		

		log.info("Exiting Price updatePrice(Price price)" + updatedPrice);

		return updatedPrice;
	}

}
