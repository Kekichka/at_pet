package ui;

import at.DriverPool;
import at.ui.bo.RegistrationBO;
import at.ui.po.LoginPage;
import at.ui.po.ShoppingPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class UITests extends BaseTest {

    private static final Logger logger = LogManager.getLogger(UITests.class);

    private String registeredEmail;
    private final String password = "123456";

    @DataProvider(name = "usersAndProducts")
    public Object[][] usersAndProducts() {
        return new Object[][]{
                {"Alice", "Test", "14.1-inch Laptop"},
                {"Charlie", "Premium", "Build your own expensive computer"},
                {"Multi", "ItemUser", "Build your own cheap computer"}
        };
    }

    @Test(dataProvider = "usersAndProducts")
    public void testProductFlow(String firstName,
                                String lastName,
                                String productName) {

        runFullFlow(firstName, lastName, productName);
    }

    private void runFullFlow(String firstName,
                             String lastName,
                             String productName) {

        WebDriver driver = DriverPool.getDriver();

        registeredEmail = "user" + System.currentTimeMillis() + "@mail.com";
        logger.info("Registering user: {} {}", firstName, lastName);

        RegistrationBO registrationBO = new RegistrationBO();
        boolean registrationSuccess = registrationBO.registerUserAndVerify(
                driver,
                firstName,
                lastName,
                registeredEmail,
                password
        );

        Assert.assertTrue(
                registrationSuccess,
                "Registration failed for " + registeredEmail
        );

        logger.info("Registration successful: {}", registeredEmail);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        logger.info("Logging in with email: {}", registeredEmail);

        loginPage.login(registeredEmail, password);
        Assert.assertTrue(
                loginPage.isLoggedIn(),
                "Login failed for " + registeredEmail
        );

        logger.info("Login successful");

        ShoppingPage shoppingPage = new ShoppingPage(driver);
        shoppingPage.open();

        logger.info("Adding product: {}", productName);
        shoppingPage.addProductToCart(productName);

        Assert.assertTrue(
                shoppingPage.isProductAdded(),
                "Product was NOT added: " + productName
        );

        logger.info("Product added: {}", productName);
    }

}
