package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class SearchPageObject extends MainPageObject {

    private static final String
        SEARCH_INIT_ELEMENT = "xpath://*[contains(@text, 'Search Wikipedia')]",
        SEARCH_INPUT = "xpath://*[contains(@text, 'Search…')]",
        SEARCH_CANCEL_BUTTON = "id:org.wikipedia:id/search_close_btn",
        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://*[contains(@text, '{SUBSTRING}')]",
        SEARCH_RESULT_ELEMENT = "xpath://*[@resource-id= 'org.wikipedia:id/search_results_list']/*[@resource-id= 'org.wikipedia:id/page_list_item_container']",
        SEARCH_EMPTY_MESSAGE= "id:org.wikipedia:id/search_empty_message";

    public SearchPageObject(AppiumDriver driver)
    {
        super(driver);
    }

    /* TEMPLATE METHODS */
    private static String getResultSearchElement(String substring)
    {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}",substring);
    }
    /* TEMPLATE METHODS */

    public void initSearchInput() {
        this.waitForElementAndClick(SEARCH_INIT_ELEMENT, "Cannot find and click search init element", 5);
        this.waitForElementPresents(SEARCH_INIT_ELEMENT, "Cannot find search input after clicking search init element", 5);
    }

    public void waitForCancelButtonToAppear()
    {
        this.waitForElementPresents(SEARCH_CANCEL_BUTTON, "Cannot find search cancel button", 5);
    }

    public void waitForCancelButtonToDisappear()
    {
        this.waitForElementNotPresent(SEARCH_CANCEL_BUTTON, "Search cancel button is still present", 5);
    }

    public void clickCancelSearch()
    {
        waitForElementAndClick(SEARCH_CANCEL_BUTTON, "Can not find and click search cancel button", 5);
    }

    public void typeSearchLine(String search_line)
    {
        this.waitForElementAndSendKeys(SEARCH_INPUT, search_line, "Can not find and type search input", 15);

    }
    public void waitForSearchResult(String substring)
    {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresents(search_result_xpath, "Can not find search result with substring" + substring, 5);
    }

    public void waitForNoSearchResults()
    {
        this.waitForElementNotPresent(SEARCH_RESULT_ELEMENT, "Search results are still present", 5);
    }

    public void clickByArticleWithSubString(String substring)
    {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(search_result_xpath, "Cannot find and click search result with substring" + substring, 10);
    }

    public int getAmountOfFoundArticles()
    {
        this.waitForElementPresents(
                SEARCH_RESULT_ELEMENT,
                "Cannot find anything by the request",
                15
        );
        return this.getAmountOfElements(SEARCH_RESULT_ELEMENT);
    }
    public void waitForEmptyResultsLable()
    {
        this.waitForElementPresents(SEARCH_EMPTY_MESSAGE, "search results are still present", 5);

    }

}
