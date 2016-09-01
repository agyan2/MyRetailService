package com.myretail.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.myretail.model.ProductName;

@Service("productNameService")
@Transactional
public class ProductNameServiceImpl implements ProductNameService {
	private static final Logger log = LogManager.getLogger();
	@Autowired
	RestTemplate restTemplate;

	public ProductName findById(long id) {
		log.info("Entering ProductName findById(long id) " + id);

		ProductName productName = restTemplate.getForObject("http://localhost:8080/MyRetailService/api/productname/" + id,
				ProductName.class);
		
		log.info("Exiting ProductName findById(long id) " + id);
		return productName;
	}
	
	
	public List<ProductName> findAll() {
		log.info("Entering List<ProductName> findAll()");

		ProductName[] productNames = restTemplate.getForObject("http://localhost:8080/MyRetailService/api/productname",ProductName[].class);
		
		log.info("Exiting ProductName findBAll()");
		return Arrays.asList(productNames);
	}

}
