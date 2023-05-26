package demo.nopcommerce.nopcommerce.page;

import demo.nopcommerce.common.BaseConst;
import demo.nopcommerce.common.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class Wishlist extends BasePage {
    String Wishlist = "//button[contains(@id, 'wishlist-button')]";

    public void WishList() {
        getWaitDriver().until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Wishlist)));
//        webDriver.findElement(By.xpath("//ul[@class = 'top-menu notmobile']//a[text() = 'Computers ']")).click();
//        clickToULWithText("Computers ");//ul[@class = 'top-menu notmobile']//a[text() = 'Computers ']
//        clickToAlWithText(" Desktops ");
//        clickToH2WithText("Build your own computer");


    }

    public void ClickWishList01() {
        waitForElementVisible(By.xpath("//a[text() = ' Desktops ']"));
        clickToAlWithText(" Desktops ");
        waitForElementVisible(By.xpath(" //h2[@class = 'product-title']//a[text() = 'Build your own computer']"));
        clickToH2WithText("Build your own computer");
        waitForElementVisible(By.xpath("//button[contains(@id, 'wishlist-button')]"));
        clickToIDWithText("wishlist-button");
        sleep(2);
        Assert.assertEquals(getElementText(webDriver, "xpath=//div[contains(@class, 'notification error')]/p[text() = 'Please select RAM']"), "Please select RAM");
        Assert.assertEquals(getElementText(webDriver, "xpath=//div[contains(@class, 'notification error')]/p//following-sibling::p"), "Please select HDD");
    }

    public void ClickWishList02() {
        waitForElementVisible(By.xpath("//a[text() = ' Desktops ']"));
        clickToAlWithText(" Desktops ");
        waitForElementVisible(By.xpath(" //h2[@class = 'product-title']//a[text() = 'Build your own computer']"));
        clickToH2WithText("Build your own computer");
        waitForElementVisible(By.xpath("//label[text() ='320 GB']//preceding-sibling::input"));//.click();
        clickToINPUTWithText("320 GB");
        selectItemInDropDown("//select[@id = 'product_attribute_2']", "2 GB");
        waitForElementVisible(By.xpath("//button[contains(@id, 'wishlist-button')]"));
        clickToIDWithText("wishlist-button");
        WebElement element = findElement("//div[@class = 'bar-notification success']//p");
        String getText = element.getText();
        // Assert.assertEquals(getElementText(webDriver, "xpath=//div[@class = 'bar-notification success']//p"), "The product has been added to your ");
        Assert.assertEquals(getText, "The product has been added to your wishlist");
        sleep(5);
        //waitForElementVisible(By.xpath("//span[@class = 'wishlist-label']")).click();
//      clickToElement(driver, "xpath=//span[@class = 'wishlist-label']");
        WebElement wishlistElement = findElement(String.format(BaseConst.DYNAMIC_LOCATOR_TEXT_FORM, "span", "Wishlist"));
        //scrollToElement(element);

        waitForElementVisible(wishlistElement);
        clickElement(wishlistElement, "Wishlist");

        Assert.assertEquals(getElementText(webDriver, "xpath=//tbody//tr[1]//td[4]//div[@class='attributes']"), "Processor: 2.5 GHz Intel Pentium Dual-Core E2200 [+$15.00]\nRAM: 2 GB\nHDD: 320 GB\nOS: Vista Home [+$50.00]\nSoftware: Microsoft Office [+$50.00]");


    }
}

