package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;


public class DriverFactory {
	
	public WebDriver driver;
	Properties prop;
	OptionsManager optionsManager;
	
	public static String highlight;
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal();

	/**
	 * this method is used to initialize driver using browser name
	 * @param browserName
	 * @return this returns the WebDriver
	 */
	public WebDriver init_driver(Properties prop) {
		
		String browserName = prop.getProperty("browser").trim();
		highlight = prop.getProperty("highlight").trim();
		System.out.println("browser name is :"+browserName);
		optionsManager = new OptionsManager(prop);
		
		if(browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			//driver = new ChromeDriver(optionsManager.getChromeOptions());
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
		}else if(browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			//driver = new FirefoxDriver(optionsManager.getFireFoxOptions());
			tlDriver.set(new FirefoxDriver(optionsManager.getFireFoxOptions()));
		}
		else {
			System.out.println("please pass the right browser "+browserName);
		}
		
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url").trim());
		
		return getDriver();
	}
	
	/**
	 * this will return the thread local copy of the driver
	 * @return
	 */
	public static WebDriver getDriver() {
		return tlDriver.get();
	}
	
	/**
	 * this method is used to initialize the properties
	 * @return this returns properties class ref
	 */
	public Properties init_prop() {
		
		prop = new Properties();
		try {
			FileInputStream ip = new FileInputStream("./src/test/resources/config/config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return prop;
		
	}
	
	/**
	 * take screenshot
	 */
	public String getScreenshot() {
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/screenshot/" + System.currentTimeMillis() + ".png";
		File destination = new File(path);
		try {
			FileUtils.copyFile(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}

}
