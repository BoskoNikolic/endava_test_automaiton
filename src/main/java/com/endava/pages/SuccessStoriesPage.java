package com.endava.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * 
 * @author jelena.corak
 *
 */

public class SuccessStoriesPage extends BasePage {		
	
	public static final String SUCCESS_STORIES_URL = getEndavaWebSiteUrlEn() + "/Success-Stories";
	public static final String SUCCESS_STORIES_TITLE = "Success Stories";	
	private By heading = By.xpath("//*[@id=\"hero-contentcarousel\"]/div[1]/div/div/div/figure/figcaption/div/h2");
	private By firstStory = By.cssSelector("body > section:nth-child(8) > div > div > div:nth-child(1) > a");
	private String firstStoryText;
	private static Logger log = Logger.getLogger(SuccessStoriesPage.class);
	
	public SuccessStoriesPage(WebDriver driver) {
		super(driver);
		scrollIntoView(firstStory);
		firstStoryText = getTextFromElement(firstStory);
	}
	
	/**
	 * @author jelena.corak
	 * @return By heading element search context
	 */
	public By getHeading() {
		return heading;
	}

	/**
	 * @author jelena.corak
	 * @return By first story element search context
	 */
	public By getFirstStory() {
		return firstStory;
	}

	public String getFirstStoryText() {
		return firstStoryText;
	}
}