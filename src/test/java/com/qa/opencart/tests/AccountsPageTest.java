package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.utils.Constants;

public class AccountsPageTest extends BaseTest {
	
	
	@BeforeClass
	public void accPageSetUp() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test
	public void accPageTitleTest() {
		String actTitle = accPage.getAccountsPageTitle();
		System.out.println("Acc Page Title is :"+actTitle);
		Assert.assertEquals(actTitle, Constants.ACCOUNT_PAGE_TITLE);
	}
	
	@Test
	public void accPageUrlTest() {
		String acturl = accPage.getAccountsPageUrl();
		System.out.println("Acc Page URL is: "+acturl);
		Assert.assertTrue(acturl.contains(Constants.ACCOUNT_PAGE_URL_FRACTION));	
	}
	
	@Test
	public void accPageHeaderTest() {
		String header = accPage.getAccPageHeader();
		System.out.println("Acc Page header is: "+header);
		Assert.assertEquals(header, Constants.ACCOUNT_PAGE_HEADER);
		
	}
	
	@Test
	public void logoutLinkTest() {
		Assert.assertTrue(accPage.isLogoutExist());
	}
	
	@Test
	public void accPageSectionsList() {
		List<String> actSectionsList = accPage.getAccPageSections();
		System.out.println("actual Section List is "+actSectionsList);
		Assert.assertEquals(actSectionsList, Constants.ACCOUNT_PAGE_SECTION_LIST);
	}
	
	@DataProvider
	public Object[][] productData() {
		return new Object[][] {
			{"Macbook"},
			{"iMac"},
			{"Apple"}
		};
	}
	
	@Test(dataProvider = "productData")
	public void searchTest(String productName) {
		resPage = accPage.doSearch("Macbook");
		Assert.assertTrue(resPage.getProductListCount()>0);
	}
	
	@DataProvider
	public Object[][] productSelectData() {
		return new Object[][] {
			{"Macbook", "MacBook Pro"},
			{"iMac", "iMac"},
			{"Apple", "Apple Cinema 30\""}
		};
	}
	
	@Test(dataProvider = "productSelectData")
	public void selectProductTest(String productName, String mainProductName) {
		resPage = accPage.doSearch(productName);
		productInfopage = resPage.selectProduct(mainProductName);
		Assert.assertEquals(productInfopage.getProductHeaderName(), mainProductName);
	}

}
