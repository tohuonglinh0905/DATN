package demo.nopcommerce.nopcommerce.page;

import demo.nopcommerce.common.BaseConst;
import demo.nopcommerce.common.BasePage;
import demo.nopcommerce.constants.FrameworkConst;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class RegisterPage extends BasePage {
    public RegisterPage(WebDriver driver) {
        WebDriver webDriver = driver;
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(webDriver, FrameworkConst.WAIT_EXPLICIT);
        PageFactory.initElements(factory, this);
    }

    //region Locator
//    String RegisterGender = "//label[text()='Male']]";
    String RegisterFirstName = "//input[@name='FirstName']";
//    String RegisterLastName = "//input[@name='LastName']";
//    String RegisterEmail = "//input[@name='Email']";
//    String RegisterPassword = "//input[@name='Password']";
//    String RegisterConfirmPassword = "//input[@name='ConfirmPassword']";


    //endregion
    public void createAccount(String firstName, String lastName, String email, String pass) {
        getWaitDriver().until(ExpectedConditions.visibilityOfElementLocated(By.xpath(RegisterFirstName)));
        inputTextWithName("FirstName", firstName);
        inputTextWithName("LastName", lastName);
        inputTextWithName("Email", email);
        inputTextWithName("Password", pass);
        inputTextWithName("ConfirmPassword", pass);
        clickToButtonWithText("Register");
    }

    public LoginPage goToLoginPage() {
//        getWaitDriver().until(ExpectedConditions.visibilityOfElementLocated(getByXpathDynamic(BaseConst.DYNAMIC_LOCATOR_TEXT_FORM,"a","Log in")));
        WebElement element = findElement(String.format(BaseConst.DYNAMIC_LOCATOR_TEXT_FORM, "a", "Log in"));
        //scrollToElement(element);

        waitForElementVisible(element);
        clickElement(element, "Login");
        return new LoginPage();

    }

    public MyAccount goToMyAccountPage() {
        WebElement element = findElement(String.format(BaseConst.DYNAMIC_LOCATOR_TEXT_FORM, "a", "My account"));
        scrollToElement(element);

        waitForElementVisible(element);
        clickElement(element, "Save");
        return new MyAccount();
    }

    public Search_and_Advanced_Search goToMySearchPage() {
        WebElement element = findElement(String.format(BaseConst.DYNAMIC_LOCATOR_TEXT_FORM, "a", "Search"));
        scrollToElement(element);
        waitForElementVisible(element);
        clickElement(element, "Save");
        return new Search_and_Advanced_Search();
    }


}
