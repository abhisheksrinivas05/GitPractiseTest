package com.qa.opencart.tests;

import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.utils.Constants;

public class LoginPageTest extends BaseTest{

	@Test
	public void loginPageTitleTest() {
		String title = loginPage.getLoginPageTitle();
		System.out.println("Login Page Title is :"+title);
		Assert.assertEquals(title, Constants.LOGIN_PAGE_TITLE);
	}
	
	@Test
	public void loginPageURLTest() {
		String URL = loginPage.loginPageUrl();
		System.out.println("Login Page URL is :"+URL);
		Assert.assertTrue(URL.contains(Constants.LOGIN_PAGE_URL_FRACTION));
	}
	
	@Test
	public void forgotPwdLinkTest() {
		Assert.assertTrue(loginPage.isForgotPasswordLinkExist());
	}
	
	@Test 
	public void loginTest()  {
		try{
			accPage = loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
		}catch(NoSuchElementException e) {
			System.out.println("Element not found");
		}
		catch(NullPointerException e) {
			System.out.println("Null");
		}
		
		
		//Assert.assertEquals(accPage.getAccountsPageTitle(), Constants.ACCOUNT_PAGE_TITLE);
		//Assert.assertTrue(accPage.isLogoutExist());
		
	}
	
}
