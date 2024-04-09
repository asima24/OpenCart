package com.opencart.test;

import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.opencart.basetest.BaseTest;

public class AccountPageTest extends BaseTest {
	
	@BeforeClass
	public void beforeClass() {
		accountPage=loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		System.out.println(prop.getProperty("username")+"---" +prop.getProperty("password"));
	}
	
	@Test
	public void isAccountPageTittlePresent() {
		String expectedTittle="My Account";
		String actualTittle=accountPage.getAccountPageTittle();
		Assert.assertEquals(actualTittle, expectedTittle);
	}

			@Test
			public void doesAccountPageURLContains() {
				String expectedinfo="route=account/account";
				String actualURL=accountPage.getAccountPageURL();
				Assert.assertTrue(actualURL.contains(expectedinfo));
			}
			
			public void isLogoutLink() {
				Assert.assertTrue(accountPage.isLogoutLinkPresent());
			}
			@Test
			public void verifyHeaders() {
				List<String> expectedHeaders = 
						Arrays.asList("My Account", "My Orders", "My Affiliate Account", "Newsletter"); 
				List<String> actualHeaders=accountPage.headersPresent();
				Assert.assertEquals(actualHeaders,expectedHeaders);
			}
			@Test
			public void verifyHeadersCount() {
				
			   int actualSize=accountPage.headersPresent().size();
				Assert.assertTrue(actualSize==4);
			}
			
			
}
