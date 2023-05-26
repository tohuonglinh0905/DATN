package demo.nopcommerce.common;

import demo.nopcommerce.driver.DriverManager;
import demo.nopcommerce.utils.WebUI;
import demo.nopcommerce.constants.FailureHandling;
import lombok.Data;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


@Data
public class BasePage extends WebUI {
    protected WebDriver webDriver;
    protected JavascriptExecutor js;
    protected WebDriverWait webDriverWait;


    public BasePage() {
        webDriver = DriverManager.getDriver();
        js = (JavascriptExecutor) webDriver;
        //webDriverWait=new WebDriverWait(webDriver,Duration.ofMillis(10));
    }


    /**
     * Finding element
     */
//    public WebElement findElement(Object selector) {
//        WebElement element;
//        if (selector instanceof WebElement) element = (WebElement) selector;
//        else if (selector instanceof String) {
//            element = webDriver.findElement(By.xpath(String.valueOf(selector)));
//        } else if (selector instanceof By) {
//            element = webDriver.findElement((By) selector);
//        } else {
//            throw new RuntimeException("Your selector is invalid, you only user String, By or WebElement object");
//        }
//        return element;
//    }


    /**
     * @param selector
     * @param value
     */
   /* public void inputText(Object selector, String value) {
        // TODO: 12/01/2023 : Verify element
        WebElement element = getElement(selector);
        element.clear();
        element.sendKeys(value);
    }*/

    /**
     * Get Element
     *
     * @param selector
     */
    public WebElement getElement(Object selector) {
        WebElement element;
        if (selector instanceof WebElement) element = (WebElement) selector;
        else if (selector instanceof By) element = webDriver.findElement((By) selector);
        else throw new RuntimeException("Your object should be a WebElement or a by object");
        return element;
    }
    public String getElementText(WebDriver drive, String xpathLocator) {

        return getWebElement(drive, xpathLocator).getText();
    }
    public WebElement getWebElement(WebDriver driver, String locatorType) {
        return driver.findElement(getByLocator(locatorType));
    }
    public By getByLocator(String locatorType) {
        By by = null;
        if (locatorType.startsWith("id=") || locatorType.startsWith("ID=") || locatorType.startsWith("Id=")) {
            locatorType = locatorType.substring(3);
            by = By.id(locatorType);
        }
        else if (locatorType.startsWith("class=") || locatorType.startsWith("CLASS=") ||locatorType.startsWith("Class=")) {
            locatorType = locatorType.substring(6);
            by = By.className(locatorType);
        }
        else if (locatorType.startsWith("name=") || locatorType.startsWith("NAME=") || locatorType.startsWith("Name=")) {
            locatorType = locatorType.substring(5);
            by = By.name(locatorType);
        }
        else if (locatorType.startsWith("css=") || locatorType.startsWith("CSS=") || locatorType.startsWith("Css=")) {
            locatorType = locatorType.substring(4);
            by = By.cssSelector(locatorType);
        }
        else if (locatorType.startsWith("xpath=") || locatorType.startsWith("XPATH=") || locatorType.startsWith("Xpath=") ||  locatorType.startsWith("XPath=")) {
            locatorType = locatorType.substring(6);
            by = By.xpath(locatorType);
        } else {
            throw new RuntimeException("Locator type is not supported ");
        }
        return by;
    }

    /**
     * Enter to input with attribute is name
     *
     * @param title
     * @param text
     */
    public void inputTextWithName(String title, String text) {
        String xpath = String.format(BaseConst.DYNAMIC_LOCATOR_INPUT_ATTRIBUTE_FORM,"name", title);
        WebElement element = findElement(xpath);
        inputTextTo(element, title, text);
    }
    public void inputTextWithClass(String title, String text) {
        String xpath = String.format(BaseConst.DYNAMIC_LOCATOR_INPUT_ATTRIBUTE_FORM,"class", title);
        WebElement element = findElement(xpath);
        inputTextTo(element, title, text);
    }

    /**
     * Find tag input by label text and enter to input
     * @param labelText
     * @param text
     */

    public void inputTextWithLabelText(String labelText, String text) {
        String xpath = String.format(BaseConst.DYNAMIC_LOCATOR_INPUT_TEXTAREA_LABEL_TEXT_FORM, labelText,"input");
        WebElement element = findElement(xpath);
        inputTextTo(element, labelText, text);
    }
    public void inputTextWithLabelTextToTextarea(String labelText, String text) {
        String xpath = String.format(BaseConst.DYNAMIC_LOCATOR_INPUT_TEXTAREA_LABEL_TEXT_FORM, labelText, "textarea");
        WebElement element = findElement(xpath);
        inputTextTo(element, labelText, text);
    }

    /**
     * Enter to input with attribute is type
     * @param type
     * @param text
     */
    public void inputTextWithType(String type, String text) {
        String xpath = String.format(BaseConst.DYNAMIC_LOCATOR_INPUT_ATTRIBUTE_FORM,"type", type);
        WebElement element = findElement(xpath);
        inputTextTo(element, type, text);
    }

    /**
     * find element
     * @param selector
     * @return
     */
    public WebElement findElement(Object selector) {
        WebElement element = null;
        try {
            element = waitForElementVisible(getByXpathDynamic(selector.toString()));

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return element;

    }

    /**
     * find list element
     * @param selector
     * @return
     */
    public List<WebElement> findElements(Object selector) {
        waitForElementVisible(selector);
        List<WebElement> list = new ArrayList<>();
        if (selector instanceof By) {
            list = webDriver.findElements((By) selector);
        }
        else if (selector instanceof String) list = webDriver.findElements(By.xpath(String.valueOf(selector)));
        else throw new RuntimeException("Your selector only used by By/String");
        return list;
    }

    /**
     * click to element with locator is dynamic
     * @param stringLocator
     * @param keyValue
     */
    public void clickToElement(String stringLocator, String... keyValue) {
        String xpathElement;
        if (keyValue.length > 0)
            xpathElement = String.format(stringLocator, keyValue);
        else xpathElement = stringLocator;
        WebElement element = findElement(xpathElement);
        scrollToElement(element);
        clickElement(element, keyValue);

    }

    /**
     * click to button with locator
     * @param text
     */
    public void clickToButtonWithText(String text) {
        WebElement element = findElement(String.format(BaseConst.DYNAMIC_LOCATOR_TEXT_FORM,"button", text));
        //scrollToElement(element);
        clickElement(element, text);


    }
    public void clickToLabelWithText(String text) {
        WebElement element = findElement(String.format(BaseConst.DYNAMIC_LOCATOR_TEXT_FORM,"label", text));
        //scrollToElement(element);
        clickElement(element, text);

    }
    public void clickToULWithText(String text) {
        WebElement element = findElement(String.format(BaseConst.DYNAMIC_LOCATOR_TEXT_UL_FORM,"ul", text));
        //scrollToElement(element);
        clickElement(element, text);
    }
    public void clickToAlWithText(String text) {
        WebElement element = findElement(String.format(BaseConst.DYNAMIC_LOCATOR_TEXT_FORM,"a", text));
        //scrollToElement(element);
        clickElement(element, text);

    }
    public void clickToH2WithText(String text) {
        WebElement element = findElement(String.format(BaseConst.DYNAMIC_LOCATOR_TEXT_H2_FORM,"a", text));
        //scrollToElement(element);
        clickElement(element, text);
    }
    public void clickToIDWithText(String text) {
        WebElement element = findElement(String.format(BaseConst.DYNAMIC_LOCATOR_ID_FORM,text));
        //scrollToElement(element);
        clickElement(element, text);
    }

    public void clickToINPUTWithText(String text) {
        WebElement element = findElement(String.format(BaseConst.DYNAMIC_LOCATOR_TEXT_INPUT_FORM,text));
        //scrollToElement(element);
        clickElement(element, text);
    }
    public void clickToSelectIDWithText(String id) {
        WebElement element = findElement(String.format(BaseConst.DYNAMIC_LOCATOR_SELECT_ID_FORM,id));
        //scrollToElement(element);
        clickElement(element, id);
    }
//    public void clickToClassWithText(String class) {
//        WebElement element = findElement(String.format(BaseConst.DYNAMIC_LOCATOR_CLASS_FORM,class));
//        //scrollToElement(element);
//        clickElement(element, class);
//    }

    //h2[@class = 'product-title']//a[text() = 'Build your own computer']

    /**
     * click button by JS
     * @param selector
     */
    public void clickButtonByJs(Object selector) {
        WebElement element = findElement(selector);
        js.executeScript("arguments[0].click();", element);

    }


    /**
     * wait element
     *
     * @param selector
     * @return
     */
    public WebElement waitElementIsDisplay(Object selector) {
        WebElement element = null;
        webDriverWait = getWaitDriver();
        try {
            if (selector instanceof By)
                element = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated((By) selector));
            else if (selector instanceof String)
                element = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.valueOf(selector))));
            else if (selector instanceof WebElement)
                element = webDriverWait.until(ExpectedConditions.visibilityOf((WebElement) selector));
            else throw new RuntimeException("Your selector only used by By/ String/ WebElement");

        } catch (Exception ex) {
            System.out.println("Not found element is display with locator is:" + selector);

        }
        //js.executeScript("arguments[0].scrollIntoView();", element);
        return element;
    }

    /**
     * scroll to element
     *
     * @param selector
     */

    public void inputToDropDown(Object selector, String text) {
        WebElement element = findElement(selector);
        element.sendKeys(text);
        Actions actions = new Actions(webDriver);
        actions.sendKeys(Keys.ENTER).perform();
    }

    /**
     * verify value of input
     * @param selector
     * @param expected
     */
    public void verifyInputData(Object selector, Object expected) {
        WebElement actualElement = findElement(selector);
        Assert.assertEquals(actualElement.getAttribute("value"), expected, "verify actualData with expectedData is the same");
    }
    /**
     * verify value of input with found by label text
     * @param labelText
     * @param exp
     */
    public void verifyInputTextByLabel(String labelText,String tagName,String exp){
        WebElement actualElement = findElement(String.format(BaseConst.DYNAMIC_LOCATOR_INPUT_TEXTAREA_LABEL_TEXT_FORM,labelText,tagName));
        Assert.assertEquals(actualElement.getAttribute("value"), exp, "verify actualData with expectedData is the same");
    }

    /**
     * verify text in element
     * @param selector
     * @param expected
     */
    public void verifyDataGetText(Object selector, Object expected) {
        WebElement actualElement = findElement(selector);
        Assert.assertEquals(actualElement.getText(), expected, "verify actualData with expectedData is the same");
    }

    /**
     * verify element contains data
     * @param selector
     * @param expected
     */
    public void verifyElementContainsData(Object selector, String expected) {
        WebElement actualElement = findElement(selector);
        Assert.assertTrue(actualElement.getText().contains(expected), "verify element contains data");
    }

    public void verifyDateData(Object selector, String day, String month, String year) throws ParseException {
        //convert date in Excel to format yyyy-MM-dd
        Calendar cal = Calendar.getInstance();
        cal.setTime(new SimpleDateFormat("MMM").parse(month));
        int monthInt = cal.get(Calendar.MONTH) + 1;
        String monthText = "";
        String dayText = "";
        //format month
        if (monthInt < 10)
            monthText = "0" + monthInt;
        else monthText = String.valueOf(monthInt);
        //format day
        if (Integer.parseInt(day) < 10)
            dayText = "0" + day;
        else dayText = String.valueOf(day);
        String dateFormat = year + "-" + monthText + "-" + dayText;
        WebElement element = findElement(selector);
        Assert.assertEquals(element.getAttribute("value"), dateFormat);
    }

    /**
     * verify element is display
     * @param selector
     */

    public void verifyElementIsDisplay(Object selector) {
        WebElement element = findElement(selector);
        Assert.assertTrue(element.isDisplayed(), "verify element is display");
    }

    /**
     * verify element is selected
     * @param selector
     */
    public void verifyElementIsSelected(Object selector) {
        WebElement element = findElement(selector);
        Assert.assertTrue(element.isSelected(), "verify element is selected");
    }

    /**
     * verify element is not selected
     * @param selector
     */
    public void verifyElementIsNoneSelected(Object selector) {
        WebElement element = findElement(selector);
        Assert.assertFalse(element.isSelected(), "verify element is not selected");
    }

    /**
     * verify element is enable
     * @param selector
     */
    public void verifyElementIsEnable(Object selector) {
        WebElement element = findElement(selector);
        Assert.assertTrue(element.isEnabled(), "verify element is enable");
    }

    /**
     * verify element is disable
     * @param selector
     */
    public void verifyElementDisable(Object selector) {
        WebElement element = findElement(selector);
        Assert.assertFalse(element.isEnabled(), "verify element is disable");
    }

    /**
     * get list item in radio or checkbox box
     * @param RadioOrCheckboxXpath
     * @return
     */

    public List<String> getListItemInRadioOrCheckbox(Object RadioOrCheckboxXpath) {
        List<WebElement> listElement = findElements(RadioOrCheckboxXpath);
        List<String> listItemValue = new ArrayList<>();
        for (WebElement item : listElement) {
            String text = item.getText();
            listItemValue.add(text);
        }
        return listItemValue;
    }

    /**
     * select item in dropdown by Select
     * @param dropDownXpath
     * @param itemValue
     */

    public void selectItemInDropDown(Object dropDownXpath, String itemValue) {
        Select select= select = new Select(findElement(dropDownXpath));
        select.selectByVisibleText(itemValue);
        //verify data after selected
        assertEqualCondition(getByXpathDynamic(dropDownXpath.toString()),select.getFirstSelectedOption().getText(), itemValue, FailureHandling.CONTINUE_ON_FAILURE,"verify selector");
    }
    public void uncheckToDefaultCheckbox(String checkboxXPath) {
        WebElement checkbox = webDriver.findElement(By.xpath(checkboxXPath));
        if (checkbox.isSelected()) {
            checkbox.click();
        }}
    /**
     * select dropdown
     * @param selectXpath
     * @param childItemSelectXpath
     */
    public void selectDynamicDropDown(Object selectXpath, Object childItemSelectXpath) {
        WebElement element = findElement(selectXpath);
        clickElement(element, "parentDropDown");
        WebElement childSelectElement = findElement(childItemSelectXpath);
        clickElement(childSelectElement, "childDropDown");
    }

    /**
     * select dropdown by label text
     * @param labelText
     * @param valueSelect
     */
    public void selectDynamicDropDownByLabelText_SpanText(String labelText, String valueSelect) {
        WebElement element = findElement(String.format(BaseConst.DYNAMIC_DIV_LABEL_TEXT, labelText));
        clickElement(element, "parentDropDown");
        WebElement childSelectElement = findElement(String.format(BaseConst.DYNAMIC_LOCATOR_TEXT_FORM,"span", valueSelect));
        clickElement(childSelectElement, "childDropDown");
    }

    /**
     * verify cells in row table by keyCellValue
     * @param tblHeaderXpath
     * @param tblRowItemXpath
     * @param cellValue
     * @param value
     */
    public void verifyRowTableInfo(String tblHeaderXpath, String tblRowItemXpath, String cellValue, List<String> value) {
        //lay list header
        List<WebElement> listHeader = findElements(tblHeaderXpath);
        List<String> rowItem = new ArrayList<>();
        for (int i = 0; i < listHeader.size(); i++) {
            WebElement element = findElement(String.format(tblRowItemXpath, cellValue, i + 1));
            if (element.getText().equals(""))
                continue;
            else {
                rowItem.add(element.getText());
            }
        }
        //verify 2 list
        Assert.assertEquals(rowItem, value);

    }

    /**
     * get index header table
     * @param keyValue
     * @return
     */
    public int getIndexHeaderTable(String... keyValue) {
        if (keyValue.length > 0) {
            String elementXpath = String.format(BaseConst.DYNAMIC_GET_INDEX_CELL_TABLE, keyValue);
            int index = findElements(elementXpath).size() + 1;
            return index;
        } else
            return 0;

    }

    /**
     * get value or text in Tag
     * @param selector
     * @return
     */
    public String getTextOfElement(Object selector) {
        String text;
        WebElement element = findElement(selector);
        if (element.getTagName().equals("input") || element.getTagName().equals("textarea")) {
            return text = element.getAttribute("value");
        }
        text = element.getText();
        return text;
    }
    public void verifyHeaderDisplay(String... keyValue) {
        if (keyValue.length > 0) {
            String xpathElement = String.format(BaseConst.DYNAMIC_LOCATOR_TEXT_FORM, keyValue);
            verifyElementIsDisplay(xpathElement);
        }
    }

    /**
     * verify header title of webpage is display
     * @param keyValue
     */
    public void verifyHeaderDisplayThemeForest(String... keyValue) {
        if (keyValue.length > 0) {
            String xpathElement = String.format(BaseConst.DYNAMIC_LOCATOR_CONTAINS_TEXT_FORM_THEMEWEB, keyValue);
            verifyElementIsDisplay(xpathElement);
        }
    }


}
