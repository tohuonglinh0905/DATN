package demo.nopcommerce.projects.demo.page;

import demo.nopcommerce.common.BasePage;
import demo.nopcommerce.common.BaseConst;
import org.openqa.selenium.WebDriver;

public class DashBoardPage extends BasePage {
    public DashBoardPage(WebDriver driver) {
        webDriver = driver;
//        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(webDriver, FrameworkConst.WAIT_EXPLICIT);
//        PageFactory.initElements(factory, this);

    }

    public PimPage goPimPage() {
        clickToElement(BaseConst.DYNAMIC_LOCATOR_TEXT_FORM,"span", "PIM");
        verifyElementIsDisplay(String.format(BaseConst.DYNAMIC_LOCATOR_TEXT_FORM,"h6","PIM"));
        return new PimPage(webDriver);
    }
    public RecruitmentPage goToRecruitmentPage(){
        clickToElement(BaseConst.DYNAMIC_LOCATOR_TEXT_FORM,"span","Recruitment");
        verifyElementIsDisplay(String.format(BaseConst.DYNAMIC_LOCATOR_TEXT_FORM,"h6","Recruitment"));
        return new RecruitmentPage(webDriver);
    }

}