package utils;

import static utils.Base.driver;

public class NavigationUtils {
    public static void getTestingSiteUrl(String propertykey) {
        try {
            String url  = FileHandler.getProperty(propertykey);
            if(url == null){
                Base.logger.error("URL is empty for property key: {}", propertykey);
                return;
            }
            driver.get(url);
            Base.logger.info("Opening {}",url);
        }catch (Exception ex){
            Base.logger.error("There was an error opening the url {}", ex.getMessage());
        }
    }
}
