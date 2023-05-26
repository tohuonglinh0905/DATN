package demo.nopcommerce.projects.amisaccounting.page;

import demo.nopcommerce.common.BasePage;
import demo.nopcommerce.constants.FrameworkConst;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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

    /**
     * Go to Login Page
     */
    public LoginPage gotoLoginPage() {
        String URL = "https://tiki.vn";
        webDriver.get(URL);
        goToURL(URL);

        String loginXPath = "//span[text()='Tài khoản']";
        WebElement loginElement = findElement(loginXPath);
        loginElement = getWebElement(getByXpathDynamic(loginXPath));
        clickElement(loginElement);
        return new LoginPage(webDriver);
    }

    //Test
    public void hover() {
        System.out.println("Hovering");
        Actions actions = new Actions(webDriver);

        String loginXPath = "//span[text()='Tui']";
        WebElement loginElement = findElement(loginXPath);
        actions.moveToElement(loginElement).perform();
        System.out.println("");
    }
}
