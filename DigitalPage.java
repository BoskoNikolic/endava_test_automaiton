package com.endava.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

/** 
 * @author jelena.corak
 */

public class DigitalPage extends BasePage {	
	
	public static final String DIGITAL_URL = ENDAVA_URL_EN + "/Digital";
	public static final String DIGITAL_TITLE = "Digital";	
	private static Logger log = Logger.getLogger(DigitalPage.class);
	
	public DigitalPage(WebDriver driver) {
		super(driver);		
	}
}
