package org.example.base;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.pages.CartPage;
import org.example.pages.DashboardPage;
import org.example.pages.LoginPage;
import org.example.pages.VideoGamesPage;
import org.example.utils.ConfigReader;
import org.example.utils.ExtentManager;
import org.example.utils.WebDriverManagerUtil;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;



public class BaseTest {
    protected WebDriver driver;
    ConfigReader config;
    protected static ExtentReports extent;
    protected static ExtentTest test;
    protected static final Logger logger = LogManager.getLogger(BaseTest.class);
    String screenshotPath = System.getProperty("user.dir") + "/screenshots/";

    @BeforeSuite
    public void setUpReport() {
        extent = ExtentManager.getInstance();
        test = ExtentManager.createTest("Amazon Purchase Test");
        test.log(Status.INFO, "Navigated to Amazon Egypt");
        logger.info("Starting Amazon Purchase Test");
        logger.info("Navigated to Amazon Egypt");

    }
    @BeforeClass
    public void setUp() {
        config = new ConfigReader();
        driver = WebDriverManagerUtil.getDriver();
        logger.info("Browser launched successfully");
    }



    @AfterMethod
    public void tearDown(ITestResult result) {
       if (ITestResult.FAILURE == result.getStatus()) {
           this.takeScreenshot(result.getName());
           test.log(Status.FAIL, "Test Failed: " + result.getThrowable());
        } else if (ITestResult.SUCCESS == result.getStatus()) {
            test.log(Status.PASS, "Test Passed");
        } else {
            test.log(Status.SKIP, "Test Skipped");
        }
    }


    @AfterSuite
    public void flushExtentReports() {
        WebDriverManagerUtil.closeDriver();
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

    public void takeScreenshot(String testName) {
        File directory = new File(screenshotPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String filePath = screenshotPath + testName + "_" + timestamp + ".png";

        try {
            FileUtils.copyFile(srcFile, new File(filePath));
            System.out.println("Screenshot saved at: " + filePath);
        } catch (IOException e) {
            System.err.println("Screenshot saving failed: " + e.getMessage());
        }
    }
}
