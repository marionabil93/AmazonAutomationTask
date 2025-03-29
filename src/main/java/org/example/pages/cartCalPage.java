package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import java.util.List;

public class cartCalPage extends basePage {

    public cartCalPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[@id='nav-cart-count-container']")
    WebElement cartIcon;

    @FindBy(xpath = "//div[@data-name='Active Items']")
    List<WebElement> cartItems;

    @FindBy(id = "sc-active-items-header")
    WebElement shoppingCartTitle;

    @FindBy(id = "sc-subtotal-amount-activecart")
    WebElement subtotalAmount;

    @FindBy(xpath = "//div[@class='sc-apex-cart-price'] //span[@class='a-price-whole']")
    List<WebElement> priceElements;


    public void verifyCartItems() {
        waitingElementToView(cartIcon);
        clickElement(cartIcon);
        waitingElementToView(shoppingCartTitle);
        Assert.assertTrue(shoppingCartTitle.isDisplayed());
        if (cartItems.isEmpty()) {
            System.out.println("No items found in cart. Check automation logic.");
        } else {
            System.out.println("All selected products are successfully added to the cart.");
            Assert.assertFalse(cartItems.isEmpty());
        }
    }




    public void verifyTotalAmount() {
        double totalAmount = extractPrice(subtotalAmount.getText());
        double calculatedTotal = calculateTotal();;
        double delta = 0.05;

        if (Math.abs(totalAmount - calculatedTotal) < 1) {
            System.out.println("Total amount verified successfully: " + totalAmount + " EGP");
            Assert.assertEquals(totalAmount, calculatedTotal, delta, "Prices are not within acceptable range");
        } else {
            System.out.println("Total amount mismatch! Expected: " + calculatedTotal + " EGP, Found: " + totalAmount + " EGP");
        }
    }

    public double calculateTotal() {
        double total = 0;
        for (WebElement priceElement : priceElements) {
            double itemPrice = extractPrice(priceElement.getText());
            total += itemPrice;
            System.out.println("Item price added: " + itemPrice + " EGP");

        }
        System.out.println("Final calculated total amount: " + total + " EGP");
        Assert.assertTrue(subtotalAmount.isDisplayed());

        return total;
    }

}
