package at.ui.po;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class LoginPage {

    private final WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 600), this);
    }

    @FindBy(id = "Email")
    private WebElement emailField;

    @FindBy(id = "Password")
    private WebElement passwordField;

    @FindBy(css = "input.button-1.login-button")
    private WebElement loginButton;

    @FindBy(linkText = "Log out")
    private WebElement logoutLink;

    public void open() {
        driver.get("https://demowebshop.tricentis.com/login");
    }

    public void login(String email, String password) {
        emailField.clear();
        emailField.sendKeys(email);

        passwordField.clear();
        passwordField.sendKeys(password);

        loginButton.click();
    }

    public boolean isLoggedIn() {
        return logoutLink.isDisplayed();
    }
}
