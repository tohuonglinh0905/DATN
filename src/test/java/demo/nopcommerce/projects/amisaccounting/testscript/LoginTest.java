package demo.nopcommerce.projects.amisaccounting.testscript;

import demo.nopcommerce.annotations.MisaAnnotation;
import demo.nopcommerce.common.TestBase;
import demo.nopcommerce.constants.AuthorType;
import demo.nopcommerce.constants.CategoryType;
import demo.nopcommerce.driver.DriverManager;
import demo.nopcommerce.driver.TargetFactory;
import demo.nopcommerce.projects.amisaccounting.dataprovider.LoginDataProvider;
import demo.nopcommerce.projects.amisaccounting.models.LoginModel;
import demo.nopcommerce.projects.amisaccounting.page.HomePage;
import demo.nopcommerce.projects.amisaccounting.page.LoginPage;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ThreadGuard;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;

@Epic("Regression Test")
@Feature("Login Function")
public class LoginTest extends TestBase {
    LoginPage loginPage;
    HomePage homePage;

    @BeforeMethod(alwaysRun = true)
    public void createDriver(@Optional("chrome") String browser) {
        WebDriver driver = ThreadGuard.protect(new TargetFactory().createInstance(browser));
        DriverManager.setDriver(driver);

        homePage = new HomePage(DriverManager.getDriver());
        loginPage = homePage.gotoLoginPage();
    }

    @AfterMethod(alwaysRun = true)
    public void closeDriver() {
        DriverManager.quit();
    }

    @MisaAnnotation(category = {CategoryType.REGRESSION}, author = {AuthorType.Vincent}, reviewer = {AuthorType.Vincent})
    @Test(priority = 1, description = "Kiểm tra đăng nhập với danh sách tài khoản hợp lệ", dataProvider = "LoginFunction", dataProviderClass = LoginDataProvider.class)
    public void SMECould_Login_ValidAccount_001(LoginModel loginModel) {
        loginPage.clickLoginWithEmailLink();
        loginPage.loginWithEmail(loginModel);

    }

}
