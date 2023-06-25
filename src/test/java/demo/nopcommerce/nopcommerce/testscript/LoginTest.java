package demo.nopcommerce.nopcommerce.testscript;

import demo.nopcommerce.annotations.MisaAnnotation;
import demo.nopcommerce.common.TestBase;
import demo.nopcommerce.constants.AuthorType;
import demo.nopcommerce.constants.CategoryType;
import demo.nopcommerce.driver.DriverManager;
import demo.nopcommerce.driver.TargetFactory;
import demo.nopcommerce.nopcommerce.dataprovider.LoginDataProvider;
import demo.nopcommerce.nopcommerce.models.LoginModel;
import demo.nopcommerce.nopcommerce.page.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ThreadGuard;
import org.testng.annotations.*;

public class LoginTest extends TestBase {
    RegisterPage registerPage;
    HomePage homePage;
    LoginPage loginPage;
    MyAccount myAccount;
    Search_and_Advanced_Search search;
    Wishlist wishlist;
    Addresses addresses;
    String Search = "Lenovo";
    String city = "HaNoi";
    String address1 = "Đống Đa";
    String address2 = "Khương Thượng";
    String ZipPostalCode = "01928284";
    String number = "019283748";
    String firstName = "linh";
    String lastName = "to";
    String email = "linhth3@gmail.com";

    String company = "linhto0905";
    @BeforeClass(alwaysRun = true)
    public void createDriver(@Optional("chrome") String browser) {
        WebDriver driver = ThreadGuard.protect(new TargetFactory().createInstance(browser));
        DriverManager.setDriver(driver);

        homePage = new HomePage(DriverManager.getDriver());
//        registerPage = homePage.registerPage();
//    }
    }
//    @BeforeTest(alwaysRun = true)
//    public void beforeMothod(LoginModel loginModel){
//        homePage = new HomePage(DriverManager.getDriver());
//
//
//    }

    @AfterMethod(alwaysRun = true)
    public void closeDriver() {
//        DriverManager.quit();
    }


    @MisaAnnotation(category = {CategoryType.REGRESSION}, author = {AuthorType.Linh}, reviewer = {AuthorType.Linh})
//    @Test(priority = 1, description = "Đăng nhập tài khoản với đầy đủ các trường")
    @Test(priority = 1,dataProvider = "login", dataProviderClass = LoginDataProvider.class)
    public void Login_01(LoginModel loginModel) {
        registerPage = homePage.registerPage();
        registerPage.createAccount(loginModel);
        loginPage = registerPage.goToLoginPage();
        homePage = loginPage.loginPage(loginModel);
    }
    @MisaAnnotation(category = {CategoryType.REGRESSION}, author = {AuthorType.Linh}, reviewer = {AuthorType.Linh})
//    @Test(priority = 1, description = "Cập nhật đầy đủ thông tin cá nhân")
    @Test(priority = 2,dataProvider = "login", dataProviderClass = LoginDataProvider.class)
    public void MyAccount_01(LoginModel loginModel) {
        registerPage = homePage.registerPage();
        registerPage.createAccount(loginModel);
        loginPage = registerPage.goToLoginPage();
        homePage = loginPage.loginPage(loginModel);
        myAccount = registerPage.goToMyAccountPage();
        myAccount.createMyAccount(firstName, lastName, email, company);
    }

    @MisaAnnotation(category = {CategoryType.REGRESSION}, author = {AuthorType.Linh}, reviewer = {AuthorType.Linh})
//    @Test(priority = 1, description = "Tìm ")
    @Test(priority = 1)
    public void Search_01() {
        homePage = homePage.gotoHomePage();
        search = homePage.goToMySearchPage();
        search.Advanced_Search(Search);
//        wishlist = registerPage.goToWishlistPage();
//        wishlist.WishList();
    }

    @MisaAnnotation(category = {CategoryType.REGRESSION}, author = {AuthorType.Linh}, reviewer = {AuthorType.Linh})
//    @Test(priority = 1, description = "Kiểm tra danh sách yêu thích khi không click vào Ram và HDD")
    @Test(priority = 4)
    public void WishList_01() {
        homePage = homePage.gotoHomePage();
        wishlist = homePage.goToWishlistPage();
        wishlist.ClickWishList01();
    }

    @MisaAnnotation(category = {CategoryType.REGRESSION}, author = {AuthorType.Linh}, reviewer = {AuthorType.Linh})
//    @Test(priority = 2, description = "Kiểm tra danh sách yêu thích khi nhập đầu đủ Ram và HDD")
    @Test(priority = 5)
    public void WishList_02() {
        homePage = homePage.gotoHomePage();
        wishlist = homePage.goToWishlistPage();
        wishlist.ClickWishList02();
    }

    @MisaAnnotation(category = {CategoryType.REGRESSION}, author = {AuthorType.Linh}, reviewer = {AuthorType.Linh})
//    @Test(priority = 1, description = "Thêm địa chỉ mới khi đã login tài khoản")
    @Test(priority = 3,dataProvider = "login", dataProviderClass = LoginDataProvider.class)
    public void Addresses(LoginModel loginModel) {
        registerPage = homePage.registerPage();
        registerPage.createAccount(loginModel);
        loginPage = registerPage.goToLoginPage();
        homePage = loginPage.loginPage(loginModel);
        addresses = homePage.goToAddressesPage();
        addresses.createAddresses(firstName, lastName, email, company, city, address1, address2, ZipPostalCode, number);
    }


}
