package at.ui.wrappers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Button extends BaseElement {

    public Button(WebDriver driver, WebElement element) {
        super(driver, element);
    }

    public void click() {
        while (true) {
            try {
                if (element.isDisplayed() && element.isEnabled()) {
                    element.click();
                    logger.info("Clicked on button: {}", element);
                    return;
                }
            } catch (Exception ignored) {}
            sleep(200);
        }
    }
}