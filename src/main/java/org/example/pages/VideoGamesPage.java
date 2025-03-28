package org.example.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class VideoGamesPage {
    protected WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;


    public VideoGamesPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        this.js = (JavascriptExecutor) driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@aria-labelledby='Free Shipping']")
    WebElement freeShippingCheckBox;

    @FindBy(xpath = "//div[@class='s-no-outline']")
    WebElement resultPage;

    @FindBy(xpath = "//span[text()='New']")
    WebElement conditionNew;

    @FindBy(id = "s-result-sort-select")
    WebElement sortDDL;

    @FindBy(id = "s-result-sort-select_2")
    WebElement sortHighToLow;



    public void FilterByShipping(){
        js.executeScript("arguments[0].click();", freeShippingCheckBox);
        Assert.assertTrue(resultPage.isDisplayed());
    }

    public void FilterByCondition(){
        js.executeScript("window.scrollBy(0,500)", "");
        wait.until(ExpectedConditions.visibilityOf(conditionNew));
        js.executeScript("arguments[0].click();", conditionNew);
        Assert.assertTrue(resultPage.isDisplayed());
    }

    public void sorting(){
        wait.until(ExpectedConditions.visibilityOf(sortDDL));
        js.executeScript("arguments[0].click();", sortDDL);
        wait.until(ExpectedConditions.visibilityOf(sortHighToLow));
        sortHighToLow.click();
        wait.until(ExpectedConditions.visibilityOf(resultPage));
        Assert.assertTrue(resultPage.isDisplayed());
    }

}

