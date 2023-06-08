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
import org.testng.Assert;


public class RegisterPage extends BasePage {
    public RegisterPage(WebDriver driver) {
        WebDriver webDriver = driver;
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(webDriver, FrameworkConst.WAIT_EXPLICIT);
        PageFactory.initElements(factory, this);
    }

    String RegisterFirstName = "//input[@name='FirstName']";

    public void createAccount(String firstName, String lastName, String email, String pass) {
        getWaitDriver().until(ExpectedConditions.visibilityOfElementLocated(By.xpath(RegisterFirstName)));
        inputTextWithName("FirstName", firstName);
        inputTextWithName("LastName", lastName);
        inputTextWithName("Email", email);
        inputTextWithName("Password", pass);
        inputTextWithName("ConfirmPassword", pass);
        clickToButtonWithText("Register");
    }

    public void createAccount01() {
        getWaitDriver().until(ExpectedConditions.visibilityOfElementLocated(By.xpath(RegisterFirstName)));
        clickToButtonWithText("Register");
        Assert.assertEquals(webDriver.findElement(By.cssSelector("span#FirstName-error")).getText(), "First name is required.");
        Assert.assertEquals(webDriver.findElement(By.cssSelector("span#LastName-error")).getText(), "Last name is required.");
        Assert.assertEquals(webDriver.findElement(By.cssSelector("span#Email-error")).getText(), "Email is required.");
        Assert.assertEquals(webDriver.findElement(By.cssSelector("span#Password-error")).getText(), "Password is required.");
        Assert.assertEquals(webDriver.findElement(By.cssSelector("span#ConfirmPassword-error")).getText(), "Password is required.");

    }

    public void createAccount02(String firstName, String lastName, String email01, String pass) {
        getWaitDriver().until(ExpectedConditions.visibilityOfElementLocated(By.xpath(RegisterFirstName)));
        inputTextWithName("FirstName", firstName);
        inputTextWithName("LastName", lastName);
        inputTextWithName("Email", email01);
        inputTextWithName("Password", pass);
        inputTextWithName("ConfirmPassword", pass);
        clickToButtonWithText("Register");
        Assert.assertEquals(webDriver.findElement(By.cssSelector("span#Email-error")).getText(), "Wrong email");
    }

    public void createAccount03(String firstName, String lastName, String email01, String pass01) {
        getWaitDriver().until(ExpectedConditions.visibilityOfElementLocated(By.xpath(RegisterFirstName)));
        inputTextWithName("FirstName", firstName);
        inputTextWithName("LastName", lastName);
        inputTextWithName("Email", email01);
        inputTextWithName("Password", pass01);
        inputTextWithName("ConfirmPassword", pass01);
        clickToButtonWithText("Register");
        Assert.assertEquals(webDriver.findElement(By.cssSelector("span#Password-error")).getText(), "Password must meet the following rules:\nmust have at least 6 characters");
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
