package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class LoginPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	// 1. Private By locators
	private By emailID = By.id("input-email");
	private By password = By.id("input-password");
	private By forgotPwd = By.linkText("Forgotten Password");
	private By loginBtn = By.xpath("//input[@value='Login']");
	private By registerLink = By.linkText("Register");

	// 2.page Constructor:
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	//3.public page actions/methods:
	public String getLoginPageTitle() {
		return eleUtil.doGetPageTitleIs(Constants.LOGIN_PAGE_TITLE, Constants.DEFAULT_TIME_OUT);
	}
	
	public String loginPageUrl() {
	return 	eleUtil.waitForUrlContains(Constants.LOGIN_PAGE_URL_FRACTION, Constants.DEFAULT_TIME_OUT);
	}
	
	public boolean isForgotPasswordLinkExist() {
		return eleUtil.doIsDisplayed(forgotPwd);
	}
	
	public AccountPage doLogin(String userName, String pwd) throws NoSuchElementException{
	 eleUtil.doSendKeys(emailID, userName);
	 eleUtil.doSendKeys(password, pwd);
	 eleUtil.doClick(loginBtn);
	 return new AccountPage(driver);
	}
	
	public RegisterPage goToRegisterPage() {
		eleUtil.doClick(registerLink);
		return new RegisterPage(driver);
	}



}
