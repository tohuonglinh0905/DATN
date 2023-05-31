package demo.nopcommerce.nopcommerce.page;

import demo.nopcommerce.common.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MyAccount extends BasePage {
    String MyAccountFirstName = "//input[@name='FirstName']";
    public void createMyAccount(String firstName, String lastName, String email,String company){
        getWaitDriver().until(ExpectedConditions.visibilityOfElementLocated(By.xpath(MyAccountFirstName)));
        clickToLabelWithText("Female");
        webDriver.findElement(By.xpath("//input[@id= 'FirstName']")).clear();
        inputTextWithName("FirstName",firstName);
        webDriver.findElement(By.xpath("//input[@id= 'LastName']")).clear();
        inputTextWithName("LastName",lastName);
        selectItemInDropDown("//select[@name='DateOfBirthDay']", "9");
        selectItemInDropDown("//select[@name='DateOfBirthMonth']", "May");
        selectItemInDropDown("//select[@name='DateOfBirthYear']", "2001");
        webDriver.findElement(By.xpath("//input[@id = 'Email']")).clear();
        inputTextWithName("Email",email);
        inputTextWithName("Company",company);
        clickToLabelWithText("Newsletter:");
//        uncheckToDefaultCheckbox("xpath=//input[@id = 'Newsletter']");
        clickToButtonWithText("Save");
    }
    }
