package demo.nopcommerce.nopcommerce.page;

import demo.nopcommerce.common.BaseConst;
import demo.nopcommerce.common.BasePage;
import demo.nopcommerce.constants.FrameworkConst;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;


public class HomePage extends BasePage {

    /**
     * Init a new instance
     *
     * @param driver : The WebDriver to interact with elements
     */
    public HomePage(WebDriver driver) {
        webDriver = driver;
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(webDriver, FrameworkConst.WAIT_EXPLICIT);
        PageFactory.initElements(factory, this);
    }

    public HomePage gotoHomePage() {
        String URL = "https://demo.nopcommerce.com/";
        webDriver.get(URL);
        goToURL(URL);
        return  new HomePage(webDriver);
    }
    public Wishlist goToWishlistPage() {
        WebElement element = findElement(String.format(BaseConst.DYNAMIC_LOCATOR_TEXT_UL_FORM,"ul", "Computers "));
        scrollToElement(element);
        waitForElementVisible(element);
        clickElement(element, "Add to wishlist");
        return new Wishlist();
    }
    public Addresses goToAddressesPage() {
        WebElement element = findElement(String.format(BaseConst.DYNAMIC_LOCATOR_TEXT_FORM, "a", "Addresses"));
        scrollToElement(element);
        waitForElementVisible(element);
        clickElement(element, "Save");
        return new Addresses();
    }
    public Search_and_Advanced_Search goToMySearchPage() {
        WebElement element = findElement(String.format(BaseConst.DYNAMIC_LOCATOR_TEXT_FORM, "a", "Search"));
        scrollToElement(element);
        waitForElementVisible(element);
        clickElement(element, "Save");
        return new Search_and_Advanced_Search();
    }
    /**
     * Go to Login Page
     */
    public RegisterPage registerPage() {
        String URL = "https://demo.nopcommerce.com/";
        webDriver.get(URL);
        goToURL(URL);
        String loginXPath = "//a[text()='Register']";
        WebElement loginElement = findElement(loginXPath);
        loginElement = getWebElement(getByXpathDynamic(loginXPath));
        clickElement(loginElement);
        return new RegisterPage(webDriver);
    }


    //Test
//    public void hover() {
//        System.out.println("Hovering");
//        Actions actions = new Actions(webDriver);
//
//        String loginXPath = "//span[text()='Tui']";
//        WebElement loginElement = findElement(loginXPath);
//        actions.moveToElement(loginElement).perform();
//        System.out.println("");
//    }
}
