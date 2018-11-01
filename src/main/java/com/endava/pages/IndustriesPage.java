package com.endava.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

/** 
 * @author jelena.corak
 */

public class IndustriesPage extends BasePage {	
	
	public static final String INDUSTRIES_URL = ENDAVA_URL_EN + "/Industries";
	public static final String INDUSTRIES_TITLE = "Industries";
	private static Logger log = Logger.getLogger(IndustriesPage.class);

	public IndustriesPage(WebDriver driver) {
		super(driver);
	}
}
