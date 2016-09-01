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
	private static List<Product> products;

	static {
		products = populateDummyProducts();
	}

	private static List<Product> populateDummyProducts() {
		List<Product> products = new ArrayList<Product>();
		products.add(new Product(13860428, "The Big Lebowski (Blu-ray) (Widescreen)", new Price(13.49, "USD")));
		products.add(new Product(15117729, "The Godfather I", new Price(23.49, "USD")));
		products.add(new Product(16483589, "The Godfather II", new Price(11.51, "USD")));
		products.add(new Product(16696652, "The Godfather III", new Price(73.49, "USD")));
		products.add(new Product(16752456, "Lord of The Rings", new Price(99.89, "USD")));
		products.add(new Product(15643793, "The Dark Knight", new Price(43.33, "USD")));
		return products;
	}

	
	public Product findById(long id) {
		// TODO Auto-generated method stub

		for (Product product : products) {
			if (product.getId() == id)
				return product;
		}
		return null;
	}

	
	public List<Product> findAllProducts() {
		//Initialize Response 
		List<Product> products = new ArrayList<Product>();
		//Get all product names
		List<ProductName> productNames = productNameService.findAll();
		//Get All product Pricing
		populateProductNames(products,productNames);
		
		return products;
	}

	
	private void populateProductNames(List<Product> products, List<ProductName> productNames) {
		Product product = null;
		for(ProductName productName:productNames)
		{
			product = new Product(productName.getId(),productName.getName(),null);
			products.add(product);
		}
		
	}


	public Product updateProduct(Product product) {
		// TODO Auto-generated method stub
		for (Product prod : products) {
			if (prod.getId() == product.getId()) {
				prod.setCurrent_price(product.getCurrent_price());
				return prod;
			}
		}
		return null;
	}

}
