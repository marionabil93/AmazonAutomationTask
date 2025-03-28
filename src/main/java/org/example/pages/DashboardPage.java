package org.example.pages;

import org.example.utils.ConfigReader;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class DashboardPage {
    WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;

    @FindBy (id = "nav-link-accountList")
    WebElement DashboardLoginBtn;

    @FindBy (id = "nav-hamburger-menu")
    WebElement hamburgerBtn;

    @FindBy(xpath = "//a[@aria-label='See All Categories']")
    WebElement seeAllBtn;

    @FindBy(xpath = "//a[@data-menu-id='16']")
    WebElement videoGamesItem;

    @FindBy(xpath = "//a[contains(text(),'All Video Games')]")
    WebElement allVideoGamesItem;

    @FindBy(xpath = "//div[@class='fst-h1-st pageBanner']")
    WebElement VideoGamesTitlePage;

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        this.js = (JavascriptExecutor) driver;  // Initialize JavaScript Executor
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void clickLoginHomePage() {
        DashboardLoginBtn.click();
    }

    public void clickSeeAllCategories() {
        hamburgerBtn.click();
        wait.until(ExpectedConditions.visibilityOf(seeAllBtn));
        js.executeScript("arguments[0].scrollIntoView(true);;", seeAllBtn);
        seeAllBtn.click();
    }

   public  void navigateToAllVideoGames(){
       wait.until(ExpectedConditions.visibilityOf(videoGamesItem));
       videoGamesItem.click();
       js.executeScript("arguments[0].click();", allVideoGamesItem);
       String pageTitle = VideoGamesTitlePage.getText();
       Assert.assertTrue(pageTitle.contains(ConfigReader.getProperty("videoGamesTitle")));
   }

    public void navigateToVideoGamesPage() {
        clickSeeAllCategories();
        navigateToAllVideoGames();
    }



}
