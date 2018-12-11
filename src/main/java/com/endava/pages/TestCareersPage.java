package com.endava;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.endava.pages.CareersPage;
import com.endava.pages.HomePage;
import com.endava.pages.MenuPage;
import com.endava.pages.SuccessStoriesPage;
import com.endava.util.Utils;

public class TestCareersPage {
	private HomePage homePage;
	private MenuPage menuPage;
	private CareersPage careersPage;	
	private static Logger log = Logger.getLogger(TestInvestorsPage.class);

	@BeforeTest
	@Parameters({ "browser" })
	public void setUp(String browser) {
		homePage = Utils.setUpWebBrowser(browser);
		log.info("Setting up chosen browser: " + browser);
	}

	/**
	 * Test validates that heading text a top of the page contains string "Be More" and validates URL.
	 * 
	 * @author andrej.marin
	 */
	@Test
	public void testCareersPageUrlAndTitle() {
		homePage.open();
		homePage.assertPageUrl(HomePage.ENDAVA_URL); 
		homePage.assertPageTitle(HomePage.ENDAVA_TITLE);		
		Utils.webDriverWaitVisibility(homePage.driver, homePage.getContactButtons());
		menuPage = homePage.openMenu();
		Utils.webDriverWaitVisibility(menuPage.driver, menuPage.getNavigationList());		
		careersPage = menuPage.clickToGetPage(CareersPage.class, menuPage.getCareersPageMenuItem());
		careersPage.assertPageUrl(CareersPage.CAREERS_URL);
		careersPage.assertPageTitle(CareersPage.CAREERS_TITLE);
		log.info("testCareersPageUrlAndTitle() Successfully validated Careers Page url and title");
	}
	
	@AfterTest
	public void tearDown() {
		homePage.quit();    
	}
}
