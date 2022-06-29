package com.orange.scripts;

import java.util.HashMap;

import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.orange.automation.farmework.webdriver.BaseClass;
import com.orange.pageobjects.AdminPage;
import com.orange.pageobjects.LoginPage;
import com.orange.pageobjects.PIMPage;

public class TS2_AdminTests extends BaseClass {

	@Test(dataProvider="getgiveAdminRightsData",priority=1)
	public void tc1_giveAdminRights(HashMap<String,String> map) {
		if(map.get("TestFlag").equals("No"))
			throw new SkipException("skipping the execution becuaser test flag as no");

		lp.doLogin("Admin", "admin123");
		ap.provideAdminRights(map.get("EmpoyeeName"), map.get("UserName"), map.get("Password"));
		//writeSpecificData("TC4_DeleteFromAdmin", "Sheet1", "UserName", map.get("map"));
		pp.doLogOut();
		
	}
	
	@Test(dataProvider="getDeleteEmployeeFromAdmin",priority=2)
	public void tc2_DeleteEmployeeFromAdmin(HashMap<String,String> map) {
		if(map.get("TestFlag").equals("No"))
			throw new SkipException("skipping the execution becuaser test flag as no");

		lp.doLogin("Admin", "admin123");
		ap.deleteEmployee(map.get("UserName"));
	}
	
	@DataProvider
	public Object[][] getgiveAdminRightsData(){
		return getData("TC2_ProvideAdminRights", "Sheet1");
	}
	
	@DataProvider
	public Object[][] getDeleteEmployeeFromAdmin(){
		return getData("TC4_DeleteFromAdmin", "Sheet1");
	}
	
	
}

