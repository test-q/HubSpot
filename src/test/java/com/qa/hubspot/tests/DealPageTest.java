package com.qa.hubspot.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BaseTest;
import com.qa.hubspot.utils.ConstantUtil;
import com.qa.hubspot.utils.ExcelUtil;

public class DealPageTest extends BaseTest{
	
	@BeforeClass
	public void DealPageSetup() {
		homepage = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		dealpage = homepage.goToDealPage();
	}
	
	@Test(priority = 1)
	public void verifyDealPageTitleTest() {
		String title = dealpage.getDealPageTitle();
		System.out.println("Deal Page Title: " +title);
		Assert.assertEquals(title, ConstantUtil.DEALPAGE_TITLE);
	}
	
	@Test(priority = 2)
	public void verifyDealPageHeaderTest() {
		String header = dealpage.getDealPageHeader();
		System.out.println("Deal Page Header: " +header);
		Assert.assertEquals(header, ConstantUtil.DEALPAGE_HEADER);
	}
	
	@DataProvider
	public Object[][] getDealTestData() {
		Object[][] data = ExcelUtil.getTestData(ConstantUtil.DEALS_SHEETNAME);
		return data;
	}
	@Test(priority = 3, dataProvider = "getDealTestData")
	public void verifyCreateDealTest(String dealName, String dealStage, String dealAmount) {
		Assert.assertTrue(dealpage.createDeals(dealName, dealStage, dealAmount));
		
	}
	

}
