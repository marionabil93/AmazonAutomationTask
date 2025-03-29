package org.example.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class CartPage extends basePage {

    public CartPage(WebDriver driver) {
       super(driver);
    }


    @FindBy(xpath = "//span[@class='a-price-whole']")  // Price elements
    List<WebElement> productPrices;

    @FindBy(xpath = "//button[@name='submit.addToCart']")
    List<WebElement> addToCartButtons;

    @FindBy(xpath = "//a[contains(@aria-label,'Go to next page')]")
    WebElement nextPageButton;

    public void addProductsBelow15kToCart() {
        while (true) { // Loop through pages
            boolean productAdded = false;

            waitingListElementsToView(productPrices);

            for (int i = 0; i < addToCartButtons.size(); i++) {
                try{
                String priceText = productPrices.get(i).getText().replace(",", "").trim();
                int price = Integer.parseInt(priceText);
                waitingElementToView(productPrices.get(i));
                if (price < 15000) {
                    productAdded = true;
                    waitingElementToView(addToCartButtons.get(i));
                    waitForElementAndScrollToClick(addToCartButtons.get(i));
                    System.out.println("Clicked Add to Cart for item " + (i + 1) + " with price: " + price);
                  wait.until(ExpectedConditions.stalenessOf(productPrices.get(i)));  // Scroll to product
                }
                } catch (Exception e) {
                    System.out.println("Skipping item " + (i + 1) + " due to error: " + e.getMessage());
                }
            }

            if (!productAdded && isNextPageAvailable()) {
                waitForElementAndScrollToClick(nextPageButton);
                waitingListElementsToView(productPrices);
            } else {
                break; // Exit if products are added or no next page
            }
        }
    }

    private boolean isNextPageAvailable() {
        try {
            return nextPageButton.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
