package at.ui.wrappers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class InputField extends BaseElement {

    public InputField(WebDriver driver, WebElement element) {
        super(driver, element);
    }

    public void setText(String text) {
        while (true) {
            try {
                if (element.isDisplayed()) {
                    element.clear();
                    element.sendKeys(text);
                    logger.info("Entered text '{}' into field: {}", text, element);
                    return;
                }
            } catch (Exception ignored) {}
            sleep(200);
        }
    }
}