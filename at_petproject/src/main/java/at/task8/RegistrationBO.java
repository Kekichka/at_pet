package at.task8;

import org.openqa.selenium.WebDriver;

public class RegistrationBO {

    public void registerUser(WebDriver driver, String firstName, String lastName, String email, String password) {
        RegistrationPage page = new RegistrationPage(driver);
        page.openRegistrationPage();
        page.setFirstName(firstName);
        page.setLastName(lastName);
        page.setEmail(email);
        page.setPassword(password);
        page.setConfirmPassword(password);
        page.clickRegister();
    }
}
