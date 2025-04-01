package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import static utils.Base.logger;

public class TestListener implements ITestListener {
    private ExtentReports extent;
    private ExtentTest test;

    @Override
    public void onStart(ITestContext context) {
        // Initialize ExtentReports using ReportUtils
        extent = ReportUtils.setUpExtentReport();
        logger.info("Test suite started: {}", context.getName());

        // Log context name into the report
        extent.createTest("Test Suite Context: " + context.getName())
                .log(Status.INFO, "Test suite started.");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        // Create test entry and log success
        String testName = "PASS: " + result.getName();
        test = extent.createTest(testName);
        test.log(Status.PASS, "Results are expected");
        logger.info("Test Passed: {}", testName);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        // Create test entry and log failure
        String testName = "FAIL: " + result.getName();
        test = extent.createTest(testName);
        test.log(Status.FAIL, "Results are NOT what was expected");
        logger.error("Test Failed: {}", testName);
         try {
             String SSPath = ReportUtils.takeScreenshot("TestFailure Screenshot");
             test.addScreenCaptureFromPath(SSPath);
         } catch (Exception e) {
             logger.error("Error attaching screenshot: {}", e.getMessage());
         }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        // Create test entry and log skipped test
        String testName = "SKIPPED: " + result.getName();
        test = extent.createTest(testName);
        test.log(Status.SKIP, "This test was not executed, it was skipped");
        logger.warn("Test Skipped: {}", testName);
    }

    @Override
    public void onFinish(ITestContext context) {
        // Flush the ExtentReports and log suite summary
        extent.flush();
        logger.info("Test suite finished. Passed: {}, Failed: {}, Skipped: {}",
                context.getPassedTests().size(),
                context.getFailedTests().size(),
                context.getSkippedTests().size());
    }
}
