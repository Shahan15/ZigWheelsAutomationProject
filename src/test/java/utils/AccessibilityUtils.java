package utils;

import com.aventstack.extentreports.ExtentTest;
import com.deque.html.axecore.results.Results;
import com.deque.html.axecore.selenium.AxeBuilder;

public class AccessibilityUtils {

    public void axeHome(ExtentTest test, String pageName) {
        AxeBuilder axeBuilder = new AxeBuilder()
                .exclude(".fc-dialog"); //cookiesconsent iframe

        try {
            Results axeResults = axeBuilder.disableIframeTesting().analyze(Base.getDriver());

            if (axeResults.violationFree()) {
                test.pass("No accessibility violations found for page: " + pageName);
            } else {
                test.fail("Accessibility violations detected for page: " + pageName);
                axeResults.getViolations().forEach(violation -> {
                    test.info("Violation: " + violation.getDescription());
                    test.info("Impact: " + violation.getImpact());
                    test.info("Tags: " + violation.getTags());
                });

                throw new Exception("Accessibility violations found for page: " + pageName + ". See Extent Reports for details.");
            }
        } catch (Exception e) {
            test.fail("Error during accessibility testing for page: " + pageName + " - " + e.getMessage());
        }
    }

}
