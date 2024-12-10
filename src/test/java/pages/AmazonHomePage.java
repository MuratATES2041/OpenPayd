package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AmazonHomePage {    private WebDriver driver;

    // Base URL of the Amazon homepage
    private final String baseUrl = "https://www.amazon.com";

    // Search box element
    private final By searchBox = By.id("twotabsearchtextbox");

    // Search button element
    private final By searchButton = By.id("nav-search-submit-button");

    public AmazonHomePage(WebDriver driver) {
        this.driver = driver;
    }

    // Opens the Amazon homepage
    public void openHomePage() {
        driver.get(baseUrl);
    }

    // Performs a search with the given keyword
    public void searchFor(String keyword) {
        driver.findElement(searchBox).sendKeys(keyword);
        driver.findElement(searchButton).click();
    }

}