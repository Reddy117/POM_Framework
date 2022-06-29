package com.orange.automation.farmework.webdriver;

import org.openqa.selenium.WebDriver;

public class DriverManager { 
	
	public static ThreadLocal<WebDriver> driverForThread = new ThreadLocal<WebDriver>();
	public synchronized static WebDriver getDriver() {
		return driverForThread.get();
	}
	public synchronized static void setWebDriver(WebDriver driver) {
		driverForThread.set(driver);
	}

}
