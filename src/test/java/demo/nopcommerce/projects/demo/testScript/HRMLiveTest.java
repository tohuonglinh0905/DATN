package demo.nopcommerce.projects.demo.testScript;

import demo.nopcommerce.annotations.MisaAnnotation;
import demo.nopcommerce.common.TestBase;
import demo.nopcommerce.constants.AuthorType;
import demo.nopcommerce.constants.CategoryType;
import demo.nopcommerce.driver.DriverManager;
import demo.nopcommerce.driver.TargetFactory;
import demo.nopcommerce.projects.demo.dataprovider.AddCustomFiledProvider;
import demo.nopcommerce.projects.demo.dataprovider.AddEmployeeProvider;
import demo.nopcommerce.projects.demo.dataprovider.AddReportProvider;
import demo.nopcommerce.projects.demo.model.CustomFiledModel;
import demo.nopcommerce.projects.demo.model.EmployeeModel;
import demo.nopcommerce.projects.demo.model.ReportModel;
import demo.nopcommerce.projects.demo.page.DashBoardPage;
import demo.nopcommerce.projects.demo.page.LoginPage;
import demo.nopcommerce.projects.demo.page.PimPage;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ThreadGuard;
import org.testng.annotations.*;

@Epic(" Test")
@Feature("Login Function")
public class HRMLiveTest extends TestBase {
    private WebDriver webDriver;
    private JavascriptExecutor js;
    private LoginPage loginPage;
    private DashBoardPage dashBoardPage;
    private PimPage pimPage;
    private String username = "Admin";
    private String password = "admin123";
    private String keySearch = "Vin";

    @BeforeClass(alwaysRun = true)
    public void createDriver(@Optional("chrome") String browser) {
        WebDriver driver = ThreadGuard.protect(new TargetFactory().createInstance(browser));
        DriverManager.setDriver(driver);
        loginPage = new LoginPage(DriverManager.getDriver());
        dashBoardPage = loginPage.loginIntoPage(username, password);

    }


    @AfterClass(alwaysRun = true)
    public void closeDriver() {
        DriverManager.quit();
    }

    @MisaAnnotation(category = {CategoryType.REGRESSION}, author = {AuthorType.moi}, reviewer = {AuthorType.Vincent})
    @Test(description = "login page")
    public void TC_01() {
        dashBoardPage.goPimPage();

    }


    @MisaAnnotation(category = {CategoryType.REGRESSION}, author = {AuthorType.moi}, reviewer = {AuthorType.Vincent})
    @Test(description = " add candidate", dataProvider = "addCustomField", dataProviderClass = AddCustomFiledProvider.class)
    public void TC_02(CustomFiledModel customFiledModel) {
        pimPage.selectCustomFiled();
        pimPage.addCustomerField(customFiledModel);
    }

    @BeforeMethod
    public void beforeMethod() {
        pimPage = dashBoardPage.goPimPage();
    }

    @MisaAnnotation(category = {CategoryType.REGRESSION}, author = {AuthorType.moi}, reviewer = {AuthorType.Vincent})
    @Test(description = " add employee", dataProvider = "addEmployee", dataProviderClass = AddEmployeeProvider.class)
    public void TC_03(EmployeeModel employeeModel) {

        pimPage.selectEmployeeList(employeeModel);
        pimPage.addPersonalDetails(employeeModel);
    }

    @MisaAnnotation(category = {CategoryType.REGRESSION}, author = {AuthorType.moi}, reviewer = {AuthorType.Vincent})
    @Test(description = " search employee")
    public void TC_04() {
        pimPage.searchEmployee(keySearch);
    }

    @MisaAnnotation(category = {CategoryType.REGRESSION}, author = {AuthorType.moi}, reviewer = {AuthorType.Vincent})
    @Test(description = "add report and edit it", dataProvider = "addReport", dataProviderClass = AddReportProvider.class)
    public void TC_05(ReportModel reportModel) {
        pimPage.addReport(reportModel);
        pimPage.findReport(reportModel);
    }


}
