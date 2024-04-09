package com.opencart.test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.swing.plaf.synth.SynthOptionPaneUI;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.opencart.basetest.BaseTest;
import com.opencart.pages.AccountPage;
import com.opencart.pages.LoginPage;

public class LoginPageTest extends BaseTest{
	
	@Test
	public void loginPageGetTittleTest() {
		String expectedTittle= "Account Login";
		String actualTittle=loginPage.getCurrenPageTittle();
		Assert.assertEquals(actualTittle,expectedTittle);
		System.out.println("Actual Title-- "+actualTittle +" matches expected tittle--"+ expectedTittle);
		}
	
	@Test
	public void loginPageGetCurrentURLTest() {
		String URL=loginPage.getCurrentPageURL();
		Assert.assertTrue(URL.contains("route=account/login"));
		System.out.println(URL+" --contains-- route=account/login");
		}
	
	@Test
	public void forgotPasswordLinkExistTest() {
		loginPage.isForgotPasswordLinkPresent();
		Assert.assertTrue(loginPage.isForgotPasswordLinkPresent(), "forgot Password Link is presnet");
		System.out.println("forgot Password Link is present--"+loginPage.isForgotPasswordLinkPresent());
		}
	
	@Test
	public void  returnCustomerLinkExistTest() {
		
		loginPage.isForgotPasswordLinkPresent();
		Assert.assertTrue(loginPage.isReturningCustomerLinkPresent(), "Returning Customer Link is presnet");
		System.out.println("Returning Customer Link is present--"+loginPage.isReturningCustomerLinkPresent());
		}
	
	@Test
	public void  newCustomerLinkExistTest() {
		loginPage.setURL();
		System.out.println(loginPage.getCurrentPageURL());
		System.out.println(loginPage.isnewCustomerLinkPresent());
		Assert.assertTrue(loginPage.isnewCustomerLinkPresent(), "New Customer Link is presnet");
		System.out.println("New Customer Link is presnet--"+loginPage.isnewCustomerLinkPresent());
		}
	
	@Test
	public void  getFooterLinkTest() {
	     List<String> expectedFooterLinkText=Arrays.asList("About Us","Delivery Information","Privacy Policy",
	    		 "Terms & Conditions","Contact Us","Returns", "Site Map","Brands","Gift Certificates", "Affiliate",
	    		 "Specials","My Account","Order History","Wish List","Newsletter","OpenCart");
		List<String> actualfooterLinkText=loginPage.getFooterLinkTest();
		 actualfooterLinkText.forEach (n ->System.out.println(n));
		 Assert.assertEquals(actualfooterLinkText, expectedFooterLinkText);
		
		}
	

	@Test
	public void  getNavbarTest() {
	     List<String> expectedNavbar=Arrays.asList("Desktops"
	     		,"Laptops & Notebooks"
	     		, "Components"
	     		, "Tablets"
	     		, "Software"
	     		, "Phones & PDAs"
	     		, "Cameras"
	     		, "MP3 Players");
		List<String> actualNavbar =loginPage.getNavigationBar();
		Collections.sort(actualNavbar);
		Collections.sort(expectedNavbar);
		actualNavbar.forEach(n->System.out.print("--"+n));
		System.out.println(" ");
		expectedNavbar.forEach(n->System.out.print("**"+n));
		System.out.println(" ");
		 Assert.assertEquals(actualNavbar, expectedNavbar);
		
		}

	
	@Test
	public void  getRightTableOptionTest() {
	     List<String> expectedRightTableOption=Arrays.asList("Login","Register","Forgotten Password","My Account"
	     		, "Address Book", "Wish List", "Order History", "Downloads", "Recurring payments"
	     		, "Reward Points","Returns","Transactions","Newsletter");
		List<String> actualRightTableOption =loginPage.getRightTableOption();
			 Assert.assertEquals(actualRightTableOption,expectedRightTableOption);
		}
	
	@Test
	public void  zloginTest() {
		String env=df.getProperty("env");
		String userName=df.getUsername(env);
       String password=df.getPassword(env);
       System.out.println("the env name is --"+ env);
       System.out.println("the user name is --"+ userName);
       System.out.println("the password is --"+ password);
		
//	     String userName=prop.getProperty("username");
//	     String password=prop.getProperty("password");
	     accountPage=loginPage.doLogin(userName, password);
	     Assert.assertTrue(accountPage.isLogoutLinkPresent());
		} 
	
	@DataProvider
	public Object[][] wrongCredentials() {
		return new Object[][]{
			{"tricky@gmail.com","xyz"},
			{"aseema.barik@google.com","test@test"},
			{"aseema.barik@google.com","xyz"},
			{"#$%&","4556"}
		};
		
	}
	
	@Test(dataProvider="wrongCredentials")
	public void loginWithWrongCredentials(String username,String pwd) throws InterruptedException {
	   boolean result=loginPage.loginWithWrongCredentials(username, pwd);
	   Assert.assertTrue(result);
		
	}

}
