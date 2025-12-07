package at.ui.wrappers;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BaseElement {
    protected final WebDriver driver;
    protected final WebElement element;
    protected final Logger logger = LogManager.getLogger(BaseElement.class);

    public BaseElement(WebDriver driver, WebElement element) {
        this.driver = driver;
        this.element = element;
    }

    protected void sleep(long millis) {
        try { Thread.sleep(millis); } catch (InterruptedException ignored) {}
    }

    public void scrollToView() {
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
            logger.info("Scrolled to element: {}", element);
        } catch (Exception e) {
            logger.error("Cannot scroll to element: {}", element, e);
        }
    }
}
