package at;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class DriverPool {

    private static WebDriver driver;

    public static WebDriver getDriver() {
        if (driver == null) {
            // беремо browser з system property або default "chrome"
            String browser = System.getProperty("browser", "chrome");

            switch (browser.toLowerCase()) {
                case "chrome":
                    ChromeOptions options = new ChromeOptions();

                    // headless можна брати з пропертіз, якщо хочеш
                    String headlessProp = System.getProperty("headless", "false");
                    if (headlessProp.equalsIgnoreCase("true")) {
                        options.addArguments("--headless=new");
                    }

                    driver = new ChromeDriver(options);
                    driver.manage().window().maximize();
                    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
                    break;

                // можна додати firefox, edge тощо
                default:
                    throw new RuntimeException("Unsupported browser: " + browser);
            }
        }
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
