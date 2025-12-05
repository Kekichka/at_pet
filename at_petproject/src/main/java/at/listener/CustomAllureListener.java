package at.listener;

import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.ByteArrayInputStream;

public class CustomAllureListener implements ITestListener {

    private WebDriver driver;

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public void onTestFailure(ITestResult result) {
        attachScreenshot("Failure Screenshot");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        attachScreenshot("Success Screenshot");
    }

    private void attachScreenshot(String name) {
        if (driver != null) {
            try {
                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                Allure.addAttachment(name, new ByteArrayInputStream(screenshot));
            } catch (Exception e) {
                System.out.println("Failed to capture screenshot: " + e.getMessage());
            }
        }
    }

    @Override public void onTestStart(ITestResult result) {}
    @Override public void onTestSkipped(ITestResult result) {}
    @Override public void onTestFailedButWithinSuccessPercentage(ITestResult result) {}
    @Override public void onStart(ITestContext context) {}
    @Override public void onFinish(ITestContext context) {}
}
