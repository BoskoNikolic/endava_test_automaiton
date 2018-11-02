package com.endava;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.endava.pages.HomePage;
import com.endava.pages.MenuPage;
import com.endava.util.Utils;
import resources.dataProviders.MenuDataProvider;

/**
 * @author jelena.corak
 */
public class TestMenuPage {

	private HomePage homePage;
	private MenuPage menuPage;
	private static Logger log = Logger.getLogger(TestMenuPage.class);

	/**
	 * @author jelena.corak
	 * @param browser web browser defined in testng.xml
	 */
	@BeforeMethod
	@Parameters({ "browser" })
	public void setUp(String browser) {
		homePage = Utils.setUpWebBrowser(browser);
		log.info("setUp()");
	}

	/**
	 * Test validates that Endava logo is a link to the home page in all Menu pages, except Investors page.
	 * 
	 * @author jelena.corak	  
	 */

	@Test(dataProvider = "MenuDataProvider", dataProviderClass = MenuDataProvider.class)
	public void testIsEndavaLogoLinkToHomePageInAllMenuPages(By element, String url, String title) {
		homePage.open();
		Utils.webDriverWaitVisibility(homePage.driver, homePage.getContactButtons());
		homePage.assertPageUrl(HomePage.ENDAVA_URL);
		homePage.assertPageTitle(HomePage.ENDAVA_TITLE);
		menuPage = homePage.openMenu();
		Utils.webDriverWait(menuPage.driver, menuPage.getNavigationList());
		menuPage.scrollIntoView(element);
		String elementText = menuPage.getTextFromElement(element);
		menuPage.clickOnElement(element);
		menuPage.assertPageUrl(url);
		menuPage.assertPageTitle(title);
		menuPage.clickOnElement(menuPage.getLogo());
		menuPage.assertPageUrl(HomePage.ENDAVA_URL_EN);
		menuPage.assertPageTitle(HomePage.ENDAVA_TITLE);		
		log.info("VALIDATION SUCCESSFUL: Endava logo is link to home page on " + elementText + " page.");		
	}

	@AfterMethod
	public void onTestFailure(ITestResult testResult) {
		menuPage.ifFailed(testResult);
	}

	@AfterMethod
	public void tearDown() {
		homePage.quit();
		log.info("tearDown()");
	}
}
