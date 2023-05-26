package demo.nopcommerce.nopcommerce.page;

import demo.nopcommerce.common.BaseConst;
import demo.nopcommerce.common.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {
    String  inputEmail="//input[@name='Email']";

    public HomePage loginPage(String email,String pass){
        getWaitDriver().until(ExpectedConditions.visibilityOfElementLocated(By.xpath(inputEmail)));
        inputTextWithName("Email",email);
        inputTextWithName("Password",pass);
        clickToButtonWithText("Log in");
        return new HomePage(webDriver);
    }

}
