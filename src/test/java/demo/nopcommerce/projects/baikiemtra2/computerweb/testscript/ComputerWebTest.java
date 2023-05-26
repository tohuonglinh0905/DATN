package demo.nopcommerce.projects.baikiemtra2.computerweb.testscript;

import demo.nopcommerce.annotations.MisaAnnotation;
import demo.nopcommerce.common.TestBase;
import demo.nopcommerce.constants.AuthorType;
import demo.nopcommerce.constants.CategoryType;
import demo.nopcommerce.driver.DriverManager;
import demo.nopcommerce.driver.TargetFactory;
import demo.nopcommerce.projects.baikiemtra2.computerweb.dataprovider.AddComputerProvider;
import demo.nopcommerce.projects.baikiemtra2.computerweb.model.ComputerModel;
import demo.nopcommerce.projects.baikiemtra2.computerweb.page.HomePage;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ThreadGuard;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;

@Epic(" Test")
@Feature("add computer model")

public class ComputerWebTest extends TestBase {
    private HomePage homePage;
    @BeforeClass(alwaysRun = true)
    public void createDriver(@Optional("chrome") String browser){
        WebDriver driver = ThreadGuard.protect(new TargetFactory().createInstance(browser));
        DriverManager.setDriver(driver);
        homePage=new HomePage(DriverManager.getDriver());
        homePage.goToHomePage();

    }
    @MisaAnnotation(category = {CategoryType.REGRESSION}, author = {AuthorType.moi}, reviewer = {AuthorType.Vincent})
    @Test(description = " add computer", dataProvider = "addComputerProvider", dataProviderClass = AddComputerProvider.class)
    public void TC_01(ComputerModel computerModel){
        homePage.addNewComputer(computerModel);

    }

    @AfterClass(alwaysRun = true)
    public void closeDriver() {
        DriverManager.quit();
    }
}
