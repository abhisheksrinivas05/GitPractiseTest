package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class RegisterPage {
     private WebDriver driver;
     private ElementUtil eleUtil;
     
 	private By firstName = By.id("input-firstname");
 	private By lastName = By.id("input-lastname");
 	private By email = By.id("input-email");
 	private By telephone = By.id("input-telephone");
 	private By password = By.id("input-password");
 	private By confirmPassword = By.id("input-confirm");
 	
 	private By subscribeYes = By.xpath("(//label[@class='radio-inline'])[1]/input[@type='radio']");
 	private By subscribeNo = By.xpath("(//label[@class='radio-inline'])[2]/input[@type='radio']");
 	
 	private By agreeCheckBox = By.name("agree");
 	private By continueBtn = By.xpath("//input[@type='submit' and @value='Continue']");
 	private By successMsg = By.cssSelector("div#content h1");
 	private By errorMsg = By.xpath("//div[@class='alert alert-danger alert-dismissible']");
 	
 	private By logoutLink = By.linkText("Logout");
 	private By registerLink = By.linkText("Register");
	
	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	public boolean accountRegistration(String firstName, String lastName, String email,
			                        String telephone, String password, String subscribe) {
		eleUtil.doSendKeys(this.firstName, firstName);
		eleUtil.doSendKeys(this.lastName, lastName);
		eleUtil.doSendKeys(this.email, email);
		eleUtil.doSendKeys(this.telephone, telephone);
		eleUtil.doSendKeys(this.password, password);
		eleUtil.doSendKeys(this.confirmPassword, password);
		
		if(subscribe.equalsIgnoreCase("yes")) {
			eleUtil.doClick(subscribeYes);
		}else {
			eleUtil.doClick(subscribeNo);
		}
		eleUtil.doClick(agreeCheckBox);
		eleUtil.doClick(continueBtn);
		
		String successMesg = eleUtil.doGetText(successMsg);
		System.out.println(successMesg);
		
		if(successMesg.contains(Constants.REGISTER_SUCCESS_MESSAGE)) {
			eleUtil.doClick(logoutLink);
			eleUtil.doClick(registerLink);
			return true;
		}
//		else {
//		//System.out.println(eleUtil.doGetText(errorMsg));	
//		eleUtil.doClick(registerLink);
		return false;
		
	}
	

	
	
}
