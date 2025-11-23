package at.task10;

import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;

public class Task10DriveManager {
    public static void main(String[] args) {
        ChromeOptions options = new ChromeOptions();
        System.setProperty("webdriver.chrome.driver", "driver/chromedriver");
//        options.addArguments("--headless=new");

        ChromeDriverManager.getInstance().setup();
        WebDriver driver = new ChromeDriver(options);

        driver.get("https://www.demoblaze.com/cart.html");

        WebElement homeButton = driver.findElement(By.xpath("//*[@id=\"navbarExample\"]/ul/li[1]/a"));
        Assert.assertTrue(homeButton.isDisplayed(), "Home button is not visible");
        homeButton.click();
        sleep(2000);

        WebElement nextButton = driver.findElement(By.xpath("//*[@id=\"carouselExampleIndicators\"]/a[2]/span[1]"));
        Assert.assertTrue(nextButton.isDisplayed(), "Next button is not visible");
        nextButton.click();
        sleep(2000);

        WebElement nextPage = driver.findElement(By.xpath("//*[@id=\"next2\"]"));
        Assert.assertTrue(nextPage.isDisplayed(), "Next page button is not visible");
        nextPage.click();
        sleep(2000);

        driver.quit();
    }

    private static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
