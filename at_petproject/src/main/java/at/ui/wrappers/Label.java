package at.ui.wrappers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Label extends BaseElement {

    public Label(WebDriver driver, WebElement element) {
        super(driver, element);
    }

    public String getText() {
        while (true) {
            try {
                if (element.isDisplayed()) {
                    String text = element.getText();
                    logger.info("Text read from label: {}", text);
                    return text;
                }
            } catch (Exception ignored) {}
            sleep(200);
        }
    }

    public boolean containsText(String expected) {
        return getText().contains(expected);
    }

    public void waitUntilVisible() {
        while (true) {
            try {
                if (element.isDisplayed()) {
                    logger.info("Element is visible: {}", element);
                    return;
                }
            } catch (Exception ignored) {}
            sleep(200);
        }
    }
}
