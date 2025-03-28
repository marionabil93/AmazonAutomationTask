package org.example.pages;
import org.example.utils.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class LoginPage {
    protected WebDriver driver;
    WebDriverWait wait;


    // Locators
    @FindBy(xpath = "//input[@name='email']")
    WebElement emailField;

    @FindBy(id = "ap_password")
    WebElement passwordField;

    @FindBy(xpath = "//input[@type='submit']")
    WebElement loginBtn;

    @FindBy(id = "nav-link-accountList-nav-line-1")  // Profile name after login
     WebElement profileName;


    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }
    // Page Actions

    public void enterUsername(String user) {
       wait.until(ExpectedConditions.visibilityOf(emailField)).sendKeys(user);
    }

    public void enterPassword(String pass) {
        wait.until(ExpectedConditions.visibilityOf(passwordField)).sendKeys(pass);
        //passwordField.sendKeys(pass);
    }
    public void loginToAccount() {
        loginBtn.click();
    }


    public void verifyLoginSuccess() {
        wait.until(ExpectedConditions.visibilityOf(profileName));
        String actualText = profileName.getText();
        Assert.assertTrue(actualText.contains(ConfigReader.getProperty("accountName")) , "Login Success !");
    }
}