import io.appium.java_client.TouchAction;
import lib.CoreTestCase;
import lib.ui.MainPageObject;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class Example extends CoreTestCase {
    private lib.ui.MainPageObject MainPageObject;

    protected void setUp() throws Exception {
        super.setUp();
        MainPageObject = new MainPageObject(driver);
    }

    @Test
    public void testEx2FindSearch() {
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "not find Search Box",
                15
        );
        MainPageObject.waitForElementPresents(
                By.xpath("//*[contains(@text, 'Search…')]"),
                "Cannot find Search input",
                15);

    }


    @Test
    public void testEx3FindWordAndCancelSearch() throws InterruptedException {
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "not find Search Box",
                15
        );
        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                "Leonardo da Vinci",
                "Cannot find Search input",
                15);


        MainPageObject.waitForElementPresents(
                By.xpath("//*[(@resource-id = 'org.wikipedia:id/page_list_item_container')]"),
                "not find",
                10
        );

        int c = 0;
        c = driver.findElements(By.xpath("//*[(@resource-id = 'org.wikipedia:id/page_list_item_container')]")).size();
        if (c >= 2) {
            MainPageObject.waitForElementAndClick(
                    By.xpath("//*[(@resource-id = 'org.wikipedia:id/search_close_btn')]"),
                    "can't find X ",
                    10
            );

            MainPageObject.waitForElementNotPresent(
                    By.id("org.wikipedia:id/page_list_item_container"),
                    "Search result is still present",
                    10
            );
        } else {
            System.out.println("The search has not given any results");
        }
    }

    @Test
    public void testEx5SavingTwoArticles() throws InterruptedException {

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find Search Wikipedia input",
                5
        );

        String name_of_the_first_request = "Leonardo da Vinci";

        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                name_of_the_first_request,
                "Cannot find search input",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.TextView[contains(@text, 'Italian Renaissance polymath')]"),
                "Cannot find Search Wikipedia input",
                5
        );

        MainPageObject.waitForElementPresents(
                By.xpath("//*[contains(@text,'Italian Renaissance polymath')]"),
                "Cannot find" + name_of_the_first_request,
                15
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc ='More options']"),
                "cannot find button to open article options",
                5

        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text= 'Add to reading list']"),
                "cannot find Add to reading list",
                5
        );

        Thread.sleep(2000);

        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/onboarding_button"),
                "cannot find button GOT IT",
                5
        );

        MainPageObject.waitForElementAndClear(
                By.id("org.wikipedia:id/text_input"),
                "cannot find text input",
                5
        );

        String name_of_the_folder = "Must to read";

        MainPageObject.waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                name_of_the_folder,
                "Cannot put text into articles folder input",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='OK']"),
                "Cannot press OK button",
                5
        );
        Thread.sleep(2000);

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@class='android.widget.ImageButton']"),
                "Cannot close article, cannot find X link",
                5
        );

        //Добавление второй статьи
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find Search Wikipedia input",
                5
        );

        String name_of_the_second_request = "Michelangelo";

        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                name_of_the_second_request,
                "Cannot find search input",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.TextView[contains(@text, 'Italian sculptor, painter, architect and poet')]"),
                "Cannot find Search Wikipedia input",
                5
        );

        MainPageObject.waitForElementPresents(
                By.xpath("//*[contains(@text,'Italian sculptor, painter, architect and poet')]"),
                "Cannot find" + name_of_the_second_request,
                15
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc ='More options']"),
                "cannot find button to open article options",
                5

        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text= 'Add to reading list']"),
                "cannot find Add to reading list",
                5
        );

        Thread.sleep(2000);

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.TextView[contains(@text, '" + name_of_the_folder + "')]"),
                "cannot find folder" + name_of_the_folder,
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@class='android.widget.ImageButton']"),
                "Cannot close article, cannot find X link",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc= 'My lists']"),
                "cannot find MyList",
                10
        );
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/item_container"),
                "cannot find saved folder " + name_of_the_folder,
                5
        );

        MainPageObject.swipeElementToLeft(
                By.xpath("//*[@text= '" + name_of_the_second_request + "']"),
                "Cannot find saved article " + name_of_the_second_request
        );
        MainPageObject.waitForElementPresents(
                By.xpath("//android.widget.TextView[contains(@text, 'Italian Renaissance polymath')]"),
                "delete saved article" + name_of_the_first_request,
                5
        );
    }

}



