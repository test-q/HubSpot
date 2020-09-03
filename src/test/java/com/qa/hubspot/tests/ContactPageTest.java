package com.qa.hubspot.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BaseTest;
import com.qa.hubspot.utils.ConstantUtil;
import com.qa.hubspot.utils.ExcelUtil;

public class ContactPageTest extends BaseTest{

	@BeforeClass
	public void ContactPageSetup() {
		homepage = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		contactpage = homepage.goToContactpage();	
	}
	
	@Test(priority = 1)
	public void verifyContactPageTitleTest() {
		String title = contactpage.getContactPageTitle();
		System.out.println("Contact Page Title: " +title);
		Assert.assertEquals(title, ConstantUtil.CONTACTPAGE_TITLE);
	}
	
	@Test(priority = 2)
	public void verifyContactPageHeaderTest() {
		String header = contactpage.getContactPageHeader();
		System.out.println("Contact Page Header: " +header);
		Assert.assertEquals(header, ConstantUtil.CONTACTPAGE_HEADER);
	}
	
	@DataProvider
	public Object[][] getContactTestData() {
		Object[][] data = ExcelUtil.getTestData(ConstantUtil.CONTACT_SHEETNAME);
		return data;
	}
	@Test(priority = 3, dataProvider = "getContactTestData")
	public void verfiyCreateContactTest(String emailId, String firstName, String lastName, String jobTitle) {
		Assert.assertTrue(contactpage.createContact(emailId, firstName, lastName, jobTitle));		
	}
}
