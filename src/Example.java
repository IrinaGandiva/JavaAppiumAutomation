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

public class Example {
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
    public void ex2FindSearch(){
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

}
