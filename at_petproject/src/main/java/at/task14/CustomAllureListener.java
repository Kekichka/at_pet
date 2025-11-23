package at.task14;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;
import at.DriverPool;

public class CustomAllureListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Failure: " + result.getName());
        makeScreenshotAttachment();
        makeDOMAttachment();
    }

    @Attachment(value="Page screen", type="image/png")
    private byte[] makeScreenshotAttachment() {
        return ((TakesScreenshot) DriverPool.getDriver()).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value="Page DOM", type="text/plain")
    private String makeDOMAttachment() {
        return DriverPool.getDriver().getPageSource();
    }
}
