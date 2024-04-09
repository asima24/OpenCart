package com.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;

import com.opencart.util.ElementUtil;

public class SearchResultPage {

  private WebDriver driver;
  private ElementUtil elementUtil;
  private ProductInfoPage productInfo;
	public SearchResultPage(WebDriver driver) {
		this.driver=driver;
		elementUtil=new ElementUtil(this.driver);
	}
    
	//div.product-layout.product-grid 
//Search - macbook
	private By searchProducts=By.cssSelector("div.product-layout.product-grid ");
	private By productCount=By.cssSelector("div#content div.col-sm-6.text-right");
	private By productName(String productName)
    {
        return By.linkText(productName);   
    }
	
	public String getSearchPageTittle() {
		return elementUtil.waitForTitleIsAndCapture("Search - ", 3);
	}
	
	public String getSearchPageURL() {
		return elementUtil.waitForURLContainsAndCapture("search&search=",4);
	}
	
	public int getProductCount() {
		return elementUtil.getElementsCount(searchProducts);
	}
	
	public ProductInfoPage selectProduct(String productName) {
		By name=productName(productName);
		System.out.println("name");
		elementUtil.doClick(name);
		return new ProductInfoPage(driver);
		
	}
}
