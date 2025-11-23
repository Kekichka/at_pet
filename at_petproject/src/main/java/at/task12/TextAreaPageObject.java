package at.task12;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

//
// TASK 12:
// Implement PageFactory for a few pages.
// Implement a Wrapper for common WebElements (choose your variant).
// Implement methods for your web element with console logging.
// Use those methods in a simple TC scenario.
//
// Textareas:
// - setText: sets the text value of a textarea
// - getText: retrieves the text value of a textarea
// - waitForText: waits for a specific text to be present in the textarea
//

public class TextAreaPageObject {

    WebDriver driver;
    WebDriverWait wait;

    @FindBy(xpath = "//*[@id=\"signin2\"]")
    WebElement signUpMenuButton;

    @FindBy(xpath = "//*[@id=\"sign-username\"]")
    WebElement textArea;

    public TextAreaPageObject(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void clickSignUpMenuButton() {
        signUpMenuButton.click();
        System.out.println("Clicked on Sign Up menu button");
    }

    public void waitForTextArea() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"sign-username\"]")));
        System.out.println("Textarea is visible");
    }

    public void setText(String text) {
        textArea.clear();
        textArea.sendKeys(text);
        System.out.println("Set text: " + text);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String getText() {
        String value = textArea.getAttribute("value");
        System.out.println("Retrieved text: " + value);
        return value;
    }

    public void waitForText(String expectedText) {
        wait.until(ExpectedConditions.textToBePresentInElementValue(textArea, expectedText));
        System.out.println("Text \"" + expectedText + "\" is present in textarea");
    }
}
