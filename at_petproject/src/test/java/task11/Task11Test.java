package task11;

import at.DriverPool;
import at.task11.LoginPage;
import at.task11.ShoppingPage;
import at.task8.RegistrationBO;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class Task11Test {

    private String registeredEmail;
    private final String password = "123456";

    @Test
    public void testFullUserFlow() {
        RegistrationBO bo = new RegistrationBO();
        registeredEmail = "user" + System.currentTimeMillis() + "@mail.com";
        boolean registrationSuccess = bo.registerUserAndVerify(
                DriverPool.getDriver(),
                "Alice",
                "Test",
                registeredEmail,
                password
        );
        Assert.assertTrue(registrationSuccess, "Registration failed!");
        System.out.println("Registration completed successfully for: " + registeredEmail);

        LoginPage loginPage = new LoginPage(DriverPool.getDriver());
        loginPage.open();
        loginPage.login(registeredEmail, password);
        Assert.assertTrue(loginPage.isLoggedIn(), "Login failed!");
        System.out.println("Login successful for: " + registeredEmail);

        ShoppingPage shoppingPage = new ShoppingPage(DriverPool.getDriver());
        shoppingPage.open();
        System.out.println("Shopping page opened.");

        String productName = "14.1-inch Laptop";
        shoppingPage.addProductToCart(productName);
        System.out.println("Attempted to add product: " + productName);

        boolean added = shoppingPage.isProductAdded();
        System.out.println("Product added to cart? " + added);
        Assert.assertTrue(added, "Product not added to cart!");
    }

    @AfterMethod
    public void tearDown() {
        DriverPool.quitDriver();
        System.out.println("Driver quit.");
    }
}
