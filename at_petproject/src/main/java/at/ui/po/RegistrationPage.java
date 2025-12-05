package at.ui.po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegistrationPage {

    WebDriver driver;
    WebDriverWait wait;

    By firstNameField = By.id("FirstName");
    By lastNameField = By.id("LastName");
    By emailField = By.id("Email");
    By passwordField = By.id("Password");
    By confirmPasswordField = By.id("ConfirmPassword");
    By registerButton = By.id("register-button");

    By resultMessage = By.cssSelector(".result");

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void openRegistrationPage() {
        driver.get("https://demowebshop.tricentis.com/register");
    }

    public void setFirstName(String firstName) {
        WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameField));
        field.clear();
        field.sendKeys(firstName);
        System.out.println("First Name set: " + firstName);
    }

    public void setLastName(String lastName) {
        WebElement field = driver.findElement(lastNameField);
        field.clear();
        field.sendKeys(lastName);
        System.out.println("Last Name set: " + lastName);
    }

    public void setEmail(String email) {
        WebElement field = driver.findElement(emailField);
        field.clear();
        field.sendKeys(email);
        System.out.println("Email set: " + email);
    }

    public void setPassword(String password) {
        WebElement field = driver.findElement(passwordField);
        field.clear();
        field.sendKeys(password);
        System.out.println("Password set: " + password);
    }

    public void setConfirmPassword(String password) {
        WebElement field = driver.findElement(confirmPasswordField);
        field.clear();
        field.sendKeys(password);
        System.out.println("Confirm Password set: " + password);
    }

    public void clickRegister() {
        driver.findElement(registerButton).click();
        System.out.println("Clicked Register button");
    }

    public boolean isRegistrationSuccessful() {
        return wait.until(ExpectedConditions.textToBePresentInElementLocated(resultMessage, "Your registration completed"));
    }

    public String getSuccessMessage() {
        return driver.findElement(resultMessage).getText();
    }
}
