package com.qa.opencart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtil;

public class ResultsPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By searchHeader = By.cssSelector("div#content h1");
	private By productResults = By.xpath("//div[@class='caption']//a");
	
	public ResultsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	public int getProductListCount() {
		int productCount = eleUtil.waitForElementsVisible(10, productResults).size();
		System.out.println(productCount);
		return productCount;
	}
	
	public ProductInfoPage selectProduct(String mainproductName) {
		System.out.println("Main product name : "+mainproductName);
		List<WebElement> searchList = eleUtil.waitForElementsVisible(10, productResults);
		
		for(WebElement e : searchList) {
			String text = e.getText();
			if(text.equals(mainproductName)) {
				e.click();
				break;
			}
		}
		return new ProductInfoPage(driver);
	}

}
