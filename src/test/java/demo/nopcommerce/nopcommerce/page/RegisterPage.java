package demo.nopcommerce.nopcommerce.page;

import demo.nopcommerce.common.BaseConst;
import demo.nopcommerce.common.BasePage;
import demo.nopcommerce.constants.FrameworkConst;
import demo.nopcommerce.nopcommerce.models.LoginModel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;


public class RegisterPage extends BasePage {
    public RegisterPage(WebDriver driver) {
        WebDriver webDriver = driver;
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(webDriver, FrameworkConst.WAIT_EXPLICIT);
        PageFactory.initElements(factory, this);
    }

    String RegisterFirstName = "//input[@name='FirstName']";

    public void createAccount(LoginModel loginModel) {
        getWaitDriver().until(ExpectedConditions.visibilityOfElementLocated(By.xpath(RegisterFirstName)));
        inputTextWithName("FirstName", loginModel.getFirstName());
        inputTextWithName("LastName", loginModel.getLastName());
        inputTextWithName("Email", loginModel.getEmail());
        inputTextWithName("Password", loginModel.getPassword());
        inputTextWithName("ConfirmPassword", loginModel.getPassword());
        clickToButtonWithText("Register");

        //verify data
        if(loginModel.getFirstName().isEmpty()){
            Assert.assertEquals(webDriver.findElement(By.cssSelector("span#FirstName-error")).getText(), "First name is required.");
            }
        if(loginModel.getLastName().isEmpty()){
            Assert.assertEquals(webDriver.findElement(By.cssSelector("span#LastName-error")).getText(), "Last name is required.");
        }
        if(loginModel.getEmail().isEmpty()){
            Assert.assertEquals(webDriver.findElement(By.cssSelector("span#Email-error")).getText(), "Email is required.");
        }
        else{
            if(!loginModel.getEmail().contains("@")){
                Assert.assertEquals(webDriver.findElement(By.cssSelector("span#Email-error")).getText(), "Wrong email");
            }
        }
        if(loginModel.getPassword().isEmpty()){
            Assert.assertEquals(webDriver.findElement(By.cssSelector("span#Password-error")).getText(), "Password is required.");
            Assert.assertEquals(webDriver.findElement(By.cssSelector("span#ConfirmPassword-error")).getText(), "Password is required.");
        }
                if(loginModel.getPassword().length()<6&&loginModel.getPassword().length()>0)
        {
            Assert.assertEquals(webDriver.findElement(By.cssSelector("span#Password-error")).getText(), "Password must meet the following rules:\nmust have at least 6 characters");
        }
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
