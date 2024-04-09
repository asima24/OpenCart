package com.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.opencart.util.ElementUtil;





public class AccountPage {
	
	private WebDriver driver;
	ElementUtil elementUtil;
	
	public AccountPage(WebDriver driver) {
		this.driver=driver;
		elementUtil=new ElementUtil(this.driver);
	}
	
	By headers=By.cssSelector("div#content h2");
	By logoutLinkText=By.linkText("Logout");
	By accountText=By.linkText("/Account");
	By searchBox=By.name("search");
	By searchFieldButton= By.cssSelector("div#search button");
	
	public String getAccountPageTittle() {
		
		return elementUtil.waitForTitleIsAndCapture("My Account", 3);
	}
	
	public String getAccountPageURL() {
		return elementUtil.waitForURLContainsAndCapture("route=account/account", 3);
	}
	
	public boolean isLogoutLinkPresent() {
	
		return elementUtil.checkElementIsDisplayed(logoutLinkText);	
	}
	
	public  List<String> headersPresent() {
return elementUtil.getElementsText(headers);
   }
	public AccountLogoutPage doLogout() {
		  //driver.findElement(logoutLink).click();
		   elementUtil.doClick(logoutLinkText);
		  return new AccountLogoutPage(driver);
	  }
	
	 public SearchResultPage doSearch(String searchKey) {
//		  driver.findElement(searchField).sendKeys("mac");
//		  driver.findElement(searchFieldButton).click();
		 elementUtil.waitForElementVisible(searchBox, 2);
		 elementUtil.dosendKeys(searchBox, searchKey);
		   elementUtil.doClick(searchFieldButton);
		return new SearchResultPage(driver);
	  }

}
