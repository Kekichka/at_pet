package ui;

import at.DriverPool;
import at.ui.bo.RegistrationBO;
import at.ui.po.LoginPage;
import at.ui.po.ShoppingPage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class Task11Test {

    private String registeredEmail;
    private final String password = "123456";

    @Test
    public void testProductFlow() {
        runFullFlow("Alice", "Test", "14.1-inch Laptop");
    }

    @Test
    public void testPremiumProductFlow() {
        runFullFlow("Charlie", "Premium", "Build your own expensive computer");
    }

    @Test
    public void testAnotherProductFlow() {
        runFullFlow("Bob", "Tester", "Build your own cheap computer");
    }

    private void runFullFlow(String firstName, String lastName, String productName) {
        WebDriver driver = DriverPool.getDriver();

        registeredEmail = "user" + System.currentTimeMillis() + "@mail.com";
        boolean registrationSuccess = new RegistrationBO().registerUserAndVerify(
                driver, firstName, lastName, registeredEmail, password
        );
        Assert.assertTrue(registrationSuccess, "Registration failed for " + registeredEmail);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.login(registeredEmail, password);
        Assert.assertTrue(loginPage.isLoggedIn(), "Login failed for " + registeredEmail);

        ShoppingPage shoppingPage = new ShoppingPage(driver);
        shoppingPage.open();

        addProductToCart(driver, productName);
        Assert.assertTrue(shoppingPage.isProductAdded(), productName + " not added to cart!");
        System.out.println("Product added successfully: " + productName);
    }

    private void addProductToCart(WebDriver driver, String productName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement productLink = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.linkText(productName)
        ));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", productLink);
        productLink.click();

        WebElement addButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("input[id^='add-to-cart-button']")
        ));
        addButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector(".bar-notification.success")
        ));
    }

    @AfterMethod
    public void tearDown() {
        DriverPool.quitDriver();
    }
}
