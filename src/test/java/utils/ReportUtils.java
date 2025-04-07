package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;


public class ReportUtils {
    public static ExtentReports extent;
    private static ExtentTest test;


    public static String getTimeStamp() {
        return new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
    }

    public static ExtentReports setUpExtentReport(String testName) {
        if (extent == null) {
            // Build a file name that includes the test method name
            String fileName = "TestReport_" + testName + "_" + getTimeStamp() + ".html";
            String filePath = FileHandler.reports + fileName;

            ExtentSparkReporter htmlReporter = new ExtentSparkReporter(filePath);
            htmlReporter.config().setDocumentTitle("Automation Report");
            htmlReporter.config().setReportName("Test Results - " + testName);
            htmlReporter.config().setTheme(Theme.STANDARD);

            extent = new ExtentReports();
            extent.attachReporter(htmlReporter);
        }
        return extent;
    }



    public static ExtentTest createTest(String testName) {
        if (extent == null) {
            throw new IllegalStateException("ExtentReports is not initialised. Call setUpExtentReport() first.");
        }
        test = extent.createTest(testName);
        Base.logger.info("Started test: " + testName);
        return test;
    }

    public static ExtentTest getTest() {
        return test;
    }

    public static void flushReport() {
        if (extent != null) {
            extent.flush();
            Base.logger.info("Extent reports flushed");
        }
    }

    public static String takeScreenshot(String screenshotName) {
        File SS = ((TakesScreenshot) Base.getDriver()).getScreenshotAs(OutputType.FILE);
        String SFile = FileHandler.screenshotPath + screenshotName + "_" + "img"+ getTimeStamp() + ".png";
        try {
            FileUtils.copyFile(SS,new File(SFile));
        } catch (Exception e) {
            Base.logger.error("Error while taking a screenshot {}",e.getMessage());
        }
        return SFile;
    }


}
