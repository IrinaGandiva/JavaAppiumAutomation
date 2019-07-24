package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class MyListsPageObject extends MainPageObject{

    public static final String
    FODER_BY_NAME_TPL = "//*[@resource-id = 'org.wikipedia:id/item_title' and @text = '{FOLDER_NAME}']",
    ARTICLE_BY_TITLE_TPL = "//*[@text= '{TITLE}']";

    private static String getFolderXPathByName(String name_of_folder)
    {
        return FODER_BY_NAME_TPL.replace("{FOLDER_NAME}", name_of_folder);
    }

    private static String getSavedArticleXpathByTitle(String article_title)
    {
        return ARTICLE_BY_TITLE_TPL.replace("{TITLE}", article_title);
    }

    public MyListsPageObject(AppiumDriver driver)
    {
        super(driver);
    }

    public void openFolderByName(String name_of_folder) {

        String folder_name_xpath = getFolderXPathByName(name_of_folder);
        this.waitForElementAndClick(
                By.xpath(folder_name_xpath),
                "cannot find folder by name " + name_of_folder,
                5
        );
    }

    public void waitForArticleToDisappearByTitle(String article_title)
    {
        String article_xpath = getFolderXPathByName(article_title);

        this.waitForElementNotPresent(
                By.xpath(article_xpath),
                "Saved article still present with title" + article_title,
                15
        );
    }

    public void waitForArticleToAppearByTitle(String article_title)
    {
        String article_xpath = getSavedArticleXpathByTitle(article_title);

        this.waitForElementPresents(
                By.xpath(article_xpath),
                "Can not find saved article by title" + article_title,
                15
        );
    }

    public void swipeByArticleToDelete(String article_title){

        this.waitForArticleToAppearByTitle(article_title);

        String article_xpath = getSavedArticleXpathByTitle(article_title);

        this.swipeElementToLeft(
                By.xpath(article_xpath),
                "Cannot find saved article"
        );

        this.waitForArticleToDisappearByTitle(article_title);
    }
}
