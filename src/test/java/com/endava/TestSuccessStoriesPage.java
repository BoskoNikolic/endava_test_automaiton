package com.endava;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.endava.pages.FirstStoryPage;
import com.endava.pages.HomePage;
import com.endava.pages.MenuPage;
import com.endava.pages.SuccessStoriesPage;
import com.endava.util.Utils;

public class TestSuccessStoriesPage {
	private HomePage homePage;
	private MenuPage menuPage;
	private SuccessStoriesPage successStoriesPage;
	private FirstStoryPage firstStoryPage;
	private static Logger log = Logger.getLogger(TestInvestorsPage.class);

	@BeforeTest
	@Parameters({ "browser" })
	public void setUp(String browser) {
		homePage = Utils.setUpWebBrowser(browser);
		log.info("Setting up chosen browser: " + browser);
	}

	/**
	 * Test validates that heading text atop of the page contains string "SUCCESS STORIES".
	 * 
	 * @author jelena.corak
	 */
	@Test
	public void testFirstStoryHeading() {
		homePage.open();
		homePage.assertPageUrl(homePage.getEndavaURL()); 
		homePage.assertPageTitle(homePage.getEndavaTitle());		
		Utils.webDriverWait(homePage.driver, homePage.getContactButtons());
		menuPage = homePage.openMenu();
		Utils.webDriverWait(menuPage.driver, menuPage.getNavigationList());		
		successStoriesPage = menuPage.clickToGetPage(SuccessStoriesPage.class, menuPage.getSuccessStoriesMenuItem());
		successStoriesPage.assertPageUrl(SuccessStoriesPage.SUCCESS_STORIES_URL);
		successStoriesPage.assertPageTitle(SuccessStoriesPage.SUCCESS_STORIES_TITLE);
		Utils.webDriverWait(successStoriesPage.driver, successStoriesPage.getHeading());		
		Assert.assertTrue(successStoriesPage.getTextFromElement(successStoriesPage.getHeading()).toUpperCase().contains(("Success Stories").toUpperCase()), "Text \"Success Stories\" not found!");
		log.info("testHeading() : VALIDATION SUCCESSFUL! Success Stories page contains SUCCESS STORIES string.");
		Utils.webDriverWait(menuPage.driver, successStoriesPage.getFirstStory());	
		successStoriesPage.scrollIntoView(successStoriesPage.getFirstStory());		
		firstStoryPage = successStoriesPage.clickToGetPage(FirstStoryPage.class, successStoriesPage.getFirstStory());
		firstStoryPage.assertPageTitle(FirstStoryPage.FIRST_STORY_TITLE);
		firstStoryPage.assertPageUrl(FirstStoryPage.FIRST_STORY_URL);		
		Assert.assertTrue(firstStoryPage.getTextFromElement(firstStoryPage.getFirstStoryHeading()).toUpperCase()
				.equals(successStoriesPage.getFirstStoryText().toUpperCase()), 
				"Text in the first story title does not match the text on the first story page heading.");
		log.info("testFirstStoryHeading(): VALIDATION SUCCESSFUL! Click on the first story at Success Stories page takes to the first story page with a matching heading.");
	}
	
	@AfterTest
	public void tearDown() {
		homePage.quit();    
	}
}
