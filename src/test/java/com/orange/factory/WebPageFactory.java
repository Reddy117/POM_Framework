package com.orange.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.orange.automation.farmework.webdriver.Page;
import com.orange.pageobjects.AdminPage;
import com.orange.pageobjects.CompanyPage;
import com.orange.pageobjects.CrmLoginPage;
import com.orange.pageobjects.LoginPage;
import com.orange.pageobjects.PIMPage;

public class WebPageFactory implements BasePageFactory{

	private WebDriver driver;
	public WebPageFactory(WebDriver driver) {
		this.driver = driver;
	}
	
	public LoginPage getLoginPage() {
		return PageFactory.initElements(new Page().driver, LoginPage.class);
	}

	public PIMPage getPIMPage() {
		return PageFactory.initElements(new Page().driver, PIMPage.class);
	}

	public AdminPage getAdminPage() {
		return PageFactory.initElements(new Page().driver, AdminPage.class);
	}

	public CrmLoginPage getCrmLoginPage() {
		return PageFactory.initElements(driver, CrmLoginPage.class);
	}

	public CompanyPage getCompanyPage() {
		return PageFactory.initElements(driver, CompanyPage.class);
	}

	

}
