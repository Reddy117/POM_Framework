package com.orange.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.orange.automation.farmework.webdriver.BaseClass;
import com.orange.automation.farmework.webdriver.Page;

public class PIMPage extends Page{
	
	
		@FindBy(xpath="//*[@id=\"menu_pim_viewPimModule\"]")
		WebElement pimLink;
		
		@FindBy(xpath="//*[@id=\"btnAdd\"]")
		WebElement addbutton;
		
		@FindBy(xpath="//*[@id=\"firstName\"]")
		WebElement firstNameTxt;
		
		@FindBy(xpath="//*[@id=\"lastName\"]")
		WebElement lastNameTxt;
		
		@FindBy(xpath="//*[@id=\"employeeId\"]")
		WebElement empIDTxt;
		
		@FindBy(xpath="//*[@id=\"btnSave\"]")
		WebElement saveBtn;
	
		@FindBy(xpath="//*[@id=\"welcome\"]")
		WebElement logOutmenu;
		
		@FindBy(xpath="//*[@id=\"welcome-menu\"]/ul/li[3]/a")
		WebElement logOutBtn;
		
						
		@FindBy(xpath="(//h1)[1]")
		WebElement fullName;
		
		public void addEmployee(String fName,String lName) {
			try {
				
				pimLink.click();
				//clickElement(pimLink);
				addbutton.click();
				firstNameTxt.sendKeys(fName);
				lastNameTxt.sendKeys(lName);
				empIDTxt.click();
				BaseClass.pressTab();
				BaseClass.pressEnter();
				Thread.sleep(2000);
				new BaseClass().upLoadFile("C:\\Framework\\OrangeHrm\\src\\main\\resources\\Images\\Mypic.jpg");
				Thread.sleep(2000);
				saveBtn.click();
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		}
		
		public void doLogOut() {
			try {
				logOutmenu.click();
				logOutBtn.click();
				
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		public String getFullName() {
			return fullName.getText();
		}

}
