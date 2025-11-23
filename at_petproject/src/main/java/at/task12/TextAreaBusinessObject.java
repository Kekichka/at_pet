package at.task12;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class TextAreaBusinessObject {

    public void fillFirstNameField(String text, WebDriver driver) {
        driver.get("https://demowebshop.tricentis.com/");

        TextAreaPageObject page = new TextAreaPageObject(driver);
        page.clickRegisterButton();      // Натискаємо Register
        page.waitForFirstNameField();    // Чекаємо, поки поле First Name стане видимим
        page.setText(text);              // Вводимо текст
        String actualText = page.getText();
        page.waitForText(text);          // Чекаємо, поки текст підтягнеться
        Assert.assertEquals(actualText, text, "Text does not match expected value");
        System.out.println("First Name content validated: " + actualText);
    }
}
