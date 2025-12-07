package at;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private static final Properties properties = new Properties();

    static {
        try (InputStream input = ConfigReader.class.getClassLoader().getResourceAsStream("conf.prop")) {
            if (input == null) {
                throw new RuntimeException("Cannot find conf.prop in resources folder");
            }
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load conf.prop", e);
        }
    }

    public static String getProp(String key) {
        return properties.getProperty(key);
    }

    // тест
    public static void main(String[] args) {
        System.out.println(getProp("base.url"));
        System.out.println(getProp("browserType"));
    }
}
