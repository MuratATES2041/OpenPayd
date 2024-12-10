package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;

    @BeforeClass
    public void setUp() {
        // WebDriverManager ile ChromeDriver'ı otomatik indirip konfigüre ediyoruz
        WebDriverManager.chromedriver().setup();

        // Tarayıcı seçeneklerini belirliyoruz
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");

        // WebDriver örneğini başlatıyoruz
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterClass
    public void tearDown() {
        /*
        // Testler tamamlandıktan sonra tarayıcıyı kapatıyoruz
        if (driver != null) {
            driver.quit();
        }

         */
    }
}