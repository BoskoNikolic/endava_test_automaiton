package com.endava;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.endava.pages.HomePage;
import com.endava.pages.InvestorsPage;
import com.endava.pages.MenuPage;
import com.endava.util.Utils;

/**
 * @author jelena.corak
 */

public class TestInvestorsPage {

	private HomePage homePage;
	private MenuPage menuPage;
	private InvestorsPage investorsPage;
	private static Logger log = Logger.getLogger(TestInvestorsPage.class);

	@BeforeTest
	@Parameters({ "browser" })
	public void setUp(String browser) {
		homePage = Utils.setUpWebBrowser(browser);
		log.info("Setting up chosen browser: " + browser);
	}

	/**
	 * Test validates that the string "ABOUT US" is visible on the "INVESTORS" page in the menu.
	 * 
	 * @author jelena.corak 
	 */
	@Test
	public void testAboutUsVisibility() {
		homePage.open();
		homePage.assertPageUrl(HomePage.ENDAVA_URL);
		homePage.assertPageTitle(HomePage.ENDAVA_TITLE);
		Utils.webDriverWaitVisibility(homePage.driver, homePage.getContactButtons());
		menuPage = homePage.openMenu();
		Utils.webDriverWaitVisibility(menuPage.driver, menuPage.getNavigationList());
		investorsPage = menuPage.clickToGetPage(InvestorsPage.class, menuPage.getInvestorsMenuItem());
		investorsPage.assertPageUrl(InvestorsPage.INVESTORS_URL);
		/*
		 * Title verification skipped for Investors page because page title is incorrect.
		 */		
		Utils.webDriverWaitVisibility(investorsPage.driver, investorsPage.getInvestorsAboutUs());
		Assert.assertTrue(investorsPage.getTextFromElement(investorsPage.getInvestorsAboutUs()).contains("ABOUT US"),
				"Text \"ABOUT US\" not found!");
		log.info("testAboutUsVisibility() : VALIDATION SUCCESSFUL!");
	}

	/**
	 * @author Vladimir Krekic
	 * 
	 *         From burger menu chooses Investors, validates url has been changed, clicks on the Search, on a search box
	 *         types "blahblah", clicks on a "magnifying glass" and validates search result is "No results found."
	 */
	@Test
	public void testInvestorsSearch() {
		homePage.open();
		homePage.assertPageUrl(HomePage.ENDAVA_URL);
		homePage.assertPageTitle(HomePage.ENDAVA_TITLE);
		Utils.webDriverWaitVisibility(homePage.driver, homePage.getContactButtons());
		menuPage = homePage.openMenu();
		Utils.webDriverWaitVisibility(menuPage.driver, menuPage.getNavigationList());
		investorsPage = menuPage.clickToGetPage(InvestorsPage.class, menuPage.getInvestorsMenuItem());
		investorsPage.assertPageUrl(InvestorsPage.INVESTORS_URL);		
		investorsPage.selectElement(investorsPage.getSearch());
		investorsPage.fillSearchBox("blahblah");
		investorsPage.selectElement(investorsPage.getSubmitButton());
		Assert.assertEquals(
				investorsPage.getSearchResult(investorsPage.driver.findElement(investorsPage.getSearchResultElement())),
				investorsPage.getSearchResult(), "Wrong search result");
		log.info("testInvestorsSearch() - test passed");
	}
	
	@AfterMethod
	public void onTestFailure(ITestResult testResult) {
		investorsPage.ifFailed(testResult);
	}

	@AfterTest
	public void tearDown() {
		homePage.quit();
		log.info("Closing browser");
	}
}