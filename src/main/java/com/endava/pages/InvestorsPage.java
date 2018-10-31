package com.endava.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @author jelena.corak
 */

public class InvestorsPage extends BasePage {

	public static final String INVESTORS_URL = "https://investors.endava.com/home/default.aspx";
	public static final String INVESTORS_TITLE = "Investors";
	private static final String SEARCH_RESULT = "No results found.";
	private WebElement search = driver.findElement(By.className("search-link"));
	private WebElement searchBox = driver.findElement(By.xpath("//*[@id=\"_ctrl0_ctl36_txtSearchInput\"]"));
	private WebElement submitButton = driver.findElement(By.xpath("//*[@id=\"_ctrl0_ctl36_btnSearch\"]"));
	private By searchResultElement = By.className("module_message");
	private By investorsAboutUs = By.xpath("//*[@id='_ctrl0_ctl66_divModuleContainer']");
	private static Logger log = Logger.getLogger(InvestorsPage.class);

	protected InvestorsPage(WebDriver driver) {
		super(driver);
	}

	public WebElement getSearch() {
		return search;
	}

	public By getSearchResultElement() {
		return searchResultElement;
	}

	public WebElement getSubmitButton() {
		return submitButton;
	}

	public String getSearchResult() {
		return SEARCH_RESULT;
	}

	/**
	 * Returns search context of About Us element on the INVESTORS page.
	 *
	 * @author jelena.corak
	 * @return By search context of About Us element
	 */
	public By getInvestorsAboutUs() {
		return investorsAboutUs;
	}

	/**
	 * @param searchText String to be searched for
	 * @author Vladimir Krekic Finds searchBox and fills it with search text
	 */
	public void fillSearchBox(String searchText) {
		makeItVisible(searchBox);
		if (searchBox.isDisplayed()) {
			searchBox.sendKeys(searchText);
			log.debug("Search Box filled with text: " + searchText);
		} else {
			log.debug("Search box not present - test failed");
			driver.quit();
		}
	}
}
