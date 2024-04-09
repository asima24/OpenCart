package com.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.opencart.util.ElementUtil;



public class RegistrationPage {
	private WebDriver driver;
	ElementUtil elementutil;
	
	public RegistrationPage(WebDriver driver) {
		this.driver=driver;
		elementutil=new ElementUtil(driver);
		}
	


	private By firstname=By.name("firstname");
	private By lastname=By.name("lastname");
	private By email=By.name("email");
	private By telephone=By.name("telephone");
	private By password=By.name("password");
	private By confirmPassword=By.name("confirm");
	private By subscribeYes=By.xpath("(//label[@class='radio-inline']/input)[1]");
	private By subscribeNo=By.xpath("(//label[@class='radio-inline']/input)[2]");
	private By privacyPolicycheckbox=By.xpath("//input[@name='agree']");
	private By continueBttn=By.xpath("//input[@type='submit']");
	private By userRegSuccMessg=By.xpath("//h1");
	private By logoutLink = By.linkText("Logout");
	private By registerLink = By.linkText("Register");
	
	public String doRegisterUser(String firstname,String lastname,String email,String telephone,String password,String subscribe) {
		elementutil.waitForElementsVisible(this.firstname,5);
		elementutil.dosendKeys(this.firstname, firstname);
	     elementutil.dosendKeys(this.lastname, lastname);
		elementutil.dosendKeys(this.email, email);
		elementutil.dosendKeys(this.telephone, telephone);
		elementutil.dosendKeys(this.password,password);
		elementutil.dosendKeys(confirmPassword, password);
		doSubscribe(subscribe);
		
		elementutil.doClick(privacyPolicycheckbox);
		elementutil.doClick(continueBttn);
		
		String userRegSuccessMesg = 
				elementutil.waitForElementVisible(userRegSuccMessg, 5).getText();
		System.out.println(userRegSuccessMesg);
		
		elementutil.doClick(logoutLink);
		elementutil.doClick(registerLink);

		
		return userRegSuccessMesg;
	}
	
	
	
	private void doSubscribe(String subscribe) {
		if(subscribe.equalsIgnoreCase("yes")) {
			elementutil.doClick(subscribeYes);
		}
		else {
			elementutil.doClick(subscribeNo);
		}
	
	
	}

}
