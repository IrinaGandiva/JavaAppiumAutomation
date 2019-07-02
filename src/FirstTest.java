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

public class FirstTest {
    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception
    {
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
    public void tearDown()
    {
        driver.quit();
    }

    @Test
    public void firstTest()
    {
        waitForElementAndClick(By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find Search Wikipedia input",
                5
        );
        waitForElementAndSendKeys(By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Java",
                "Cannot find search input",
                5);

        waitForElementPresents(
                By.xpath("//*[@text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' topic searching by 'Java",
                15);
    }

    @Test
    public void testmenu()
    {
        waitForElementPresents(
                By.id("org.wikipedia:id/search_container"),
                "cannot find search container",
                5
        );
        waitForElementAndClick(
                By.id("org.wikipedia:id/view_card_header_image"),
                "cannot click menu",
                5
        );
    }

    @Test
    public void testCompareArticleTitle()
    {
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find Search Wikipedia input",
                5
        );
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Java",
                "Cannot find search input",
                5);

        waitForElementAndClick(
                By.xpath("//*[@text='Object-oriented programming language']"),
                "Cannot find Search Wikipedia input",
                5);

        WebElement title_element = waitForElementPresents(
                By.xpath("//*[contains(@text,'Java (programming language)')]"),
                "Cannot find article title 'Java (programming language)'",
                25);
        String article_title = title_element.getAttribute("text");

        Assert.assertEquals(
              "We see unexpected title", "Java (programming language)",
                article_title);
    }

    @Test
    public void testCancelSearch()
    {
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "don't find search container",
                15);
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "don't find X",
                15);


    }


    private WebElement waitForElementPresents(By by, String error_massage, long timeoutSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver,timeoutSeconds);
        wait.withMessage(error_massage + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    private WebElement waitForElementPresents(By by, String error_massage)
    {
        return waitForElementPresents(by, error_massage, 5);
    }

    private WebElement waitForElementAndClick(By by, String error_massage, long timeoutSeconds)
    {
        WebElement element = waitForElementPresents(by, error_massage, 5);
        element.click();
        return element;
    }

    private WebElement waitForElementAndSendKeys(By by, String value, String error_massage, long timeoutSeconds)
    {
        WebElement element = waitForElementPresents(by, error_massage, 5);
        element.sendKeys(value);
        return element;
    }

//    private WebElement waitForElementPresentsById(String id, String error_massage, long timeoutInSeconds)
//    {
//        WebDriverWait wait = new WebDriverWait(driver,timeoutInSeconds);
//        wait.withMessage(error_massage + "\n");
//        By by = By.id(id);
//        return wait.until(
//                ExpectedConditions.presenceOfElementLocated(by)
//        );
//    }
//
//    private WebElement waitForElementByIdAndClick(String id, String error_massage, long timeoutSeconds)
//    {
//        WebElement element = waitForElementPresentsById(id, error_massage, 5);
//        element.click();
//        return element;
//    }

    private boolean waitForElementNotPresent(By by, String error_massage, long timeOutSeconds)
    {
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

}
