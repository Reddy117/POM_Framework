package com.orange.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.orange.automation.farmework.webdriver.BaseClass;
import com.orange.automation.farmework.webdriver.Page;

public class AdminPage extends Page{

	@FindBy(xpath="//*[@id=\"menu_admin_viewAdminModule\"]")
	WebElement adminLink;
	
	@FindBy(xpath="//*[@id=\"menu_admin_UserManagement\"]")
	WebElement userManagementLink;
	
	@FindBy(xpath="//*[@id=\"menu_admin_viewSystemUsers\"]")
	WebElement usersLink;
	
	@FindBy(xpath="//*[@id=\"btnAdd\"]")
	WebElement addBtn;
				   //*[@id="searchSystemUser_employeeName_empName"]
	@FindBy(xpath="//*[@id=\"systemUser_employeeName_empName\"]")
	WebElement employeeNameTxt;
	
	@FindBy(xpath="//*[@id=\"systemUser_userName\"]")
	WebElement userNameTxt;
	
	@FindBy(xpath="//*[@id=\"systemUser_password\"]")
	WebElement passWordTxt;
	
	@FindBy(xpath="//*[@id=\"systemUser_confirmPassword\"]")
	WebElement confrimPasswordTxt;
	
	@FindBy(xpath="//*[@id=\"btnSave\"]")
	WebElement saveBtn;
	
	@FindBy(xpath="//*[@id=\"searchSystemUser_employeeName_empName\"]")
	WebElement employeeName1;
	
	@FindBy(xpath="//*[@id=\"searchBtn\"]")
	WebElement searchBtn;
	
	@FindBy(xpath="//input[@name='chkSelectRow[]']")
	WebElement empcheckBox;
	
	@FindBy(xpath="//*[@id=\"btnDelete\"]")
	WebElement deleteBtn;
	
	@FindBy(xpath="//*[@id=\"dialogDeleteBtn\"]")
	WebElement Okbtn;
	
	public void provideAdminRights(String empName,String userName,String passWord) {
		try {
			BaseClass.mouseHover(adminLink);
			BaseClass.mouseHover(userManagementLink);
			usersLink.click();
			addBtn.click();
			employeeNameTxt.sendKeys(empName);
			userNameTxt.sendKeys(userName);
			passWordTxt.sendKeys(passWord);
			confrimPasswordTxt.sendKeys(passWord);
			saveBtn.click();
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteEmployee(String employeeName) {
		try{
			BaseClass.mouseHover(adminLink);
			BaseClass.mouseHover(userManagementLink);
			usersLink.click();
			employeeName1.sendKeys(employeeName);
			searchBtn.click();
			empcheckBox.click();
			deleteBtn.click();
			Okbtn.click();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
