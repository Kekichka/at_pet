package at.ui.po;

import at.ui.wrappers.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    private final WebDriver driver;

    @FindBy(id = "Email")
    private WebElement emailField;

    @FindBy(id = "Password")
    private WebElement passwordField;

    @FindBy(css = "input.button-1.login-button")
    private WebElement loginButton;

    @FindBy(linkText = "Log out")
    private WebElement logoutLink;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void open() {
        driver.get("https://demowebshop.tricentis.com/login");
    }

    public void login(String email, String password) {
        new InputField(driver, emailField).setText(email);
        new InputField(driver, passwordField).setText(password);
        new Button(driver, loginButton).click();
    }

    public boolean isLoggedIn() {
        return new Label(driver, logoutLink).getText().equals("Log out");
    }
}
