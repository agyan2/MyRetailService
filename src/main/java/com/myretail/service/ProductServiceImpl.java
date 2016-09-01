package com.myretail.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myretail.model.Price;
import com.myretail.model.Product;
import com.myretail.model.ProductName;

@Service("productService")
@Transactional
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductNameService productNameService;
	@Autowired
	ProductPriceService productPriceService;

	public Product findById(long id) {
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

	public List<Product> findAllProducts() {
		// Initialize Response
		List<Product> products = new ArrayList<Product>();
		// Get all product names
		List<ProductName> productNames = productNameService.findAll();
		// Get All product Pricing
		populateProducts(products, productNames);

		return products;
	}

	private void populateProducts(List<Product> products, List<ProductName> productNames) {
		Product product = null;
		for (ProductName productName : productNames) {
			product = new Product();

			populateProduct(product, productName, productPriceService.findById(productName.getId()));

			products.add(product);
		}

	}

	public Product updateProduct(Product product) {
		// TODO Auto-generated method stub
		product.getCurrent_price().setId(product.getId());
		Price updatedPrice = productPriceService.updatePrice(product.getCurrent_price());
		product.setCurrent_price(updatedPrice);
		return product;
	}

}
