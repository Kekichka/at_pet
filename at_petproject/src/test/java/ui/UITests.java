package ui;

import at.DriverPool;
import at.ui.bo.RegistrationBO;
import at.ui.po.LoginPage;
import at.ui.po.ShoppingPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UITests extends BaseTest {

    private static final Logger logger = LogManager.getLogger(UITests.class);

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
    public void testMultipleItemsFlow() {
        WebDriver driver = DriverPool.getDriver();

        registeredEmail = "user" + System.currentTimeMillis() + "@mail.com";
        RegistrationBO registrationBO = new RegistrationBO();
        logger.info("Registering user: {} {}", "Multi", "ItemUser");
        boolean registrationSuccess = registrationBO.registerUserAndVerify(
                driver, "Multi", "ItemUser", registeredEmail, password
        );
        Assert.assertTrue(registrationSuccess, "Registration failed for " + registeredEmail);
        logger.info("Registration successful for email: {}", registeredEmail);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        logger.info("Logging in with email: {}", registeredEmail);
        loginPage.login(registeredEmail, password);
        Assert.assertTrue(loginPage.isLoggedIn(), "Login failed for " + registeredEmail);
        logger.info("Login successful for email: {}", registeredEmail);

        ShoppingPage shoppingPage = new ShoppingPage(driver);
        shoppingPage.open();

        String[] products = {"14.1-inch Laptop", "Build your own cheap computer", "Build your own expensive computer"};

        for (String productName : products) {
            logger.info("Adding product to cart: {}", productName);

            shoppingPage.addProductToCart(productName);

            Assert.assertTrue(shoppingPage.isProductAdded(), productName + " not added to cart!");
            logger.info("Product added successfully: {}", productName);
        }


    }

    private void runFullFlow(String firstName, String lastName, String productName) {
        WebDriver driver = DriverPool.getDriver();

        registeredEmail = "user" + System.currentTimeMillis() + "@mail.com";
        RegistrationBO registrationBO = new RegistrationBO();
        logger.info("Registering user: {} {}", firstName, lastName);
        boolean registrationSuccess = registrationBO.registerUserAndVerify(driver, firstName, lastName, registeredEmail, password);
        Assert.assertTrue(registrationSuccess, "Registration failed for " + registeredEmail);
        logger.info("Registration successful for email: {}", registeredEmail);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        logger.info("Logging in with email: {}", registeredEmail);
        loginPage.login(registeredEmail, password);
        Assert.assertTrue(loginPage.isLoggedIn(), "Login failed for " + registeredEmail);
        logger.info("Login successful for email: {}", registeredEmail);

        ShoppingPage shoppingPage = new ShoppingPage(driver);
        shoppingPage.open();

        shoppingPage.addProductToCart(productName);
        Assert.assertTrue(shoppingPage.isProductAdded(), productName + " not added to cart!");
        logger.info("Product added successfully: {}", productName);

    }
}
