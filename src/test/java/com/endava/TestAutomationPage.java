package com.endava;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.endava.pages.AutomationPage;
import com.endava.pages.HomePage;
import com.endava.pages.MenuPage;
import com.endava.util.Utils;

/**
 * @author Goran.Kukolj
 */
public class TestAutomationPage {

	private HomePage homePage;
	private MenuPage menuPage;
	private AutomationPage automationPage;
	private static Logger log = Logger.getLogger(TestAutomationPage.class);

	@BeforeMethod
	@Parameters({ "browser" })
	public void setUp(String browser) {
		homePage = Utils.setUpWebBrowser(browser);
		log.info("Sets up web browser");
	}

	/**
	 * Test validates that automation link on automation page is active, and checks if URL has changed
	 * 
	 * @author Goran.Kukolj
	 */
	@Test
	public void testAutomationMenuIsActive() {
		homePage.open();
		Utils.webDriverWaitVisibility(homePage.driver, homePage.getContactButtons());
		menuPage = homePage.openMenu();
		Utils.webDriverWaitVisibility(menuPage.driver, menuPage.getNavigationList());
		automationPage = menuPage.openAutomationPage();
		Utils.webDriverWaitVisibility(automationPage.driver, automationPage.getAutomationPageLink());
		automationPage.assertPageTitle(AutomationPage.ENDAVA_AUTOMATION_TITLE);
		Assert.assertTrue(automationPage.isAutomationPageLinkActive(), "Link is not active.");
		automationPage.assertPageUrl(AutomationPage.ENDAVA_AUTOMATION_URL);
	}
	
	@AfterMethod
	public void onTestFailure(ITestResult testResult) {
		automationPage.ifFailed(testResult);
	}

	@AfterMethod
	public void tearDown() {
		homePage.quit();
	}
}
