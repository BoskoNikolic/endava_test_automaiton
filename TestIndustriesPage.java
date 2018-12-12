package com.endava;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.endava.pages.CareersPage;
import com.endava.pages.HomePage;
import com.endava.pages.IndustriesPage;
import com.endava.pages.MenuPage;
import com.endava.pages.SuccessStoriesPage;
import com.endava.util.Utils;

public class TestIndustriesPage {
	private HomePage homePage;
	private MenuPage menuPage;
	private IndustriesPage industriesPage;	
	private static Logger log = Logger.getLogger(TestIndustriesPage.class);
	

	@BeforeTest
	@Parameters({ "browser" })
	public void setUp(String browser) {
		homePage = Utils.setUpWebBrowser(browser);
		log.info("Setting up chosen browser: " + browser);
	}

	/**
	 * Test validates URL and that following sections are present on page - Finance, Insurance And Healthcare, RETAIL AND CONSUMER GOODS, Telecommunications, media, and technology.
	 * 
	 * @author andrej.marin
	 */
	@Test
	public void testIndustriesPageUrlAndTitle() {
		homePage.open();
		homePage.assertPageUrl(HomePage.ENDAVA_URL); 
		homePage.assertPageTitle(HomePage.ENDAVA_TITLE);		
		Utils.webDriverWaitVisibility(homePage.driver, homePage.getContactButtons());
		menuPage = homePage.openMenu();
		Utils.webDriverWaitVisibility(menuPage.driver, menuPage.getNavigationList());		
		industriesPage = menuPage.clickToGetPage(IndustriesPage.class, menuPage.getIndustriesPageMenuItem());
		industriesPage.assertPageUrl(IndustriesPage.INDUSTRIES_URL);
		industriesPage.assertPageTitle(IndustriesPage.INDUSTRIES_TITLE);
		log.info("testIndustriesPageUrlAndTitle() Successfully validated Industry Page url and title");
		//Utils.webDriverWaitVisibility(menuPage.driver, menuPage.getNavigationList());
		
		Utils.webDriverWaitVisibility(industriesPage.driver, industriesPage.getFinanceHeading());		
		Assert.assertTrue(industriesPage.getTextFromElement(industriesPage.getFinanceHeading()).toUpperCase().contains(("Finance").toUpperCase()), "Text \"Finance\" not found!");
		//industriesPage.assertPageUrl(IndustriesPage.FINANCE_TITLE);
		
		Utils.webDriverWaitVisibility(industriesPage.driver, industriesPage.getInsuranceAndHealtcareHeading());		
		Assert.assertTrue(industriesPage.getTextFromElement(industriesPage.getInsuranceAndHealtcareHeading()).toUpperCase().contains(("Insurance and healthcare").toUpperCase()), "Text \"Insurance and healthcare\" not found!");
		//industriesPage.assertPageUrl(IndustriesPage.INSURANCE_AND_HEALTHCARE_TITLE);
		
		Utils.webDriverWaitVisibility(industriesPage.driver, industriesPage.getReatailAndConsumerGoodsHeading());		
		Assert.assertTrue(industriesPage.getTextFromElement(industriesPage.getReatailAndConsumerGoodsHeading()).toUpperCase().contains(("Retail and consumer goods").toUpperCase()), "Text \"Retail and consumer goods\" not found!");
		//industriesPage.assertPageUrl(IndustriesPage.REATAIL_AND_CONSUMERS_GOODS_TITLE);
		
		Utils.webDriverWaitVisibility(industriesPage.driver, industriesPage.getTelecomunicationsMediaTechnologyHeading());		
		Assert.assertTrue(industriesPage.getTextFromElement(industriesPage.getTelecomunicationsMediaTechnologyHeading()).toUpperCase().contains(("Telecommunications, media, and technology").toUpperCase()), "Text \"Telecommunications, media, and technology\" not found!");
		//industriesPage.assertPageUrl(IndustriesPage.TELECOMUNICATIONS_MEDIA_AND_TECHNOLOGY_TITLE);
		log.info("testIndustriesPageUrlAndTitle() Successfully validated that four sections are presented.");
	}
	
	@AfterTest
	public void tearDown() {
		homePage.quit();    
	}
}
