import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.AppiumDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.net.URL;
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
        driver.quit();
    }

    @Test
    public void exampleTestXpath() {
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "not find Search Box",
                15
        );

        waitForElementAndSendKeys(
                By.xpath("//*[(@resource-id = 'org.wikipedia:id/search_close_btn')]"),
                "Java",
                "not find x",
                5
        );
    }

    @Test
    public void exampleTestId() {
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "not find Search Box",
                15);

        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_close_btn"),
                "Java",
                "not find x",
                5
        );
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
            //не работает этот метод. Ошибка:
            // INFO: HTTP Status: '405' -> incorrect JSON status mapping for 'unknown error' (500 expected)
            //org.openqa.selenium.WebDriverException: Method is not implemented
            // Не понимаю почему, т.к.метод идентичен методу урока.
            waitForElementNotPresent(
                    By.xpath("//*[(@resource-id = 'org.wikipedia:id/page_list_item_container')]"),
                    "Search result is still present",
                    10
            );
        } else {
            System.out.println("The search has not given any results");
        }
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

}
