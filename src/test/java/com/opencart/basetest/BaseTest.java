package com.opencart.basetest;

import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;



import com.opencart.driverfactory.DriverFactory;
import com.opencart.pages.AccountPage;
import com.opencart.pages.LoginPage;
import com.opencart.pages.ProductInfoPage;
import com.opencart.pages.RegistrationPage;
import com.opencart.pages.SearchResultPage;


public class BaseTest {
	
	WebDriver driver;
	protected DriverFactory df;
	protected Properties prop;
	protected LoginPage loginPage;
	protected AccountPage accountPage;
	protected SearchResultPage searchPage;
	protected ProductInfoPage prodInfoPage;
	protected SoftAssert softAssert;
	protected RegistrationPage  registrationPage;
	
	@Parameters({"browser"})
	@BeforeTest
	public void setUp(@Optional("Chrome")String browserName) {
		df= new DriverFactory();
		prop=df.initProperty();
		if(browserName!=null) {
			prop.setProperty("browser", browserName);
		}
	
		driver=df.initDriver(prop);
		softAssert=new SoftAssert();
	    loginPage=new LoginPage(driver);
		}

	@AfterTest
	public void tearDown() {
		driver.quit();	
	}

}
