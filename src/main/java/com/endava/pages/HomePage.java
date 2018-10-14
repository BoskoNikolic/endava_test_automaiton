package com.endava.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * @author jana.djordjevic@endava.com
 *
 */
public class HomePage extends BasePage {

	private final String ENDAVA_URL = "http://www.endava.com";

	public By contactButtons = By.id("contact-buttons");
	public By burgerMenu = By.id("menu-toggle");

	public HomePage(WebDriver driver) {
		super(driver);
	}

	public void open() {
		driver.get(ENDAVA_URL);
		driver.manage().window().maximize();
		Logger.getLogger(this.getClass()).debug(" method open()");
	}

	public MenuPage openMenu() {
		driver.findElement(this.burgerMenu).click();
		Logger.getLogger(this.getClass()).debug(" method openMenu()");
		return new MenuPage(driver);
	}

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		HomePage homePage = new HomePage(new ChromeDriver());
		homePage.open();
	}
}
