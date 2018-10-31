package com.endava.util;

import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;

import com.endava.pages.AboutPage;
import com.endava.pages.AgilePage;
import com.endava.pages.AutomationPage;
import com.endava.pages.CareersPage;
import com.endava.pages.ContactPage;
import com.endava.pages.DigitalPage;
import com.endava.pages.IndustriesPage;
import com.endava.pages.ServicesPage;
import com.endava.pages.SuccessStoriesPage;

/**
 * @author jelena.corak
 */

public class MenuDataProvider {
	@DataProvider(name = "MenuDataProvider")
	public static Object[][] getDataFromDataprovider() {
		Object[][] menuDataArray = new Object[9][3];

		for (int i = 0; i < menuDataArray.length; i++) {
			if (i < 4)
				menuDataArray[i][0] = By.xpath(String.format("//*[@id=\"mCSB_1_container\"]/div[1]/nav/ul/li[%d]/a", i+1));
			else
				menuDataArray[i][0] = By.xpath(String.format("//*[@id=\"mCSB_1_container\"]/div[1]/nav/ul/li[%d]/a", i+2));
		}

		menuDataArray[0][1] = DigitalPage.DIGITAL_URL;
		menuDataArray[1][1] = AgilePage.AGILE_URL;
		menuDataArray[2][1] = AutomationPage.ENDAVA_AUTOMATION_URL;
		menuDataArray[3][1] = ServicesPage.SERVICES_URL;
		menuDataArray[4][1] = IndustriesPage.INDUSTRIES_URL;
		menuDataArray[5][1] = SuccessStoriesPage.SUCCESS_STORIES_URL;
		menuDataArray[6][1] = AboutPage.ABOUT_URL;
		menuDataArray[7][1] = CareersPage.CAREERS_URL;
		menuDataArray[8][1] = ContactPage.CONTACT_URL;

		menuDataArray[0][2] = DigitalPage.DIGITAL_TITLE;
		menuDataArray[1][2] = AgilePage.AGILE_TITLE;
		menuDataArray[2][2] = AutomationPage.ENDAVA_AUTOMATION_TITLE;
		menuDataArray[3][2] = ServicesPage.SERVICES_TITLE;
		menuDataArray[4][2] = IndustriesPage.INDUSTRIES_TITLE;
		menuDataArray[5][2] = SuccessStoriesPage.SUCCESS_STORIES_TITLE;
		menuDataArray[6][2] = AboutPage.ABOUT_TITLE;
		menuDataArray[7][2] = CareersPage.CAREERS_TITLE;
		menuDataArray[8][2] = ContactPage.CONTACT_TITLE;

		return menuDataArray;
	}
}
