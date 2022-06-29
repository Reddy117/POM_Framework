package com.orange.factory;

import com.orange.pageobjects.AdminPage;
import com.orange.pageobjects.CompanyPage;
import com.orange.pageobjects.CrmLoginPage;
import  com.orange.pageobjects.LoginPage;
import com.orange.pageobjects.PIMPage;

public interface BasePageFactory {	
	LoginPage getLoginPage();
	PIMPage getPIMPage();
	AdminPage getAdminPage();
	CrmLoginPage getCrmLoginPage();
	CompanyPage getCompanyPage();
}
