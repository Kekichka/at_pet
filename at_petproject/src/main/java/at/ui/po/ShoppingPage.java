package at.ui.po;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ShoppingPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public ShoppingPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 30), this);
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

        WebElement targetProduct = wait.until(d -> {
            for (WebElement p : products) {
                String name = p.findElement(By.cssSelector("h2.product-title a")).getText();
                if (name.equalsIgnoreCase(productName)) return p;
            }
            return null;
        });

        WebElement addButton = targetProduct.findElement(By.cssSelector("input[value='Add to cart']"));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", addButton);

        addButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".bar-notification.success")));
    }

    private void addBuildYourOwnProduct(String productName) {
        WebElement productLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.linkText(productName)
        ));
        productLink.click();

        WebElement addButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("input[id^='add-to-cart-button']")
        ));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", addButton);

        addButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".bar-notification.success")));
    }

    public boolean isProductAdded() {
        WebElement notification = driver.findElement(By.id("bar-notification"));
        return notification.getText().contains("The product has been added to your");
    }
}
