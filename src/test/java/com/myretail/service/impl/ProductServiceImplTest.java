package com.myretail.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.powermock.modules.junit4.PowerMockRunner;

import com.myretail.controller.DataProviderController;
import com.myretail.exception.SystemException;
import com.myretail.model.Price;
import com.myretail.model.Product;
import com.myretail.model.ProductName;
import com.myretail.service.ProductNameService;
import com.myretail.service.ProductPriceService;
import com.myretail.util.ProductTestUtil;
@RunWith(PowerMockRunner.class)
public class ProductServiceImplTest {

	@Mock
	ProductNameService productNameService;
	@Mock
	ProductPriceService productPriceService;
	@Spy
	@InjectMocks
	ProductServiceImpl productService = new ProductServiceImpl();
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.setProperty("log4j.configurationFile", "log4j-E1.xml");
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testFindById() {
		ProductName productName = getDummyProductName();
		Price price = ProductTestUtil.getDummyPrice(productName.getId());
		Product expectedProduct = getDummyProduct(productName,price);
		
		Product actualProduct = null;
		
		try {
			when(productNameService.findById(expectedProduct.getId())).thenReturn(productName);
			when(productPriceService.findById(expectedProduct.getId())).thenReturn(price);
			actualProduct = productService.findById(expectedProduct.getId());
			assertNotNull(actualProduct);
			ProductTestUtil.compareProduct(expectedProduct, actualProduct);
		} catch (SystemException e) {
			e.printStackTrace();
			fail("should not reach here");
		}
	}


	


	private Product getDummyProduct(ProductName productName, Price price) {
		return new Product(productName.getId(), productName.getName(), new Price(null,productName.getId(),price.getValue(),price.getCurrency_code()));
	}
	
	private ProductName getDummyProductName() {

		return DataProviderController.productNames.get(0);
	}


	@Test
	public void testFindAllProducts() {
		List<ProductName> productNames = getDummyProductNames();
		List<Price> prices = getDummyPrices();
		Map<Long, Price> priceMap = getDummyPricesAsMap();
		List<Product> expectedProducts=getDummyProducts(productNames,prices); 
		List<Product> actualProducts = null;
		Product actualProduct=null;
		
		try {
			when(productNameService.findAll()).thenReturn(productNames);
			when(productPriceService.findAllAsMap()).thenReturn(priceMap);
			actualProducts = productService.findAllProducts();
			assertNotNull(actualProducts);
			assertEquals(expectedProducts.size(), actualProducts.size());
			for(Product product:expectedProducts)
			{
				actualProduct=getMatchingProduct(product.getId(),actualProducts);
				assertNotNull(actualProduct);
				ProductTestUtil.compareProduct(product, actualProduct);
			}
		} catch (SystemException e) {
			e.printStackTrace();
			fail("should not reach here");
		}
	}


	private Map<Long, Price> getDummyPricesAsMap() {
		// TODO Auto-generated method stub
		Map<Long, Price> priceMap = new HashMap<Long, Price>();
		for (Price price : getDummyPrices()) {
			priceMap.put(price.getId(), price);
		}
		return priceMap;
	}

	private Product getMatchingProduct(long id, List<Product> actualProducts) {
		// TODO Auto-generated method stub
		for(Product product:actualProducts)
		{
			if(id==product.getId())
				return product;
		}
		return null;
	}

	private List<Product> getDummyProducts(List<ProductName> productNames, List<Price> prices) {
		// TODO Auto-generated method stub
		List<Product> products = new ArrayList<Product>();
		Product product = null;
		for(ProductName productName: productNames)
		{
			product = new Product(productName.getId(), productName.getName(), getPriceForProduct(productName.getId(),prices));
			products.add(product);
		}
		return products;
	}

	private Price getPriceForProduct(long id, List<Price> prices) {
		for(Price price:prices)
		{
			if(id==price.getId())
				return price;
		}
		return null;
	}

	private List<Price> getDummyPrices() {
		// TODO Auto-generated method stub
		return DataProviderController.prices;
	}

	private List<ProductName> getDummyProductNames() {
		// TODO Auto-generated method stub
		return DataProviderController.productNames;
	}
	
	@Test
	public void testUpdateProduct() {
		
		ProductName productName = getDummyProductName();
		Price price = ProductTestUtil.getDummyPrice(productName.getId());
		Price inputPrice = new Price();
		inputPrice.setId(productName.getId());
		Product expectedProduct = getDummyProduct(productName, price);
		Product actualProduct = null;
		try {
			when(productPriceService.updatePrice(any(Price.class))).thenReturn(price);
			actualProduct = productService.updateProduct(expectedProduct);
			assertNotNull(actualProduct);
			ProductTestUtil.compareProduct(expectedProduct, actualProduct);
		} catch (SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("Exception Occured");
		}
	}

}
