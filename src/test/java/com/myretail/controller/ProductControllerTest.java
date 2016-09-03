package com.myretail.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.anyLong;
import static org.powermock.api.mockito.PowerMockito.when;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.Spy;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.myretail.exception.SystemException;
import com.myretail.exception.SystemExceptionEnum;
import com.myretail.model.ErrorResponse;
import com.myretail.model.Price;
import com.myretail.model.Product;
import com.myretail.model.ProductName;
import com.myretail.service.ProductService;
import com.myretail.util.ProductTestUtil;

@RunWith(PowerMockRunner.class)
public class ProductControllerTest {

	@Mock
	ProductService productService;
	@Spy
	@InjectMocks
	ProductController controller = new ProductController();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.setProperty("log4j.configurationFile", "log4j-E1.xml");
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testListAllProductsForSuccess() {
		List<ProductName> productNames = ProductTestUtil.getDummyProductNamesAsList();
		List<Price> prices = ProductTestUtil.getDummyPrices();
		Map<Long, Price> priceMap = ProductTestUtil.getDummyPricesAsMap();
		List<Product> expectedProducts = ProductTestUtil.getDummyProducts(productNames, prices);
		List<Product> actualProducts = null;
		Product actualProduct = null;
		ResponseEntity<List<Product>> res = null;
		try {
			when(productService.findAllProducts()).thenReturn(expectedProducts);
			res = controller.listAllProducts();
			assertEquals(HttpStatus.OK, res.getStatusCode());
			actualProducts = res.getBody();

			assertNotNull(actualProducts);
			assertEquals(expectedProducts.size(), actualProducts.size());
			for (Product product : expectedProducts) {
				actualProduct = ProductTestUtil.getMatchingProduct(product.getId(), actualProducts);
				assertNotNull(actualProduct);
				ProductTestUtil.compareProduct(product, actualProduct);
			}
			verify(productService,times(1)).findAllProducts();
			verify(productService,times(0)).findById(anyLong());
			verify(productService,times(0)).updateProduct(any(Product.class));
		} catch (SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testListAllProductsForFailureNoResponseFromService() {
		List<Product> expectedProducts = null;
		List<Product> actualProducts = null;
		Product actualProduct = null;
		ResponseEntity<List<Product>> res = null;
		try {
			when(productService.findAllProducts()).thenReturn(expectedProducts);
			res = controller.listAllProducts();
			assertEquals(HttpStatus.NO_CONTENT, res.getStatusCode());
			actualProducts = res.getBody();

			assertNull(actualProducts);
		} catch (SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void testListAllProductsForFailureExceptionFromService() {
		List<Product> expectedProducts = null;
		List<Product> actualProducts = null;
		Product actualProduct = null;
		ResponseEntity<List<Product>> res = null;
		try {
			when(productService.findAllProducts())
					.thenThrow(new SystemException(SystemExceptionEnum.PRODUCTPRICE_RETRIEVE_ERROR));
			res = controller.listAllProducts();

			fail("Should not reach here");

		} catch (SystemException e) {
			// TODO Auto-generated catch block
			assertEquals(SystemException.class, e.getClass());
		}

		assertNull(actualProducts);
	}



	@Test
	public void testGetProductByIdForSuccess() {
		ProductName productName = ProductTestUtil.getDummyProductName();
		Price price = ProductTestUtil.getDummyPrice(productName.getId());
		
		Product expectedProduct = ProductTestUtil.getDummyProduct(productName, price);
		
		Product actualProduct = null;
		ResponseEntity<Product> res = null;
		try {
			when(productService.findById(expectedProduct.getId())).thenReturn(expectedProduct);
			res = controller.getProductById(expectedProduct.getId());
			assertEquals(HttpStatus.OK, res.getStatusCode());
			actualProduct = res.getBody();

			assertNotNull(actualProduct);
			ProductTestUtil.compareProduct(expectedProduct, actualProduct);
			verify(productService,times(0)).findAllProducts();
			verify(productService,times(1)).findById(anyLong());
			verify(productService,times(0)).updateProduct(any(Product.class));

		} catch (SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("exception occured");
		}

	}

	
	@Test
	public void testGetProductByIdForFailureProductNotFound() {
		//ProductName productName = ProductTestUtil.getDummyProductName();
		//Price price = ProductTestUtil.getDummyPrice(productName.getId());
		
		Product expectedProduct = null;//ProductTestUtil.getDummyProduct(productName, price);
		
		Product actualProduct = null;
		ResponseEntity<Product> res = null;
		try {
			when(productService.findById(anyLong())).thenReturn(expectedProduct);
			res = controller.getProductById(1);
			assertEquals(HttpStatus.NOT_FOUND, res.getStatusCode());
			actualProduct = res.getBody();

			assertNull(actualProduct);
			


		} catch (SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("exception occured");
		}

	}

	
	@Test
	public void testGetProductByIdForFailureProductError() {
		ProductName productName = ProductTestUtil.getDummyProductName();
		Price price = ProductTestUtil.getDummyPrice(productName.getId());
		
		Product expectedProduct = ProductTestUtil.getDummyProduct(productName, price);
		
		Product actualProduct = null;
		ResponseEntity<Product> res = null;
		try {
			when(productService.findById(expectedProduct.getId())).thenThrow(new SystemException(SystemExceptionEnum.PRODUCTPRICE_RETRIEVE_ERROR));
			res = controller.getProductById(expectedProduct.getId());

			
			fail("should not reach here");


		} catch (SystemException e) {
			// TODO Auto-generated catch block
			assertEquals(SystemException.class, e.getClass());
		}
		assertNull(actualProduct);

	}

	@Test
	public void testUpdatePriceByIdForSuccess() {
		ProductName productName = ProductTestUtil.getDummyProductName();
		Price price = ProductTestUtil.getDummyPrice(productName.getId());
		
		Product inputProduct = ProductTestUtil.getDummyProduct(productName, price);
		Product currentDBProduct = ProductTestUtil.getDummyProduct(productName, price);
		Product expectedProduct = ProductTestUtil.getDummyProduct(productName, price);
		
		Product actualProduct = null;
		ResponseEntity<Product> res = null;
		try {
			when(productService.findById(inputProduct.getId())).thenReturn(currentDBProduct);
			when(productService.updateProduct(currentDBProduct)).thenReturn(expectedProduct);
			res = controller.updatePriceById(inputProduct.getId(),inputProduct);
			assertEquals(HttpStatus.OK, res.getStatusCode());
			actualProduct = res.getBody();

			assertNotNull(actualProduct);
			ProductTestUtil.compareProduct(expectedProduct, actualProduct);
			
			verify(productService,times(0)).findAllProducts();
			verify(productService,times(1)).findById(anyLong());
			verify(productService,times(1)).updateProduct(any(Product.class));
			InOrder io = inOrder(productService);
			io.verify(productService).findById(anyLong());
			io.verify(productService).updateProduct(any(Product.class));

		} catch (SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("exception occured");
		}

		
	}

	
	@Test
	public void testUpdatePriceByIdForFailureIdMismatch() {
		ProductName productName = ProductTestUtil.getDummyProductName();
		Price price = ProductTestUtil.getDummyPrice(productName.getId());
		
		Product inputProduct = ProductTestUtil.getDummyProduct(productName, price);
		Product currentDBProduct = ProductTestUtil.getDummyProduct(productName, price);
		Product expectedProduct = ProductTestUtil.getDummyProduct(productName, price);
		
		Product actualProduct = null;
		ResponseEntity<Product> res = null;
		try {
			when(productService.findById(inputProduct.getId())).thenReturn(currentDBProduct);
			when(productService.updateProduct(currentDBProduct)).thenReturn(expectedProduct);
			res = controller.updatePriceById(1,inputProduct);
			assertEquals(HttpStatus.BAD_REQUEST, res.getStatusCode());
			actualProduct = res.getBody();

			assertNull(actualProduct);

			verify(productService,times(0)).findAllProducts();
			verify(productService,times(0)).findById(anyLong());
			verify(productService,times(0)).updateProduct(any(Product.class));


		} catch (SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("exception occured");
		}

		
	}

	
	@Test
	public void testUpdatePriceByIdForFailureProductNotFound() {
		ProductName productName = ProductTestUtil.getDummyProductName();
		Price price = ProductTestUtil.getDummyPrice(productName.getId());
		
		Product inputProduct = ProductTestUtil.getDummyProduct(productName, price);
		Product currentDBProduct = ProductTestUtil.getDummyProduct(productName, price);
		Product expectedProduct = ProductTestUtil.getDummyProduct(productName, price);
		
		Product actualProduct = null;
		ResponseEntity<Product> res = null;
		try {
			when(productService.findById(inputProduct.getId())).thenReturn(null);
			when(productService.updateProduct(currentDBProduct)).thenReturn(expectedProduct);
			inputProduct.setId(1);
			res = controller.updatePriceById(1,inputProduct);
			assertEquals(HttpStatus.NOT_FOUND, res.getStatusCode());
			actualProduct = res.getBody();

			assertNull(actualProduct);

			verify(productService,times(0)).findAllProducts();
			verify(productService,times(1)).findById(anyLong());
			verify(productService,times(0)).updateProduct(any(Product.class));


		} catch (SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("exception occured");
		}

		
	}

	
	@Test
	public void testUpdatePriceByIdForFailureProductServiceError() {
		ProductName productName = ProductTestUtil.getDummyProductName();
		Price price = ProductTestUtil.getDummyPrice(productName.getId());
		
		Product inputProduct = ProductTestUtil.getDummyProduct(productName, price);
		Product currentDBProduct = ProductTestUtil.getDummyProduct(productName, price);
		Product expectedProduct = ProductTestUtil.getDummyProduct(productName, price);
		
		Product actualProduct = null;
		ResponseEntity<Product> res = null;
		try {
			when(productService.findById(inputProduct.getId())).thenReturn(null);
			when(productService.updateProduct(currentDBProduct)).thenThrow(new SystemException(SystemExceptionEnum.PRODUCTNAME_ERROR));
			
			res = controller.updatePriceById(inputProduct.getId(),inputProduct);


		} catch (SystemException e) {
			assertEquals(SystemException.class, e.getClass());
			try {
				verify(productService,times(0)).findAllProducts();
				verify(productService,times(1)).findById(anyLong());
				verify(productService,times(0)).updateProduct(any(Product.class));
			} catch (SystemException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			

		}
		assertNull(actualProduct);

		
	}

	
	@Test
	public void testExceptionHandlerSystemException() {
		SystemException se = new SystemException(SystemExceptionEnum.PRODUCTNAME_ERROR);
		ResponseEntity<ErrorResponse> res = controller.exceptionHandler(se);
		assertNotNull(res);
		assertEquals(SystemExceptionEnum.PRODUCTNAME_ERROR.getHttpStatus(), res.getStatusCode());
		ErrorResponse errorResponse = res.getBody();
		assertNotNull(errorResponse);
		assertEquals(SystemExceptionEnum.PRODUCTNAME_ERROR.getCode(), errorResponse.getCode());
		assertEquals(SystemExceptionEnum.PRODUCTNAME_ERROR.getMessage(), errorResponse.getMessage());
		
	}

	
	@Test
	public void testExceptionHandlerException() {
		Exception e = new Exception("Abhinav");
		ResponseEntity<ErrorResponse> res = controller.exceptionHandler(e);
		assertNotNull(res);
		assertEquals(HttpStatus.BAD_REQUEST, res.getStatusCode());
		ErrorResponse errorResponse = res.getBody();
		assertNotNull(errorResponse);
		assertEquals(HttpStatus.BAD_REQUEST.value(), errorResponse.getCode());
		assertEquals(e.getMessage(), errorResponse.getMessage());
		
	}

}
