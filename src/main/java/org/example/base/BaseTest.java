package org.example.base;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.utils.ConfigReader;
import org.example.utils.ExtentManager;
import org.example.utils.ScreenshotUtil;
import org.example.utils.WebDriverManagerUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.time.Duration;


public class BaseTest {
    protected WebDriver driver;
    ConfigReader config;
    protected static ExtentReports extent;
    protected static ExtentTest test;
    protected static final Logger logger = LogManager.getLogger(BaseTest.class);

        @BeforeSuite
    public void setUpReport() {
            extent = ExtentManager.getInstance();
    }
    @BeforeClass
    public void setUp() {

        config = new ConfigReader();
        driver = WebDriverManagerUtil.getDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(ConfigReader.getProperty("url"));
        logger.info("Browser launched successfully");
    }



    @AfterMethod
    public void tearDown(ITestResult result) {
       if (ITestResult.FAILURE == result.getStatus()) {
            ScreenshotUtil.captureScreenshot(driver, result.getName());
            test.log(Status.FAIL, "Test Failed: " + result.getThrowable());
        } else if (ITestResult.SUCCESS == result.getStatus()) {
            test.log(Status.PASS, "Test Passed");
        } else {
            test.log(Status.SKIP, "Test Skipped");
        }
        WebDriverManagerUtil.closeDriver();
    logger.info("Browser closed successfully");
    }

    @AfterSuite
    public void flushExtentReports() {
        ExtentManager.flush();
    }


    public void waitForPageToLoad(WebElement locator) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.stalenessOf(locator));
//        new WebDriverWait(driver, Duration.ofSeconds(20)).until(
//                webDriver -> ((JavascriptExecutor) webDriver)
//                        .executeScript("return document.readyState").equals("complete")
//        );
    }
}
