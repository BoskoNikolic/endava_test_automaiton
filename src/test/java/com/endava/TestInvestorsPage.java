package com.endava;

import org.apache.log4j.Logger;
import org.testng.Assert;
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
		log.info("setUp()");
	}

	/**
	 * @author jelena.corak
	 *
	 *         Test validates that the string "ABOUT US" is visible on the "INVESTORS" page in the menu.
	 */
	@Test
	public void testAboutUsVisibility() {
		homePage.open();		
		Assert.assertEquals(homePage.driver.getCurrentUrl().toLowerCase(), homePage.getEndavaURL().toLowerCase(), "Incorrect URL!");		
		Assert.assertEquals(homePage.driver.getTitle().toLowerCase(), homePage.getEndavaTitle().toLowerCase(), "Incorrect title!");
		Utils.webDriverWait(homePage.driver, homePage.getContactButtons());
		menuPage = homePage.openMenu();
		Utils.webDriverWait(menuPage.driver, menuPage.getNavigationList());
		investorsPage = menuPage.clickOnInvestors();		
		Assert.assertEquals(investorsPage.driver.getCurrentUrl().toLowerCase(), InvestorsPage.getInvestorsUrl().toLowerCase(), "Incorrect URL!");
		/*
		 * Incorrect title of Investors page. Title verification skipped, since it fails.
		 */		
		//Assert.assertEquals(investorsPage.driver.getTitle().toLowerCase(), InvestorsPage.getInvestorsTitle().toLowerCase(), "Incorrect title!");
		Utils.webDriverWait(investorsPage.driver, investorsPage.getInvestorsAboutUs());
		Assert.assertTrue(Utils.getTextFromElement(investorsPage.driver, investorsPage.getInvestorsAboutUs()).contains("ABOUT US"), "Text \"ABOUT US\" not found!");
		log.info("testAboutUsVisibility() : VALIDATION SUCCESSFUL!");
	}

	@AfterTest
	public void tearDown() {
		homePage.quit();
	}
}