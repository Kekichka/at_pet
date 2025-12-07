package at.ui.po;

import at.ui.wrappers.Button;
import at.ui.wrappers.Label;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ShoppingPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public ShoppingPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void open() {
        driver.get("https://demowebshop.tricentis.com/");
    }

    public void addProductToCart(String productName) {
        if (productName.toLowerCase().contains("cheap") || productName.toLowerCase().contains("expensive")) {
            addBuildYourOwnProduct(productName);
        } else {
            addNormalProduct(productName);
        }
    }

    private void addNormalProduct(String productName) {
        List<WebElement> products = driver.findElements(By.cssSelector(".product-item"));

        WebElement targetProduct = wait.until(driver -> {
            for (WebElement product : products) {
                String name = product.findElement(By.cssSelector("h2.product-title a")).getText();
                if (name.equalsIgnoreCase(productName)) {
                    return product;
                }
            }
            return null;
        });

        Button addButton = new Button(driver, targetProduct.findElement(By.cssSelector("input[value='Add to cart']")));
        addButton.scrollToView();
        addButton.click();

        Label notification = new Label(driver, driver.findElement(By.id("bar-notification")));
        notification.waitUntilVisible();
    }

    private void addBuildYourOwnProduct(String productName) {
        WebElement productLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.linkText(productName)
        ));
        productLink.click();

        WebElement addButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("input[id^='add-to-cart-button']")
        ));
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addButton);
        addButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".bar-notification.success")));
    }

    public boolean isProductAdded() {
        Label notification = new Label(driver, driver.findElement(By.id("bar-notification")));
        return notification.getText().contains("The product has been added to your");
    }
}
