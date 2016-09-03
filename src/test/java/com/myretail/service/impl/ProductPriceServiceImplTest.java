package com.myretail.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyDouble;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
import org.springframework.dao.DataAccessResourceFailureException;

import com.myretail.exception.SystemException;
import com.myretail.exception.SystemExceptionEnum;
import com.myretail.model.Price;
import com.myretail.repo.PriceRepo;
import com.myretail.util.ProductTestUtil;

@RunWith(PowerMockRunner.class)
public class ProductPriceServiceImplTest {

	@Mock
	PriceRepo priceRepo;
	@Spy
	@InjectMocks
	ProductPriceServiceImpl productPriceService = new ProductPriceServiceImpl();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.setProperty("log4j.configurationFile", "log4j-E1.xml");
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testFindByIdForSuccess() {
		Price expectedPrice = ProductTestUtil.getDummyPrice();
		when(priceRepo.searchById(expectedPrice.getId())).thenReturn(expectedPrice);
		Price actualPrice = null;
		try {
			actualPrice = productPriceService.findById(expectedPrice.getId());
			assertNotNull(actualPrice);
			ProductTestUtil.comparePrice(expectedPrice, actualPrice);
		} catch (SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("Exception Occured");
		}

	}



	@Test
	public void testFindByIdForFailurePriceNotFound() {
		long id = 1;
		Price expectedPrice = ProductTestUtil.getDummyPrice();
		when(priceRepo.searchById(expectedPrice.getId())).thenReturn(expectedPrice);
		Price actualPrice = null;
		try {
			actualPrice = productPriceService.findById(id);
			fail("Should not come here");
		} catch (SystemException e) {

			assertEquals(e.getClass(), SystemException.class);
			assertEquals(SystemExceptionEnum.PRODUCTPRICE_NOT_FOUND, e.getSystemExceptionEnum());
		}
		assertNull(actualPrice);
	}

	@Test
	public void testFindByIdForFailurePriceError() {
		Price expectedPrice = ProductTestUtil.getDummyPrice();
		when(priceRepo.searchById(expectedPrice.getId())).thenThrow(
				new DataAccessResourceFailureException("Abhinav Exception"));
		Price actualPrice = null;
		try {
			actualPrice = productPriceService.findById(expectedPrice.getId());
			fail("Should not come here");
		} catch (SystemException e) {

			assertEquals(e.getClass(), SystemException.class);
			assertEquals(SystemExceptionEnum.PRODUCTPRICE_RETRIEVE_ERROR, e.getSystemExceptionEnum());
		}
		assertNull(actualPrice);
	}



	@Test
	public void testFindAllForSuccess() {
		List<Price> expectedPrices = ProductTestUtil.getDummyPrices();
		when(priceRepo.findAll()).thenReturn(expectedPrices);
		List<Price> actualPrices = null;
		try {
			actualPrices = productPriceService.findAll();
			assertNotNull(actualPrices);
			assertEquals(expectedPrices.size(), actualPrices.size());
			// Check individual objects if get time
		} catch (SystemException e) {
			e.printStackTrace();
			fail("Exception Occured");
		}
	}

	@Test
	public void testFindAllForSuccessFailureForPricesNotFound() {
		List<Price> expectedPrices = null;
		when(priceRepo.findAll()).thenReturn(expectedPrices);
		List<Price> actualPrices = null;
		try {
			actualPrices = productPriceService.findAll();
			fail("Should not reach here");
		} catch (SystemException e) {
			
			assertEquals(e.getClass(), SystemException.class);
			assertEquals(SystemExceptionEnum.PRODUCTPRICE_NOT_FOUND, e.getSystemExceptionEnum());
		}
		assertNull(actualPrices);
	}

	@Test
	public void testFindAllForSuccessFailureForPricesRetrieveError() {

		when(priceRepo.findAll()).thenThrow(new DataAccessResourceFailureException("Abhinav"));
		List<Price> actualPrices = null;
		try {
			actualPrices = productPriceService.findAll();
			fail("Should not reach here");
		} catch (SystemException e) {
			
			assertEquals(e.getClass(), SystemException.class);
			assertEquals(SystemExceptionEnum.PRODUCTPRICE_RETRIEVE_ERROR, e.getSystemExceptionEnum());
		}
		assertNull(actualPrices);
	}

	@Test
	public void testFindAllAsMapForSuccess() {
		List<Price> expectedPrices = ProductTestUtil.getDummyPrices();
		Map<Long, Price> expectedPriceMap = ProductTestUtil.getDummyPricesAsMap();
		when(priceRepo.findAll()).thenReturn(expectedPrices);
		Map<Long, Price> actualPriceMap = null;
		try {
			actualPriceMap = productPriceService.findAllAsMap();
			assertNotNull(actualPriceMap);
			assertEquals(expectedPriceMap.size(), actualPriceMap.size());
			for (Long id : actualPriceMap.keySet()) {
				assertEquals(expectedPriceMap.get(id).getId(), actualPriceMap.get(id).getId());
				assertEquals(expectedPriceMap.get(id).getValue(), actualPriceMap.get(id).getValue(), 0.0);
				assertEquals(expectedPriceMap.get(id).getCurrency_code(), actualPriceMap.get(id).getCurrency_code());
			}
		} catch (SystemException e) {
			e.printStackTrace();
			fail("Exception Occuered");
		}
	}

	@Test
	public void testFindAllAsMapForFailure() {
		List<Price> expectedPrices = null;
		when(priceRepo.findAll()).thenReturn(expectedPrices);
		Map<Long, Price> actualPriceMap = null;
		try {
			actualPriceMap = productPriceService.findAllAsMap();
			fail("Should not reach here");
		} catch (SystemException e) {
			
			assertEquals(SystemException.class, e.getClass());
		}
		assertNull(actualPriceMap);
	}


	
	@Mock
	Price priceFromDB;
	@Test
	public void testUpdatePrice() {
		
		Price inputPrice = ProductTestUtil.getDummyPrice();
		Price expectedPrice = ProductTestUtil.getDummyPrice();
		
		Price actualPrice = null;
		try {
			//when(productPriceService.findById(anyLong())).thenReturn(priceFromDB);
			doReturn(priceFromDB).when(productPriceService).findById(anyLong());
			when(priceRepo.save(any(Price.class))).thenReturn(expectedPrice);
			actualPrice = productPriceService.updatePrice(inputPrice);
			assertEquals(expectedPrice.getId(), actualPrice.getId());
			assertEquals(expectedPrice.getCurrency_code(), actualPrice.getCurrency_code());
			assertEquals(expectedPrice.getValue(), actualPrice.getValue(),0.0);
			
			//Behavioural Test Case
			verify(priceFromDB,times(1)).setCurrency_code(anyString());
			verify(priceFromDB,times(1)).setValue(anyDouble());
			
			
		} catch (SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("exception occured");
		}
	}

}
