package org.example.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class VideoGamesPage extends basePage {

    public VideoGamesPage(WebDriver driver) {
       super(driver);
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
        clickUsingJS(freeShippingCheckBox);
        Assert.assertTrue(resultPage.isDisplayed());
    }

    public void FilterByCondition(){
        scrollInWindow(conditionNew);
        clickUsingJS(conditionNew);
        Assert.assertTrue(resultPage.isDisplayed());
    }

    public void sorting(){
        clickUsingJS(sortDDL);
        clickElement(sortHighToLow);
        waitingElementToView(resultPage);
        Assert.assertTrue(resultPage.isDisplayed());
    }

}

