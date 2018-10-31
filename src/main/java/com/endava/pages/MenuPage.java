package com.endava.pages;

import java.util.ArrayList;
import java.util.List;

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
	private By successStoriesMenuItem = By.xpath("//*[@id=\"mCSB_1_container\"]/div[1]/nav/ul/li[7]/a");
	private By logo = By.xpath("//*[@id=\"header\"]/div/div[1]/div[1]/span/a");
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
	 * Returns search context of Success Stories element in the Menu.
	 * 
	 * @author jelena.corak
	 * @return By search context of Success Stories element
	 */
	public By getSuccessStoriesMenuItem() {
		return successStoriesMenuItem;
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

	/**
	 * Returns search context of Endava logo element in menu pages (except Investors page).
	 * 
	 * @author jelena.corak
	 * @return By search context of About Us element
	 */
	public By getLogo() {
		return logo;
	}

	/**
	 * Returns list of menu pages URLs.
	 * 
	 * @author jelena.corak
	 * @return List<String> list of menu pages URLs, except Investors page
	 * 
	 */
	public List<String> getMenuPagesUrlList() {
		List<String> menuPagesUrlList = new ArrayList<>();
		menuPagesUrlList.add(DigitalPage.DIGITAL_URL);
		menuPagesUrlList.add(AgilePage.AGILE_URL);
		menuPagesUrlList.add(AutomationPage.ENDAVA_AUTOMATION_URL);
		menuPagesUrlList.add(ServicesPage.SERVICES_URL);
		menuPagesUrlList.add(IndustriesPage.INDUSTRIES_URL);
		menuPagesUrlList.add(SuccessStoriesPage.SUCCESS_STORIES_URL);
		menuPagesUrlList.add(AboutPage.ABOUT_URL);
		menuPagesUrlList.add(CareersPage.CAREERS_URL);
		menuPagesUrlList.add(ContactPage.CONTACT_URL);
		return menuPagesUrlList;
	}

	/**
	 * Returns list of menu pages URLs.
	 * 
	 * @author jelena.corak
	 * @return List<String> list of menu pages URLs, except Investors page
	 * 
	 */
	public List<String> getMenuPagesTitleList() {
		List<String> menuPagesTitleList = new ArrayList<>();
		menuPagesTitleList.add(DigitalPage.DIGITAL_TITLE);
		menuPagesTitleList.add(AgilePage.AGILE_TITLE);
		menuPagesTitleList.add(AutomationPage.ENDAVA_AUTOMATION_TITLE);
		menuPagesTitleList.add(ServicesPage.SERVICES_TITLE);
		menuPagesTitleList.add(IndustriesPage.INDUSTRIES_TITLE);
		menuPagesTitleList.add(SuccessStoriesPage.SUCCESS_STORIES_TITLE);
		menuPagesTitleList.add(AboutPage.ABOUT_TITLE);
		menuPagesTitleList.add(CareersPage.CAREERS_TITLE);
		menuPagesTitleList.add(ContactPage.CONTACT_TITLE);
		return menuPagesTitleList;
	}

	/**
	 * Returns list of menu items search contexts, except for Investors page.
	 * 
	 * @author jelena.corak
	 * @return List<By> list of menu items search contexts
	 * 
	 */
	public List<By> getMenuItemsByList() {
		List<By> menuItemsByList = new ArrayList<>();
		for (int i = 1; i <= 10; i++) {
			if (i == 5)
				continue;
			else
				menuItemsByList.add(By.xpath(String.format("//*[@id=\"mCSB_1_container\"]/div[1]/nav/ul/li[%d]/a", i)));
		}
		return menuItemsByList;
	}
}