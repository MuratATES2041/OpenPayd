package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.BaseTest;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class AmazonSiteCrawlerTest extends BaseTest {

    @Test
    public void testSiteCrawler() throws IOException {
        // Open Amazon homepage
        driver.get("https://www.amazon.com");

        // Open the "Shop By Department" dropdown menu
        WebElement shopByDepartmentMenu = driver.findElement(By.id("nav-hamburger-menu"));
        shopByDepartmentMenu.click();

        // Retrieve all links from the menu
        List<WebElement> departmentLinks = driver.findElements(By.cssSelector(".hmenu-item"));
        List<String> visitedLinks = new ArrayList<>();

        // Name the result file with the current date and time
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        String fileName = timestamp + "_results.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            // Check each link
            for (WebElement linkElement : departmentLinks) {
                String linkText = linkElement.getText();
                String linkHref = linkElement.getAttribute("href");

                // If the link contains a URL, validate it
                if (linkHref != null && !linkHref.isEmpty()) {
                    driver.navigate().to(linkHref);

                    // Get the page title
                    String pageTitle = driver.getTitle();
                    boolean isPageValid = pageTitle != null && !pageTitle.isEmpty();

                    // Determine the link status
                    String status = isPageValid ? "OK" : "Dead link";
                    visitedLinks.add(linkHref);

                    // Write results to the file
                    writer.write(linkHref + " | " + pageTitle + " | " + status + "\n");
                }
            }
        }

        // Verify that all links were checked
        Assert.assertFalse(visitedLinks.isEmpty(), "No links were found!");
    }

}