package com.endava;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.endava.pages.ContactPage;
import com.endava.pages.HomePage;
import com.endava.pages.MenuPage;
import com.endava.util.Utils;

/**
 * @author Vladimir Krekic
 */

class TestContactPage {

	private static Logger log = Logger.getLogger(ContactPage.class);
	private HomePage homePage;
	private MenuPage menuPage;
	private ContactPage contactPage;

	@BeforeTest
	@Parameters({ "browser" })
	public void setUp(String browser) {
		homePage = Utils.setUpWebBrowser(browser);
		log.info("Seting up " + browser + " browser in TestContactPage");
	}

	/**
	 * @author Vladimir Krekic From Burger menu go to Contact page, validate new URL is correct, validate both radio buttons
	 *         ("Interested in our services?" and "Want to join our team?") are not selected, click on "Want to join our
	 *         team? " Radio button, validate it's selected Validate message "Please visit the Careers section on our
	 *         website to apply for job openings. Please use the form below if you have another question or important
	 *         message." appears.
	 */
	@Test
	public void testContactPage() {
		homePage.open();
		Utils.webDriverWaitVisibility(homePage.driver, homePage.getContactButtons());
		homePage.assertPageUrl(HomePage.ENDAVA_URL);
		homePage.assertPageTitle(HomePage.ENDAVA_TITLE);
		menuPage = homePage.openMenu();
		Utils.webDriverWaitVisibility(menuPage.driver, menuPage.getNavigationList());		
		contactPage = menuPage.clickToGetPage(ContactPage.class, menuPage.getContact());
		contactPage.assertPageUrl(ContactPage.CONTACT_URL);		
		contactPage.assertPageTitle(ContactPage.CONTACT_TITLE);
		Assert.assertFalse(contactPage.driver.findElement(contactPage.getServicesRadioButton()).isSelected(),
				"Element \"ServiceRadioButton\" selected");
		Assert.assertFalse(contactPage.driver.findElement(contactPage.getJoinRadioButton()).isSelected(), "Element \"JoinRadioButton\" selected");
		contactPage.selectElement(contactPage.driver.findElement(contactPage.getJoinRadioButton()));
		Assert.assertFalse(contactPage.getSearchResult(contactPage.driver.findElement(contactPage.getJoinMessage())).isEmpty(),
				"Element \"JoinRadioButton\" not selected");
		Assert.assertEquals(contactPage.getMessage(), contactPage.getSearchResult(contactPage.driver.findElement(contactPage.getJoinMessage())),
				"JoinMessage does not mach");
		log.debug("testContactPage() - test passed");
	}

	@AfterMethod
	public void onTestFailure(ITestResult testResult) {
		contactPage.ifFailed(testResult);
	}
	
	@AfterTest
	public void tearDown() {
		homePage.quit();
		log.info("Closing browser in TestContactPage");
	}
}
