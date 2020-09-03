package com.qa.hubspot.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BaseTest;
import com.qa.hubspot.pages.HomePage;
import com.qa.hubspot.utils.ConstantUtil;

public class HomePageTest extends BaseTest {
	
	@BeforeClass
	public void HomePageSetUp() {
		homepage = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test(priority = 1)
	public void verifyHomePageTitleTest() {
		String title = homepage.getHomePageTitle();
		System.out.println("Home page Title is: " + title);
		Assert.assertEquals(title, ConstantUtil.HOMEPAGE_TITLE.trim());
	}

	@Test(priority = 2)
	public void verifyHomePageHeaderValue() {
		String header = homepage.getHeaderValue();
		System.out.println("Home Page Header is: " + header);
		Assert.assertEquals(header, ConstantUtil.HOMEPAGE_HEADER.trim());
	}

	@Test(priority = 3)
	public void verifyHomePageAccountName() {
		String accountname = homepage.getAccountName();
		System.out.println("Account Name Is: " + accountname);
		Assert.assertEquals(accountname, ConstantUtil.HOMEPAGE_ACCOUNTNAME.trim());
	}

	@Test(priority = 4)
	public void verifyHomePageIsSettingIconExits() {
		Assert.assertTrue(homepage.isSettingIconExist());
	}

}
