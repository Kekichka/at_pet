package at.task12;

import org.openqa.selenium.WebElement;

public class ElementWrapper {
    private final WebElement element;
    private final String name;

    public ElementWrapper(WebElement element, String name) {
        this.element = element;
        this.name = name;
    }

    public void click() {
        element.click();
        System.out.println("Clicked on element: " + name);
    }

    public void setText(String text) {
        element.clear();
        element.sendKeys(text);
        System.out.println("Set text \"" + text + "\" into element: " + name);
    }

    public String getText() {
        String value = element.getAttribute("value");
        System.out.println("Retrieved text \"" + value + "\" from element: " + name);
        return value;
    }
}
