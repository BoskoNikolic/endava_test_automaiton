package com.endava.pages;

import org.apache.log4j.Logger;

/** 
 * @author jelena.corak
 */
import org.openqa.selenium.WebDriver;

public class ServicesPage extends BasePage {
	
	public static final String SERVICES_URL = ENDAVA_URL_EN + "/Services";
	public static final String SERVICES_TITLE = "Services";
	private static Logger log = Logger.getLogger(ServicesPage.class);

	public ServicesPage(WebDriver driver) {
		super(driver);
	}
}
