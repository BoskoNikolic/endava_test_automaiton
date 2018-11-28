package com.endava.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/** 
 * @author jelena.corak
 */

public class SuccessStoriesPage extends BasePage {		
	
	public static final String SUCCESS_STORIES_URL = ENDAVA_URL_EN + "/Success-Stories";
	public static final String SUCCESS_STORIES_TITLE = "Success Stories";	
	public static final String FIRST_STORY_TITLE = "Supporting a social campaign through a versatile tapestry app";
	public static final String FIRST_STORY_URL = ENDAVA_URL_EN + "/Success-Stories/Retail-Enhancing-a-Social-Awareness-Campaign-with-a-new-Interaction-Model";
	private By heading = By.xpath("//*[@id=\"hero-contentcarousel\"]/div[1]/div/div/div/figure/figcaption/div/h2");
	private By firstStoryHeading = By.xpath("//*[@id=\"hero-contentcarousel\"]/div[1]/div/div/div/figure/figcaption/div/h2");	
	private By firstStory = By.xpath("//a[contains(.,'Supporting a social campaign through a versatile tapestry app')]");//By.cssSelector("body > section:nth-child(8) > div > div > div:nth-child(1) > a");
	private By firstStoryText = By.xpath("//h2[contains(.,'Supporting a social campaign through a versatile tapestry app')]");
	private static Logger log = Logger.getLogger(SuccessStoriesPage.class);
	
	public SuccessStoriesPage(WebDriver driver) {
		super(driver);
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
	 * @return By first story page heading search context
	 */
	public By getFirstStoryHeading() {
		return firstStoryHeading;
	}

	/**
	 * @author jelena.corak
	 * @return By first story element search context
	 */
	public By getFirstStory() {
		return firstStory;
	}
	
	/**
	 * @author jelena.corak
	 * @return String first story heading text
	 */
	public By getFirstStoryText() {
		return firstStoryText;
	}
}
