package demo.nopcommerce.projects.baikiemtra2.themeforest.page;

import demo.nopcommerce.common.BaseConst;
import demo.nopcommerce.common.BasePage;
import demo.nopcommerce.projects.baikiemtra2.themeforest.model.UserModel;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CartPage extends BasePage {
    public CartPage(WebDriver webDriver) {
    }
    public void clickSecureCheckout(){
       // List<WebElement> listPrice=findElements(getByXpathDynamic(String.format(BaseConst.DYNAMIC_LOCATOR_CLASS_CONTAINS_TEXT_FORM_THEMEWEB,"b","item-price t-currency")));
        clickToElement(BaseConst.DYNAMIC_LOCATOR_CLASS_CONTAINS_TEXT_FORM_THEMEWEB,"input","e-btn--3d -color-primary -width-full -size-l");
        verifyHeaderDisplay("span","Create Account");

    }
    public void fillInfo(UserModel userModel){

        inputTextWithName("firstName",userModel.getFirstName());
        inputTextWithName("lastName",userModel.getLastName());
        inputTextWithName("email",userModel.getEmail());
        inputTextWithName("username",userModel.getUserName());
        inputTextWithName("password",userModel.getPassword());
    }
    public void inputTextWithName(String title, String text) {
        String xpath = String.format(BaseConst.DYNAMIC_LOCATOR_INPUT_NAME, title);
        WebElement element = findElement(xpath);
        inputTextTo(element, title, text);
    }
}
