package demo.nopcommerce.common;

import com.aventstack.extentreports.Status;
import demo.nopcommerce.annotations.MisaAnnotation;
import demo.nopcommerce.annotations.TFSLink;
import demo.nopcommerce.constants.AuthorType;
import demo.nopcommerce.constants.CategoryType;
import demo.nopcommerce.constants.FrameworkConst;
import demo.nopcommerce.driver.DriverManager;
import demo.nopcommerce.helpers.CaptureHelpers;
import demo.nopcommerce.helpers.Retry;
import demo.nopcommerce.report.ExtentReportManager;
import demo.nopcommerce.report.ExtentTestManager;
import demo.nopcommerce.utils.BrowserInfoUtils;
import demo.nopcommerce.utils.IconUtils;
import demo.nopcommerce.utils.Log;
import lombok.var;
import org.apache.logging.log4j.util.Strings;
import org.testng.*;

import java.util.Objects;

/*
 * Purpose: Implement the testing listener
 * Datetime:
 */
public class TestListener implements ITestListener, ISuiteListener, IInvokedMethodListener, IConfigurationListener {
    static int totalTCs;
    static int passedTCs;
    static int skippedTCs;
    static int failedTCs;

    private String separateItem = "\n---------------------------------------------------------------";

    public String getTestName(ITestResult result) {
        return result.getTestName() != null ? result.getTestName() : result.getMethod().getConstructorOrMethod().getName();
    }

    public String getTestDescription(ITestResult result) {
        return result.getMethod().getDescription() != null ? result.getMethod().getDescription() : Strings.EMPTY;
    }

    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        // System.out.println("beforeInvocation ------" + method.getTestMethod().getMethodName() + " -- " + testResult.getTestName());
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        //System.out.println("afterInvocation ------" + method.getTestMethod().getMethodName() + " -- " + testResult.getTestName());
    }

    @Override
    public void onStart(ISuite iSuite) {
        Log.info(String.format("%s\nTestListener: TESTING FOR TEST SUITE: %s%s", separateItem, iSuite.getName(), separateItem));
        iSuite.setAttribute("WebDriver", DriverManager.getDriver());
        //Starting record video
        if (FrameworkConst.video_record.trim().equalsIgnoreCase(FrameworkConst.YES)) {
            CaptureHelpers.startRecord(iSuite.getName());
        }
        ExtentReportManager.initReports();
    }

    @Override
    public void onFinish(ISuite iSuite) {
        Log.info(String.format("\nTestListener: FINISH TESTING FOR TEST SUITE: %s %s", iSuite.getName(), separateItem));
        ExtentReportManager.flushReports();
        //Stop recording the video
        if (FrameworkConst.video_record.trim().equalsIgnoreCase(FrameworkConst.YES)) {
            CaptureHelpers.stopRecord();
        }
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        Log.info(String.format("%s\nTestListener: START TC:  %s", separateItem, getTestName(iTestResult)));
        totalTCs = increaseTestNum(totalTCs);

        ExtentTestManager.unload();
        addTestToExtentReport(iTestResult);
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        Log.info(String.format("\nTestListener: COMPLETED TC: %s - PASS %s", getTestName(iTestResult), separateItem));
        updateRetryTestName(iTestResult);

        passedTCs = increaseTestNum(passedTCs);

        if (FrameworkConst.screenshot_passed_steps.equals(FrameworkConst.YES)) {
            CaptureHelpers.captureScreenshot(DriverManager.getDriver(), getTestName(iTestResult));
        }

        //ExtentReports log operation for passed tests.
        ExtentReportManager.logMessage(Status.PASS, "Test case: " + getTestName(iTestResult) + " - PASS");
        ExtentReportManager.unloadTest();
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        Log.info(String.format("\nTestListener: COMPLETED TC: %s - FAIL %s", getTestName(iTestResult), separateItem));
        updateRetryTestName(iTestResult);
        failedTCs = increaseTestNum(failedTCs);
        if (FrameworkConst.screenshot_failed_steps.equals(FrameworkConst.YES)) {
            CaptureHelpers.captureScreenshot(DriverManager.getDriver(), getTestName(iTestResult));
        }
        Log.error("FAILED !! Screenshot for test case: " + getTestName(iTestResult));
        if (ExtentTestManager.getExtentTest() == null) {
            addTestToExtentReport(iTestResult);
        }

        //Extent report screenshot file and log
        ExtentReportManager.addScreenShot(Status.FAIL, getTestName(iTestResult));
        ExtentReportManager.logMessage(Status.FAIL, "Test case: " + getTestName(iTestResult) + " - FAIL");
        if (Objects.nonNull(iTestResult.getThrowable())) {
            Log.error(iTestResult.getThrowable());
            ExtentReportManager.logMessage(Status.FAIL, iTestResult.getThrowable());
        }
        ExtentReportManager.unloadTest();
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        Log.info(String.format("\nTestListener: COMPLETED TC: %s - SKIP %s", getTestName(iTestResult), separateItem));

        skippedTCs = increaseTestNum(skippedTCs);
        updateRetryTestName(iTestResult);

        if (FrameworkConst.screenshot_skipped_steps.equals(FrameworkConst.YES)) {
            CaptureHelpers.captureScreenshot(DriverManager.getDriver(), getTestName(iTestResult));
        }

        if (ExtentTestManager.getExtentTest() == null) {
            addTestToExtentReport(iTestResult);
        }

        ExtentReportManager.logMessage(Status.SKIP, "Test case: " + getTestName(iTestResult) + " - SKIP");
        ExtentReportManager.unloadTest();
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        if (ExtentTestManager.getExtentTest() == null) {
            addTestToExtentReport(iTestResult);
        }
        Log.error("Test failed but it is in defined success ratio " + getTestName(iTestResult));
        ExtentReportManager.logMessage("Test failed but it is in defined success ratio " + getTestName(iTestResult));
        ExtentReportManager.unloadTest();
    }

    public AuthorType[] getAuthorType(ITestResult iTestResult) {
        if (iTestResult.getMethod().getConstructorOrMethod().getMethod().getAnnotation(MisaAnnotation.class) == null) {
            return null;
        }
        AuthorType authorType[] = iTestResult.getMethod().getConstructorOrMethod().getMethod().getAnnotation(MisaAnnotation.class).author();
        return authorType;
    }

    public CategoryType[] getCategoryType(ITestResult iTestResult) {
        if (iTestResult.getMethod().getConstructorOrMethod().getMethod().getAnnotation(MisaAnnotation.class) == null) {
            return null;
        }
        CategoryType categoryType[] = iTestResult.getMethod().getConstructorOrMethod().getMethod().getAnnotation(MisaAnnotation.class).category();
        return categoryType;
    }

    @Override
    public void onConfigurationSuccess(ITestResult tr) {
        String className = tr.getTestClass().getName();
        ExtentReportManager.logMessage(Status.WARNING, "Configuration: " + getTestName(tr) + " - PASS");
        ExtentReportManager.unloadTest();
        ExtentReportManager.removeTest(tr.getName() + " " + className.substring(className.lastIndexOf(".") + 1));
    }

    @Override
    public void onConfigurationFailure(ITestResult tr) {
        ExtentReportManager.addScreenShot(Status.WARNING, getTestName(tr));
        ExtentReportManager.logMessage(Status.WARNING, "Configuration: " + getTestName(tr) + " - FAIL");
        if (Objects.nonNull(tr.getThrowable())) {
            Log.error(tr.getThrowable());
            ExtentReportManager.logMessage(Status.WARNING, tr.getThrowable());
        }
        ExtentReportManager.unloadTest();
    }

    @Override
    public void onConfigurationSkip(ITestResult tr) {
        ExtentReportManager.logMessage(Status.WARNING, "Configuration: " + getTestName(tr) + " - SKIP");
        ExtentReportManager.unloadTest();
    }

    @Override
    public void beforeConfiguration(ITestResult tr) {
        String className = tr.getTestClass().getName();
        ExtentReportManager.createTest(tr.getName() + " " + className.substring(className.lastIndexOf(".") + 1));
        ExtentReportManager.logMessage(Status.WARNING, "START - Configuration: " + getTestName(tr));
    }

    private void updateRetryTestName(ITestResult iTestResult) {
        String oldName = getTestName(iTestResult);
        if (Objects.nonNull(ExtentTestManager.getExtentTest())) {
            String extendName = ExtentTestManager.getExtentTest().getModel().getName();
            String newName = extendName.replace(oldName, iTestResult.getName());
            ExtentTestManager.getExtentTest().getModel().setName(newName);
        }
    }

    private int increaseTestNum(int current) {
        int retryStatus = Retry.getRetryStatus();
        if (Objects.equals(retryStatus, ITestResult.CREATED) || Objects.equals(retryStatus, ITestResult.SUCCESS))
            return current + 1;
        return current;
    }

    private void addTestToExtentReport(ITestResult iTestResult) {
        String browser = iTestResult.getTestContext().getCurrentXmlTest().getParameter("browser");
        if (Objects.isNull(browser)) browser = FrameworkConst.BROWSER.toUpperCase();
        else browser = browser.trim().toUpperCase();

        var author = getAuthorType(iTestResult);
        var des = (author.length > 0 ? (author[0] + " - ") : Strings.EMPTY) + getTestDescription(iTestResult);
        if  (iTestResult.getAttributeNames().contains("invocation"))
            des = des + " - Invocation " + iTestResult.getAttribute("invocation");
        ExtentReportManager.createTest(iTestResult.getName(), des, browser);
        ExtentReportManager.addAuthors(author);
        String nameTestClass = iTestResult.getTestClass().getName();
        ExtentReportManager.addCategory(nameTestClass.substring(nameTestClass.lastIndexOf(".") + 1));
        ExtentReportManager.addDevices(browser);
        ExtentReportManager.addTFSLink(getTFSLink(iTestResult));
        ExtentReportManager.info(FrameworkConst.BOLD_START + IconUtils.getOSIcon() + " " + BrowserInfoUtils.getOSInfo() + FrameworkConst.BOLD_END);
    }

    public String getTFSLink(ITestResult iTestResult) {
        if (iTestResult.getMethod().getConstructorOrMethod().getMethod().getAnnotation(TFSLink.class) == null) {
            return null;
        }
        return iTestResult.getMethod().getConstructorOrMethod().getMethod().getAnnotation(TFSLink.class).value();
    }
}
