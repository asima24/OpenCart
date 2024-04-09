package com.opencart.test;

import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.opencart.basetest.BaseTest;

public class ProductInfoPageTest extends BaseTest {
	@BeforeClass
	public void login() {
		accountPage=loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
	}
	
	
	
	@Test
	public void productInfoTest() {
		searchPage=accountPage.doSearch("Macbook");
		System.out.println("Starting rpoductInfo");
		prodInfoPage=searchPage.selectProduct("MacBook Pro");
		Map<String,String> productInfoMap=prodInfoPage.getProductInfo();
		System.out.println(productInfoMap);
		softAssert.assertEquals(productInfoMap.get("Brand"), "Apple");
		softAssert.assertEquals(productInfoMap.get("Availability"), "In Stock");
		//softAssert.assertEquals(productInfoMap.get("productHeader"), "MacBook Pro");
		softAssert.assertEquals(productInfoMap.get("Ex Tax"), "$2,000.00");
		softAssert.assertEquals(productInfoMap.get("Product Code"), "Product 18");
		softAssert.assertEquals(productInfoMap.get("Reward Points"), "800");
		softAssert.assertEquals(productInfoMap.get("productPrice"), "$2,000.00");
		softAssert.assertAll();
	}
	

	}


