package task21;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
import org.testng.annotations.Test;

import java.io.File;

import static at.task21.ScreenHelper.makeScreenShot;

public class Task21Test {

    @Test
    void calculatorTest() throws FindFailed, InterruptedException {
        Screen screen = new Screen();
        // new Screen().capture().save(".", "capture_" + System.currentTimeMillis() + ".png");
        Pattern pattern = new Pattern(
                new File("src/main/resources/pattern/search.png")
                        .getAbsolutePath());
        screen.find(pattern).click();
        screen.type("кальк");
        screen.type(Key.ENTER);

        Thread.sleep(1500);
        makeScreenShot();

        screen.type("бубу");
        Thread.sleep(1000);
        makeScreenShot();

        System.out.println("Handled invalid input.");
    }
}

