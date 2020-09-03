package com.qa.hubspot.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BaseTest;
import com.qa.hubspot.utils.ConstantUtil;


public class LoginPageTest extends BaseTest {

	
	@Test(priority = 1)
	public void verifyLoginPageTitleTest() {
		String title = loginpage.getLoginPageTitle();
		Assert.assertEquals(title, ConstantUtil.LOGINPAGE_TITLE.trim());
	}

	@Test(priority = 2)
	public void verifyLoginPageURLTest() {
		boolean flag = loginpage.getLoginPageURL();
		Assert.assertTrue(flag);
	}

	@Test(priority = 3)
	public void verifyLoginPageSignupLinkTest() {
		Assert.assertTrue(loginpage.isSignUpLinkExist());
	}

	@Test(priority = 4)
	public void verifyLoginTest() {
		loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

}
