package com.orange.scripts;
import java.util.HashMap;

import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.orange.automation.farmework.webdriver.BaseClass;
import com.orange.pageobjects.LoginPage;
import com.orange.pageobjects.PIMPage;

public class TS1_PIMTests extends BaseClass {
	
	@Test(priority=1,dataProvider="getAddEmployeefromPIMData")
	public void addEmployeeFromPIM(HashMap<String,String> map) throws Exception {
		
		if(map.get("TestFlag").equals("No"))
			throw new SkipException("skipping the execution becuaser test flag as no");
		
		lp.doLogin("Admin", "admin123");
		pp.addEmployee(map.get("FirstName"),map.get("LastName"));
		String fullNameVal=pp.getFullName();
		pp.doLogOut();
		
		writeSpecificData("TC2_ProvideAdminRights", "Sheet1", "EmpoyeeName", fullNameVal);
		writeSpecificData("TC4_DeleteFromAdmin", "Sheet1", "UserName", fullNameVal);
		
	}
	

	@DataProvider
	public Object[][] getAddEmployeefromPIMData(){
		return getData("TC1_addEmployeeFromPIM", "Sheet1");
	}
	
}
