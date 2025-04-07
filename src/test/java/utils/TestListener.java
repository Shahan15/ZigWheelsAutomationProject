package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import static utils.Base.logger;

public class TestListener implements ITestListener {
    private static ExtentReports extent;
    private ExtentTest test;

    public static void setExtent(ExtentReports sharedExtent) {
        extent = sharedExtent; // Set the shared instance
    }


    @Override
    public void onStart(ITestContext context) {
        logger.info("Test suite started: {}", context);
    }

    @Override
    public void onTestStart(ITestResult result) {
        String testName = result.getMethod().getMethodName(); // Get test method name
        this.test = ReportUtils.createTest(testName); // Create and assign the test node
        test.log(Status.INFO, "Starting test: " + testName);
        logger.info("Test started: {}", testName);
    }


    @Override
    public void onTestSuccess(ITestResult result) {
        test.log(Status.PASS, "Test passed: " + result.getMethod().getMethodName());
        logger.info("Test Passed: {}", result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.log(Status.FAIL, "Test failed: " + result.getMethod().getMethodName());
        logger.error("Test Failed: {}", result.getMethod().getMethodName());

        try {
            String screenshotPath = ReportUtils.takeScreenshot(result.getMethod().getMethodName());
            test.addScreenCaptureFromPath(screenshotPath);
        } catch (Exception e) {
            logger.error("Error attaching screenshot: {}", e.getMessage());
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.log(Status.SKIP, "Test skipped: " + result.getMethod().getMethodName());
        logger.warn("Test Skipped: {}", result.getMethod().getMethodName());
    }

    @Override
    public void onFinish(ITestContext context) {
        if (extent != null) {
            extent.flush(); // Use shared instance to flush the report
            logger.info("Test suite finished. Passed: {}, Failed: {}, Skipped: {}",
                    context.getPassedTests().size(),
                    context.getFailedTests().size(),
                    context.getSkippedTests().size());
        }
    }

}
