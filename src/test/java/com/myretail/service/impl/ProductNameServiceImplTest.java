package com.myretail.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;
import static org.powermock.api.mockito.PowerMockito.when;

import java.net.MalformedURLException;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.myretail.controller.DataProviderController;
import com.myretail.exception.SystemException;
import com.myretail.exception.SystemExceptionEnum;
import com.myretail.model.ProductName;
import com.myretail.util.ProductTestUtil;

@RunWith(PowerMockRunner.class)
public class ProductNameServiceImplTest {

	@Mock
	RestTemplate restTemplate;
	@Spy
	@InjectMocks
	ProductNameServiceImpl productNameService = new ProductNameServiceImpl();

	@BeforeClass
	public static void setLogger() throws MalformedURLException {
		System.setProperty("log4j.configurationFile", "log4j-E1.xml");

	}

	@Before
	public void setUp() throws Exception {

	}

	@Test
	public void testFindByIdForSuccess() {

		ProductName expectedProductName = ProductTestUtil.getDummyProductName();

		when(
				restTemplate.getForObject("http://localhost:8080/MyRetailService/api/v1/productname/"
						+ expectedProductName.getId(), ProductName.class)).thenReturn(expectedProductName);
		try {
			ProductName actualPoductName = productNameService.findById(expectedProductName.getId());
			assertNotNull(actualPoductName);
			assertEquals(expectedProductName.getName(), actualPoductName.getName());
			assertEquals(expectedProductName.getId(), actualPoductName.getId());
		} catch (SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("Exception occured");
		}
	}

	@Test
	public void testFindByIdForFailureProductNameNotFound() {

		ProductName expectedProductName = ProductTestUtil.getDummyProductName();

		when(
				restTemplate.getForObject("http://localhost:8080/MyRetailService/api/v1/productname/" + 1,
						ProductName.class)).thenReturn(expectedProductName);
		ProductName actualPoductName = null;
		try {
			actualPoductName = productNameService.findById(expectedProductName.getId());
		} catch (SystemException e) {
			// TODO Auto-generated catch block
			
			assertEquals(e.getClass(), SystemException.class);
			assertEquals(SystemExceptionEnum.PRODUCTNAME_NOT_FOUND, e.getSystemExceptionEnum());
		}
		assertNull(actualPoductName);

	}

	@Test
	public void testFindByIdForFailureProductNameError() {

		ProductName expectedProductName = ProductTestUtil.getDummyProductName();

		when(
				restTemplate.getForObject("http://localhost:8080/MyRetailService/api/v1/productname/"
						+ expectedProductName.getId(), ProductName.class))
				.thenThrow(new RestClientException("Abhinav"));
		ProductName actualPoductName = null;
		try {
			actualPoductName = productNameService.findById(expectedProductName.getId());
		} catch (SystemException e) {
			// TODO Auto-generated catch block
			
			assertEquals(e.getClass(), SystemException.class);
			assertEquals(SystemExceptionEnum.PRODUCTNAME_ERROR, e.getSystemExceptionEnum());
		}
		assertNull(actualPoductName);

	}

	@Test
	public void testFindAllForSuccess() {

		ProductName[] expectedProductNames = ProductTestUtil.getDummyProductNames();

		when(restTemplate.getForObject("http://localhost:8080/MyRetailService/api/v1/productname", ProductName[].class))
				.thenReturn(expectedProductNames);
		try {
			List<ProductName> actualPoductNames = productNameService.findAll();

			assertNotNull(actualPoductNames);
			assertEquals(actualPoductNames.size(), expectedProductNames.length);
			//Check individual objects if get time
		} catch (SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("Exception occured");
		}
	}


	@Test
	public void testFindAllForFailureProductNamesNotFound() {

		ProductName[] expectedProductNames = null;
		List<ProductName> actualPoductNames = null;
		when(restTemplate.getForObject("http://localhost:8080/MyRetailService/api/v1/productname", ProductName[].class))
				.thenReturn(expectedProductNames);
		try {
			actualPoductNames = productNameService.findAll();

			fail("should not execute");
		} catch (SystemException e) {
			// TODO Auto-generated catch block
			
			assertEquals(e.getClass(), SystemException.class);
			assertEquals(SystemExceptionEnum.PRODUCTNAMES_NOT_FOUND, e.getSystemExceptionEnum());

		}
		assertNull(actualPoductNames);
	}

	@Test
	public void testFindAllForFailureProductNamesError() {

		List<ProductName> actualPoductNames = null;
		when(restTemplate.getForObject("http://localhost:8080/MyRetailService/api/v1/productname", ProductName[].class))
				.thenThrow(new RestClientException("Abhinav Exception"));
		try {
			actualPoductNames = productNameService.findAll();

			fail("should not execute");
		} catch (SystemException e) {
			// TODO Auto-generated catch block
			
			assertEquals(e.getClass(), SystemException.class);
			assertEquals(SystemExceptionEnum.PRODUCTNAMES_ERROR, e.getSystemExceptionEnum());

		}
		assertNull(actualPoductNames);
	}
}
