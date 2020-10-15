package com.qa.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.utils.ConstantUtil;
import com.qa.hubspot.utils.ElementUtils;

public class HomePage extends BasePage{

	private WebDriver driver;
	ElementUtils elementUtil;

	// By Locators - OR
	private By header = By.tagName("h1");
	private By accountName = By.cssSelector("span.account-name");
	private By settingsIcon = By.id("navSetting");
	private By contactsParentMenu = By.id("nav-primary-contacts-branch");
	private By contactsSubMenu =  By.id("nav-secondary-contacts");
	private By salesParentMenu = By.id("nav-primary-sales-branch");
	private By dealSubMenu = By.xpath("(//a[@id='nav-secondary-deals'])[1]");
	

	// Constructor of the page:
	public HomePage(WebDriver driver) {
		elementUtil = new ElementUtils(driver);
		this.driver = driver;
	}

	// Page Actions:
	public String getHomePageTitle() {
		return elementUtil.waitForTitlePresent(ConstantUtil.HOMEPAGE_TITLE.trim(), 10);
	}

	public String getHeaderValue() {
		if (elementUtil.doIsDisplayed(header)) {
			return elementUtil.doGetText(header);
		}
		return null;
	}

	public String getAccountName() {
		if (elementUtil.doIsDisplayed(accountName)) {
			return elementUtil.doGetText(accountName);
		}
		return null;
	}

	public boolean isSettingIconExist() {
		return elementUtil.doIsDisplayed(settingsIcon);
	}
	
	private void clickOnContacts() {
		elementUtil.waitForElementPresent(contactsParentMenu, 10);
		elementUtil.doClick(contactsParentMenu);
		elementUtil.waitForElementPresent(contactsSubMenu, 5);
		elementUtil.doClick(contactsSubMenu);	
	}
	
	public ContactPage goToContactpage() {
		clickOnContacts();
		return new ContactPage(driver);  //Page chaining for Contact Page
	}
	
	private void clickonSales() {
		elementUtil.waitForElementPresent(salesParentMenu, 10);
		elementUtil.doClick(salesParentMenu);
		elementUtil.waitForElementPresent(dealSubMenu, 10);
		elementUtil.doClick(dealSubMenu);
	}
	
	public DealPage goToDealPage() {
		clickonSales();
		driver.navigate().to("https://app.hubspot.com/contacts/8229364/deals/board/view/all/");
		
		return new DealPage(driver);  //Page chaining for Deal Page
	}
	
	
}
