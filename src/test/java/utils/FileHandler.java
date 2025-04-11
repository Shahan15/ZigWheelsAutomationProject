package utils;

import java.io.File;
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

    public static String resourcesPath = dir + Fs + "src" + Fs + "test" + Fs + "resources" + Fs;
    public static String configFile = resourcesPath + "config.properties";

    public static String reports = resourcesPath + "Reports" + Fs;
    public static String screenshotPath = resourcesPath + "ScreenshotsFolder" + Fs;

    /**
     *
     * @param key Config property we want to get
     * @return The results i.e. config property
     */
    public static String getConfigProperty(String key) {
        String result = "";
        try {
            FileInputStream fis = new FileInputStream(configFile);
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


    /**
     * @param fileName Excel Sheet we want to check for existence
     * @return True or False if file exists
     */
    public static boolean doesExcelSheetExist(String fileName) {
        File file = new File(fileName);
        return file.exists();
    }

}
