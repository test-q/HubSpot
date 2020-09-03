package com.qa.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.utils.ConstantUtil;
import com.qa.hubspot.utils.ElementUtils;

public class ContactPage extends BasePage{
	private WebDriver driver;
	ElementUtils elementUtil;
	
	private By header = By.xpath("(//*[text()='Contacts'])[position()=2]");
	private By createContactPrimary = By.xpath("//span[text()='Create contact']");
	private By emailId = By.xpath("//input[@data-field='email']");
	private By firstname = By.xpath("//input[@data-field='firstname']");
	private By lastname = By.xpath("//input[@data-field='lastname']");
	private By jobtitle = By.xpath("//input[@data-field='jobtitle']");
	private By createContactSecondary = By.xpath("(//span[text()='Create contact'])[position()=2]");
	private By contactsBackLink = By.xpath("(//*[text()='Contacts'])[position()=2]");
	
	public ContactPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtils(driver);
	}
	
	public String getContactPageHeader() {
		elementUtil.waitForElementPresent(header, 10);
		return  elementUtil.doGetText(header);
	}
	
	public String getContactPageTitle() {
		return elementUtil.waitForTitlePresent(ConstantUtil.CONTACTPAGE_TITLE, 10);
	}
	
	/**
	 * This method create contact.
	 * @param emailId
	 * @param firstname
	 * @param lastname
	 * @param jobtitle
	 * @return
	 */
	public boolean createContact(String emailId, String firstname, String lastname, String jobtitle) {
		String fullName ="";
		elementUtil.clickWhenReady(createContactPrimary, 10); // waiting for element as well as clicking
		elementUtil.waitForElementToBeVisible(this.emailId, 10);
		elementUtil.doSendKeys(this.emailId, emailId);
		elementUtil.doSendKeys(this.firstname, firstname);
		elementUtil.doSendKeys(this.lastname, lastname);
		elementUtil.doSendKeys(this.jobtitle, jobtitle);
		elementUtil.clickWhenReady(createContactSecondary, 10);
		
		try {
			Thread.sleep(2000);
			elementUtil.clickWhenReady(contactsBackLink, 10);
			//span[text()='Purvi Rathod']
			fullName = firstname+ " " +lastname;
			System.out.println("Contact Created: " +fullName);
			//span[text()='"+fullName+"']
			elementUtil.waitForElementToBeVisible(By.xpath("//span[text()='"+fullName+"']"), 20);	

			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		boolean flag = elementUtil.doIsDisplayed(By.xpath("//span[text()='"+fullName+"']"));
		return flag;
	}
	
	

}
