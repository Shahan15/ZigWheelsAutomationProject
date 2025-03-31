package utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class WebScrapingUtils {

    static WebDriver driver = Base.driver;

    public WebScrapingUtils() {
        driver.get(NavigationUtils.getTestingSiteUrl("filteredBikes"));
    }


    public static String  convertPrice(String priceClean) {
        String cleaned = priceClean.replace("Rs.", "").trim();

        if(cleaned.contains("Lakh")){
            cleaned = cleaned.replace("Lakh","").trim();
            int converted = (int) (Double.parseDouble(cleaned) * 100000);//convert to rupees
            return String.valueOf(converted);
        } else if (cleaned.equalsIgnoreCase("Price to be announced")) {
            return "Price to be announced";
        } else {
            cleaned = cleaned.replace(",","");
            int converted = Integer.parseInt(cleaned);
            return String.valueOf(converted);
        }
    }


    public static List<Bike> webScraper() {
        List<WebElement> bikeNamesElement = driver.findElements(By.cssSelector("a[data-track-label='model-name'] strong"));
        List<WebElement> bikePriceElement = driver.findElements(By.cssSelector("div.b.fnt-15"));
        List<WebElement> bikeLaunchDateElement = driver.findElements(By.cssSelector(".clr-try.fnt-14")); // Fetching launch dates
        Base.logger.info("Pulling Bike model names, prices, and expected launch dates from site");

        List<Bike> bikes = new ArrayList<>();

        for (int i = 0; i < bikeNamesElement.size(); i++) {
            String bikeName = bikeNamesElement.get(i).getText();
            String bikePrice = bikePriceElement.get(i).getText();
            String launchDate = bikeLaunchDateElement.get(i).getText(); // Get the launch date
            bikes.add(new Bike(bikeName, bikePrice, launchDate));
        }

        return bikes;
    }

    public static void writeToExcel() {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Bikes");

        // Create header row
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("Bike Name");
        header.createCell(1).setCellValue("Price");
        header.createCell(2).setCellValue("Expected Launch Date");

        List<Bike> bikes = webScraper();


        // Populate rows in the sheet for bikes under 4 lakh
        int rowIndex = 1; // Start from row 1 (row 0 is the header)
        for (Bike bike: bikes) {

            String convertedPrice = convertPrice(bike.getPrice());

            // Only include bikes with price under 4 lakh or "Price to be announced"
            if (convertedPrice.equalsIgnoreCase("Price to be announced")) {
                Row row = sheet.createRow(rowIndex++);
                row.createCell(0).setCellValue(bike.getName()); // Bike Name
                row.createCell(1).setCellValue(convertedPrice); // "Price to be announced"
                row.createCell(2).setCellValue(bike.getLaunchDate()); // Launch Date
            } else {
                int numericPrice = Integer.parseInt(convertedPrice);
                if (numericPrice < 400000) { // Check if price is under 4 lakh
                    Row row = sheet.createRow(rowIndex++);
                    row.createCell(0).setCellValue(bike.getName()); // Bike Name
                    row.createCell(1).setCellValue("Rs. " + convertedPrice); // Converted Price
                    row.createCell(2).setCellValue(bike.getLaunchDate()); // Launch Date
                }
            }
        }

        // Write the data to an Excel file
        try {
            FileOutputStream fileOut = new FileOutputStream("FilteredBikesData.xlsx");
            workbook.write(fileOut);
            fileOut.close();
            workbook.close();
            Base.logger.info("Bike data successfully written to FilteredBikesData.xlsx");
        } catch (Exception e) {
            Base.logger.error("Error while writing bike data to Excel file: " + e.getMessage());
        }
    }
}

