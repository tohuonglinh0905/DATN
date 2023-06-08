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
        //Assert.assertEquals(getElementText(webDriver, "xpath=//li[contains(@class, 'email')][text()='linhth3@gmail.com']"), "linhth3@gmail.com");
        WebElement emailelement=findElement("//li[contains(@class, 'email')][text()='linhth3@gmail.com']");
        String getEmail=emailelement.getText();
        Assert.assertTrue(getEmail.contains( "linhth3@gmail.com"));
        WebElement phonelement=findElement("//li[contains(@class, 'phone')][text()=' 019283748']");
        String getPhone=phonelement.getText();
        Assert.assertTrue(getPhone.contains( " 019283748"));
        WebElement addresses1element=findElement("//li[text()=\"Đống Đa\"]");
        String getAddresses1=addresses1element.getText();
        Assert.assertTrue(getAddresses1.contains( "Đống Đa"));
        WebElement addresses2element=findElement("//li[text()=\"Khương Thượng\"]");
        String getAddresses2=addresses2element.getText();
        Assert.assertTrue(getAddresses2.contains( "Khương Thượng"));
        WebElement cityelement=findElement("//li[text()=\"HaNoi, 01928284\"]");
        String getCity=cityelement.getText();
        Assert.assertTrue(getCity.contains( "HaNoi, 01928284"));
        WebElement countryelement=findElement("//li[text()=\"Viet Nam\"]");
        String getCountry=countryelement.getText();
        Assert.assertTrue(getCountry.contains( "Viet Nam"));
        //popup
//        waitElementIsDisplay(String.format(BaseConst.DYNAMIC_LOCATOR_TEXT_FORM,"button","No thanks"));
//        clickToButtonWithText("No thanks");
    }
//    public Addresses goToAddressesPage() {
//        WebElement element = findElement(String.format(BaseConst.DYNAMIC_LOCATOR_TEXT_FORM, "a", "Addresses"));
//        scrollToElement(element);
//        waitForElementVisible(element);
//        clickElement(element, "Save");
//        return new Addresses();
//    }
}
