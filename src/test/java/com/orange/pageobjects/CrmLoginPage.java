package com.orange.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.orange.automation.farmework.webdriver.Page;

public class CrmLoginPage extends Page{

	@FindBy(xpath="//input[@name=\"email\"]")
	WebElement uNameTxt;
	
	@FindBy(xpath="//input[@name=\"password\"]")
	WebElement pWordTxt;
	
	@FindBy(xpath="(//div[contains(text(),'Login')])[1]")
	WebElement loginBtn;
	
	public void do_Login() throws InterruptedException {
		uNameTxt.sendKeys("gnrorga@gmail.com");
		Thread.sleep(2000);
		pWordTxt.sendKeys("Anishrithwika@0821");
		Thread.sleep(2000);
		loginBtn.click();
	}
}
