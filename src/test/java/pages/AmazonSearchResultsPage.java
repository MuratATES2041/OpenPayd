package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class AmazonSearchResultsPage {private WebDriver driver;

    // Product card element
    private final By productCards = By.cssSelector(".s-main-slot .s-result-item");
    private final By productTitle = By.cssSelector("h2 a span");
    private final By addToCartButton = By.cssSelector("input[name='submit.add-to-cart']");

    public AmazonSearchResultsPage(WebDriver driver) {
        this.driver = driver;
    }

    // Adds non-discounted products to the cart
    public void addNonDiscountedProductsToCart() {
        // Select all product cards
        List<WebElement> products = driver.findElements(productCards);

        // Print product details
        System.out.println("Found " + products.size() + " products:");
        for (int i = 0; i < products.size(); i++) {
            try {
                WebElement product = products.get(i);
                WebElement title = product.findElement(productTitle); // Get product title
                System.out.println((i + 1) + ". Product: " + title.getText()); // Print product title
            } catch (Exception e) {
                System.out.println((i + 1) + ". Product: Unable to retrieve details.");
            }
        }

        // Add products to the cart
        for (WebElement product : products) {
            try {
                // Find product title and "Add to Cart" button
                WebElement title = product.findElement(productTitle);
                WebElement button = product.findElement(addToCartButton);

                // Add to cart
                button.click();
                System.out.println("Added to cart: " + title.getText());
            } catch (Exception e) {
                // Skip to the next product in case of an error
                System.out.println("Skipping a product.");
            }
        }
    }

}