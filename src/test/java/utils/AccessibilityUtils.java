package utils;

import com.aventstack.extentreports.ExtentTest;
import com.deque.html.axecore.results.Results;
import com.deque.html.axecore.selenium.AxeBuilder;

public class AccessibilityUtils {

    /**
     * @param test The ExtentTest instance used for reporting.
     * @param pageName The name of the page being tested.
     */
    public void axeHome(ExtentTest test, String pageName) {
        AxeBuilder axeBuilder = new AxeBuilder()
                .exclude(".fc-dialog"); //cookies iframe

        try {
            Results axeResults = axeBuilder.disableIframeTesting().analyze(Base.getDriver());

            if (axeResults.violationFree()) {
                test.pass("No accessibility violations found for page: " + pageName);
            } else {
                test.fail("Accessibility violations detected for page: " + pageName);
                axeResults.getViolations().forEach(violation -> {
                    test.info("Violation: " + violation.getDescription());
                    //specific issue
                    test.info("Impact: " + violation.getImpact());
                    //how big of an issue it is
                    test.info("Tags: " + violation.getTags());
                    //keywords to describe issue
                });

                throw new Exception("Accessibility violations found for page: " + pageName + ". See Extent Reports for details.");
            }
        } catch (Exception e) {
            test.fail("Error during accessibility testing for page: " + pageName + " - " + e.getMessage());
        }
    }

}
