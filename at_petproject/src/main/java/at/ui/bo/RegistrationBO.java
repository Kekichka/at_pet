package at.ui.bo;

import at.ui.po.RegistrationPage;
import org.openqa.selenium.WebDriver;

public class RegistrationBO {

    public boolean registerUserAndVerify(WebDriver driver, String firstName, String lastName, String email, String password) {
        RegistrationPage page = new RegistrationPage(driver);
        page.openRegistrationPage();
        page.setFirstName(firstName);
        page.setLastName(lastName);
        page.setEmail(email);
        page.setPassword(password);
        page.setConfirmPassword(password);
        page.clickRegister();

        boolean success = page.isRegistrationSuccessful();
        System.out.println("Registration success: " + success);
        if (success) {
            System.out.println("Message: " + page.getSuccessMessage());
        }
        return success;
    }
}
