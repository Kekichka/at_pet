package at.ui.bo;

import at.ui.po.RegistrationPage;
import org.openqa.selenium.WebDriver;

public class RegistrationBO {

    public boolean registerUserAndVerify(WebDriver driver, String firstName, String lastName, String email, String password) {
        RegistrationPage page = new RegistrationPage(driver);

        page.open();

        page.register(firstName, lastName, email, password);

        boolean success = page.isRegistrationSuccessful();

        if (success) {
            System.out.println("Message: Registration successful for " + email);
        } else {
            System.out.println("Message: Registration FAILED for " + email);
        }

        return success;
    }
}
