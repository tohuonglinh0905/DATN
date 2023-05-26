package demo.nopcommerce.projects.amisaccounting.page;

import demo.nopcommerce.common.BasePage;
import demo.nopcommerce.constants.FrameworkConst;
import demo.nopcommerce.projects.amisaccounting.models.LoginModel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {
    /**
     * Init a new instance
     *
     * @param driver : The WebDriver to interact with elements
     */
    public LoginPage(WebDriver driver) {
        webDriver = driver;
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(webDriver, FrameworkConst.WAIT_EXPLICIT);
        PageFactory.initElements(factory, this);
    }

    //region Locator
    String loginWithEmailXPath = "//p[@class='login-with-email']";
    String loginButtonXPath = "//button[text()='Đăng nhập']";
    //endregion

    public void clickLoginWithEmailLink() {
        getWaitDriver().until(ExpectedConditions.visibilityOfElementLocated(By.xpath(loginWithEmailXPath)));
        WebElement lnkLoginWithEmail = findElement(loginWithEmailXPath);
        lnkLoginWithEmail = getWebElement(getByXpathDynamic(loginWithEmailXPath));
        clickElement(lnkLoginWithEmail);
    }

    /**
     * Login with email and password
     */
    public HomePage loginWithEmail(LoginModel loginModel) {
        // Luu y: Day la vi du, trong qua trinh lam KHONG duoc hardcode
        inputTextWithType("email","");


        inputTextWithType("email", loginModel.getUsername());
        inputTextWithType("password", loginModel.getPassword());

        clickElement(getByXpathDynamic(loginButtonXPath));
        return new HomePage(webDriver);
    }

}
