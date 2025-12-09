package at.listener;

import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import at.DriverPool;

import java.io.ByteArrayInputStream;

public class CustomAllureListener implements ITestListener {

    private WebDriver getDriver() {
        return DriverPool.getDriver();
    }

    @Override
    public void onTestFailure(ITestResult result) {
        attachScreenshot(result.getName() + " - FAILURE");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        attachScreenshot(result.getName() + " - SUCCESS");
    }

    @Override
    public void onTestStart(ITestResult result) { }

    @Override
    public void onTestSkipped(ITestResult result) { }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) { }

    @Override
    public void onStart(ITestContext context) { }

    @Override
    public void onFinish(ITestContext context) { }

    private void attachScreenshot(String name) {
        try {
            WebDriver driver = getDriver();
            if (driver != null) {
                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                Allure.addAttachment(name, new ByteArrayInputStream(screenshot));
            }
        } catch (Exception e) {
            System.out.println("Cannot attach screenshot: " + e.getMessage());
        }
    }
}
