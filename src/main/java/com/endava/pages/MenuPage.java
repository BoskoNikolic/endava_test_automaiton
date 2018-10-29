package com.endava.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

/**
 * @author jana.djordjevic@endava.com
 */
public class MenuPage extends BasePage {

	private By automationMenuItem = By.xpath(".//*[@id='mCSB_1_container']/div[1]/nav/ul/li[3]/a");
	private By navigationList = By.className("navigation");
	private By investorsMenuItem = By.xpath(".//*[@id='mCSB_1_container']/div[1]/nav/ul/li[5]/a");
	private static Logger log = Logger.getLogger(MenuPage.class);

	public MenuPage(WebDriver driver) {
		super(driver);
	}

	/**
	 * Checks if link is present on the page and clicks on it
	 *
	 * @return driver to automationPage
	 * @author Goran.Kukolj
	 */
	public AutomationPage openAutomationPage() {
		driver.findElement(this.automationMenuItem).click();
		log.debug("Finds automation menu item in burger menu and clicks on it");
		return new AutomationPage(driver);
	}

	/**
	 * Returns search context of Investors element in the Menu.
	 *
	 * @author jelena.corak
	 * @return By search context of Investors element
	 */
	public By getInvestorsMenuItem() {
		return investorsMenuItem;
	}
	
	/**
	 * Returns text contained in the web element.
	 *
	 * @param By Search context of a web element
	 * @return String text of the web element
	 * @author jelena.corak
	 */
	public String getTextFromElement(By context) {
		WebElement webElement = driver.findElement(context);
		if (!webElement.isDisplayed()) {
			Assert.fail("No element found.");
		}
		log.debug("Text contained in the following element(" + context + "): " + webElement.getText());
		return webElement.getText();
	}

	public By getNavigationList() {
		return navigationList;
	}
}