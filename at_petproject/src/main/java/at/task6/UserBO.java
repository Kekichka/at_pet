package at.task6;

import org.openqa.selenium.WebDriver;

public class UserBO {

    public boolean createUser(WebDriver driver, String firstName, String lastName, String email, String password) {
        UserPage page = new UserPage(driver);
        page.openRegistrationPage();
        page.setFirstName(firstName);
        page.setLastName(lastName);
        page.setEmail(email);
        page.setPassword(password);
        page.clickRegister();

        return page.isRegistrationSuccessful();
    }

    public boolean validateUserCreation(UserPage page) {
        return page.isRegistrationSuccessful();
    }

    public void updateUserFirstName(WebDriver driver, String newFirstName) {
        System.out.println("Pretend we update first name to: " + newFirstName);
    }

    public void deleteUser(WebDriver driver) {
        driver.get("https://demowebshop.tricentis.com/logout");
        System.out.println("User logged out (delete simulated)");
    }
}
