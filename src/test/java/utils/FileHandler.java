package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;
import static java.nio.file.FileSystems.getDefault;

public class FileHandler {

    static Properties properties = new Properties();//instantiating Properties class

    //To make code more dynamic and portable need to build file path dynamically
    //Fs will get the system separator e.g. / or \
    //dir will get the user directory
    public static String Fs = getDefault().getSeparator();
    public static String dir = System.getProperty("user.dir");
    public static String ResourcesPath = dir + Fs + "src" + Fs + "test" + Fs + "resources" + Fs;
    public static String ConfigFile = ResourcesPath + "config.properties";

        public static String reports = ResourcesPath + "Reports" + Fs;
        public static String screenshotPath = ResourcesPath + "ScreenshotsFolder" + Fs;

    public static String getConfigProperty(String key) {
        String result = "";
        try {
            FileInputStream fis = new FileInputStream(ConfigFile);
            properties.load(fis); //loads properties from file
            result = properties.getProperty(key);
        } catch (Exception ex) {
            Base.logger.error("Error reading property from config file {}", ex.getMessage());

        }
        return result;
    }

    public static String getProperty(String propKey) throws FileNotFoundException {
        String results = properties.getProperty(propKey);
        return results;
    }


}
