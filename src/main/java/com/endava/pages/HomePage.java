package com.endava.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.testng.Assert;

/**
 * @author jana.djordjevic@endava.com
 */
public class HomePage extends BasePage {

	public static final String ENDAVA_URL = ENDAVA_WEBSITE_URL + "/";
	public static final String ENDAVA_TITLE = "Endava";	
	private By contactButtons = By.id("contact-buttons");
	private By burgerMenu = By.id("menu-toggle");
	private By solutionMenus = By.className("proposition-section");
	private By centerScroll = By.className("fe_downarrow");
	private By agileItem = By.xpath("//*[@id=\"mCSB_1_container\"]/div[1]/nav/ul/li[2]/a");
	private By language = By.xpath("//*[@id=\"selected-lang\"]");
	private By englishLanguage = By.xpath("/html/body/header/div/div[1]/div[2]/div/nav/div/ul/li[2]/a");
	private By deutschLanguage = By.xpath("/html/body/header/div/div[1]/div[2]/div/nav/div/ul/li[1]/a");
	private By copyRightsMessage = By.xpath("/html/body/footer/section[2]/div/div/div[3]");
	private By about = By.xpath("//*[@id=\"mCSB_1_container\"]/div[1]/nav/ul/li[8]/a");
	private By contact = By.xpath("//*[@id=\"mCSB_1_container\"]/div[1]/nav/ul/li[10]/a");
	private By investors = By.xpath("//*[@id=\"mCSB_1_container\"]/div[1]/nav/ul/li[5]/a");
	private By phoneIcon = By.className("fe_phone");
	private By socialMediaIcons = By.cssSelector("div.social:nth-child(1) > ul:nth-child(1)");
	private By cookiesPolicyMessage = By.xpath("//*[@id=\"homePage\"]/div[7]");
	private By cookiesLearnMore = By.xpath("//*[@id=\"homePage\"]/div[7]/div/div/div[2]/div/div[1]/p/a");	
	private static Logger log = Logger.getLogger(HomePage.class);

	public HomePage(WebDriver driver) {
		super(driver);
	}

	public void open() {
		driver.get(ENDAVA_URL);
		driver.manage().window().maximize();
		log.debug("Opens " + ENDAVA_URL);
	}

	/**
	 * Opens AgilePage and instantiate AgilePage object if Agile item is present on "burger" menu
	 *
	 * @return AgilePage
	 * @author Vladimir Krekic
	 */
	public AgilePage openAgilePage() {
		if (selectElement(driver.findElement(this.agileItem))) {
			log.debug("AgilePage opened and instantiated");
			return new AgilePage(driver);
		} else {
			log.debug("Agile item on \"burger\" menu is not present");
			return null;
		}
	}

	public MenuPage openMenu() {
		driver.findElement(this.burgerMenu).click();
		log.debug("Finds burger menu and clicks on it");
		return new MenuPage(driver);
	}

	/**
	 * finds the down arrow element and clicks on it
	 *
	 * @author Goran.Kukolj
	 */
	public void clickOnDownArrow() {
		driver.findElement(this.centerScroll).click();
		log.debug("Finds the down arrow element and clicks on it");
	}

	/**
	 * @return true or false depending on the visibility of solution menus
	 * @author Goran.Kukolj
	 */
	public boolean isSolutionMenusVisible() {
		log.debug("Checks if solution menus are visible");
		return driver.findElement(solutionMenus).isDisplayed();
	}
	
	/**
	 * @return contact button element
	 * @author Goran.Kukolj
	 */
	public By getContactButtons() {
		return contactButtons;
	}

	/**
	 * @return By language menu element on HomePage
	 * @author Vladimir Krekic
	 */
	public By getLanguage() {
		return language;
	}

	/**
	 * @return By Endava English element in language menu
	 * @author Vladimir Krekic
	 */
	public By getEnglishLanguage() {
		return englishLanguage;
	}

	/**
	 * @return By Endava Deutsch element in language menu
	 * @author Vladimir Krekic
	 */
	public By getDeutschLanguage() {
		return deutschLanguage;
	}

	/**
	 * @return By Copy Rights text on HomePage
	 * @author Vladimir Krekic
	 */
	public By getCopyRightsMessage() {
		return copyRightsMessage;
	}

	/**
	 * Opens ContactPage and instantiate ContactPage object if "Contacts" item is present on "burger" menu
	 *
	 * @return ContactPage
	 * @author Vladimir Krekic
	 */
	public ContactPage openContactsPage() {
		if (selectElement(driver.findElement(this.contact))) {
			log.debug("ContactPage opened and instantiated");
			return new ContactPage(driver);
		} else {
			log.debug("Contacts item on \"burger\" menu is not present");
			return null;
		}
	}

	/**
	 * Opens AboutPage and instantiate AboutPage object if About item is present on "burger" menu
	 *
	 * @return AboutPage
	 * @author Vladimir Krekic
	 */
	public AboutPage openAboutPage() {
		if (selectElement(driver.findElement(this.about))) {
			log.debug("AboutPage opened and instantiated");
			return new AboutPage(driver);
		} else {
			log.debug("About item on \"burger\" menu is not present");
			return null;
		}
	}

	/**
	 * @return By search context of the phone icon
	 * @author jelena.corak
	 */
	public By getPhoneIcon() {
		return phoneIcon;
	}
	
	/**
	 * @author jelena.corak
	 * @return By search context of cookies policy message element
	 */
	public By getCookiesPolicyMessage() {
		return cookiesPolicyMessage;
	}

	/**
	 * @author jelena.corak
	 * @return By search context of Learn More link element in the cookies policy message.
	 */
	public By getCookiesLearnMore() {
		return cookiesLearnMore;
	}

	/**
	 * @author jelena.corak
	 * @return By search context of social media icon list
	 */
	public By getSocialMediaIcons() {
		return socialMediaIcons;
	}

	/**
	 * Opens InvestorsPage and instantiate InvestorsPage object if "Investors" item is present on "burger" menu
	 *
	 * @return InvestorsPage
	 * @author Vladimir Krekic
	 */
	public InvestorsPage openInvestorsPage() {
		if (selectElement(driver.findElement(this.investors))) {
			log.debug("InvestorsPage opened and instantiated");
			return new InvestorsPage(driver);
		} else {
			log.debug("Investors item on \"burger\" menu is not present");
			return null;
		}
	}

	/**
	 * Returns String values of expected social media URLs.
	 * 
	 * @author jelena.corak
	 * @return List<String> social media URL list
	 */
	public List<String> getListOfSocialMediaUrls() {
		List<String> listOfLinks = new ArrayList<>();
		listOfLinks.add("https://www.facebook.com/endava");
		listOfLinks.add("https://twitter.com/endava");
		listOfLinks.add("https://www.linkedin.com/company/endava");
		listOfLinks.add("https://www.instagram.com/endava/");
		listOfLinks.add("https://plus.google.com/u/0/111956919197222464721/posts?_ga=1.55764843.114380448.1443786751");
		return listOfLinks;
	}

	/**
	 * Returns list of social media icon elements with corresponding links.
	 * 
	 * @author jelena.corak
	 * @return List<WebElement> social media icon list
	 */
	public List<WebElement> getSocialMediaIconList() {
		return driver.findElement(By.cssSelector("div.social:nth-child(1) > ul:nth-child(1)"))
				.findElements(By.tagName("li")).stream().map(e -> e.findElement(By.tagName("a")))
				.collect(Collectors.toList());
	}
	
	/**
	 * Validates Cookies Policy element text.
	 * 
	 * @author jelena.corak
	 */
	public void validateCookiesPolicyText() {
		WebElement cookiesMessage = driver.findElement(cookiesPolicyMessage);
		if (cookiesMessage.isDisplayed()) {
			WebElement cookiesTextElement = driver
					.findElement(By.xpath("//*[@id=\"homePage\"]/div[7]/div/div/div[2]/div/div[1]/p/span"));
			Assert.assertEquals(cookiesTextElement.getText(),
					"By using this site you agree to the use of cookies for analytics, personalized content and ads.",
					"Text in the cookie policy message is not correct!");
		} else 			
			Assert.fail("Cookies policy message not present!");		
	}
}
