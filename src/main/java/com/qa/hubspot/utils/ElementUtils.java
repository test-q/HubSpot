package com.qa.hubspot.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.hubspot.base.BasePage;

public class ElementUtils {

	WebDriver driver;
	JavaScriptUtil jsUtil;

	public ElementUtils(WebDriver driver) {
		this.driver = driver;
		jsUtil = new JavaScriptUtil(driver);
	}

	/**
	 * this method is used to create WebElement on basis of given BY Locator.
	 * 
	 * @param locator
	 * @return WebElement
	 */
	public WebElement getElement(By locator) {
		WebElement element = driver.findElement(locator);
		if(Boolean.parseBoolean(BasePage.flashElement))
		jsUtil.flash(element);
		return element;
	}

	/**
	 * this method is used to create List of WebElement on basis of given BY
	 * Locator.
	 * 
	 * @param locator
	 * @return List of WebElement
	 */
	public List<WebElement> getElementList(By locator) {
		List<WebElement> eleList = driver.findElements(locator);
		return eleList;
	}

	public void doSendKeys(By locator, String value) {
		getElement(locator).sendKeys(value);
		//driver.findElement(locator).sendKeys(value);
	}

	public void doClick(By locator) {
		getElement(locator).click();
	}

	public String doGetText(By locator) {
		return getElement(locator).getText();
	}

	public boolean doIsDisplayed(By locator) {
		return getElement(locator).isDisplayed();
	}

	/**
	 * this method is used to find all Links on Web page and click given value
	 * 
	 * @param locator
	 * @param value
	 */
	public void doLinkClick(By locator, String value) {
		int length = getElementList(locator).size();
		System.out.println("No. of Link available: " + length);

		for (WebElement ele : getElementList(locator)) {
			String linkName = ele.getText();
			System.out.println(linkName);

			if (linkName.equals(value)) {
				ele.click();
				break;
			}
		}

	}

	/**
	 * this method is used to find all image on Web page and click given image
	 * 
	 * @param locator
	 */
	public void getAllImages(By locator) {
		int length = getElementList(locator).size();
		System.out.println("No. of Images available: " + length);

		for (WebElement img : getElementList(locator)) {
			System.out.println(img.getAttribute("alt"));
		}
	}

	// ***************************** DROP DOWN UTILITY
	// ******************************
	/**
	 * this method is used to select drop down box value by visible text
	 * 
	 * @param locator
	 * @param value
	 */
	public void getDropDownByVisibleText(By locator, String value) {
		WebElement ele = driver.findElement(locator);
		Select s = new Select(ele);
		s.selectByVisibleText(value);

	}

	/**
	 * this method is used to select drop down box value by index based
	 * 
	 * @param locator
	 * @param index
	 */
	public void getDropDownByIndex(By locator, int index) {
//		WebElement ele = driver.findElement(locator);
//		Select s = new Select(ele);
//		s.selectByIndex(index);

		WebElement ele1 = getElement(locator); // using getElement()
		Select s = new Select(ele1);
		s.selectByIndex(index);
	}

	/**
	 * this method is used to select drop down box value by value text
	 * 
	 * @param locator
	 * @param value
	 */
	public void getDropDownByValue(By locator, String value) {
		WebElement ele = driver.findElement(locator);
		Select s = new Select(ele);
		s.selectByValue(value);
	}

	/**
	 * this method is used to find out size of drop down box.
	 * 
	 * @param locator
	 * @return int
	 */
	public int getDropdownOptionsCount(By locator) {
		WebElement dropdownBox = driver.findElement(locator);

		Select s = new Select(dropdownBox);
		List<WebElement> optionList = s.getOptions();
		return optionList.size();

	}

	/**
	 * this method is used to display all options in drop down box
	 * 
	 * @param locator
	 */
	public void getDropdownOptionsValue(By locator) {
		WebElement dropdownBox = driver.findElement(locator);
		Select s = new Select(dropdownBox);
		List<WebElement> optionList = s.getOptions();

		optionList.stream().forEach(ele -> System.out.println(ele.getText()));
	}

	/**
	 * this method is used to return drop down options text.
	 * 
	 * @param locator
	 * @param value
	 * @return Drop down values List of text
	 */
	public List<String> getDropdownOptionsText(By locator) {
		List<String> textList = new ArrayList<String>();

		WebElement dropdownBox = driver.findElement(locator);
		Select s = new Select(dropdownBox);
		List<WebElement> optionList = s.getOptions();

		for (WebElement ele : optionList) {
			String text = ele.getText();
			System.out.println(text);
			textList.add(text);
		}
		return textList;
	}

	public void getValueFromDropDownWithoutSelectClass(By locator, String value) {

		List<WebElement> optionList = driver.findElements(locator);

		System.out.println("Size Of Dropdown Box: " + optionList.size());

		for (WebElement ele : optionList) {
			String text = ele.getText();
			System.out.println(text);
			if (text.equals(value)) {
				ele.click();
				System.out.println(text + " : Click Successfully");
				break;
			}
		}

	}

	/**
	 * this method is used to click value from suggestion list open and return
	 * suggestion list
	 * 
	 * @param locator
	 * @param text
	 * @return
	 */
	public ArrayList<String> doSuggestionListClick(By locator, String text) {
		boolean flag = true;
		ArrayList<String> suggestionText = new ArrayList<String>();
		List<WebElement> suggestionEleList = driver.findElements(locator);
		System.out.println(suggestionEleList.size());

		for (WebElement ele : suggestionEleList) {
			String value = ele.getText();
			System.out.println(value);
			suggestionText.add(value);

			if (value.equals(text)) {
				ele.click();
				System.out.println(text + " : Is Found In Suggestion List.");
				flag = false;
				break;
			}
		}
		if (flag == true) {
			System.out.println(text + " : Is not found in suggestion list.");
		}
		return suggestionText;
	}

	/**
	 * this method is used to click on "single choice/Multiple choice/ all choice"
	 * in choice drop down/jquery drop down box.
	 * 
	 * @param locator
	 * @param value
	 */
	public void selectChoiceValue(By locator, String... value) {
		List<WebElement> choiceList = driver.findElements(locator);

		try {
			if (value[0].equalsIgnoreCase("all")) {
				for (int all = 0; all < choiceList.size(); all++) {
					String text = choiceList.get(all).getText();
					choiceList.get(all).click();
					System.out.println(text + " : is Clicked");
				}
			}

			else {
				for (int i = 0; i < choiceList.size(); i++) {

					String text = choiceList.get(i).getText();
					System.out.println(text);

					for (int j = 0; j < value.length; j++) {
						if (text.equals(value[j])) {
							choiceList.get(i).click();
							System.out.println(text + " : is Clicked");
							break;
						}
					}
				}
			}

		} catch (Exception e) {

		}
	}

	/**
	 * to drag the source element and drop it at target element.
	 * 
	 * @param source
	 * @param target
	 */
	public void doDragAndDrop(WebElement source, WebElement target) {
		Actions act = new Actions(driver);
		act.dragAndDrop(source, target).perform();

	}

	/**
	 * to move the mouse to the middle of element.
	 * 
	 * @param ele
	 */
	public void doMovetoElement(WebElement ele) {
		Actions act = new Actions(driver);
		act.moveToElement(ele).perform();

	}

	/**
	 * to send the keys at selected targeted location
	 * 
	 * @param locator
	 * @param value
	 */
	public void doActionsSendKeys(By locator, String value) {
		Actions act = new Actions(driver);
		act.sendKeys(getElement(locator), value).perform();
	}

	/**
	 * to click on targeted location
	 * 
	 * @param locator
	 */
	public void doActionsClick(By locator) {
		Actions act = new Actions(driver);
		act.click(getElement(locator)).perform();
	}

	// ***************************** Wait UTILITY ******************************

	public WebElement waitForElementPresent(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	public WebElement waitForElementToBeVisible2(By locator, int timeOut) {
		WebElement element = getElement(locator);
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		return wait.until(ExpectedConditions.visibilityOf(element));

	}
	public WebElement waitForElementToBeVisible(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public String waitForTitlePresent(String titleValue, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.titleIs(titleValue));
		return driver.getTitle();
	}

	public Boolean waitForUrl(String url, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		return wait.until(ExpectedConditions.urlContains(url));
	}
	
	public Alert waitForAlertToBePresent(int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		return alert;
	}
	
	public WebElement waitForElementToBeClickable(By locator, int timeOut){
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		return wait.until(ExpectedConditions.elementToBeClickable(locator));
	}
	
	public void clickWhenReady(By locator, int timeOut){
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
		element.click();
	}
	
	public List<WebElement> visibilityofAllElements(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}

	public void getPageLinksText(By locator, int timeOut) {
		visibilityofAllElements(locator, timeOut).stream().forEach(ele -> System.out.println(ele.getText()));
	}
	
	public WebElement waitForElementWithFluentWait(By locator, int timeout , int interval) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
									.withTimeout(Duration.ofSeconds(timeout))
									.pollingEvery(Duration.ofSeconds(interval))
									.ignoring(NoSuchElementException.class);
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	
	// ***************************** WINDOW HANDLE UTILITY ******************************
	
	public void getBrowserWindowHandle(Set<String> windowSet, String parentWindowId) {
		int count = windowSet.size();
		System.out.println("No. Of Windows Opened: " + count);
		Iterator<String> it = windowSet.iterator();
		while(it.hasNext()) {
			String curChildWindowId = it.next();
			if(! parentWindowId.equals(curChildWindowId)) {
				driver.switchTo().window(curChildWindowId);
				System.out.println("Title Of child Window: " + driver.getTitle());
				driver.close();
			}			
		}		
	}
	
	public void getBrowserWindowHandleWithList(Set<String> windowSet, String parentWindowId) {
		List<String> windowList= new ArrayList<String>(windowSet);
		int count = windowList.size();
		System.out.println("No of Windows Opened: " + count);
		
		for(int i=0; i<count; i++) {
			String curChildWindowId = windowList.get(i);
			if(! parentWindowId.equals(curChildWindowId)) {
				driver.switchTo().window(curChildWindowId);
				System.out.println("Title Of Child Window " + i + " : " + driver.getTitle());
				driver.close();
			}
		}
	}


}
	
