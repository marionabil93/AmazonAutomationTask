package listeners;

import org.testng.ITestListener;
import org.testng.ITestResult;
import org.example.utils.ExtentManager;
import org.example.utils.ScreenshotUtil;
import org.openqa.selenium.WebDriver;

public class TestListener implements ITestListener {
    @Override
    public void onTestFailure(ITestResult result) {
        WebDriver driver = (WebDriver) result.getTestContext().getAttribute("WebDriver");
        String screenshotPath = ScreenshotUtil.captureScreenshot(driver, result.getName());

        ExtentManager.createTest(result.getName()).fail("Test Failed: " + result.getThrowable());
        ExtentManager.createTest(result.getName()).addScreenCaptureFromPath(screenshotPath);
    }
}
