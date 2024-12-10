package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AmazonCartPage;
import pages.AmazonHomePage;
import pages.AmazonSearchResultsPage;
import utils.BaseTest;


public class AmazonAcceptanceTest extends BaseTest {

    @Test
    public void testAddToCart() {
        AmazonHomePage homePage = new AmazonHomePage(driver);
        AmazonSearchResultsPage searchResultsPage = new AmazonSearchResultsPage(driver);
        AmazonCartPage cartPage = new AmazonCartPage(driver);

        // Open the homepage
        homePage.openHomePage();

        // Search for a laptop
        homePage.searchFor("laptop");

        // Add non-discounted products to the cart
        searchResultsPage.addNonDiscountedProductsToCart();

        // Check the cart
        cartPage.openCart();
        int itemCount = cartPage.getCartItemCount();
        Assert.assertTrue(itemCount > 0, "The cart should not be empty!");
    }

}