package com.qa.opencart.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.opencart.factory.DriverFactory;

public class ElementUtil {

	private WebDriver driver;
	private JavaScriptUtil jsUtil;

	public ElementUtil(WebDriver driver) {
		this.driver = driver;
		jsUtil = new JavaScriptUtil(driver);
	}

	public By getBy(String locatorType, String locatorValue) {
		By locator = null;
		switch (locatorType.toLowerCase()) {
		case "id":
			locator = By.id(locatorValue);
			break;
		case "name":
			locator = By.name(locatorValue);
			break;
		case "classname":
			locator = By.className(locatorValue);
			break;
		case "xpath":
			locator = By.xpath(locatorValue);
			break;
		case "css":
			locator = By.cssSelector(locatorValue);
			break;
		case "linktext":
			locator = By.linkText(locatorValue);
			break;
		case "partiallinktext":
			locator = By.partialLinkText(locatorValue);
			break;
		case "tagname":
			locator = By.tagName(locatorValue);
			break;

		default:
			break;
		}
		return locator;
	}

	public WebElement getElement(By locator) {
		
		WebElement ele = driver.findElement(locator);
		if(Boolean.parseBoolean(DriverFactory.highlight)) {
			jsUtil.flash(ele);
		}
		return ele;
	}

	public void doClick(By locator) {
		getElement(locator).click();
	}

	public void doClick(String locatorType, String locatorValue) {
		getElement(getBy(locatorType, locatorValue)).click();
	}

	public void doSendKeys(By locator, String value) {
		WebElement ele = getElement(locator);
		ele.clear();
		ele.sendKeys(value);
	}

	public void doSendKeys(String locatorType, String locatorValue, String value) {
		getElement(getBy(locatorType, locatorValue)).sendKeys(value);
	}

	public String doGetText(By locator) {
		return getElement(locator).getText();
	}

	public String doGetAttribute(By locator, String attrName) {
		return getElement(locator).getAttribute(attrName);
	}

	public boolean doIsDisplayed(By locator) {
		return getElement(locator).isDisplayed();
	}

	public List<WebElement> getElements(By locator) {
		return driver.findElements(locator);
	}

	public int getElementCount(By locator) {
		return getElements(locator).size();
	}

	public void printElementsText(By locator) {
		List<WebElement> eleList = getElements(locator);
		for (WebElement e : eleList) {
			String text = e.getText();
			System.out.println(text);
		}

	}

	/**
	 * This method will return the list of element's text
	 * 
	 * @param locator
	 * @return
	 */
	public List<String> getElementsTextList(By locator) {
		List<WebElement> eleList = getElements(locator);
		List<String> eleTextList = new ArrayList<String>();

		for (WebElement e : eleList) {
			String text = e.getText();
			if (!text.isEmpty()) {
				eleTextList.add(text);
			}
		}
		return eleTextList;

	}

	/**
	 * This method will return list of Elements attribute value
	 * 
	 * @param locator
	 * @param attrName
	 * @return
	 */
	public List<String> getElementsAttributeList(By locator, String attrName) {

		List<WebElement> eleList = getElements(locator);
		List<String> eleAttrList = new ArrayList<String>();
		for (WebElement e : eleList) {
			String attrValue = e.getAttribute(attrName);
			eleAttrList.add(attrValue);
		}
		return eleAttrList;
	}

	public void clickOnLink(By locator, String linkText) {
		List<WebElement> langList = getElements(locator);

		for (WebElement e : langList) {
			String langtext = e.getText();
			System.out.println(langtext);
			if (langtext.contains(linkText)) {
				e.click();
				break;
			}
		}
	}

	/**************************************************** Drop-Down Utils******************************************************/

	public void doSelectDropDownByIndex(By locator, int index) {
		Select select = new Select(getElement(locator));
		select.selectByIndex(index);
	}

	public void doSelectDropDownByValue(By locator, String value) {
		Select select = new Select(getElement(locator));
		select.selectByValue(value);
	}

	public void doSelectDropDownByVisibleText(By locator, String visibleText) {
		Select select = new Select(getElement(locator));
		select.selectByVisibleText(visibleText);
	}

	public int getDropDownOptionsCount(By locator) {
		Select select = new Select(getElement(locator));
		return select.getOptions().size();
	}

	public List<String> getDropDownOptionsList(By locator) {
		Select select = new Select(getElement(locator));
		List<WebElement> optionsList = select.getOptions();
		List<String> optionsTextList = new ArrayList<String>();
		System.out.println(optionsList.size());

		for (WebElement e : optionsList) {
			String text = e.getText();
			optionsTextList.add(text);
		}
		return optionsTextList;

	}

	public void selectValueFromDropDown(By locator, String value) {
		Select select = new Select(getElement(locator));
		List<WebElement> optionsList = select.getOptions();

		for (WebElement e : optionsList) {
			String text = e.getText();
			if (text.equals(value)) {
				e.click();
				break;
			}
		}

	}

	/************************************************ Actions Utils***************************************************************/

	public void doActionsSendKeys(By locator, String value) {
		Actions act = new Actions(driver);
		act.sendKeys(getElement(locator), value).perform();
	}

	/**
	 * Clicks in the middle of the given element. Equivalent to:
	 * Actions.moveToElement(onElement).click()
	 * 
	 * @param locator
	 */
	public void doActionsClick(By locator) {
		Actions act = new Actions(driver);
		act.click(getElement(locator)).perform();
	}

	/*********************************************** Wait Utils*********************************************************************/

	
	
	
	public void clickWhenReady(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		 wait.until(ExpectedConditions.elementToBeClickable(locator));
	}
	/**
	 * An expectation for checking that an element is present on the DOM of a page.
	 * This does not necessarily mean that the element is visible
	 * 
	 * @param locator
	 * @param timeOut
	 * @return
	 */
	public WebElement waitForElementPresence(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	
	/**
	 * An expectation for checking that there is at least one element present on a web page.
	 * @param timeOut
	 * @param locator
	 * @return
	 */
	public List<WebElement> waitForElementsPresence(int timeOut, By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
	}

	/**
	 * An expectation for checking that an element is present on the DOM of a page
	 * and visible. Visibility means that the element is not only displayed but also
	 * has a height and width that is greater than 0.
	 * 
	 * @param locator
	 * @param timeOut
	 * @return
	 */
	public WebElement waitForElementVisible(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	/**
	 * An expectation for checking that all elements present on the web page that match the locatorare visible. 
	 * Visibility means that the elements are not only displayed but also have a heightand width that is greater than 0.
	 * @param timeOut
	 * @param locator
	 * @return
	 */
	public List<WebElement> waitForElementsVisible(int timeOut, By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}

	// non web elements:title, url, alert
	// title
	public Boolean waitForPageTitle(String titleVal, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.titleContains(titleVal));
	}

	public Boolean waitForPageActTitle(String titleVal, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.titleIs(titleVal));
	}

	public String doGetPageTitleContains(String titleVal, int timeOut) {
		if (waitForPageTitle(titleVal, timeOut)) {
			return driver.getTitle();
		}
		return null;
	}

	public String doGetPageTitleIs(String titleVal, int timeOut) {
		if (waitForPageActTitle(titleVal, timeOut)) {
			return driver.getTitle();
		}
		return null;
	}

	// URL
	public String waitForUrlContains(String urlFraction, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		try {
			if (wait.until(ExpectedConditions.urlContains(urlFraction))) {
				return driver.getCurrentUrl();
			}

		} catch (TimeoutException e) {
			return null;
		}
		return null;
	}

	public String waitForUrlToBe(String url, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		if (wait.until(ExpectedConditions.urlToBe(url))) {
			return driver.getCurrentUrl();
		}
		return null;

	}
	
	
	//Alert
	public Alert waitForAlert(int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        return wait.until(ExpectedConditions.alertIsPresent());	
	}
	
	public String getAlertText(int timeOut) {
		return waitForAlert(timeOut).getText();
	}
	

	public void acceptAlert(int timeOut) {
	  waitForAlert(timeOut).accept();
	}
	
	public void dismissAlert(int timeOut) {
		  waitForAlert(timeOut).dismiss();
    }
	
	//Frames
	public void waitForFrameByIndex(int timeOut, int frameIndex) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		 wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameIndex));
	}
	
	public WebDriver waitForFrameByLocator(int timeOut, By frameLocator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
	}
	
	public WebDriver waitForFrameByWebElement(int timeOut, WebElement frameElement) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameElement));
	}
	
	public WebElement waitForElementPresenceWithFluentWait(By locator, int timeOut, int pollingTime) {

		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				 .withTimeout(Duration.ofSeconds(10))
				 .pollingEvery(Duration.ofSeconds(2))
				 .ignoring(NoSuchElementException.class);
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	
	public WebElement waitForElementPresenceWithWait(By locator, int timeOut, int pollingTime) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
	    wait.pollingEvery(Duration.ofSeconds(2))
		  .ignoring(NoSuchElementException.class)
		  .withMessage(locator + "is not found within the given Time...");
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
