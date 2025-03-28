package org.example.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class CartPage {
    protected WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        this.js = (JavascriptExecutor) driver;
        PageFactory.initElements(driver, this);
    }

//    @FindBy(xpath = "//span[@data-component-id='2'] //div[@class='a-section']")
//    List<WebElement> products;

    @FindBy(xpath = "//div[@data-component-type='s-search-result']") // Product links
     List<WebElement> productLinks;

//    @FindBy(css = ".a-price-whole")
//    WebElement priceElement;

    @FindBy(xpath = "//span[@class='a-price-whole']")  // Price elements
    List<WebElement> productPrices;

    @FindBy(xpath = "//button[@name='submit.addToCart']")
    List<WebElement> addToCartButtons;

    @FindBy(xpath = "//a[contains(@aria-label,'Go to next page')]")
    WebElement nextPageButton;

    @FindBy(xpath = "//*[@id='ewc-content']")
    WebElement leftAddedItems;

    public void addProductsBelow15kToCart() {
        boolean productAdded = false;

        while (true) { // Loop through pages
            wait.until(ExpectedConditions.visibilityOfAllElements(productPrices));

            for (int i = 0; i < addToCartButtons.size(); i++) {
                try{
                String priceText = productPrices.get(i).getText().replace(",", "").trim();
                int price = Integer.parseInt(priceText);
                //Thread.sleep(2000);
                    wait.until(ExpectedConditions.visibilityOf(productPrices.get(i)));

                if (price < 15000) {
                    productAdded = true;
                   //Thread.sleep(2000);
                    wait.until(ExpectedConditions.visibilityOf(addToCartButtons.get(i)));
                    waitForElementAndScrollToClick(addToCartButtons.get(i));

                    System.out.println("Clicked Add to Cart for item " + (i + 1) + " with price: " + price);
//                    addToCartButtons.get(i).click();
//                    scrollToElement(addToCartButtons.get(i));
//                  wait.until(ExpectedConditions.stalenessOf(productPrices.get(i)));// Scroll to product
                }
                } catch (Exception e) {
                    System.out.println("Skipping item " + (i + 1) + " due to error: " + e.getMessage());
                }
            }

            if (!productAdded && isNextPageAvailable()) {
                waitForElementAndScrollToClick(nextPageButton);
                wait.until(ExpectedConditions.visibilityOfAllElements(productPrices));
                //nextPageButton.click();
                //wait.until(ExpectedConditions.visibilityOfAllElements(productPrices));
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

    private void waitForElementAndScrollToClick(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);
        element.click();
    }

    double extractPrice(String priceText) {
        return Double.parseDouble(priceText.replaceAll("[^0-9.]", "").trim()); // Extract numbers safely
    }


}

//    private void addProductToCart() {
//        wait.until(ExpectedConditions.elementToBeClickable(addToCartButton)).click();
//    }

  /*  public void addProductsBelow15kToCart() {
        while (true) {
            boolean foundProduct = false;
            List<WebElement> products = driver.findElements(By.xpath("//div[@class='a-section']"));

            for (WebElement product : products) {
                try {
                    WebElement priceElement = product.findElement(By.xpath("//span[@class='a-price-whole']"));
                    double price = extractPrice(priceElement.getText());

                    if (price < PRICE_LIMIT) {
                        WebElement addToCartButton = product.findElement(By.xpath("//button[@aria-label='Add to cart']"));
                        js.executeScript("arguments[0].scrollIntoView(true);", addToCartButton);
                        addToCartButton.click();
                        foundProduct = true;
                        wait.until(ExpectedConditions.stalenessOf(products.get(0))); // Ensure product is added before proceeding
                        System.out.println("Product added to cart.");
                    }
                } catch (Exception e) {
                    System.out.println("Error processing product: " + e.getMessage());
                }
            }

            if (foundProduct) {
                verifyCartItems(); // Ensure all added products are in the cart
                calculateTotal();
                verifyTotalAmount();
                return; // Exit function after adding products below 15k EGP
            }

            WebElement nextPageButton = driver.findElement(By.xpath("//a[contains(@aria-label,'Go to next page')]"));
            if (nextPageButton.isDisplayed() && nextPageButton.isEnabled()) {
                nextPageButton.click();
                wait.until(ExpectedConditions.stalenessOf(products.get(0)));
            } else {
                break; // No more pages
            }
        }
    }*/

/*public void addProductsBelow15K() {
    while (true) { // Loop through pages
        boolean productAdded = false;

        wait.until(ExpectedConditions.visibilityOfAllElements(products)); // Ensure products are loaded
        wait.until(ExpectedConditions.visibilityOfAllElements(priceElement));
        wait.until(ExpectedConditions.visibilityOfAllElements(addToCart));

        for (WebElement product : products) {
            try {
                // Extract price

                WebElement priceElement = product.findElement(By.cssSelector(".a-price-whole"));
                String priceText = priceElement.getText().replace(",", "").trim();

                if (!priceText.isEmpty()) {
                    int price = Integer.parseInt(priceText);

                    // If price < 15,000 EGP, add to cart
                    if (price < 15000) {
                        productAdded = true;

                        // Click "Add to Cart" button
                        WebElement addToCart = product.findElement(By.xpath("//button[@aria-label='Add to cart']"));
                        wait.until(ExpectedConditions.elementToBeClickable(addToCart)).click();

                        // Wait for cart confirmation
                        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@target='AmazonHelp']")));
                        js.executeScript("arguments[0].scrollIntoView(true);", addToCart);
                    }
                }
            } catch (NoSuchElementException | TimeoutException e) {
                System.out.println("Skipping product due to missing price or button.");
            }
        }

        // If no product below 15K, move to the next page
        try {
            if (!productAdded && nextPage.isDisplayed()) {
                // ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", nextPage);
                wait.until(ExpectedConditions.elementToBeClickable(nextPage)).click();
                // wait.until(ExpectedConditions.stalenessOf(products.get(0))); // Wait for new page load
                wait.until(ExpectedConditions.visibilityOfAllElements(products));
            } else {
                break; // Exit loop if no more pages or products found
            }
        } catch (NoSuchElementException e) {
            System.out.println("No more pages available.");
            break; // Exit loop
        }
    }
}*/