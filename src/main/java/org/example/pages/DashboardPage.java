package org.example.pages;

import org.example.utils.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;


public class DashboardPage extends basePage {

    public DashboardPage(WebDriver driver) {
        super(driver);
    }


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


    public void clickSeeAllCategories() {
        clickElement(hamburgerBtn);
        waitingElementToView(seeAllBtn);
        scrollToElement(seeAllBtn);
        clickElement(seeAllBtn);
    }

   public  void navigateToAllVideoGames() {
       waitingElementToView(videoGamesItem);
       clickElement(videoGamesItem);
       clickUsingJS(allVideoGamesItem);
       String pageTitle = VideoGamesTitlePage.getText();
       Assert.assertTrue(pageTitle.contains(ConfigReader.getProperty("videoGamesTitle")));
   }

    public void navigateToVideoGamesPage() {
        clickSeeAllCategories();
        navigateToAllVideoGames();
    }
}
