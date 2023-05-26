package demo.nopcommerce.projects.demo.testScript;

import demo.nopcommerce.annotations.MisaAnnotation;
import demo.nopcommerce.common.TestBase;
import demo.nopcommerce.constants.AuthorType;
import demo.nopcommerce.constants.CategoryType;
import demo.nopcommerce.driver.DriverManager;
import demo.nopcommerce.driver.TargetFactory;
import demo.nopcommerce.projects.demo.dataprovider.AddVacancyProvider;
import demo.nopcommerce.projects.demo.model.VacancyModel;
import demo.nopcommerce.projects.demo.page.DashBoardPage;
import demo.nopcommerce.projects.demo.page.LoginPage;
import demo.nopcommerce.projects.demo.page.RecruitmentPage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ThreadGuard;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;

public class HRMLive_Recruitment_Test extends TestBase {
    private WebDriver webDriver;
    private JavascriptExecutor js;

    private String username = "Admin";
    private String password = "admin123";
    private LoginPage loginPage;
    private DashBoardPage dashBoardPage;
    private RecruitmentPage recruitmentPage;
    private String ascSort="Ascending";
    private String desc="Decending";
    @BeforeClass
    public void createDriver(@Optional("chrome")String browser){
        WebDriver driver= ThreadGuard.protect(new TargetFactory().createInstance(browser));
        DriverManager.setDriver(driver);
        loginPage=new LoginPage(DriverManager.getDriver());
        dashBoardPage=loginPage.loginIntoPage(username,password);
    }
    @AfterClass
    public void closeDriver(){
        DriverManager.quit();
    }

    @MisaAnnotation(category = {CategoryType.REGRESSION}, author = {AuthorType.moi}, reviewer = {AuthorType.Vincent})
    @Test(description = "add vacancy",dataProvider = "addVacancy", dataProviderClass = AddVacancyProvider.class)
    public void TC_01(VacancyModel vacancyModel) {
        recruitmentPage=dashBoardPage.goToRecruitmentPage();
        recruitmentPage.clickToVacancies();
        recruitmentPage.addVacancy(vacancyModel);
        recruitmentPage.clickToVacancies();
        recruitmentPage.sortVacancyByJobTitle(ascSort);
        recruitmentPage.sortVacancyByJobTitle(desc);
        recruitmentPage.deleteVacancyItem(vacancyModel.getVacancyName());


    }



}
