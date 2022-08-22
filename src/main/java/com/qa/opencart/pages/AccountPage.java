package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class AccountPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By header = By.cssSelector("div#logo a");
	private By sections = By.cssSelector("div#content h2");
	private By logoutLink = By.linkText("Logout");
	private By search = By.name("search");
	private By searchIcon = By.xpath("//div[@id='search']//button");
	
	public AccountPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	public String getAccountsPageTitle() {
		return eleUtil.doGetPageTitleIs(Constants.ACCOUNT_PAGE_TITLE, Constants.DEFAULT_TIME_OUT);
	}
	
	public String getAccountsPageUrl() {
		return eleUtil.waitForUrlContains(Constants.ACCOUNT_PAGE_URL_FRACTION, Constants.DEFAULT_TIME_OUT);
	}
	
	public String getAccPageHeader() {
		return eleUtil.doGetText(header);
	}
	
	public boolean isLogoutExist() {
		return eleUtil.doIsDisplayed(logoutLink);
	}
	
	public boolean logout() {
		if(isLogoutExist()) {
			eleUtil.doClick(logoutLink);
			return true;
		}
		return false;
	}
	
	public List<String> getAccPageSections() {
		List<WebElement> sectionsList = eleUtil.waitForElementsVisible(10, sections);
		List<String> secValList = new ArrayList<String>();
		for(WebElement e : sectionsList) {
		  String value = e.getText();
		  secValList.add(value);
		}
		return secValList;
	}
	
	public boolean doSeearchExist() {
		return eleUtil.doIsDisplayed(search);
	}
	
	public ResultsPage doSearch(String productName) {
		if(doSeearchExist()) {
			eleUtil.doSendKeys(search, productName);
			eleUtil.doClick(searchIcon);
		}
		return new ResultsPage(driver);
		
	}
}
