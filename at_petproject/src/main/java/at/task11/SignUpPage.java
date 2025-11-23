package at.task11;

import at.DriverPool;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SignUpPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By signUpLink = By.linkText("Sign up");
    private final By usernameField = By.id("sign-username");
    private final By passwordField = By.id("sign-password");
    private final By signUpButton = By.xpath("//button[text()='Sign up']");

    public SignUpPage() {
        this.driver = DriverPool.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://www.demoblaze.com/");
    }

    // Відкриваємо модальне вікно Sign Up
    public void openSignUpModal() {
        driver.findElement(signUpLink).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField));
    }

    public void enterUsername(String username) {
        wait.until(ExpectedConditions.elementToBeClickable(usernameField)).sendKeys(username);
    }

    public void enterPassword(String password) {
        wait.until(ExpectedConditions.elementToBeClickable(passwordField)).sendKeys(password);
    }

    public void clickSignUpButton() {
        wait.until(ExpectedConditions.elementToBeClickable(signUpButton)).click();
    }

    public String getAlertMessage() {
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        String msg = alert.getText();
        alert.accept();
        return msg;
    }
}
