package com.endava;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.endava.pages.AgilePage;
import com.endava.pages.HomePage;
import com.endava.pages.MenuPage;
import com.endava.util.Utils;

/**
 * @author Vladimir Krekic
 */

class TestAgilePage {

	private HomePage homePage;
	private MenuPage menuPage;
	private AgilePage agilePage;
	private static Logger log = Logger.getLogger(TestAgilePage.class);

	@BeforeTest
	@Parameters({ "browser" })
	public void setUp(String browser) {
		homePage = Utils.setUpWebBrowser(browser);
		log.info("setUp()");
	}

	/**
	 * @author Vladimir Krekic Test validates AGILE menu item is active in DIGITAL - AGILE - AUTOMATION menu
	 */
	@Test(priority = 1)
	public void testAgileItemActiveInDAAMenu() {
		homePage.open();
		Utils.webDriverWaitVisibility(homePage.driver, homePage.getContactButtons());
		homePage.assertPageUrl(HomePage.ENDAVA_URL);
		homePage.assertPageTitle(HomePage.ENDAVA_TITLE);
		menuPage = homePage.openMenu();
		Utils.webDriverWaitVisibility(menuPage.driver, menuPage.getNavigationList());
		agilePage = menuPage.clickToGetPage(AgilePage.class, menuPage.getAgileItem());
		agilePage.assertPageUrl(AgilePage.AGILE_URL);		
		agilePage.assertPageTitle(AgilePage.AGILE_TITLE);
		Assert.assertEquals(agilePage.driver.findElement(agilePage.getAgileOnRibbonMenu()).getAttribute("class"), "active");
		log.debug("testAgileItemActiveInDAAMenu() - Test passed - AGILE menu item is active in DIGITAL - AGILE - AUTOMATION menu");
	}

	/**
	 * Test checks autofill with linkedin button, validates new window pops up, and validates error messages when invalid
	 * email is input
	 *
	 * @author Goran.Kukolj
	 */
	@Test(priority = 2)
	public void testAutofillWithLinkedin() {
		homePage.open();
		Utils.webDriverWaitVisibility(homePage.driver, homePage.getContactButtons());
		homePage.assertPageUrl(HomePage.ENDAVA_URL);
		homePage.assertPageTitle(HomePage.ENDAVA_TITLE);
		menuPage = homePage.openMenu();
		Utils.webDriverWaitVisibility(menuPage.driver, menuPage.getNavigationList());
		agilePage = menuPage.clickToGetPage(AgilePage.class, menuPage.getAgileItem());
		agilePage.assertPageTitle(AgilePage.AGILE_TITLE);
		agilePage.assertPageUrl(AgilePage.AGILE_URL);
		String agileWindow = agilePage.driver.getWindowHandle();
		agilePage.scrollToAutofillWithLinkedinButton();
		agilePage.clickOnAutofillWithLinkedinButton();
		Utils.switchControlToNewWindow(agilePage.driver);
		Utils.webDriverWaitVisibility(agilePage.driver, agilePage.getSignInToLinkedinMessage());
		Assert.assertTrue(agilePage.validateString(agilePage.driver, agilePage.getSignInToLinkedinMessage(), agilePage.getSignInToLinkedin()), "Strings for sign in are not the same");
		agilePage.assertPageTitle(agilePage.getPopUpWindowTitle());
		agilePage.enterEmailAddress();
		agilePage.clickOnAllowAccessButton();
		Assert.assertTrue(agilePage.validateString(agilePage.driver, agilePage.getEnterValidEmailErrorMessage(), agilePage.getEnterValidEmail()), "Strings for email are not the same");
		Assert.assertTrue(agilePage.validateString(agilePage.driver, agilePage.getEnterPasswordErrorMessage(), agilePage.getEnterPassword()), "Strings for password are not the same");
		Assert.assertTrue(agilePage.validateString(agilePage.driver, agilePage.getCorrectMarkedFieldsErrorMessage(), agilePage.getCorrectMarkedFields()), "Strings for marked fields are not the same");
		agilePage.assertPageUrl(agilePage.getPopUpWindowSubmitUrl());
		agilePage.clickOnCancelButton();
		agilePage.driver.switchTo().window(agileWindow);		
		agilePage.assertPageUrl(AgilePage.AGILE_URL);
		agilePage.assertPageTitle(AgilePage.AGILE_TITLE);
		log.info("Validation successful! Autofill functionality is as expected.");
	}
	
	@AfterMethod
	public void onTestFailure(ITestResult testResult) {
		agilePage.ifFailed(testResult);
	}

	@AfterClass
	public void tearDown() {
		homePage.quit();
	}
}
