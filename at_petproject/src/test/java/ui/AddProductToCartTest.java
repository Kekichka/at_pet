package ui;

import at.listener.CustomAllureListener;
import at.ui.bo.RegistrationBO;
import at.ui.po.LoginPage;
import at.ui.po.ShoppingPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({CustomAllureListener.class})
public class AddProductToCartTest extends BaseTest {

    private static final Logger logger = LogManager.getLogger(AddProductToCartTest.class);

    @Test
    public void addProductToCartFlow() {
        String email = "user" + System.currentTimeMillis() + "@mail.com";

        logger.info("Starting registration for email: {}", email);
        boolean registered = new RegistrationBO().registerUserAndVerify(driver,
                "Test", "User", email, "123456");
        Assert.assertTrue(registered, "Registration failed");
        logger.info("Registration successful for email: {}", email);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        logger.info("Logging in with email: {}", email);
        loginPage.login(email, "123456");
        Assert.assertTrue(loginPage.isLoggedIn(), "Login failed");
        logger.info("Login successful for email: {}", email);

        ShoppingPage shoppingPage = new ShoppingPage(driver);
        shoppingPage.open();
        logger.info("Adding product '14.1-inch Laptop' to cart");
        shoppingPage.addProductToCart("14.1-inch Laptop");
        Assert.assertTrue(shoppingPage.isProductAdded(), "Product not added");
        logger.info("Product '14.1-inch Laptop' added to cart successfully");
    }
}
