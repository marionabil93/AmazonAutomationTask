package tests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.example.base.BaseTest;
import org.example.pages.*;
import org.example.utils.ConfigReader;
import org.example.utils.ExtentManager;
import org.testng.annotations.Test;


public class testScript extends BaseTest {

    @Test(priority = 1)
    public void testValidLogin() {
        ExtentTest test = extent.createTest("Test 1");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickLoginHomePage();
        loginPage.enterUsername(ConfigReader.getProperty("username"));
        loginPage.loginToAccount();
        loginPage.enterPassword(ConfigReader.getProperty("password"));
        loginPage.loginToAccount();
        loginPage.verifyLoginSuccess();

        test.log(Status.PASS, "Amazon Login Test Passed");
    }

@Test(priority = 2)
public void OpenVideoGames() {
    ExtentTest test = extent.createTest("Test 2");
    DashboardPage dashboard = new DashboardPage(driver);
    VideoGamesPage videoGames = new VideoGamesPage(driver);
    dashboard.navigateToVideoGamesPage();
    videoGames.FilterByShipping();
    videoGames.FilterByCondition();
    videoGames.sorting();
    test.log(Status.PASS, "Amazon OpenVideoGames Test Passed");
}

    @Test(priority = 3)
    public void addProductsBelow15k() {
        ExtentTest test = extent.createTest("Test 3");
        CartPage pageCart = new CartPage(driver);
        pageCart.addProductsBelow15kToCart();
        test.log(Status.PASS, "Amazon add Products Test Passed");
    }


    @Test(priority = 4)
    public void productsCartsCalculations() {
        ExtentTest test = extent.createTest("Test 4");
        cartCalPage calObj = new cartCalPage(driver);
        calObj.verifyCartItems();
        calObj.calculateTotal();
        calObj.verifyTotalAmount();
        test.log(Status.PASS, "Products Calculations Test Passed");
    }
}

