package com.endava.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

/** 
 * @author jelena.corak
 */

public class CareersPage extends BasePage {

	public static final String CAREERS_URL = "https://careers.endava.com/en";
	public static final String CAREERS_TITLE = "Be More";	
	private static Logger log = Logger.getLogger(CareersPage.class);
	
	public CareersPage(WebDriver driver) {
		super(driver);
	}	
}
