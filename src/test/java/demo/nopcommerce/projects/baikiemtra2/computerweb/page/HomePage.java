package demo.nopcommerce.projects.baikiemtra2.computerweb.page;

import demo.nopcommerce.common.BaseConst;
import demo.nopcommerce.common.BasePage;
import demo.nopcommerce.projects.baikiemtra2.computerweb.model.ComputerModel;
import demo.nopcommerce.constants.FailureHandling;
import demo.nopcommerce.utils.WebUI;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage extends BasePage {
    private String url="https://computer-database.gatling.io/computers";
    public HomePage(WebDriver driver){
        webDriver=driver;
    }

    /**
     * go to homepage
     */
    public void goToHomePage(){
        WebUI.goToURL(url);
        verifyHeaderDisplayComputerWeb("a","Computer database");
    }

    /**
     * add new computer model
     * @param computerModel
     */

    public void addNewComputer(ComputerModel computerModel){
        clickToElement(BaseConst.DYNAMIC_LOCATOR_TEXT_FORM2,"a","Add a new computer");
        verifyHeaderDisplayComputerWeb("h1","Add a computer");
        inputTextWithNameComputerWeb("name",computerModel.getComputerName());
        inputTextWithNameComputerWeb("introduced",computerModel.getIntroduced());
        inputTextWithNameComputerWeb("discontinued",computerModel.getDiscontinued());
        selectItemInDropDown(String.format(BaseConst.DYNAMIC_LOCATOR_SELECT_NAME,"company"),computerModel.getCompany());

        //verify
        verifyInputName("name",computerModel.getComputerName());
        verifyInputName("introduced",computerModel.getIntroduced());
        verifyInputName("discontinued",computerModel.getDiscontinued());
        clickToElement(BaseConst.DYNAMIC_LOCATOR_INPUT_TYPE,"submit");

        verifyHeaderDisplay("strong","Done !  ");


    }
    //region computerWeb

    /**
     * verify header title of webpage is display
     * @param keyValue
     */
    public void verifyHeaderDisplayComputerWeb(String... keyValue) {
        if (keyValue.length > 0) {
            String xpathElement = String.format(BaseConst.DYNAMIC_LOCATOR_TEXT_FORM2, keyValue);
            verifyElementIsDisplay(xpathElement);
        }
    }

    /**
     * input text to input tag with name
     * @param title
     * @param text
     */
    public void inputTextWithNameComputerWeb(String title, String text) {
        String xpath = String.format(BaseConst.DYNAMIC_LOCATOR_INPUT_NAME, title);
        WebElement element = findElement(xpath);
        inputTextTo(element, title, text);
    }

    /**
     * verify input name has same data with expData
     * @param title
     * @param expData
     */
    public void verifyInputName(String title, String expData){
        WebElement actElement=findElement(String.format(BaseConst.DYNAMIC_LOCATOR_INPUT_NAME,title));
        WebUI.assertEqualCondition(actElement,actElement.getAttribute("value"),expData, FailureHandling.CONTINUE_ON_FAILURE,"verify input name data");
    }
    //endregion



}
