package com.endava.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FirstStoryPage extends BasePage {
	public static final String FIRST_STORY_TITLE = "Supporting a social campaign through a versatile tapestry app";
	public static final String FIRST_STORY_URL = "https://www.endava.com/en/Success-Stories/Retail-Enhancing-a-Social-Awareness-Campaign-with-a-new-Interaction-Model";
	private By firstStoryHeading = By.xpath("//*[@id=\"hero-contentcarousel\"]/div[1]/div/div/div/figure/figcaption/div/h2");
	private static Logger log = Logger.getLogger(FirstStoryPage.class);

	public FirstStoryPage(WebDriver driver) {
		super(driver);
	}
	
	/**
	 * @author jelena.corak
	 * @return By first story page heading search context
	 */
	public By getFirstStoryHeading() {
		return firstStoryHeading;
	}
}
