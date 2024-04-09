package com.opencart.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.opencart.DataProvider.ProductDataProvider;
import com.opencart.basetest.BaseTest;

import open.cart.pojo.Product;

public class SearchTest extends BaseTest{
	
	
	 @BeforeClass
	 public void searchSetup() {
		accountPage= loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	 }
	 
	 @Test
	 public void  isTittleCorrect() {
		 searchPage=accountPage.doSearch("mac");
		 System.out.println(searchPage.getSearchPageTittle());
		 Assert.assertEquals(searchPage.getSearchPageTittle(),"Search - "+"mac"); 
	 }
	 
	 @Test
	 public void  isURLCorrect() {
		 searchPage=accountPage.doSearch("mac");
		 System.out.println(searchPage.getSearchPageURL());
		 Assert.assertTrue(searchPage.getSearchPageURL().contains("search&search="+"mac")); 
	 }
     @Test
	 public void searchProductCountTest() {
		 searchPage=accountPage.doSearch("mac");
		 Assert.assertTrue(searchPage.getProductCount() >0);
	 }
     

 		
 	@DataProvider(name="productData")
	public Object[][] getProductData() {
		return new Object[][] {
			
			{"Macbook","MacBook Pro",4},
			{"imac", "iMac",4},
			{"samsung","Samsung Galaxy Tab 10.1",7},
			{"samsung","Samsung SyncMaster 941BW",1},
			
		};
	}
	
     
	 public void countProductImageTest() {
			searchPage= accountPage.doSearch("mac");
			prodInfoPage=searchPage.selectProduct("MacBook Pro");
			int acualImageCount=prodInfoPage.getProductImageCount();
			Assert.assertEquals(acualImageCount,4);
		}
 	
     @Test(dataProvider="productData")
	 public void countProductImageTestWithDataProvider(String searchKey,String productName,int imagesCount) {
			searchPage= accountPage.doSearch(searchKey);
			prodInfoPage=searchPage.selectProduct( productName);
			int acualImageCount=prodInfoPage.getProductImageCount();
			Assert.assertEquals(acualImageCount,imagesCount);
		}

     @Test(dataProvider="dataProviderPojo",dataProviderClass=ProductDataProvider.class)
	 public void countProductImageTestWithPOJO(Product prod) {
			searchPage= accountPage.doSearch(prod.getSearchKey());
			prodInfoPage=searchPage.selectProduct( prod.getProductName());
			int acualImageCount=prodInfoPage.getProductImageCount();
			Assert.assertEquals(acualImageCount,prod.getProductImage());
		}

}
