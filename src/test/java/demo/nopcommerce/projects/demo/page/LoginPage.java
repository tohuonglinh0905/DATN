package demo.nopcommerce.projects.demo.page;

import demo.nopcommerce.common.BasePage;
import demo.nopcommerce.common.BaseConst;
import demo.nopcommerce.constants.FrameworkConst;
import demo.nopcommerce.utils.WebUI;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class LoginPage extends BasePage {
    public LoginPage(WebDriver driver) {
        webDriver = driver;
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(webDriver, FrameworkConst.WAIT_EXPLICIT);
        PageFactory.initElements(factory, this);
    }

    String url = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";

    public DashBoardPage loginIntoPage(String username, String password) {
        WebUI.goToURL(url);
        waitElementIsDisplay(String.format(BaseConst.DYNAMIC_LOCATOR_INPUT_TEXTAREA_LABEL_TEXT_FORM, "Username", "input"));
        verifyElementIsDisplay(String.format(BaseConst.DYNAMIC_LOCATOR_INPUT_TEXTAREA_LABEL_TEXT_FORM, "Username", "input"));
        inputTextWithName("username", username);
        verifyInputData(String.format(BaseConst.DYNAMIC_LOCATOR_INPUT_TEXTAREA_LABEL_TEXT_FORM, "Username", "input"), username);
        inputTextWithName("password", password);
        verifyInputData(String.format(BaseConst.DYNAMIC_LOCATOR_INPUT_TEXTAREA_LABEL_TEXT_FORM, "Password", "input"), password);
        clickToButtonWithText(" Login ");
        verifyElementIsDisplay(String.format(BaseConst.DYNAMIC_LOCATOR_TEXT_FORM,"h6", "Dashboard"));
        return new DashBoardPage(webDriver);

    }
}

