import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.AppiumDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
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

public class Example {
    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        capabilities.setCapability("platformVersion", "6.0");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "/Users/itmacoshdd/Desktop/JavaAppiumAutomation/apks/org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

    }

    @After
    public void tearDown() {
        driver.rotate(ScreenOrientation.PORTRAIT);
        driver.quit();
    }

    @Test
    public void ex2FindSearch() {
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "not find Search Box",
                15
        );
        waitForElementPresents(
                By.xpath("//*[contains(@text, 'Search…')]"),
                "Cannot find Search input",
                15);

    }


    @Test
    public void ex3FindWordAndCancelSearch() throws InterruptedException {
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "not find Search Box",
                15
        );
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                "Leonardo da Vinci",
                "Cannot find Search input",
                15);


        waitForElementPresents(
                By.xpath("//*[(@resource-id = 'org.wikipedia:id/page_list_item_container')]"),
                "not find",
                10
        );

        int c = 0;
        c = driver.findElements(By.xpath("//*[(@resource-id = 'org.wikipedia:id/page_list_item_container')]")).size();
        if (c >= 2) {
            waitForElementAndClick(
                    By.xpath("//*[(@resource-id = 'org.wikipedia:id/search_close_btn')]"),
                    "can't find X ",
                    10
            );

            waitForElementNotPresent(
                    By.id("org.wikipedia:id/page_list_item_container"),
                    "Search result is still present",
                    10
            );
        } else {
            System.out.println("The search has not given any results");
        }
    }

    @Test
    public void ex5SavingTwoArticles() throws InterruptedException {

        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find Search Wikipedia input",
                5
        );

        String name_of_the_first_request = "Leonardo da Vinci";

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                name_of_the_first_request,
                "Cannot find search input",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.TextView[contains(@text, 'Italian Renaissance polymath')]"),
                "Cannot find Search Wikipedia input",
                5
        );

        waitForElementPresents(
                By.xpath("//*[contains(@text,'Italian Renaissance polymath')]"),
                "Cannot find" + name_of_the_first_request,
                15
        );

        waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc ='More options']"),
                "cannot find button to open article options",
                5

        );
        waitForElementAndClick(
                By.xpath("//*[@text= 'Add to reading list']"),
                "cannot find Add to reading list",
                5
        );

        Thread.sleep(2000);

        waitForElementAndClick(
                By.id("org.wikipedia:id/onboarding_button"),
                "cannot find button GOT IT",
                5
        );

        waitForElementAndClear(
                By.id("org.wikipedia:id/text_input"),
                "cannot find text input",
                5
        );

        String name_of_the_folder = "Must to read";

        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                name_of_the_folder,
                "Cannot put text into articles folder input",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[@text='OK']"),
                "Cannot press OK button",
                5
        );
        Thread.sleep(2000);

        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@class='android.widget.ImageButton']"),
                "Cannot close article, cannot find X link",
                5
        );

        //Добавление второй статьи
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find Search Wikipedia input",
                5
        );

        String name_of_the_second_request = "Michelangelo";

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                name_of_the_second_request,
                "Cannot find search input",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.TextView[contains(@text, 'Italian sculptor, painter, architect and poet')]"),
                "Cannot find Search Wikipedia input",
                5
        );

        waitForElementPresents(
                By.xpath("//*[contains(@text,'Italian sculptor, painter, architect and poet')]"),
                "Cannot find" + name_of_the_second_request,
                15
        );

        waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc ='More options']"),
                "cannot find button to open article options",
                5

        );
        waitForElementAndClick(
                By.xpath("//*[@text= 'Add to reading list']"),
                "cannot find Add to reading list",
                5
        );

        Thread.sleep(2000);

        waitForElementAndClick(
                By.xpath("//android.widget.TextView[contains(@text, '" + name_of_the_folder + "')]"),
                "cannot find folder" + name_of_the_folder,
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@class='android.widget.ImageButton']"),
                "Cannot close article, cannot find X link",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc= 'My lists']"),
                "cannot find MyList",
                10
        );
        waitForElementAndClick(
                By.id("org.wikipedia:id/item_container"),
                "cannot find saved folder " + name_of_the_folder,
                5
        );

        swipeElementToLeft(
                By.xpath("//*[@text= '" + name_of_the_second_request + "']"),
                "Cannot find saved article " + name_of_the_second_request
        );
        waitForElementPresents(
                By.xpath("//android.widget.TextView[contains(@text, 'Italian Renaissance polymath')]"),
                "delete saved article" + name_of_the_first_request,
                5
        );
    }

    @Test
    public void ex6AssertTitle(){


        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find Search Wikipedia input",
                5
        );

        String name_of_the_request = "Leonardo da Vinci";

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                name_of_the_request,
                "Cannot find search input",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.TextView[contains(@text, 'Italian Renaissance polymath')]"),
                "Cannot find Search Wikipedia input",
                5
        );

        assertElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "there is no such element"
        );
        }


    private WebElement waitForElementPresents(By by, String error_massage, long timeoutSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutSeconds);
        wait.withMessage(error_massage + "\n");
        return wait.until(
                presenceOfElementLocated(by)
        );
    }

    private WebElement waitForElementPresents(By by, String error_massage) {
        return waitForElementPresents(by, error_massage, 5);
    }

    private WebElement waitForElementAndClick(By by, String error_massage, long timeoutSeconds) {
        WebElement element = waitForElementPresents(by, error_massage, 5);
        element.click();
        return element;
    }

    private WebElement waitForElementAndSendKeys(By by, String value, String error_massage, long timeoutSeconds) {
        WebElement element = waitForElementPresents(by, error_massage, 5);
        element.sendKeys(value);
        return element;
    }

    private boolean waitForElementNotPresent(By by, String error_massage, long timeOutSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeOutSeconds);
        wait.withMessage(error_massage + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    private WebElement waitForElementAndClear(By by, String error_massage, long timeOutSeconds)
    {
        WebElement element = waitForElementPresents(by, error_massage, timeOutSeconds);
        element.clear();
        return element;
    }
    protected void swipeElementToLeft(By by, String error_message) {
        WebElement element = waitForElementPresents(
                by,
                error_message,
                10
        );

        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y) / 2;

        TouchAction action = new TouchAction(driver);
        action
                .press(right_x, middle_y)
                .waitAction(150)
                .moveTo(left_x, middle_y)
                .release()
                .perform();

    }

    private int getAmountOfElements(By by)
    {
        List elements = driver.findElements(by);
        return elements.size();
    }

    private void assertElementPresent(By by, String error_message)
    {
       int amount_of_elements = getAmountOfElements(by);
       if (amount_of_elements >= 1){
           String default_message = "An element " + by.toString() + "supposed to be present ";
           throw new AssertionError(default_message + " " + error_message);

       }
    }
}
