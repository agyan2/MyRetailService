package com.myretail.service.impl;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ ProductNameServiceImplTest.class, ProductPriceServiceImplTest.class, ProductServiceImplTest.class })
public class AllTestsSuite {
//Test Suite can be fine grained to factor slow and fast tests
}
