package demo.nopcommerce.nopcommerce.testscript;


import demo.nopcommerce.annotations.MisaAnnotation;
import demo.nopcommerce.common.TestBase;
import demo.nopcommerce.constants.AuthorType;
import demo.nopcommerce.constants.CategoryType;
import demo.nopcommerce.driver.DriverManager;
import demo.nopcommerce.driver.TargetFactory;
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
    String pass = "123456";
    String company = "linhto0905";
    String Search = "abc";
    String city = "HaNoi";
    String address1 = "Đống Đa";
    String address2 = "Khương Thượng";
    String ZipPostalCode= "01928284";
    String number ="019283748";
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
//    @Test(priority = 1, description = "Kiểm tra đăng nhập với danh sách tài khoản hợp lệ")
    @Test
    public void Login_Account_001() {
//        String firstName = "linh";
//        String lastName = "to";
//        String email = "linhth3@gmail.com";
//        String pass = "123456";
//        String company = "linhto0905";
//        String Search = "abc";
        registerPage = homePage.registerPage();
        registerPage.createAccount(firstName, lastName, email, pass);
        loginPage = registerPage.goToLoginPage();
        homePage = loginPage.loginPage(email, pass);
        myAccount = registerPage.goToMyAccountPage();
        myAccount.createMyAccount(firstName, lastName, email, company);
        search = registerPage.goToMySearchPage();
        search.Advanced_Search(Search);
//        wishlist = registerPage.goToWishlistPage();
//        wishlist.WishList();
    }

    @MisaAnnotation(category = {CategoryType.REGRESSION}, author = {AuthorType.Linh}, reviewer = {AuthorType.Linh})
//    @Test(priority = 1, description = "Kiểm tra danh sách yêu thích khi không click vào Ram và HDD")
    @Test
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
//    @Test(priority = 2, description = "Kiểm tra danh sách yêu thích khi nhập đầu đủ Ram và HDD")
    @Test
    public void Addresses(){
        registerPage = homePage.registerPage();
        registerPage.createAccount(firstName, lastName, email, pass);
        loginPage = registerPage.goToLoginPage();
        homePage = loginPage.loginPage(email, pass);
        addresses = homePage.goToAddressesPage();
        addresses.createAddresses(firstName, lastName, email, company,city,address1,address2,ZipPostalCode,number);
    }
}
