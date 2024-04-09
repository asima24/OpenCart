package com.opencart.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.opencart.util.ElementUtil;

public class ProductInfoPage {

	 private WebDriver driver;
	 private ElementUtil elementUtil ;
	public ProductInfoPage(WebDriver driver) {
		this.driver=driver;
		 elementUtil = new ElementUtil(this.driver);
	}
	
	private By productImages=By.cssSelector("div#content img");
	private By productNameHeader=By.linkText("MacBook");
	private By productDescRevSpec=By.cssSelector("ul.nav.nav-tabs li");
    private By productInfo=By.cssSelector("div#content ul.list-unstyled li");
   
	public String getProductHeader() {
	return elementUtil.doGetText(productNameHeader); 
	}
	
	public int getNavBarHeaderCount() {
		List<String> elementText=elementUtil.getElementsText(productDescRevSpec);
		return elementText.size();
	}

	public List<String> getNavBarHeaderText() {
	
		return elementUtil.getElementsText(productDescRevSpec);
	}

	public int getProductImageCount() {
		// TODO Auto-generated method stub
		return elementUtil.getElementsCount(productImages);
	}
	
	public Map<String, String> getProductInfo() {
		List<String> infoList=elementUtil.getElementsText(productInfo);
		Map<String,String> infoMap=new HashMap<String,String>();
		System.out.println(infoList);
		int itemsSize=infoList.size()-2;
		for(int i=0;i<itemsSize;i++) {
			String key=(infoList.get(i).split(":"))[0].trim();
			String value=(infoList.get(i).split(":"))[1].trim();
			infoMap.put(key, value);
		
	}
		infoMap.put("productPrice", infoList.get(itemsSize).trim());
		infoMap.put((infoList.get(itemsSize+1).split(":"))[0].trim(),(infoList.get(itemsSize+1).split(":"))[1].trim());
	
		return infoMap;
	}

	
}
