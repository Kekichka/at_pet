package task12;

import at.task12.TextAreaBusinessObject;
import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Task12Test {
    WebDriver driver;

    @BeforeTest
    void setup() {
        ChromeDriverManager.getInstance().setup();
        driver = new ChromeDriver();
    }

    @Test
    void task12() {
        TextAreaBusinessObject textAreaBO = new TextAreaBusinessObject();
        textAreaBO.signupAndFillTextArea("MEOW :333 !", driver);
    }

    @AfterTest
    void teardown() {
        driver.quit();
    }
}
