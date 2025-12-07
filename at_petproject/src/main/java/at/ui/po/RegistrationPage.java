package at.ui.po;

import at.ui.wrappers.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegistrationPage {

    private final WebDriver driver;

    @FindBy(id = "FirstName")
    private WebElement firstNameField;

    @FindBy(id = "LastName")
    private WebElement lastNameField;

    @FindBy(id = "Email")
    private WebElement emailField;

    @FindBy(id = "Password")
    private WebElement passwordField;

    @FindBy(id = "ConfirmPassword")
    private WebElement confirmPasswordField;

    @FindBy(id = "register-button")
    private WebElement registerButton;

    @FindBy(css = ".result")
    private WebElement resultMessage;

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void openRegistrationPage() {
        driver.get("https://demowebshop.tricentis.com/register");
    }

    public void setFirstName(String firstName) {
        new InputField(driver, firstNameField).setText(firstName);
    }

    public void setLastName(String lastName) {
        new InputField(driver, lastNameField).setText(lastName);
    }

    public void setEmail(String email) {
        new InputField(driver, emailField).setText(email);
    }

    public void setPassword(String password) {
        new InputField(driver, passwordField).setText(password);
    }

    public void setConfirmPassword(String password) {
        new InputField(driver, confirmPasswordField).setText(password);
    }

    public void clickRegister() {
        new Button(driver, registerButton).click();
    }

    public boolean isRegistrationSuccessful() {
        return new Label(driver, resultMessage).containsText("Your registration completed");
    }

    public String getSuccessMessage() {
        return new Label(driver, resultMessage).getText();
    }
}
