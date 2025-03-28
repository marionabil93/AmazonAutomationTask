package tests;

import com.aventstack.extentreports.Status;
import org.example.base.BaseTest;
import org.example.pages.CartPage;
import org.example.pages.DashboardPage;
import org.example.pages.VideoGamesPage;
import org.example.utils.ConfigReader;
import org.example.utils.ExtentManager;
import org.testng.annotations.Test;
import org.example.pages.LoginPage;


public class LoginTest extends BaseTest {
    @Test
    public void testValidLogin() throws InterruptedException {
//        final Logger log = LogManager.getLogger(LoginTest.class);
//        driver.get(ConfigReader.getProperty("url"));
        //        ExtentTest test = ExtentReportManager.startTest("Valid Login Test");

        test = ExtentManager.createTest("Amazon Purchase Test");
        logger.info("Starting Amazon Purchase Test");
        logger.info("Navigated to Amazon Egypt");
        test.log(Status.INFO, "Navigated to Amazon Egypt");
        DashboardPage dashboard = new DashboardPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        VideoGamesPage videoGames = new VideoGamesPage(driver);
        CartPage pageCart = new CartPage(driver);
        dashboard.clickLoginHomePage();
        loginPage.enterUsername(ConfigReader.getProperty("username"));
        loginPage.loginToAccount();
        loginPage.enterPassword(ConfigReader.getProperty("password"));
        loginPage.loginToAccount();
        loginPage.verifyLoginSuccess();
        dashboard.navigateToVideoGamesPage();
        videoGames.FilterByShipping();
        videoGames.FilterByCondition();
        videoGames.sorting();
        pageCart.addProductsBelow15kToCart();
        test.log(Status.PASS, "Amazon Purchase Test Passed");


//
//        String expectedTitle = "Dashboard";
//        Assert.assertEquals(driver.getTitle(), expectedTitle, "Login failed!");
//        log.info("Starting Login Test");
//
//        ExtentReportManager.endTest();
    }
}
