package com.myretail.util;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.myretail.controller.DataProviderController;
import com.myretail.model.Price;
import com.myretail.model.Product;
import com.myretail.model.ProductName;

public class ProductTestUtil {
	
	public static void compareProduct(Product expectedProduct, Product actualProduct) {
		assertEquals(expectedProduct.getId(), actualProduct.getId());
		assertEquals(expectedProduct.getName(), actualProduct.getName());
		assertEquals(expectedProduct.getCurrent_price().getId(), actualProduct.getCurrent_price().getId());
		assertEquals(expectedProduct.getCurrent_price().getValue(), actualProduct.getCurrent_price().getValue(), 0.0);
		assertEquals(expectedProduct.getCurrent_price().getCurrency_code(),
				actualProduct.getCurrent_price().getCurrency_code());
	}

	public static Map<Long, Price> getDummyPricesAsMap() {
		// TODO Auto-generated method stub
		Map<Long, Price> priceMap = new HashMap<Long, Price>();
		for (Price price : getDummyPrices()) {
			priceMap.put(price.getId(), price);
		}
		return priceMap;
	}

	public static Product getMatchingProduct(long id, List<Product> actualProducts) {
		// TODO Auto-generated method stub
		for (Product product : actualProducts) {
			if (id == product.getId())
				return product;
		}
		return null;
	}

	public static List<Product> getDummyProducts(List<ProductName> productNames, List<Price> prices) {
		// TODO Auto-generated method stub
		List<Product> products = new ArrayList<Product>();
		Product product = null;
		for (ProductName productName : productNames) {
			product = new Product(productName.getId(), productName.getName(),
					getPriceForProduct(productName.getId(), prices));
			products.add(product);
		}
		return products;
	}
	
	public static Product getDummyProduct(ProductName productName, Price price) {
		// TODO Auto-generated method stub
		return new Product(productName.getId(),productName.getName(),new Price(null,price.getId(),price.getValue(),price.getCurrency_code()));
	}

	public static Price getPriceForProduct(long id, List<Price> prices) {
		for (Price price : prices) {
			if (id == price.getId())
				return price;
		}
		return null;
	}


	public static List<Price> getDummyPrices() {
		// TODO Auto-generated method stub
		return DataProviderController.prices;
	}

	public static ProductName getDummyProductName() {

		return DataProviderController.productNames.get(0);
	}

	public static ProductName[] getDummyProductNames() {
		// TODO Auto-generated method stub
		return DataProviderController.productNames.toArray(new ProductName[DataProviderController.productNames.size()]);
	}
	
	public static List<ProductName> getDummyProductNamesAsList() {
		// TODO Auto-generated method stub
		return DataProviderController.productNames;
	}

	
	public static void comparePrice(Price expectedPrice, Price actualPrice) {
		assertEquals(expectedPrice.getId(), actualPrice.getId());
		assertEquals(expectedPrice.getValue(), actualPrice.getValue(), 0.0);
		assertEquals(expectedPrice.getCurrency_code(), actualPrice.getCurrency_code());
	}

	
	public static Price getDummyPrice() {
		return DataProviderController.prices.get(0);
	}


	public static Price getDummyPrice(long id) {
		return getPriceForProduct(id, DataProviderController.prices);
	}

}
