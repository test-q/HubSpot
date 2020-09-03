package com.qa.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.utils.ConstantUtil;
import com.qa.hubspot.utils.ElementUtils;

public class DealPage {
	
	private WebDriver driver;
	ElementUtils elementUtil;
	
	private By header = By.xpath("(//*[text()='Deals'])[position()=2]");
	private By createDealPrimary = By.xpath("//span[text()='Create deal']");
	private By dealName = By.xpath("//input[@data-field='dealname']");
	private By dealAmount = By.xpath("//input[@data-field='amount']");
	private By createDealSecondary = By.xpath("//span[text()='Create']");
	private By dealBackLink = By.xpath("(//*[text()='Deals'])[position()=2]");
	private By dealStage = By.xpath("//span[text()='Appointment scheduled']");
	private By dealStageList = By.xpath("//ul[@class='private-typeahead-results']/li");
	
	public DealPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtils(driver);
	}
	
	public String getDealPageTitle() {
		return elementUtil.waitForTitlePresent(ConstantUtil.DEALPAGE_TITLE, 10);
	}
	
	public String getDealPageHeader() {
		elementUtil.waitForElementPresent(header, 10);
		return elementUtil.doGetText(header);
	}
	
	public boolean createDeals(String dealName, String dealStage, String dealAmount) {
		elementUtil.clickWhenReady(createDealPrimary, 10);
		elementUtil.waitForElementToBeVisible(this.dealName, 10);
		elementUtil.doSendKeys(this.dealName, dealName);
		elementUtil.doClick(this.dealStage);
		elementUtil.waitForElementToBeVisible(this.dealStageList, 10);
		elementUtil.doSuggestionListClick(this.dealStageList, dealStage);
		elementUtil.doSendKeys(this.dealAmount, dealAmount);
		elementUtil.waitForElementToBeVisible(createDealSecondary, 10);
		elementUtil.doClick(createDealSecondary);
		//span[text()='beast1']
		boolean flag = elementUtil.doIsDisplayed(By.xpath("//span[text()='"+dealName+"']"));
		elementUtil.clickWhenReady(dealBackLink, 10);
		return flag;
	}
	
	

}
