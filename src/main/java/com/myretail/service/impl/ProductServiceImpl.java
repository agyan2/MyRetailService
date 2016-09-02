package com.myretail.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myretail.exception.SystemException;
import com.myretail.model.Price;
import com.myretail.model.Product;
import com.myretail.model.ProductName;
import com.myretail.service.ProductNameService;
import com.myretail.service.ProductPriceService;
import com.myretail.service.ProductService;

@Service("productService")

public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductNameService productNameService;
	@Autowired
	ProductPriceService productPriceService;

	public Product findById(long id) throws SystemException {
		Product product = new Product();
		ProductName productName = productNameService.findById(id);
		Price price = productPriceService.findById(id);
		populateProduct(product, productName, price);
		return product;
	}

	private void populateProduct(Product product, ProductName productName, Price price) {
		// TODO Auto-generated method stub
		product.setId(productName.getId());
		product.setName(productName.getName());
		product.setCurrent_price(price);
	}

	public List<Product> findAllProducts() throws SystemException {
		// Initialize Response
		List<Product> products = new ArrayList<Product>();
		// Get all product names
		List<ProductName> productNames = productNameService.findAll();
		//Optimize to get products in one shot
		Map<Long,Price> priceMap = productPriceService.findAllAsMap();
		// Get All product Pricing
		populateProducts(products, productNames,priceMap);

		return products;
	}

	private void populateProducts(List<Product> products, List<ProductName> productNames, Map<Long, Price> priceMap) {
		Product product = null;
		for (ProductName productName : productNames) {
			product = new Product();
			populateProduct(product, productName, priceMap.get(productName.getId()));
			products.add(product);
		}

	}

	public Product updateProduct(Product product) throws SystemException {
		// TODO Auto-generated method stub
		product.getCurrent_price().setId(product.getId());
		Price updatedPrice = productPriceService.updatePrice(product.getCurrent_price());		
		product.setCurrent_price(updatedPrice);
		return product;
	}

}
