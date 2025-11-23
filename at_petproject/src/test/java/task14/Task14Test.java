package task14;

import at.task12.TextAreaBusinessObject;
import at.task14.CustomListener;
import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

@Listeners(CustomListener.class)
public class Task14Test {
    WebDriver driver;

    @BeforeTest
    void setup() {
        ChromeDriverManager.getInstance().setup();
        driver = new ChromeDriver();
    }

    @Test
    void task14() {
        TextAreaBusinessObject textAreaBO = new TextAreaBusinessObject();
        textAreaBO.signupAndFillTextArea("MEOW :333 !", driver);
    }
    @Test
    void task14Fail() {
        TextAreaBusinessObject textAreaBO = new TextAreaBusinessObject();
        textAreaBO.signupAndFillTextArea("MEOW :333 !", driver);
        Assert.fail("Test Failed Message");
    }

    @AfterTest
    void teardown() {
        System.out.println("Driver quit done");
        driver.quit();
    }
}