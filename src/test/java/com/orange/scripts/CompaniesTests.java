package com.orange.scripts;

import java.util.HashMap;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.orange.automation.farmework.webdriver.BaseClass;
import com.orange.pageobjects.CompanyPage;
import com.orange.pageobjects.CrmLoginPage;

public class CompaniesTests extends BaseClass{

	@Test(dataProvider="getcreateCompanydata")
	public void tc1_createCompany(HashMap<String,String> map) throws InterruptedException {
		CrmLoginPage cp=getPageFactory().getCrmLoginPage();
		CompanyPage ccp=getPageFactory().getCompanyPage();
		
		cp.do_Login();
		ccp.createCompany(map.get("ComName"), map.get("ComWebSite"), map.get("Address"), map.get("City"), map.get("State"), map.get("Zipcode"));
	}
	
	@DataProvider
	public Object[][] getcreateCompanydata(){
		return getData("TC5_CreateCompany", "Sheet1");
	}
}
