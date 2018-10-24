package com.endava.pages;

import com.endava.util.Utils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @author Vladimir Krekic
 */

public class InvestorsPage extends BasePage {

    private static final String INVESTORS_URL = "https://investors.endava.com/home/default.aspx";
    private static final String INVESTORS_TITLE = "Endava - Home";
    private static final String SEARCH_RESULT = "No results found.";
    private WebElement search = driver.findElement(By.className("search-link"));
    private WebElement searchBox = driver.findElement(By.xpath("//*[@id=\"_ctrl0_ctl36_txtSearchInput\"]"));
    private WebElement submitButton = driver.findElement(By.xpath("//*[@id=\"_ctrl0_ctl36_btnSearch\"]"));
    private By searchResultElement = By.className("module_message");
    private static Logger log = Logger.getLogger(InvestorsPage.class);

    protected InvestorsPage(WebDriver driver) {
        super(driver);
    }

    /**
     * @author Vladimir Krekic
     * Finds searchBox and fills it with search text
     * @param searchText String to be searched for
     */
    public void fillSearchBox(String searchText){
        Utils.makeItVisible(searchBox);
        if(searchBox.isDisplayed()){
            searchBox.sendKeys(searchText);
            log.debug("Search Box filled with text: " + searchText);
        }else {
            log.debug("Search box not present - test failed");
            driver.quit();
        }
    }

    public WebElement getSearch() {
        return search;
    }

    public By getSearchResultElement() {
        return searchResultElement;
    }

    public WebElement getSubmitButton() {
        return submitButton;
    }

    public String getInvestorsUrl() {
        return INVESTORS_URL;
    }

    public String getInvestorsTitle() {
        return INVESTORS_TITLE;
    }

    public String getSearchResult() {
        return SEARCH_RESULT;
    }
}