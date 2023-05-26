package demo.nopcommerce.nopcommerce.page;

import demo.nopcommerce.common.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Search_and_Advanced_Search extends BasePage {
    String Search = "//input[@class='search-text']";
    public void Advanced_Search(String search){
        getWaitDriver().until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Search)));
        inputTextWithClass("search-text",search);
        clickToLabelWithText("Advanced search");
        selectItemInDropDown("//select[@name='cid']", "Computers");
        clickToLabelWithText("Automatically search sub categories");
        selectItemInDropDown("//select[@name='mid']", "Apple");
        clickToLabelWithText("Search In product descriptions");
        clickToButtonWithText("Search");
    }
}
