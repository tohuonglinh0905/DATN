package demo.nopcommerce.nopcommerce.testscript;


import demo.nopcommerce.annotations.MisaAnnotation;
import demo.nopcommerce.common.TestBase;
import demo.nopcommerce.constants.AuthorType;
import demo.nopcommerce.constants.CategoryType;
import demo.nopcommerce.driver.DriverManager;
import demo.nopcommerce.driver.TargetFactory;
import demo.nopcommerce.nopcommerce.dataprovider.LoginDataProvider;
import demo.nopcommerce.nopcommerce.dataprovider.RegisterDataProvider;
import demo.nopcommerce.nopcommerce.models.LoginModel;
import demo.nopcommerce.nopcommerce.page.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ThreadGuard;
import org.testng.annotations.*;

public class RegisterTest extends TestBase {
    RegisterPage registerPage;
    HomePage homePage;
    LoginPage loginPage;
    MyAccount myAccount;

    String lastName = "to";
    String email = "linhth3@gmail.com";
    String email01="linh#1234";
    String pass = "123456";
    String pass01 ="123";
    String company = "linhto0905";


    @BeforeClass(alwaysRun = true)
    public void createDriver(@Optional("chrome") String browser) {
        WebDriver driver = ThreadGuard.protect(new TargetFactory().createInstance(browser));
        DriverManager.setDriver(driver);

        //homePage = new HomePage(DriverManager.getDriver());
//        registerPage = homePage.registerPage();
    }
    @BeforeMethod(alwaysRun = true)
    public void beforeMothod(){
        homePage = new HomePage(DriverManager.getDriver());

    }

    @AfterMethod(alwaysRun = true)
    public void closeDriver() {
//        DriverManager.quit();
    }

    @MisaAnnotation(category = {CategoryType.REGRESSION}, author = {AuthorType.Linh}, reviewer = {AuthorType.Linh})
//    @Test(priority = 3, description = "Đăng ký tài khoản khi nhập đầy đủ các trường")
    @Test(priority = 1, description = " create account ", dataProvider = "register", dataProviderClass = RegisterDataProvider.class)
    public void RegisterPage_03(LoginModel loginModel) {
        registerPage = homePage.registerPage();
        registerPage.createAccount(loginModel);
    }

}
