package com.endava;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.endava.pages.BasePage;
import com.endava.pages.ContactPage;
import com.endava.pages.HomePage;
import com.endava.pages.MenuPage;
import com.endava.pages.CookiePolicyPage; 
import com.endava.util.Utils;

/**
 * @author jana.djordjevic@endava.com
 */
public class TestHomePage {

	private HomePage homePage;
	private MenuPage menuPage;	
	private static Logger log = Logger.getLogger(TestHomePage.class);

	@BeforeTest
	@Parameters({ "browser" })
	public void setUp(String browser) {
		homePage = Utils.setUpWebBrowser(browser);
		log.info("setUp()");
	}

	@Test(priority = 1)
	public void testHomePageIsOpened() {
		homePage.open();
		Utils.webDriverWaitVisibility(homePage.driver, homePage.getContactButtons());
		homePage.assertPageUrl(HomePage.ENDAVA_URL);
		homePage.assertPageTitle(HomePage.ENDAVA_TITLE);
		log.info("Home Page is opened.");
	}

	@Test(priority = 2)
	public void testOpenMenu() {
		homePage.open();
		Utils.webDriverWaitVisibility(homePage.driver, homePage.getContactButtons());
		homePage.clickOnDownArrow();
		Assert.assertTrue(homePage.isSolutionMenusVisible(), "Solution menus are not visible.");
		menuPage = homePage.openMenu();
		Utils.webDriverWaitVisibility(menuPage.driver, menuPage.getNavigationList());
		menuPage.assertPageUrl(HomePage.ENDAVA_URL);
		log.info("Menu Page is opened.");

	}

	/**
	 * @author Vladimir Krekic At home page chooses 'Deutsch' language. Verifies that language is changed: check that
	 *         url is https://www.endava.com/de-DE and then goes at bottom page and checks if copyright message has
	 *         expected german text "Alle Rechte vorbehalten" (instead of 'All rights reserved'). Changes language back
	 *         to 'English'. Verifies that language is changed : url should be https://www.endava.com/en and then goes
	 *         at bottom page and check if copyright message has expected English text 'All rights reserved'.
	 */
	@Test(dependsOnMethods = { "testOpenMenu" })
	public void testLanguageMenu() {
		homePage.open();
		homePage.assertPageUrl(HomePage.ENDAVA_URL);		
		homePage.assertPageTitle(HomePage.ENDAVA_TITLE);
		Utils.webDriverWaitVisibility(homePage.driver, homePage.getLanguage());
		homePage.selectElement(homePage.driver.findElement(homePage.getLanguage()));
		Utils.webDriverWaitVisibility(homePage.driver, homePage.getDeutschLanguage());
		homePage.selectElement(homePage.driver.findElement(homePage.getDeutschLanguage()));
		homePage.assertPageUrl(HomePage.ENDAVA_URL_DE);		
		Assert.assertTrue(homePage.driver.findElement(homePage.getCopyRightsMessage()).getText()
				.contains("Alle Rechte vorbehalten"), "DE Copy Rights message does not mach");
		Utils.webDriverWaitVisibility(homePage.driver, homePage.getLanguage());
		homePage.selectElement(homePage.driver.findElement(homePage.getLanguage()));
		Utils.webDriverWaitVisibility(homePage.driver, homePage.getEnglishLanguage());
		homePage.selectElement(homePage.driver.findElement(homePage.getEnglishLanguage()));
		homePage.assertPageUrl(HomePage.ENDAVA_URL_EN);		
		Assert.assertTrue(
				homePage.driver.findElement(homePage.getCopyRightsMessage()).getText().contains(" All rights reserved"),
				"EN Copy Rights message does not mach");
	}

	/**
	 * Test validates that click on the phone icon is a link to the Contact page.
	 *
	 * @author jelena.corak
	 */
	@Test(priority = 3)
	public void testPhoneIconLink() {
		homePage.open();
		Utils.webDriverWaitVisibility(homePage.driver, homePage.getContactButtons());
		homePage.assertPageTitle(HomePage.ENDAVA_TITLE);
		homePage.assertPageUrl(HomePage.ENDAVA_URL);
		homePage.directClickOnElement(homePage.getPhoneIcon());		
		homePage.assertPageUrl(ContactPage.CONTACT_URL);
		homePage.assertPageTitle(ContactPage.CONTACT_TITLE);
		log.info("testPhoneIconLink(): VALIDATION SUCCESSFUL! Phone icon link is a link to Contacts page.");
	}

	/**
	 * Test validates that links of social media icons are correct.
	 * 
	 * @author jelena.corak
	 * 
	 */
	@Test(priority = 4)
	public void testSocialMediaIconsLinks() {
		homePage.open();
		homePage.assertPageTitle(HomePage.ENDAVA_TITLE);
		homePage.assertPageUrl(HomePage.ENDAVA_URL);
		Utils.webDriverWaitVisibility(homePage.driver, homePage.getSocialMediaIcons());
		Assert.assertEquals(5, homePage.getSocialMediaIconList().size(),
				"Not all social media icons are visible on the home page.");
		log.info("testSocialMediaIconCount(): VALIDATION SUCCESSFUL! All icons are visible.");
		for (int i = 0; i < homePage.getSocialMediaIconList().size(); i++)
			BasePage.assertElementLink(homePage.getSocialMediaIconList().get(i),
					homePage.getListOfSocialMediaUrls().get(i));
		log.info("testSocialMediaIconsLinks(): VALIDATION SUCCESSFUL! All icons have correct links.");
	}
	
	/**
	 * Validates text in cookies policy message. Clicks on "Learn More" and validates that user is taken to Cookie
	 * Policy page.
	 * 
	 * @author jelena.corak
	 * 
	 */
	@Test(priority = 5)
	public void testCookiePolicy() {
		homePage.open();
		Utils.webDriverWaitVisibility(homePage.driver, homePage.getContactButtons());
		homePage.assertPageUrl(HomePage.ENDAVA_URL);
		homePage.assertPageTitle(HomePage.ENDAVA_TITLE);
		homePage.validateCookiesPolicyText();			
		homePage.clickOnElement(homePage.getCookiesLearnMore());		
		homePage.assertPageUrl(CookiePolicyPage.COOKIE_POLICY_URL);
		homePage.assertPageTitle(CookiePolicyPage.COOKIE_POLICY_TITLE);
		log.info("VALIDATION SUCCESSFUL! Cookie text is correct and click on Learn More takes to Cookies Policy page.");
	}
	
	/**
	 * After text in cookies policy message has been validated, closes the message and validates that
	 * it is not present anymore.
	 * 
	 * @author jelena.corak
	 * 
	 */
	@Test(dependsOnMethods = { "testCookiePolicy" })
	public void testCookiePolicyVisibility() {
		homePage.open();
		Utils.webDriverWaitVisibility(homePage.driver, homePage.getContactButtons());
		homePage.assertPageUrl(HomePage.ENDAVA_URL);
		homePage.assertPageTitle(HomePage.ENDAVA_TITLE);
		Utils.webDriverWaitVisibility(homePage.driver, homePage.getCookiesCloseMessage());
		homePage.clickOnElement(homePage.getCookiesCloseMessage());	
		Utils.webDriverWaitInvisibility(homePage.driver, homePage.getCookiesPolicyMessage());		
		Assert.assertTrue(homePage.getElement(homePage.getCookiesPolicyMessage()) == null, "Cookie Policy message is still present.");
		log.info("VALIDATION SUCCESSFUL! Cookie policy message has been removed.");
	}
  
	@AfterMethod
	public void onTestFailure(ITestResult testResult) {
		homePage.ifFailed(testResult);
	}

	@AfterClass
	public void tearDown() {
		homePage.quit();
		log.info("tearDown()");
	}
}
