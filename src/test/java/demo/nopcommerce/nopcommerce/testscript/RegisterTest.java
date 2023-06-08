package demo.nopcommerce.nopcommerce.testscript;


import demo.nopcommerce.annotations.MisaAnnotation;
import demo.nopcommerce.common.TestBase;
import demo.nopcommerce.constants.AuthorType;
import demo.nopcommerce.constants.CategoryType;
import demo.nopcommerce.driver.DriverManager;
import demo.nopcommerce.driver.TargetFactory;
import demo.nopcommerce.nopcommerce.dataprovider.LoginDataProvider;
import demo.nopcommerce.nopcommerce.page.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ThreadGuard;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;

public class RegisterTest extends TestBase {
    RegisterPage registerPage;
    HomePage homePage;
    LoginPage loginPage;
    MyAccount myAccount;
    Search_and_Advanced_Search search;
    Wishlist wishlist;
    Addresses addresses;
    String firstName = "linh";
    String lastName = "to";
    String email = "linhth3@gmail.com";
    String email01="linh#1234";
    String pass = "123456";
    String pass01 ="123";
    String company = "linhto0905";
    String Search = "Lenovo";
    String city = "HaNoi";
    String address1 = "Đống Đa";
    String address2 = "Khương Thượng";
    String ZipPostalCode = "01928284";
    String number = "019283748";

    @BeforeMethod(alwaysRun = true)
    public void createDriver(@Optional("chrome") String browser) {
        WebDriver driver = ThreadGuard.protect(new TargetFactory().createInstance(browser));
        DriverManager.setDriver(driver);

        homePage = new HomePage(DriverManager.getDriver());
//        registerPage = homePage.registerPage();
    }

    @AfterMethod(alwaysRun = true)
    public void closeDriver() {
//        DriverManager.quit();
    }
    @MisaAnnotation(category = {CategoryType.REGRESSION}, author = {AuthorType.Linh}, reviewer = {AuthorType.Linh})
//    @Test(priority = 1, description = "Đăng ký tài khoản chưa nập các trường")
    @Test(priority = 1)
    public void RegisterPage_01()
    {
        registerPage = homePage.registerPage();
        registerPage.createAccount01();
    }
    @MisaAnnotation(category = {CategoryType.REGRESSION}, author = {AuthorType.Linh}, reviewer = {AuthorType.Linh})
//    @Test(priority = 2, description = "Đăng ký tài khoản sai định dạng email")
    @Test(priority = 2)
    public void RegisterPage_02()
    {
        registerPage = homePage.registerPage();
        registerPage.createAccount02(firstName, lastName, email01, pass);
    }
    @MisaAnnotation(category = {CategoryType.REGRESSION}, author = {AuthorType.Linh}, reviewer = {AuthorType.Linh})
//    @Test(priority = 3, description = "Đăng ký tài khoản khi nhập đầy đủ các trường")
    @Test(priority = 3, description = " login website", dataProvider = "login", dataProviderClass = LoginDataProvider.class)
    public void RegisterPage_03() {
        registerPage = homePage.registerPage();
        registerPage.createAccount(firstName, lastName, email, pass);
    }
    @MisaAnnotation(category = {CategoryType.REGRESSION}, author = {AuthorType.Linh}, reviewer = {AuthorType.Linh})
//    @Test(priority = 4, description = "Mật khẩu ít hơn 6 kí tự")
    @Test(priority = 4)
    public void RegisterPage_04() {
        registerPage = homePage.registerPage();
        registerPage.createAccount03(firstName, lastName, email, pass01);
    }
    @MisaAnnotation(category = {CategoryType.REGRESSION}, author = {AuthorType.Linh}, reviewer = {AuthorType.Linh})
//    @Test(priority = 1, description = "Đăng nhập tài khoản với đầy đủ các trường")
    @Test(priority = 1)
    public void Login_01() {
        registerPage = homePage.registerPage();
        registerPage.createAccount(firstName, lastName, email, pass);
        loginPage = registerPage.goToLoginPage();
        homePage = loginPage.loginPage(email, pass);
    }

    @MisaAnnotation(category = {CategoryType.REGRESSION}, author = {AuthorType.Linh}, reviewer = {AuthorType.Linh})
//    @Test(priority = 1, description = "Cập nhật đầy đủ thông tin cá nhân")
    @Test(priority = 1)
    public void MyAccount_01() {
        registerPage = homePage.registerPage();
        registerPage.createAccount(firstName, lastName, email, pass);
        loginPage = registerPage.goToLoginPage();
        homePage = loginPage.loginPage(email, pass);
        myAccount = registerPage.goToMyAccountPage();
        myAccount.createMyAccount(firstName, lastName, email, company);}
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
    @Test(priority = 1)
    public void WishList_01() {
        homePage = homePage.gotoHomePage();
        wishlist = homePage.goToWishlistPage();
        wishlist.ClickWishList01();
    }

    @MisaAnnotation(category = {CategoryType.REGRESSION}, author = {AuthorType.Linh}, reviewer = {AuthorType.Linh})
//    @Test(priority = 2, description = "Kiểm tra danh sách yêu thích khi nhập đầu đủ Ram và HDD")
    @Test(priority = 2)
    public void WishList_02() {
        homePage = homePage.gotoHomePage();
        wishlist = homePage.goToWishlistPage();
        wishlist.ClickWishList02();
    }

    @MisaAnnotation(category = {CategoryType.REGRESSION}, author = {AuthorType.Linh}, reviewer = {AuthorType.Linh})
//    @Test(priority = 1, description = "Thêm địa chỉ mới khi đã login tài khoản")
    @Test(priority = 1)
    public void Addresses() {
        registerPage = homePage.registerPage();
        registerPage.createAccount(firstName, lastName, email, pass);
        loginPage = registerPage.goToLoginPage();
        homePage = loginPage.loginPage(email, pass);
        addresses = homePage.goToAddressesPage();
        addresses.createAddresses(firstName, lastName, email, company, city, address1, address2, ZipPostalCode, number);
    }

}
