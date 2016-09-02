package com.myretail.service.impl;

import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.myretail.exception.SystemException;
import com.myretail.exception.SystemExceptionEnum;
import com.myretail.model.ProductName;
import com.myretail.service.ProductNameService;

/**
 * ProductName Service retrieved ProductName 
 * @author agyan2
 *
 */
@Service("productNameService")

public class ProductNameServiceImpl implements ProductNameService {
	private static final Logger log = LogManager.getLogger();
	@Autowired
	RestTemplate restTemplate;

	/**
	 * Find productname by id
	 */
	public ProductName findById(long id) throws SystemException {
		log.info("Entering ProductName findById(long id) " + id);
		ProductName productName = null;
		try {
			productName = restTemplate.getForObject("http://localhost:8080/MyRetailService/api/v1/productname/" + id,
					ProductName.class);

		} catch (RestClientException rce) {// Exception handling can be fine
											// grained if needed
			log.error("RestClient Error: " + rce.getStackTrace());
			throw new SystemException(SystemExceptionEnum.PRODUCTNAME_ERROR);
		}
		if (null == productName)
			throw new SystemException(SystemExceptionEnum.PRODUCTNAME_NOT_FOUND);

		log.info("Exiting ProductName findById(long id) " + id);
		return productName;
	}

	/**
	 * find all product names
	 */
	public List<ProductName> findAll() throws SystemException {
		log.info("Entering List<ProductName> findAll()");

		ProductName[] productNames = null;

		try {
			productNames = restTemplate.getForObject("http://localhost:8080/MyRetailService/api/v1/productname",
					ProductName[].class);
		} catch (RestClientException rce) {// Exception handling can be fine
											// grained if needed
			log.error("RestClient Error: " + rce.getStackTrace());
			throw new SystemException(SystemExceptionEnum.PRODUCTNAMES_ERROR);

		}
		if (null == productNames)
			throw new SystemException(SystemExceptionEnum.PRODUCTNAMES_NOT_FOUND);
		log.info("Exiting List<ProductName> findAll()");
		return Arrays.asList(productNames);
	}

}
