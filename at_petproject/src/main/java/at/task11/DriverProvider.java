package at.task11;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverProvider {
    public static WebDriver driver;

    public static synchronized WebDriver getDriver(){
        if (driver == null){
            driver = new ChromeDriver();
        }
        return driver;
    }
}
