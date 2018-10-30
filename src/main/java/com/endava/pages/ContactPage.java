package com.endava.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * @author jelena.corak
 */

public class ContactPage extends BasePage {

    private static final String CONTACT_URL = getEndavaWebSiteUrlEn() + "/Contact";
    private static final String CONTACT_TITLE = "Contact Us";
    private By contactMenuItem = By.xpath(".//*[@id='mCSB_1_container']/div[1]/nav/ul/li[10]/a");
    private static Logger log = Logger.getLogger(ContactPage.class);
    private By servicesRadioButton = By.xpath("//*[@id=\"contact-form\"]/fieldset[1]/p[1]/label");
    private By joinRadioButton = By.xpath("//*[@id=\"contact-form\"]/fieldset[1]/p[2]/label");
    private By joinMessage = By.xpath("//*[@id=\"please-visit\"]");
    private static final String MESSAGE = "Please visit the Careers section on our website to apply for job openings." +
            " Please use the form below if you have another question or important message.";

    public ContactPage(WebDriver driver) {
        super(driver);
    }

    /**
     * @author jelena.corak
     * @return String Contact page URL
     */
    public String getContactUrl() {
        return CONTACT_URL;
    }

    /**
     * @author jelena.corak
     * @return String Contact page title
     */
    public String getContactTitle() {
        return CONTACT_TITLE;
    }

    /**
     * @author jelena.corak
     * @return By Contact menu item search context
     */
    public By getContactMenuItem() {
        return contactMenuItem;
    }

    /**
     * @return By Services radio button
     * @author Vladimir Krekic
     */
    public By getServicesRadioButton() {
        return servicesRadioButton;
    }

    /**
     * @return By Join Our Team radio button
     * @author Vladimir Krekic
     */
    public By getJoinRadioButton() {
        return joinRadioButton;
    }

    /**
     * @return By Join Our Team message
     * @author Vladimir Krekic
     */
    public By getJoinMessage() {
        return joinMessage;
    }

    /**
     * @return By Expected message after Join Our Team radio button
     * @author Vladimir Krekic
     */
    public String getMessage() {
        return MESSAGE;
    }
}
