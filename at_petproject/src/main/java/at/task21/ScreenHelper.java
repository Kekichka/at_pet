package at.task21;

import org.sikuli.script.Screen;

public class ScreenHelper {
    public static void makeScreenShot(){
        new Screen().capture().save(".", "capture_" + System.currentTimeMillis() + ".png");
    }

}
