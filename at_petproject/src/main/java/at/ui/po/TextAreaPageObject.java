package at.ui.po;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TextAreaPageObject {

    WebDriver driver;
    WebDriverWait wait;

    // Кнопка "Register" на головній сторінці
    @FindBy(linkText = "Register")
    WebElement registerButton;

    // Поле First Name
    @FindBy(id = "FirstName")
    WebElement firstNameField;

    public TextAreaPageObject(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void clickRegisterButton() {
        registerButton.click();
        System.out.println("Clicked on Register button");
    }

    public void waitForFirstNameField() {
        wait.until(ExpectedConditions.visibilityOf(firstNameField));
        System.out.println("First Name field is visible");
    }

    public void setText(String text) {
        firstNameField.clear();
        firstNameField.sendKeys(text);
        System.out.println("Set text: " + text);
    }

    public String getText() {
        String value = firstNameField.getAttribute("value");
        System.out.println("Retrieved text: " + value);
        return value;
    }

    public void waitForText(String expectedText) {
        wait.until(ExpectedConditions.textToBePresentInElementValue(firstNameField, expectedText));
        System.out.println("Text \"" + expectedText + "\" is present in First Name field");
    }
}
