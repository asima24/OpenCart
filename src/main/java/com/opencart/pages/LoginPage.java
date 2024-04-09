package com.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.opencart.util.ElementUtil;


public class LoginPage {

		//
//				browser=chrome
//				username=aseema.barik@gmail.com
//				password=test@test
			//1. Make the webdriver private
			private WebDriver driver;
			private ElementUtil elementUtil;
			//2.Create a public constructor
			public LoginPage(WebDriver driver) {
				this.driver=driver;
				elementUtil=new ElementUtil(this.driver);
			}
			
			//3.private Bylocators of the page
			 private By emailid=By.id("input-email");
			 private By passwordid=By.id("input-password");
			 private By loginButton=By.xpath("//input[@type='submit']");
			 private By footerLinks=By.xpath("//footer//a");
			 private By tableOptions=By.xpath("//div[@class='list-group']/a");
			 private By optionNavigationBars=By.xpath("//ul[@class='nav navbar-nav']/li/a");
			 private By forgotPwdlink = By.linkText("Forgotten Password");
			 private By returningCustomer= By.xpath("//h2[text()='Returning Customer']");
			 private By newCustomer =By.xpath("//h2[text()='New Customer']");
			 private By accounLink=By.linkText("My Account");
			 private By loginLink= By.linkText("Login");
			 //private By unsucessfulCredentialsWarningMsg=By.cssSelector("i.fa.fa-exclamation-circle");
			  private  By  unsucessfulCredentialsWarningMsg=By.cssSelector("div.alert.alert-danger.alert-dismissible");
			  private  By  registrationLink=By.linkText("Register");
			 
			 //4.Public methods
			 public String getCurrenPageTittle() {
				  return elementUtil. waitForTitleIsAndCapture("Account Login",3);
			 }
			 
			 public String getCurrentPageURL() {
				 String pageCurrentURL=driver.getCurrentUrl();
				 return elementUtil.waitForURLContainsAndCapture("route=account/login",3);
			 }
			 
			 public boolean isForgotPasswordLinkPresent() {
				 
				return elementUtil.IsElementDisplayed(forgotPwdlink);
			 }
			 public boolean isReturningCustomerLinkPresent() {
					return elementUtil.IsElementDisplayed(returningCustomer);
				 }
			 
			 public boolean isnewCustomerLinkPresent() {
					return elementUtil.IsElementDisplayed(newCustomer);
				 }
			 
			 public List<String> getFooterLinkTest() {
			 return elementUtil.getElementsText(footerLinks);
			 }
			 
			 public List<String> getNavigationBar() {
				
				 return  elementUtil.getElementsText(optionNavigationBars);
			 }
			 
			 public List<String>  getRightTableOption() {
				 
				 return elementUtil.getElementsText(tableOptions);
			 }
			 
			 public AccountPage doLogin(String username,String pwd) {			
				 elementUtil.waitForElementVisible(emailid, 2);
				 elementUtil.doClear(emailid);
				 elementUtil.dosendKeys(emailid, username);
				 elementUtil.dosendKeys(passwordid, pwd);
				 elementUtil.doClick(loginButton);				 
				 return new AccountPage(driver);		
			 }
			 
			 public boolean loginWithWrongCredentials(String username,String password) throws InterruptedException {
				 elementUtil.waitForElementVisible(emailid, 2);
				 elementUtil.doClear(emailid);
				 elementUtil.dosendKeys(emailid, username);
				 Thread.sleep(10);
				 elementUtil.dosendKeys(passwordid, password);
				 Thread.sleep(10);
				 elementUtil.doClick(loginButton);		
				 String warningMsg=elementUtil.doGetElementText(unsucessfulCredentialsWarningMsg);
				 System.out.println(" The warning message is==>"+warningMsg);
				 if(warningMsg.contains("Warning: No match for E-Mail Address and/or Password.")) {
				return true;
				}else 
					return false;
				 
			 }
			 
			 public void  setURL() {
				 driver.get("https://naveenautomationlabs.com/opencart/index.php?route=account/login");
			 }
			 
			 public RegistrationPage doRegistation() {
					
				 elementUtil.doClick(registrationLink, 5);
				 return new RegistrationPage(driver);
			}
				

}
