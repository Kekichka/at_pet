package ui;

import io.qameta.allure.Allure;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import at.DriverPool;

import java.io.ByteArrayInputStream;
import java.time.Duration;
import java.util.List;

public class AllureTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        driver = DriverPool.getDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @AfterMethod
    public void tearDown() {
        // Скриншот для Allure перед закриттям драйвера
        try {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            Allure.addAttachment("Final Screenshot", new ByteArrayInputStream(screenshot));
        } catch (Exception e) {
            e.printStackTrace();
        }

        DriverPool.quitDriver();
    }

    @Test
    public void addItemToCartTest() {
        driver.get("https://demowebshop.tricentis.com/");

        List<WebElement> items = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                By.cssSelector(".product-item .product-title a")));

        items.get(1).click();

        WebElement addToCart = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("input.add-to-cart-button")));

        String productId = addToCart.getAttribute("data-productid");
        System.out.println("Adding product with ID: " + productId);

        addToCart.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector(".bar-notification.success")));

        byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        Allure.addAttachment("Cart Notification Screenshot", new ByteArrayInputStream(screenshot));

        driver.get("https://demowebshop.tricentis.com/cart");

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector(".page.shopping-cart-page")));

        byte[] cartScreenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        Allure.addAttachment("Cart Page Screenshot", new ByteArrayInputStream(cartScreenshot));
    }

}
