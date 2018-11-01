package com.endava.pages;

import java.io.IOException;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Locatable;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.testng.Assert;
import org.testng.ITestResult;
import com.endava.util.Utils;

/**
 * @author jana.djordjevic@endava.com
 */
public class BasePage {

	public WebDriver driver;
	public static final String ENDAVA_WEBSITE_URL = "https://www.endava.com";
	public static final String ENDAVA_URL_EN = "https://www.endava.com/en";
	public static final String ENDAVA_URL_DE = "https://www.endava.com/de-DE";
	private static Logger log = Logger.getLogger(BasePage.class);

	public BasePage(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * Verifies page URL.
	 * 
	 * @author jelena.corak
	 * @param String expected URL
	 */
	public void assertPageUrl(String expectedUrl) {
		Assert.assertEquals(driver.getCurrentUrl().toLowerCase(), expectedUrl.toLowerCase(), "Incorrect URL!");
	}

	/**
	 * Verifies page title.
	 *
	 * @author jelena.corak
	 * @param String expected title
	 */
	public void assertPageTitle(String expectedTitle) {
		Assert.assertEquals(driver.getTitle().toLowerCase(), expectedTitle.toLowerCase(), "Incorrect title!");
	}

	/**
	 * Scrolls element into view.
	 *
	 * @author jelena.corak
	 * @param By element search context
	 */
	public void scrollIntoView(By context) {
		WebElement element = driver.findElement(context);
		try {
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		} catch (Exception g) {
			log.debug(">>>> Exception in scrollIntoView! Element may not have been found: " + element);
			Assert.fail();
		}
	}

	/**
	 * Asserts that the link in the element is correct.
	 *
	 * @author jelena.corak
	 * @param WebElement web element whose link is being checked
	 * @param String     expected link
	 */
	public static void assertElementLink(WebElement element, String expectedLink) {
		Assert.assertTrue(element.getAttribute("href").equalsIgnoreCase(expectedLink),
				"Incorrect link for icon " + element.getAttribute("class"));
	}

	/**
	 * Clicks on the element.
	 * 
	 * @author jelena.corak
	 * @param By Search context of a web element
	 */
	public void clickOnElement(By context) {
		WebElement eventElement = driver.findElement(context);
		String elementToString = eventElement.toString();
		Assert.assertTrue(eventElement.isDisplayed(), "Element is not present.");
		eventElement.click();
		log.debug("Clicked on element " + elementToString);
	}

	/**
	 * Clicks directly on element in case of overlay.
	 *
	 * @author jelena.corak
	 * @param By element search context
	 */
	public void directClickOnElement(By context) {
		WebElement element = driver.findElement(context);
		try {
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
		} catch (Exception ex) {
			log.debug(">>>> Exception in directClickOnElement! Element may not have been found: " + element);
			Assert.fail();
		}
	}

	/**
	 * Method is selecting (clicking on) WebElement
	 * 
	 * @author Vladimir Krekic
	 * @param element WebElement
	 * @return boolean
	 */
	public boolean selectElement(WebElement element) {
		makeItVisible(element);
		if (element.isDisplayed()) {
			element.click();
			log.debug("WebElement clicked " + element.toString());
			return true;
		}
		log.debug("WebElement not visible " + element.toString());
		return false;
	}

	/**
	 * @return search result text
	 * @author Vladimir Krekic
	 */
	public String getSearchResult(WebElement element) {
		log.debug("Search Result found on element " + element.toString());
		return element.getText();
	}

	/**
	 * @param webElement
	 * @author Vladimir Krekic Makes web element visible
	 */
	public void makeItVisible(WebElement webElement) {
		Coordinates coordinates = ((Locatable) webElement).getCoordinates();
		coordinates.inViewPort();
	}

	/**
	 * @author Goran.Kukolj
	 * @param driver
	 * @param locator
	 * @param originalText
	 * @return true or false depending on comparing two strings
	 */

	public boolean validateString(WebDriver driver, By locator, String originalText) {
		WebElement element = driver.findElement(locator);
		String textToCompare = element.getText();
		log.debug("Compares two strings");
		return textToCompare.equals(originalText);
	}

	/**
	 * Returns text contained in the web element.
	 * 
	 * @author jelena.corak
	 * @param WebDriver driver
	 * @param By        Search context of a web element
	 * @return String text of the web element
	 */
	public String getTextFromElement(By context) {
		WebElement webElement = driver.findElement(context);
		if (!webElement.isDisplayed()) {
			Assert.fail("No element found.");
		}
		log.debug("Text contained in the following element(" + context + "): " + webElement.getText());
		return webElement.getText();
	}

	public void quit() {
		if (this.driver != null) {
			driver.quit();
			log.debug("Browser closed");
		}
	}

	/**
	 * Finds the element that takes user to a new page and clicks on it.
	 * 
	 * @author jelena.corak
	 * @param    Class<Page> class of the page to be opened
	 * @param By search context of the element whose click takes user to a new page of the Class<Page> class
	 * @return instance of a <Page> or null in case of an exception
	 */
	public <Page extends BasePage> Page clickToGetPage(Class<Page> pageClass, By context) {
		WebElement element = driver.findElement(context);
		Assert.assertTrue(element.isDisplayed(), "Element " + element + " is not present.");
		element.click();
		try {
			return pageClass.getDeclaredConstructor(WebDriver.class).newInstance(this.driver);
		} catch (Exception e) {
			log.debug(e.getMessage());
			return null;
		}
	}

	/**
	 * Takes screen shot in case of test failure.
	 * 
	 * @author jelena.corak
	 * @param ITestResult
	 */
	public void ifFailed(ITestResult testResult) {
		if (testResult.getStatus() == ITestResult.FAILURE) {
			try {
				Utils.takeScreenShot(driver, testResult.getMethod().getMethodName());
			} catch (IOException e) {
				log.error("Screenshot failed." + e.getMessage());
			}
		}
	}

	/**
	 * Returns WebElement element from By search context.
	 * 
	 * @author jelena.corak
	 * @param By search context of an element
	 */
	public WebElement getElement(By context) {
		try {			
			return driver.findElement(context);
		} catch (Exception e) {
			log.debug("Element not found. " + e.getMessage());
			return null;
		}
	}
}