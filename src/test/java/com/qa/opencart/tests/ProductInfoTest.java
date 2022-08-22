package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.utils.Constants;

public class ProductInfoTest extends BaseTest {

	
	@BeforeClass
	public void accPageSetUp() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@DataProvider
	public Object[][] productData() {
		return new Object[][] {
			{"Macbook", "MacBook Pro"},
			{"iMac", "iMac"},
			{"Apple", "Apple Cinema 30\""}
		};
	}
	@Test(dataProvider = "productData")
	public void productHeaderTest(String productName, String mainProductName) {
		resPage = accPage.doSearch(productName);
		productInfopage = resPage.selectProduct(mainProductName);
		Assert.assertEquals(productInfopage.getProductHeaderName(), mainProductName);
	}
	
	@DataProvider
	public Object[][] productImageData() {
		return new Object[][] { 
			{"Macbook", "MacBook Pro", Constants.MACBOOKPRO_IMAGES_COUNT},
			{"iMac", "iMac", Constants.IMAC_IMAGES_COUNT}
		};
	}
	@Test(dataProvider = "productImageData")
	public void imagesCountTest(String productName, String mainProductName, int imagesCount) {
		resPage = accPage.doSearch(productName);
		productInfopage = resPage.selectProduct(mainProductName);
		int totalCount = productInfopage.getProductImageCount();
		System.out.println("Total Images for :"+mainProductName+":"+totalCount);
		Assert.assertEquals(totalCount, imagesCount);
	}
//	
//	Main product name : MacBook Pro
//	Brand:Apple
//	Availability:Out Of Stock
//	ExTaxPrice:Ex Tax: $2,000.00
//	totalimages:4
//	price:$2,000.00
//	name:MacBook Pro
//	Product Code:Product 18
//	Reward Points:800
	@Test
	public void productDataTest() {
		resPage = accPage.doSearch("Macbook");
		productInfopage = resPage.selectProduct("MacBook Pro");
		Map<String, String> actProductInfoMap = productInfopage.getProductInfo();
		actProductInfoMap.forEach((k,v) -> System.out.println(k+":"+v));
		softAssert.assertEquals(actProductInfoMap.get("Brand"), "Apple");
		softAssert.assertEquals(actProductInfoMap.get("price"), "$2,000.00");
		softAssert.assertEquals(actProductInfoMap.get("Product Code"), "Product 18");
		softAssert.assertAll();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
