package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class WelcomePageObject extends MainPageObject
{
    private static final String
    STEP_LEARN_MORE_LINK = "id:Learn more about Wikipedia",
    STEP_NEW_WAYS_TO_EXPLORE_TEXT = "id:New ways to explore",
    STEP_LEARN_MORE_ABOUT_DATA_COLLECTED = "id:Learn more about data collected",
    STEP_ADD_OR_EDIT_PREFERRED_LANGUAGES = "id:Add or edit preferred languages",
    NEXT_BUTTON = "id:Next",
    GET_STARTED_BUTTON = "id:Get started";

    public WelcomePageObject(AppiumDriver driver)
    {
        super(driver);
    }

    public void waitForLearningMoreLink()
    {
        this.waitForElementPresents(STEP_LEARN_MORE_LINK, "Can not find 'Learn more about Wikipedia'", 10);
    }

    public void clickNextButton()
    {
        this.waitForElementAndClick(NEXT_BUTTON, "Can not find 'Next'", 10);
    }

    public void waitForNewWaysToExploreLink()
    {
        this.waitForElementPresents(STEP_NEW_WAYS_TO_EXPLORE_TEXT, "Can not find 'New ways to explore'", 10);
    }

    public void waitForAddOrEditPreferredLanguagesLink()
    {
        this.waitForElementPresents(STEP_ADD_OR_EDIT_PREFERRED_LANGUAGES, "Can not find 'Add or edit preferred languages'", 10);
    }

    public void waitForLearnMoreAboutDataCollected()
    {
        this.waitForElementPresents(STEP_LEARN_MORE_ABOUT_DATA_COLLECTED, "Can not find 'Learn more about data collected'", 10);
    }

    public void clickGetStartedButton()
    {
        this.waitForElementAndClick(GET_STARTED_BUTTON, "Can not find 'Get started'", 10);
    }



}
