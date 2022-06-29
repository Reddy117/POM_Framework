package com.orange.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.orange.automation.farmework.webdriver.Page;

public class CompanyPage extends Page{

	@FindBy(xpath="//*[@id=\"main-nav\"]/div[4]/a/span")
	WebElement CompaniesLink;
	
	@FindBy(xpath="//*[@id=\"dashboard-toolbar\"]/div[2]/div/a/button")
	WebElement createLink;
	
	@FindBy(xpath="//*[@id=\"main-content\"]/div/div[2]/form/div[1]/div[1]/div/div/div/input")
	WebElement companyNametxt;
	
	@FindBy(xpath="//*[@id=\"main-content\"]/div/div[2]/form/div[2]/div[1]/div/div/input")
	WebElement websiteTxt;
	
	@FindBy(xpath="//*[@id=\"main-content\"]/div/div[2]/form/div[2]/div[2]/div/div/div/div[1]/div/input")
	WebElement addressTxt;
	
	@FindBy(xpath="//*[@id=\"main-content\"]/div/div[2]/form/div[2]/div[2]/div/div/div/div[2]/div/input")
	WebElement cityTxt;
	
	@FindBy(xpath="//*[@id=\"main-content\"]/div/div[2]/form/div[2]/div[2]/div/div/div/div[3]/div/input")
	WebElement stateTxt;
	
	@FindBy(xpath="//*[@id=\"main-content\"]/div/div[2]/form/div[2]/div[2]/div/div/div/div[4]/div/input")
	WebElement zipcodeTxt;
	
	@FindBy(xpath="//*[@id=\"dashboard-toolbar\"]/div[2]/div/button[2]")
	WebElement saveBtn;
	
	public void createCompany(String comName,String comWeb,String add,String comcity,String comState,String comzip) {
		
		CompaniesLink.click();
		createLink.click();
		companyNametxt.sendKeys(comName);
		websiteTxt.sendKeys(comWeb);
		addressTxt.sendKeys(add);
		cityTxt.sendKeys(comcity);
		stateTxt.sendKeys(comState);
		zipcodeTxt.sendKeys(comzip);
		saveBtn.click();
		
		
	}
}
