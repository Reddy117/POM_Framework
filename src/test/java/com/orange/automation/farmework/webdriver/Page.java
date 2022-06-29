package com.orange.automation.farmework.webdriver;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;

import com.orange.factory.BasePageFactory;
import com.orange.pageobjects.PIMPage;

public class Page {
	public WebDriver driver;
	public WebDriverHelper webDriverHelper;
	BasePageFactory pageFactory;
	
	public Page() {
		if(driver==null) {
			this.driver = DriverManager.getDriver();
			webDriverHelper = new WebDriverHelper(driver);
			pageFactory = new BaseClass().getPageFactory();
		}
	}
	
	
}
