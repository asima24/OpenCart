package com.opencart.test;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.opencart.DataProvider.ProductDataProvider;
import com.opencart.basetest.BaseTest;
import com.opencart.pages.LoginPage;

public class RegistrationPageTest extends BaseTest {
	
	@BeforeClass
	public void setup() {
		 registrationPage=loginPage.doRegistation();
	}
	private String generateEmail() {
		System.out.println("generated email==>"+  "test"+System.currentTimeMillis()+"@abc.com");
	return "test"+System.currentTimeMillis()+"@abc.com";	
	}
	
	@DataProvider
	public Object[][] registrationData(){
		return new Object[][] {
			{"Tay","swift","1234590898","test@123","yes"},
			{"Priya","Chopra","1234590899","test@223","No"},
		};
	}
//	@Test(dataProvider="registrationData")
//	public void doRegistrationTest(String firstname,String lastname,String telephone,String password, String subscribe) {
//		registrationPage.doRegisterUser(firstname, lastname, generateEmail(), telephone, password, subscribe);
//	}
	@Test(dataProvider="registrationDataExcel",dataProviderClass=ProductDataProvider.class)
	public void doRegistrationExcelTest(String firstname,String lastname,String telephone,String password, String subscribe) {
		registrationPage.doRegisterUser(firstname, lastname, generateEmail(), telephone, password, subscribe);
	}

}
