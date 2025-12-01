package at.task6;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class UserPage {
    WebDriver driver;
    WebDriverWait wait;

    By firstNameField = By.id("FirstName");
    By lastNameField = By.id("LastName");
    By emailField = By.id("Email");
    By passwordField = By.id("Password");
    By confirmPasswordField = By.id("ConfirmPassword");
    By registerButton = By.id("register-button");
    By resultMessage = By.cssSelector(".result");

    public UserPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void openRegistrationPage() {
        driver.get("https://demowebshop.tricentis.com/register");
    }

    public void setFirstName(String name) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameField)).sendKeys(name);
    }

    public void setLastName(String name) {
        driver.findElement(lastNameField).sendKeys(name);
    }

    public void setEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    public void setPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(confirmPasswordField).sendKeys(password);
    }

    public void clickRegister() {
        driver.findElement(registerButton).click();
    }

    public boolean isRegistrationSuccessful() {
        return wait.until(ExpectedConditions.textToBePresentInElementLocated(resultMessage, "Your registration completed"));
    }
}
