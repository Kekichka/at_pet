package task8;

import at.DriverPool;
import at.task8.RegistrationBO;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Task8Test {

    @DataProvider(name = "registrationData")
    public Object[][] registrationData() {
        return new Object[][]{
                {"Pookie", "Ble", "pooks" + System.currentTimeMillis() + "@mail.com", "123456"},
                {"Wookie", "Bla", "wooks" + System.currentTimeMillis() + "@mail.com", "abcdef"},
        };
    }

    @Test(dataProvider = "registrationData")
    public void registerUserTest(String firstName, String lastName, String email, String password) {
        RegistrationBO bo = new RegistrationBO();
        boolean success = bo.registerUserAndVerify(DriverPool.getDriver(), firstName, lastName, email, password);
        assert success : "Registration failed for user: " + firstName + " " + lastName;
    }
}
