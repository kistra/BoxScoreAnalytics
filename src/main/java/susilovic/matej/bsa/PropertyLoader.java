package susilovic.matej.bsa;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyLoader {

    public static String getProperty(String key) {

        Properties properties = new Properties();
        InputStream input = null;

        try {
            input = PropertyLoader.class.getResourceAsStream("/config.properties");
            properties.load(input);

            return properties.getProperty(key);

        } catch (IOException e) {
            throw new RuntimeException("Error reading property " + key, e);

        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {

        System.out.println(getProperty("dburl"));
    }
}
