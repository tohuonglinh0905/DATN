package demo.nopcommerce.nopcommerce.page;

import demo.nopcommerce.common.BaseConst;
import demo.nopcommerce.common.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class Addresses extends BasePage {
    String AddressesFirstName = "//input[@name='FirstName']";

    public void createAddresses(String firstName, String lastName, String email, String company, String city, String address1, String address2, String ZipPostalCode, String number) {
//        getWaitDriver().until(ExpectedConditions.visibilityOfElementLocated(By.xpath(AddressesFirstName)));
        clickToButtonWithText("Add new");
        webDriver.findElement(By.xpath("//input[@name= 'Address.FirstName']"));
        inputTextWithName("Address.FirstName", firstName);
        webDriver.findElement(By.xpath("//input[@name= 'Address.LastName']"));
        inputTextWithName("Address.LastName", lastName);
        webDriver.findElement(By.xpath("//input[@name = 'Address.Email']"));
        inputTextWithName("Address.Email", email);
        inputTextWithName("Address.Company", company);
        selectItemInDropDown("//select[@name='Address.CountryId']", "Viet Nam");
        webDriver.findElement(By.xpath("//input[@name= 'Address.City']"));
        inputTextWithName("Address.City", city);
        webDriver.findElement(By.xpath("//input[@name= 'Address.Address1']"));
        inputTextWithName("Address.Address1", address1);
        webDriver.findElement(By.xpath("//input[@name= 'Address.Address2']"));
        inputTextWithName("Address.Address2", address2);
        webDriver.findElement(By.xpath("//input[@name= 'Address.ZipPostalCode']"));
        inputTextWithName("Address.ZipPostalCode", ZipPostalCode);
        webDriver.findElement(By.xpath("//input[@name= 'Address.PhoneNumber']"));
        inputTextWithName("Address.PhoneNumber", number);
//        sleep(2);
//        Assert.assertEquals(getElementText(webDriver, "xpath=//div[contains(@class, 'bar-notification success')]/p[text() = 'The new address has been added successfully.']"), "The new address has been added successfully.");

        clickToButtonWithText("Save");
        sleep(2);
        Assert.assertEquals(getElementText(webDriver, "xpath=//div[contains(@class, 'bar-notification success')]/p[text() = 'The new address has been added successfully.']"), "The new address has been added successfully.");
        Assert.assertEquals(getElementText(webDriver, "xpath=//li[contains(@class, 'email')][text()=' linhth3@gmail.com']"), " linhth3@gmail.com");

    }
//    public Addresses goToAddressesPage() {
//        WebElement element = findElement(String.format(BaseConst.DYNAMIC_LOCATOR_TEXT_FORM, "a", "Addresses"));
//        scrollToElement(element);
//        waitForElementVisible(element);
//        clickElement(element, "Save");
//        return new Addresses();
//    }
}
