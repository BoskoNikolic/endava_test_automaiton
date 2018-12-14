package com.endava;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.endava.pages.DigitalPage;
import com.endava.pages.HomePage;
import com.endava.pages.MenuPage;
import com.endava.util.Utils;

public class TestDigitalPage {
	private HomePage homePage;
	private MenuPage menuPage;
	private DigitalPage digitalPage;	
	private static Logger log = Logger.getLogger(TestDigitalPage.class);

	@BeforeTest
	@Parameters({ "browser" })
	public void setUp(String browser) {
		homePage = Utils.setUpWebBrowser(browser);
		log.info("Setting up chosen browser: " + browser);
	}

	/**
	 * Test validates that current URL is https://www.endava.com/en/Digital and that heading text on top of the page contains string "Be More".
	 * 
	 * @author andrej.marin
	 */
	@Test
	public void testDigitalPageUrlAndTitle() {
		homePage.open();
		homePage.assertPageUrl(HomePage.ENDAVA_URL); 
		homePage.assertPageTitle(HomePage.ENDAVA_TITLE);		
		Utils.webDriverWaitVisibility(homePage.driver, homePage.getContactButtons());
		menuPage = homePage.openMenu();
		Utils.webDriverWaitVisibility(menuPage.driver, menuPage.getNavigationList());		
		digitalPage = menuPage.clickToGetPage(DigitalPage.class, menuPage.getDigitalPageMenuItem());
		digitalPage.assertPageUrl(DigitalPage.DIGITAL_URL);
		digitalPage.assertPageTitle(DigitalPage.DIGITAL_TITLE);
		log.info("testDigitalPageUrlAndTitle() Successfully validated Digital Page url and title");
	}
	
	@AfterTest
	public void tearDown() {
		homePage.quit();    
	}
}
