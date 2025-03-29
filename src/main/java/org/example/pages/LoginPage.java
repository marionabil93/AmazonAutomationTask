package org.example.pages;
import org.example.utils.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class LoginPage extends basePage {


    public LoginPage(WebDriver driver) {
       super(driver);
    }

    @FindBy (id = "nav-link-accountList")
    WebElement DashboardLoginBtn;
    // Locators
    @FindBy(xpath = "//input[@name='email']")
    WebElement emailField;

    @FindBy(id = "ap_password")
    WebElement passwordField;

    @FindBy(xpath = "//input[@type='submit']")
    WebElement loginBtn;

    @FindBy(id = "nav-link-accountList-nav-line-1")  // Profile name after login
     WebElement profileName;


    // Page Actions
    public void clickLoginHomePage() {
        clickElement(DashboardLoginBtn);
    }
    public void enterUsername(String user) {
        enterText(emailField,user);
    }

    public void enterPassword(String pass) {
        enterText(passwordField,pass);
    }
    public void loginToAccount() {
        clickElement(loginBtn);
    }


    public void verifyLoginSuccess() {
        waitingElementToView(profileName);
        String actualText = profileName.getText();
        Assert.assertTrue(actualText.contains(ConfigReader.getProperty("accountName")) , "Login Success !");
    }
}