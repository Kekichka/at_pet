package task14;

import at.task12.TextAreaBusinessObject;
import at.task14.CustomListener;
import at.DriverPool;
import org.testng.Assert;
import org.testng.annotations.*;

@Listeners({CustomListener.class}) // можна додати CustomAllureListener, якщо потрібно
public class Task14Test {

    @BeforeTest
    void setup() {
        // Створюємо драйвер і відкриваємо сайт
        DriverPool.getDriver().get("https://demowebshop.tricentis.com/");
    }

    @Test
    void task14() {
        TextAreaBusinessObject textAreaBO = new TextAreaBusinessObject();
        textAreaBO.fillFirstNameField("MEOW :333 !", DriverPool.getDriver());
    }

    @Test
    void task14Fail() {
        TextAreaBusinessObject textAreaBO = new TextAreaBusinessObject();
        textAreaBO.fillFirstNameField("MEOW :333 !", DriverPool.getDriver());
        Assert.fail("Test Failed Message");
    }

    @AfterTest
    void teardown() {
        System.out.println("Driver quit done");
        DriverPool.quitDriver();
    }
}
