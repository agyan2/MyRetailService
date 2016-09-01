package com.myretail.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myretail.model.Price;

@Service("productPriceService")
@Transactional
public class ProductPriceServiceImpl implements ProductPriceService {
	private static final Logger log = LogManager.getLogger();

	private static List<Price> prices;

	static {
		prices = populateDummyPrices();
	}

	private static List<Price> populateDummyPrices() {
		List<Price> prices = new ArrayList<Price>();
		prices.add(new Price(13860428, 13.49, "USD"));
		prices.add(new Price(15117729, 23.49, "USD"));
		prices.add(new Price(16483589, 11.51, "USD"));
		prices.add(new Price(16696652, 73.49, "USD"));
		prices.add(new Price(16752456, 99.89, "USD"));
		prices.add(new Price(15643793, 43.33, "USD"));
		return prices;
	}

	public Price findById(long id) {
		log.info("Entering Price findById(long id) " + id);

		for (Price price : prices) {
			if (id == price.getId()) {
				log.info("Exiting Price findById(long id) " + id);
				return price;
			}

		}
		log.info("Price not found for id " + id);
		return null;
	}

	public List<Price> findAll() {
		log.info("Entering List<Price> findAll()");

		log.info("Exiting List<Price> findAll()");
		return prices;
	}
	
	public Price updatePrice(Price current_price) {
		log.info("Entering Price updatePrice(Price price)");
		for(Price price: prices)
		{
			if(current_price.getId()==price.getId())
			{
				price.setValue(current_price.getValue());
				log.info("Exiting Price updatePrice(Price price)");
				return price;
			}
		}
		log.info("Price not found for id )"+current_price.getId());
		return null;
	}

}
