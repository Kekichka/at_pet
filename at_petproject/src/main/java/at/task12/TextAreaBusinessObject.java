package at.task12;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class TextAreaBusinessObject {
    public void signupAndFillTextArea(String text, WebDriver driver) {
        driver.get("https://www.demoblaze.com/");

        TextAreaPageObject textAreaPage = new TextAreaPageObject(driver);
        textAreaPage.clickSignUpMenuButton(); // Open the main page and click SignUp
        textAreaPage.waitForTextArea();       // wait for the sign-up form
        textAreaPage.setText(text);           // Type text
        textAreaPage.getText();               // Read the text

        String actualText = textAreaPage.getText();

        textAreaPage.waitForText(text);
        Assert.assertEquals(actualText, text, "Text does not match expected value");  // validate
        System.out.println("Textarea content validated: " + actualText);
    }
}
