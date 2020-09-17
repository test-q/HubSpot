package com.qa.hubspot.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.qa.hubspot.pages.ContactPage;
import com.qa.hubspot.pages.DealPage;
import com.qa.hubspot.pages.HomePage;
import com.qa.hubspot.pages.LoginPage;

public class BaseTest {

	WebDriver driver;
	public BasePage basepage;
	public Properties prop;
	public LoginPage loginpage;
	public HomePage homepage;
	public ContactPage contactpage;
	public DealPage dealpage;

	//If you want to execute some test case on chrome and some on firefox than use @Parameters
	@Parameters("browser")
	@BeforeTest
	public void setup(String browsername) {
		System.out.println("BrowserName is: " +browsername);
		basepage = new BasePage();
		prop = basepage.init_prop();
		prop.setProperty("browser", browsername);
		driver = basepage.init_driver(prop);
		loginpage = new LoginPage(driver);
	}
	
	//If you want to execute all test cases in single browser
//	@BeforeTest
//	public void setup() {
//		basepage = new BasePage();
//		prop = basepage.init_prop();
//		driver = basepage.init_driver(prop);
//		loginpage = new LoginPage(driver);
//	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
