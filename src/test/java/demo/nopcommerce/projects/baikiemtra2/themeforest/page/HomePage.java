package demo.nopcommerce.projects.baikiemtra2.themeforest.page;

import demo.nopcommerce.common.BaseConst;
import demo.nopcommerce.common.BasePage;
import demo.nopcommerce.utils.WebUI;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {
    private String url = "https://themeforest.net/";

    public HomePage(WebDriver driver) {
        webDriver = driver;
    }

    /**
     * go to homepage
     */
    public void goToHomePage() {
        WebUI.goToURL(url);
        verifyHeaderDisplayThemeForest("h1", "Professional WordPress Themes");
    }

    public CodePage goToCodePage() {
        clickToElement(BaseConst.DYNAMIC_LOCATOR_TEXT_FORM_THEMEWEB,"a","Code");
        verifyHeaderDisplayThemeForest("h1", "Thousands of code");
        clickToElement(BaseConst.DYNAMIC_LOCATOR_TEXT_FORM_THEMEWEB,"a","All Items");
        verifyHeaderDisplayThemeForest("h1", "Plugins, Code & Scripts");
        return new CodePage(webDriver);
    }
}
