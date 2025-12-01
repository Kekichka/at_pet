package task6;

import at.DriverPool;
import at.task6.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Task6Test {

    @Test
    public void ormStyleCRUDTest() {
        var driver = DriverPool.getDriver();
        UserBO userBO = new UserBO();

        String firstName = "AQA" + System.currentTimeMillis();
        String lastName = "Tester";
        String email = "user" + System.currentTimeMillis() + "@mail.com";
        String password = "123456";

        boolean created = userBO.createUser(driver, firstName, lastName, email, password);
        Assert.assertTrue(created, "User creation failed");

        UserPage page = new UserPage(driver);
        boolean valid = userBO.validateUserCreation(page);
        Assert.assertTrue(valid, "User validation failed");

        userBO.updateUserFirstName(driver, firstName + "_Updated");

        userBO.deleteUser(driver);
    }
}
