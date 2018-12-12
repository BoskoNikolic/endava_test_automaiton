package com.endava.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/** 
 * @author andrej.marin
 */

public class IndustriesPage extends BasePage {	
	
	public static final String INDUSTRIES_URL = ENDAVA_URL_EN + "/Industries";
	public static final String INDUSTRIES_TITLE = "Industries";
	private static Logger log = Logger.getLogger(IndustriesPage.class);
	public static final String FINANCE_TITLE = "Finance";
	public static final String INSURANCE_AND_HEALTHCARE_TITLE = "Insurance And Healthcare";
	public static final String REATAIL_AND_CONSUMERS_GOODS_TITLE = "Retail and consumer goods";
	public static final String TELECOMUNICATIONS_MEDIA_AND_TECHNOLOGY_TITLE = "Telecommunications, media, and technology";
	
	private By financeHeading = By.xpath("//h2[contains(.,'Finance')]");
	private By insuranceAndHealtcareHeading = By.xpath("//h2[contains(.,'Insurance And Healthcare')]");
	private By reatailAndConsumerGoodsHeading = By.xpath("//h2[contains(.,'RETAIL AND CONSUMER GOODS')]");
	private By telecomunicationsMediaTechnologyHeading = By.xpath("//h2[contains(.,'Telecommunications, media, and technology')]");
	
	public By getFinanceHeading() {
		return financeHeading;
	}
	
	public By getInsuranceAndHealtcareHeading() {
		return insuranceAndHealtcareHeading;
	}
	
	public By getReatailAndConsumerGoodsHeading() {
		return reatailAndConsumerGoodsHeading;
	}
	
	public By getTelecomunicationsMediaTechnologyHeading() {
		return telecomunicationsMediaTechnologyHeading;
	}
	

	public IndustriesPage(WebDriver driver) {
		super(driver);
	}
}
