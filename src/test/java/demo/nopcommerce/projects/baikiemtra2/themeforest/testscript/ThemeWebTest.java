package demo.nopcommerce.projects.baikiemtra2.themeforest.testscript;

import demo.nopcommerce.annotations.MisaAnnotation;
import demo.nopcommerce.common.TestBase;
import demo.nopcommerce.constants.AuthorType;
import demo.nopcommerce.constants.CategoryType;
import demo.nopcommerce.driver.DriverManager;
import demo.nopcommerce.driver.TargetFactory;
import demo.nopcommerce.projects.baikiemtra2.themeforest.dataprovider.AddUserProvider;
import demo.nopcommerce.projects.baikiemtra2.themeforest.model.UserModel;
import demo.nopcommerce.projects.baikiemtra2.themeforest.page.CartPage;
import demo.nopcommerce.projects.baikiemtra2.themeforest.page.CodePage;
import demo.nopcommerce.projects.baikiemtra2.themeforest.page.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ThreadGuard;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;

public class ThemeWebTest extends TestBase {
    private HomePage homePage;
    private CodePage codePage;
    private CartPage cartPage;
    private String keySearch = "elementor";

    @BeforeClass(alwaysRun = true)
    public void createDriver(@Optional("chrome") String browser) {
        WebDriver driver = ThreadGuard.protect(new TargetFactory().createInstance(browser));
        DriverManager.setDriver(driver);
        homePage = new HomePage(DriverManager.getDriver());
        homePage.goToHomePage();
        codePage = homePage.goToCodePage();
        codePage.searchValue(keySearch);
        codePage.sortByBestSeller();
        //codePage.addToCart();
        //cartPage = codePage.goToCart();
        //cartPage.clickSecureCheckout();

    }

    @MisaAnnotation(category = {CategoryType.REGRESSION}, author = {AuthorType.moi}, reviewer = {AuthorType.Vincent})
    @Test(description = " add user", dataProvider = "addUser", dataProviderClass = AddUserProvider.class)
    public void TC_02(UserModel userModel) {
        //cartPage.fillInfo(userModel);

    }

    @AfterClass(alwaysRun = true)
    public void closeDriver() {
        //DriverManager.quit();
    }
}
