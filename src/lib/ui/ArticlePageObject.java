package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject
{
    private static final String
    TITLE = "org.wikipedia:id/view_page_title_text",
    FOOTER_ELEMENT = "//*[@text='View page in browser']",
    OPTIONS_BUTTON = "//android.widget.ImageView[@content-desc='More options']",
    OPTIONS_ADD_TO_MY_LIST_BUTTON = "//*[@text= 'Add to reading list']",
    ADD_TO_MY_LIST_OVERLAY = "org.wikipedia:id/onboarding_button",
    MY_LIST_NAME_INPUT = "org.wikipedia:id/text_input",
    MY_LIST_OK_BUTTON = "//*[@text='OK']",
    CLOSE_ARTICLE_BUTTON = "//android.widget.ImageButton[@class='android.widget.ImageButton']",
            NAME_OF_SAVED_FOLDER = "//*[contains(@text, '{NAME_OF_FOLDER}')]",
    TITLE_OF_ARTICLE_XPATH = "//*[@resource-id='org.wikipedia:id/view_page_header_container']/*[@resource-id='org.wikipedia:id/view_page_title_text']";


    public ArticlePageObject(AppiumDriver driver)
    {
        super(driver);
    }

    /* TEMPLATE METHODS */
    private static String getNameOfFolder(String name_of_folder)
    {
        return NAME_OF_SAVED_FOLDER.replace("{NAME_OF_FOLDER}", name_of_folder);
    }
    /* TEMPLATE METHODS */
    public WebElement waitForTitleElement()
    {
        return this.waitForElementPresents(By.id(TITLE), "Can not find article title on page!", 15);
    }
    public String getArticleTitle()
    {
        WebElement title_element = waitForTitleElement();
        return title_element.getAttribute("text");
    }
    public void swipeToFooter()
    {
        this.swipeUpToFindElement(
                By.xpath(FOOTER_ELEMENT),
                "Can not find the end of article",
                20
        );
    }

    public void addArticleToMyList(String name_of_folder)
    {
        this.waitForElementAndClick(
                By.xpath(OPTIONS_BUTTON),
                "cannot find button to open article options",
                5

        );
        this.waitForElementAndClick(
                By.xpath(OPTIONS_ADD_TO_MY_LIST_BUTTON),
                "cannot find Add to reading list",
                5
        );

        this.waitForElementAndClick(
                By.id(ADD_TO_MY_LIST_OVERLAY),
                "cannot find button GOT IT",
                5
        );
        this.waitForElementAndClear(
                By.id(MY_LIST_NAME_INPUT),
                "cannot find text input",
                5
        );
        this.waitForElementAndSendKeys(
                By.id(MY_LIST_NAME_INPUT),
                name_of_folder,
                "Cannot put text into articles folder input",
                5
        );
        this.waitForElementAndClick(
                By.xpath("//*[@text='OK']"),
                "Cannot press OK button",
                5
        );
    }
    public void closeArticle()
    {
        this.waitForElementAndClick(
                By.xpath(CLOSE_ARTICLE_BUTTON),
                "Cannot close article, cannot find X link",
                5);
    }

    public void addToFolderByName(String name_of_folder)
    {
        this.waitForElementAndClick(
                By.xpath(OPTIONS_BUTTON),
                "cannot find button to open article options",
                5

        );
        this.waitForElementAndClick(
                By.xpath(OPTIONS_ADD_TO_MY_LIST_BUTTON),
                "cannot find Add to reading list",
                5
        );
        String name_of_folder_xpath = getNameOfFolder(name_of_folder);
        this.waitForElementAndClick(By.xpath(name_of_folder_xpath), "there is no such folder", 5);

    }

    public void assertTitleOfArticlePresent() {

        int amount_of_title = getAmountOfElements(By.xpath(TITLE_OF_ARTICLE_XPATH));
        Assert.assertTrue("There is no title of article", amount_of_title > 0);

    }
}
