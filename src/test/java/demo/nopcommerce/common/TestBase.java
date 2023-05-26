package demo.nopcommerce.common;

import demo.nopcommerce.driver.DriverManager;
import demo.nopcommerce.driver.TargetFactory;
import demo.nopcommerce.helpers.PropertiesHelpers;
import demo.nopcommerce.utils.Log;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ThreadGuard;
import org.testng.ITestResult;
import org.testng.annotations.*;


@Listeners({TestListener.class})
public class TestBase {
    public TestBase() {
        PropertiesHelpers.loadAllFiles();
    }

    @Parameters({"browser"})
    @BeforeSuite
    public void beforeSuite(@Optional("chrome") String browser) {
        Log.info("TestBase: beforeSuite");
    }

    @BeforeMethod(alwaysRun = true)
    public void addInvocation(ITestResult tr) {
    }

    @Parameters({"browser"})
    @BeforeClass(alwaysRun = true)
    public void createDriver(@Optional("chrome") String browser) {
        WebDriver driver = ThreadGuard.protect(new TargetFactory().createInstance(browser));
        DriverManager.setDriver(driver);
    }

    @AfterClass(alwaysRun = true)
    public void closeDriver() {
        Log.info("TestBase: Close Driver ");
        DriverManager.quit();
    }
}
