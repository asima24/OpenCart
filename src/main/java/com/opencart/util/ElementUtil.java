package com.opencart.util;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Function;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedCondition;

import com.opencart.frameworkException.CustomException;

public class ElementUtil {
	
	WebDriver driver;
	public ElementUtil(WebDriver driver) {
		this.driver=driver;
	}

	public void dosendKeys(By locator,String message) {
		if(message.equals(null)) {
			System.out.println("Null values are not allowed");
		throw new CustomException("Invalid null values");
		}
		 WebElement el=getElement(locator);
		 el.clear();
		 el.sendKeys(message);
	}
	
	public void doClick(By locator) {
		getElement(locator).click();
	}
	
	public void doClick(By locator,int timeOut) {
		checkElementClickable(locator,timeOut).click();
	}
	public boolean checkElementIsDisplayed(By locator) {
		return getElement(locator).isDisplayed();
	}

  public WebElement getElement(By locator,int timeOut) {
	  return waitForElementVisible(locator,timeOut);
  }
  public String doGetElementText(By locator) {
		return getElement(locator).getText();
	}
	private WebElement getElement(By locator) {
		  WebElement element=null;
		   try {
			   element=driver.findElement(locator);
			   System.out.println("Element is found with locator" + locator);
			   
		   } catch(NoSuchElementException ex) {
			   System.out.println("Element is not found using this locator..." + locator); 
			   element=waitForElementVisible(locator, 10);
		   }
		   
		return element;
	}
	public void doClear(By locator) {
		getElement(locator).clear();
	}
	
	public String doGetText(By locator) {
		return getElement(locator).getText();
	}
	
	public boolean checkElementIsVisible(By locator) {
		return getElement(locator).isDisplayed();
	}
	
	public String doGetAttributeValue(By locator, String attrName) {
	return getElement(locator).getAttribute(attrName);
		
	}
	public int getElementsCount(By locator) {
		return getElements(locator).size();
	}
	private List<WebElement> getElements(By locator) {
	return driver.findElements(locator);
		
	}
	
     public List<String> getElementsText(By locator) {
    	 List<WebElement> elementList= driver.findElements(locator);
		 //footerLink.forEach(name -> System.out.println(name));
		 List<String> textList=new ArrayList<String>();
		 for(WebElement link:elementList) {
			 textList.add(link.getText());	 
		 }
		
		 return textList;
     }
     
	public List<String> getElementsAttributeValue(By locator, String attrName) {
		       List<WebElement> elementList=getElements(locator);
		       List<String> attributeList= new ArrayList<String>();
		       for(WebElement attribute:elementList)
		       {
		    	   attributeList.add(attribute.getAttribute(attrName));
		       }
		       
		return attributeList;
	}
	
	public void clickElementFromPageSection(By locator, String eleText) {
		List<WebElement> eleList = getElements(locator);
		for (WebElement e : eleList) {
			String text = e.getText();
			System.out.println(text);
			if (text.equals(eleText)) {
				e.click();
				break;
			}
		}
	}
	public boolean IsElementDisplayed(By locator) {
		List<WebElement> eleList = getElements(locator);
		if (eleList.size() > 0) {
			System.out.println(locator + " element is present on the page");
			return true;
		} else {
			return false;
		}
	}
	/**
	 * An expectation for checking an element is visible and enabled such that you
	 * can click it
	 * @param locator
	 * @param timeOut
	 * @return
	 */
	
	private WebElement checkElementClickable(By locator, int timeOut) {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofMillis(timeOut));
		return wait.until(ExpectedConditions.elementToBeClickable(locator));
	}
	/**
	 * An expectation for checking that an element is present on the DOM of a page
	 * and visible on the page. Visibility means that the element is not only
	 * displayed but also has a height and width that is greater than 0.
	 * 
	 * @param locator
	 * @param timeOut
	 */

	public WebElement waitForElementVisible(By locator, int timeOut) {
		 WebDriverWait wait=new WebDriverWait(driver,Duration.ofMillis(timeOut));
	     return  wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	// **************************Drop Down Utils************************//
	public void doSelectDropDownByIndex(By locator, int index) {
		Select select = new Select(getElement(locator));
		select.selectByIndex(index);
	}

	public void doSelectDropDownByVisibleText(By locator, String text) {
		Select select = new Select(getElement(locator));
		select.selectByVisibleText(text);
	}

	public void doSelectDropDownByValueAttribute(By locator, String value) {
		Select select = new Select(getElement(locator));
		select.selectByValue(value);
	}

	public int getDropDownValueCount(By locator) {
		return getAllDropDownOptions(locator).size();
	}

	public List<String> getAllDropDownOptions(By locator) {
		Select select = new Select(getElement(locator));
		List<WebElement> optionsList = select.getOptions();
		List<String> optionsValueList = new ArrayList<String>();
		System.out.println("total values : " + optionsList.size());

		for (WebElement e : optionsList) {
			String text = e.getText();
			System.out.println(text);
			optionsValueList.add(text);
		}
		return optionsValueList;
	}

	public boolean doSelectDropDownValue(By locator, String dropDownValue) {
		boolean flag = false;
		Select select = new Select(getElement(locator));
		List<WebElement> optionsList = select.getOptions();
		System.out.println("total values : " + optionsList.size());

		for (WebElement e : optionsList) {
			String text = e.getText();
			System.out.println(text);
			if (text.equals(dropDownValue)) {
				flag = true;
				e.click();
				break;
			}
		}

		if (flag == false) {
			System.out.println(dropDownValue + " is not present in the drop down " + locator);
		}
		return flag;
	}

	public boolean DoSelectValueFromDropDownWithoutSelect(By locator, String value) {
		boolean flag = false;
		List<WebElement> optionsList = getElements(locator);
		for (WebElement e : optionsList) {
			String text = e.getText();
			if (text.equals(value)) {
				flag = true;
				e.click();
				break;
			}
		}

		if (flag == false) {
			System.out.println(value + " is not present in the drop down " + locator);
		}

		return flag;

	}
	
	// ****************Actions class Utils************************
	public void doActionsSendKeys(By locator, String value) {
		Actions act=new Actions(driver);
		act.sendKeys(getElement(locator), value).build().perform();
		
	}
	
	public void doActionsClick(By locator) {
		Actions act=new Actions(driver);
		act.click(getElement(locator)).build().perform();
	}
	
	public void doActionsClick(By locator, int timeOut) {
		Actions act=new Actions(driver);
		act.click(checkElementClickable(locator, timeOut)).build().perform();
	}
	
	public void doDragAndDrop(By sourceLocator, By targetLocator) {
		Actions act = new Actions(driver);
		act.dragAndDrop(getElement(sourceLocator), getElement(targetLocator)).build().perform();
	}
	
	public void doContextClick(By locator) {
		Actions act = new Actions(driver);
		act.contextClick(getElement(locator)).build().perform();
	}
	public void doMoveToElement(By locator) {
		Actions act = new Actions(driver);
		act.moveToElement(getElement(locator)).build().perform();
	}

	public void handleTwoLevelMenu(By parentMenu, By childMenu) throws InterruptedException {
		doMoveToElement(parentMenu);
		//Thread.sleep(2000);
		waitForElementVisible(childMenu, 2000);
		doClick(childMenu);
	}
	
	public void handleTwoLevelMenu(By parentMenu, String childMenuLinkText) throws InterruptedException {
		doMoveToElement(parentMenu);
		waitForElementVisible(By.linkText(childMenuLinkText), 2000);
		doClick(By.linkText(childMenuLinkText));
	}
	
	public void multiLevelMenuChildMenuHandle(By parentMenuLocator, String level2LinkText, String level3LinkText,
			String level4LinkText) throws InterruptedException {

		WebElement level1 = getElement(parentMenuLocator);
		Actions act = new Actions(driver);

		act.moveToElement(level1).build().perform();
		waitForElementVisible(By.linkText(level2LinkText), 2000);

		WebElement level2 = getElement(By.linkText(level2LinkText));
		act.moveToElement(level2).build().perform();
		waitForElementVisible(By.linkText(level3LinkText), 2000);

		WebElement level3 = getElement(By.linkText(level3LinkText));
		act.moveToElement(level3).build().perform();
		waitForElementVisible(By.linkText(level4LinkText), 2000);

		doClick(By.linkText(level4LinkText));
	}
	
	// ***********************Wait Utils***********************//
	public Alert waitForAlertJsPopUpWithFluentWait(int timeOut, int pollingTime) {
		
		Wait<WebDriver> wait=new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofMillis(2000))
				.ignoring(NoAlertPresentException.class)
				.pollingEvery(Duration.ofMillis(5000))
				.withMessage("---Time Out---Alert Not Found--");
		return wait.until(ExpectedConditions.alertIsPresent());
		
	}
	
	//Custom Wait
	
	public void customWaitForElement(int timeOut,int pollingTime,By locator) {
		Wait<WebDriver> wait=new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofMillis(timeOut))
				.ignoring(NoSuchElementException.class)
				.pollingEvery(Duration.ofMillis(pollingTime));
		
		wait.until(new ExpectedCondition<Boolean>() {
			
			public Boolean apply(WebDriver driver) {
				return (driver.findElement(locator).isEnabled() && driver.findElement(locator).isDisplayed());
			}
		});
		}
	
	public WebElement customWaitForElementX(int timeOut,int pollingTime,By locator) {
		Wait<WebDriver> wait=new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofMillis(timeOut))
				.ignoring(NoSuchElementException.class)
				.pollingEvery(Duration.ofMillis(pollingTime));
		
		WebElement foo =wait.until(new Function<WebDriver,WebElement>() {
			
			public WebElement apply(WebDriver driver) {
				return driver.findElement(locator);
			}
		});
		return foo;
	}
	//*******Alert*********
	public Alert waitForAlertJsPopUp(int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.alertIsPresent());
	}

	public String alertJSGetText(int timeOut) {
		return waitForAlertJsPopUp(timeOut).getText();
	}

	public void alertAccpet(int timeOut) {
		waitForAlertJsPopUp(timeOut).accept();
	}

	public void alertDismiss(int timeOut) {
		waitForAlertJsPopUp(timeOut).dismiss();
	}

	public void EnterAlertValue(int timeOut, String value) {
		waitForAlertJsPopUp(timeOut).sendKeys(value);
	}
	
	public String waitForTitleIsAndCapture(String titleFraction, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		if(wait.until(ExpectedConditions.titleContains(titleFraction))) {
			String title = driver.getTitle();
			return title;
		}
		else {
			System.out.println("title is not present within the given timeout : " + timeOut);
			return null;
		}
	}
	
	public String waitForFullTitleAndCapture(String titleVal, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		if(wait.until(ExpectedConditions.titleIs(titleVal))) {
			String title = driver.getTitle();
			return title;
		}
		else {
			System.out.println("title is not present within the given timeout : " + timeOut);
			return null;
		}
	}
	public String waitForURLContainsAndCapture(String urlFraction, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		if(wait.until(ExpectedConditions.urlContains(urlFraction))) {
			String url = driver.getCurrentUrl();
			return url;
		}
		else {
			System.out.println("url is not present within the given timeout : " + timeOut);
			return null;
		}
	}
	
	public String waitForURLAndCapture(String urlValue, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		if(wait.until(ExpectedConditions.urlToBe(urlValue))) {
			String url = driver.getCurrentUrl();
			return url;
		}
		else {
			System.out.println("url is not present within the given timeout : " + timeOut);
			return null;
		}
	}
	
	public boolean waitForTotalWindows(int totalWindowsToBe, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		return wait.until(ExpectedConditions.numberOfWindowsToBe(totalWindowsToBe));
	}
	
	public List<WebElement> waitForElementsVisible(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}
	
	
	//default timeout = intervalTime
	public List<WebElement> waitForElementsVisible(By locator, int timeOut, int intervalTime) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut), Duration.ofSeconds(intervalTime));
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}
	/**
	 * An expectation for checking an element is visible and enabled such that you
	 * can click it.
	 * 
	 * @param locator
	 * @param timeOut
	 */
	public void clickElementWhenReady(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
	}

	/**
	 * An expectation for checking an element is visible and enabled such that you
	 * can click it.
	 * 
	 * @param locator
	 * @param timeOut
	 */
	
	
	public void waitForFrameAndSwitchToItWithFluentWait(int timeOut, int pollingTime, String idOrName) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(timeOut))
				.ignoring(NoSuchFrameException.class)
				.pollingEvery(Duration.ofSeconds(pollingTime))
				.withMessage("------time out is done...frame is not found.....");		
		 wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(idOrName));
	}
	
	
	public void waitForFrameAndSwitchToItByIDOrName(int timeOut, String idOrName) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(idOrName));
	}

	public void waitForFrameAndSwitchToItByIndex(int timeOut, int frameIndex) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameIndex));
	}

	public void waitForFrameAndSwitchToItByFrameElement(int timeOut, WebElement frameElement) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameElement));
	}

	public void waitForFrameAndSwitchToItByFrameLoctor(int timeOut, By frameLocator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
	}
	
	

	public WebElement waitForElementVisibleWithFluentWait(By locator, int timeOut, int pollingTime) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(timeOut))
				.ignoring(NoSuchElementException.class)
				.ignoring(StaleElementReferenceException.class)
				.ignoring(ElementNotInteractableException.class)
				.pollingEvery(Duration.ofSeconds(pollingTime))
				.withMessage("------time out is done...element is not found.....");
		
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

	}
	
	public WebElement waitForElementPresenceWithFluentWait(By locator, int timeOut, int pollingTime) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(timeOut))
				.ignoring(NoSuchElementException.class)
				.ignoring(StaleElementReferenceException.class)
				.ignoring(ElementNotInteractableException.class)
				.pollingEvery(Duration.ofSeconds(pollingTime))
				.withMessage("------time out is done...element is not found.....");
		
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));

	}
	/**
	 * Tryig for multiple attempts provided by the end user.
	 * @param locator
	 * @param timeOut
	 * @param pollingTime
	 * @return
	 */
	public WebElement retryingElement(By locator, int timeOut, int pollingTime) {// 20

		WebElement element = null;
		int attempts = 0;

		while (attempts < timeOut) {
			try {
				element = getElement(locator);
				System.out.println("element is found...." + locator + " in attempt : " + attempts);
				break;
			} catch (NoSuchElementException e) {
				System.out.println("element is not found..." + locator + " in attempt : " + attempts);
				try {
					Thread.sleep(pollingTime);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
			attempts++;
		}

		if (element == null) {
			System.out.println(
					"element is not found...tried for " + timeOut + " secs " + " with the interval of 500 millisecons");
		}
		return element;

	}
	
	public WebElement retryingElement(By locator, int timeOut) {// 20

		WebElement element = null;
		int attempts = 0;

		while (attempts < timeOut) {
			try {
				element = getElement(locator);
				System.out.println("element is found...." + locator + " in attempt : " + attempts);
				break;
			} catch (NoSuchElementException e) {
				System.out.println("element is not found..." + locator + " in attempt : " + attempts);
				try {
					Thread.sleep(500);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
			attempts++;
		}

		if (element == null) {
			System.out.println(
					"element is not found...tried for " + timeOut + " secs " + " with the interval of 500 millisecons");
		}
		return element;

	}
	
}
	

