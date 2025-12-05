package at.ui.po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ShoppingPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public ShoppingPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void open() {
        driver.get("https://demowebshop.tricentis.com/");
    }

    public void addProductToCart(String productName) {
        // Wait until products are visible
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".product-item")));

        List<WebElement> products = driver.findElements(By.cssSelector(".product-item"));
        WebElement targetProduct = null;

        for (WebElement product : products) {
            String name = product.findElement(By.cssSelector("h2.product-title a")).getText();
            // Use containsIgnoreCase to avoid exact match issues
            if (name.toLowerCase().contains(productName.toLowerCase())) {
                targetProduct = product;
                break;
            }
        }

        if (targetProduct == null) {
            throw new RuntimeException("Product not found: " + productName);
        }

        WebElement addButton = targetProduct.findElement(By.cssSelector("input[value='Add to cart']"));
        wait.until(ExpectedConditions.elementToBeClickable(addButton)).click();

        // Wait for the success notification to appear
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("bar-notification")));

    }

    public boolean isProductAdded() {
        return driver.findElement(By.id("bar-notification")).isDisplayed();
    }

}
