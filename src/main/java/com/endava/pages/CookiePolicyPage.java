package com.endava.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

/**
 * @author jelena.corak
 */

public class CookiePolicyPage extends BasePage {
	
	public static final String COOKIE_POLICY_URL = ENDAVA_URL_EN + "/Cookie-Policy";
	public static final String COOKIE_POLICY_TITLE = "Cookie Policy";	
	private static Logger log = Logger.getLogger(CookiePolicyPage.class);

	public CookiePolicyPage(WebDriver driver) {
		super(driver);
	}
}
