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

//    public void addProductsBelow15K() {
//        for (WebElement product : products) {
//            String priceText = product.getText().replace(",", "").replace("EGP", "").trim();
//            double price = Double.parseDouble(priceText);
//            if (price < 15000) {
//                product.click();
//                addToCart.click();
//                nextPage.click();
//                wait.until(ExpectedConditions.stalenessOf(products.get(0)));
//            }
//        }
//    }


   /* public void addProductsBelow15K() {
        while (true) { // Keep paginating

            for (WebElement product : products) {
                try {
                    // Extract product price
                    priceElement =product;
                    String priceText = priceElement.getText().replace(",", "").trim();

                    if (!priceText.isEmpty()) {
                        int price = Integer.parseInt(priceText);

                        // If price < 15,000 EGP, add to cart
                        if (price < 15000) {
                            productAdded = true;

                            // Click "Add to Cart" button
                            addToCart=product;
                            addToCart.click();

                            // Wait for the cart confirmation
//                            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("sw-gtc")));
                        }
                    }
                } catch (Exception e) {
                    // Handle missing price or "Add to Cart" button
                }
            }

            // If no product below 15K found, move to next page
            try {
                nextPage.click();
                wait.until(ExpectedConditions.stalenessOf(products.get(0))); // Wait for page refresh
            } catch (NoSuchElementException e) {
                System.out.println("No more pages to navigate.");
                break; // Stop if no next page
            }
        }
    }*/
}

