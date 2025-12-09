package at.ui.po;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class RegistrationPage {

    private final WebDriver driver;

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 600), this);
    }

    @FindBy(id = "FirstName") private WebElement firstNameField;
    @FindBy(id = "LastName") private WebElement lastNameField;
    @FindBy(id = "Email") private WebElement emailField;
    @FindBy(id = "Password") private WebElement passwordField;
    @FindBy(id = "ConfirmPassword") private WebElement confirmPasswordField;
    @FindBy(id = "register-button") private WebElement registerButton;
    @FindBy(css = ".result") private WebElement resultMessage;

    public void open() {
        driver.get("https://demowebshop.tricentis.com/register");
    }

    public void register(String firstName, String lastName, String email, String password) {
        firstNameField.clear(); firstNameField.sendKeys(firstName);
        lastNameField.clear(); lastNameField.sendKeys(lastName);
        emailField.clear(); emailField.sendKeys(email);
        passwordField.clear(); passwordField.sendKeys(password);
        confirmPasswordField.clear(); confirmPasswordField.sendKeys(password);

        registerButton.click();
    }

    public boolean isRegistrationSuccessful() {
        return resultMessage.getText().contains("Your registration completed");
    }
}
