package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AmazonCartPage {    private WebDriver driver;

    // Cart items element
    private final By cartItems = By.cssSelector(".sc-list-item");

    public AmazonCartPage(WebDriver driver) {
        this.driver = driver;
    }

    // Opens the cart
    public void openCart() {
        driver.findElement(By.id("nav-cart")).click();
    }

    // Checks the items in the cart
    public int getCartItemCount() {
        return driver.findElements(cartItems).size();
    }

}

