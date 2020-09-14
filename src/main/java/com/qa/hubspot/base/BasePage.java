package com.qa.hubspot.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.qa.hubspot.utils.OptionsManager;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * 
 * @author rupal
 *
 */
public class BasePage {

	WebDriver driver;
	Properties prop;
	OptionsManager optionsManager;
	public static String flashElement;
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

	public WebDriver init_driver(Properties prop) {

		flashElement = prop.getProperty("highlights").trim();
		
		if(Boolean.parseBoolean(prop.getProperty("bluePipeline"))) {
			//When Blue Ocean Pipeline is true than this code is executed bcoz we are reading bowser value from system
			String browser = System.getProperty("browser");
			System.out.println("Browser Name is: " + browser);
			optionsManager = new OptionsManager(prop);

			if (browser == null) {
				WebDriverManager.chromedriver().setup();
				tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));

			} else {
				switch (browser) {
				case "chrome":
					WebDriverManager.chromedriver().setup();
					tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
					break;

				case "firefox":
					WebDriverManager.firefoxdriver().setup();
					tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
					break;

				default:
					System.out.println("Please Pass The Correct Browser Name : " + browser);

				}

			}
			getDriver().manage().deleteAllCookies();
			getDriver().manage().window().maximize();
			getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			getDriver().get("https://app.hubspot.com/login");
			//return getDriver();
			
		}
		else {
			//When Blue Ocean Pipeline is false and take browser name from Config.property file i.e when normal jenkin job run.
			
			String browserName = prop.getProperty("browser");
			System.out.println("Browser Name is: " + browserName);
			optionsManager = new OptionsManager(prop);

			if (browserName.equalsIgnoreCase("chrome")) {
				WebDriverManager.chromedriver().setup();
				// driver = new ChromeDriver(optionsManager.getChromeOptions());
				tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
			} else if (browserName.equalsIgnoreCase("firefox")) {
				WebDriverManager.firefoxdriver().setup();
				// driver = new FirefoxDriver(optionsManager.getFirefoxOptions());
				tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
			} else {
				System.out.println("Please Pass The Correct Browser Name : " + browserName);
			}

			getDriver().manage().deleteAllCookies();
			getDriver().manage().window().maximize();
			getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			getDriver().get("https://app.hubspot.com/login");
			//return getDriver();			
		}
		
		return getDriver();
	}

	/**
	 * This method is used to initialize the WebDriver on the basis of given browser name
	 * @param Pass Properties
	 * @return This method return driver
	 */
//	public WebDriver init_driver(Properties prop) {
//		flashElement = prop.getProperty("highlights").trim();
//		String browserName = prop.getProperty("browser");
//		System.out.println("Browser Name is: " + browserName);
//		optionsManager = new OptionsManager(prop);
//		
//		if (browserName.equalsIgnoreCase("chrome")) {
//			WebDriverManager.chromedriver().setup();
//			//driver = new ChromeDriver(optionsManager.getChromeOptions());
//			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
//		} 
//		else if (browserName.equalsIgnoreCase("firefox")) {
//			WebDriverManager.firefoxdriver().setup();
//			//driver = new FirefoxDriver(optionsManager.getFirefoxOptions());
//			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
//		}
//		else {
//			System.out.println("Please Pass The Correct Browser Name : " + browserName);
//		}
//		
//		getDriver().manage().deleteAllCookies();
//		getDriver().manage().window().maximize();
//		getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//		getDriver().get("https://app.hubspot.com/login");
//		return getDriver();
//	}

	/**
	 * 
	 * @param prop
	 * @return This method return driver. Use This method When you use Blue Ocean Pipeline concept
	 */
//	public WebDriver init_driver(Properties prop) {
//		// String browser = "";
//		flashElement = prop.getProperty("highlights").trim();
//		String browser = System.getProperty("browser");
//		System.out.println("Browser Name is: " + browser);
//		optionsManager = new OptionsManager(prop);
//
//		if (browser == null) {
//			WebDriverManager.chromedriver().setup();
//			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
//
//		} else {
//			switch (browser) {
//			case "chrome":
//				WebDriverManager.chromedriver().setup();
//				tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
//				break;
//
//			case "firefox":
//				WebDriverManager.firefoxdriver().setup();
//				tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
//				break;
//
//			default:
//				System.out.println("Please Pass The Correct Browser Name : " + browser);
//
//			}
//
//		}
//		getDriver().manage().deleteAllCookies();
//		getDriver().manage().window().maximize();
//		getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//		getDriver().get("https://app.hubspot.com/login");
//		return getDriver();
//	}

	/**
	 * 
	 * @return This method written synchronized ThreadLocal WebDriver
	 */
	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
	}

	
	
	/**
	 * This method is used to get properties value form Config.properties file
	 * 
	 * @return it return prop
	 */
	public Properties init_prop() {
		prop = new Properties();
		String path = null;
		String env = null;

		try {

			env = System.getProperty("env");
			System.out.println("Running on Envirnment: " + env);

			if (env == null) {
				System.out.println("Running on Envirnment: " + "PROD");
				path = "D:\\Rupali\\Workspace\\June2020HubSpot\\src\\main\\java\\com\\qa\\hubspot\\config\\Config.prod.properties";

			} else {
				switch (env) {
				case "qa":
					path = "D:\\Rupali\\Workspace\\HubSpotFramework\\src\\main\\java\\com\\qa\\hubspot\\config\\Config.qa.properties";
					break;
				case "dev":
					path = "D:\\Rupali\\Workspace\\HubSpotFramework\\src\\main\\java\\com\\qa\\hubspot\\config\\Config.dev.properties";
					break;
				case "stage":
					path = "D:\\Rupali\\Workspace\\HubSpotFramework\\src\\main\\java\\com\\qa\\hubspot\\config\\Config.stage.properties";
					break;
				case "prod":
					path = "D:\\Rupali\\Workspace\\HubSpotFramework\\src\\main\\java\\com\\qa\\hubspot\\config\\Config.prod.properties";
					break;

				default:
					System.out.println("Please Pass the Correct Env Value...");
					break;
				}
			}

			FileInputStream finput = new FileInputStream(path);
			prop.load(finput);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return prop;
	}

	/**
	 * This method take screenshot
	 * 
	 * @return path of screenshot
	 */
	public String getScreenshot() {
		File src = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/screenshots/" + System.currentTimeMillis() + ".png";
		File destination = new File(path);
		try {
			FileUtils.copyFile(src, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}

}
