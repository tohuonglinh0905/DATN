package demo.nopcommerce.projects.demo.page;

import demo.nopcommerce.common.BaseConst;
import demo.nopcommerce.common.BasePage;
import demo.nopcommerce.projects.demo.model.CustomFiledModel;
import demo.nopcommerce.projects.demo.model.EmployeeModel;
import demo.nopcommerce.projects.demo.model.ReportModel;
import demo.nopcommerce.utils.WebUI;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;


public class PimPage extends BasePage {
    private String rowItemXpath = "//div[(text()='%s')]/ancestor::div[@role='row']/div[%s]";
    private String deleteBtnXpath = "//%s[text()='%s']/following::i[contains(@class,'%s')]";
    private String imgInfoXpath = "//img[@class='employee-image']";
    private String checkAllItemXpath = "//div[text()='%s']/preceding::div[contains (@class,'oxd-padding-cell')]//span";
    private String includeHeaderIconXpath = "//p[text()='Include Header']/following::span";

    public PimPage(WebDriver driver) {
        webDriver = driver;
//        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(webDriver, FrameworkConst.WAIT_EXPLICIT);
//        PageFactory.initElements(factory, this);
    }

    public void selectCustomFiled() {
        selectDynamicDropDown(String.format(BaseConst.DYNAMIC_LOCATOR_TEXT_FORM, "span", "Configuration "), String.format(BaseConst.DYNAMIC_LOCATOR_TEXT_FORM, "a", "Custom Fields"));
        verifyHeaderDisplay("h6", "Custom Fields");
        clickToButtonWithText(" Add ");
        waitElementIsDisplay(String.format(BaseConst.DYNAMIC_LOCATOR_TEXT_FORM, "span", "PIM "));
        verifyHeaderDisplay("h6", "Add Custom Field");

    }

    public void addCustomerField(CustomFiledModel customFiledModel) {
        inputTextWithLabelText("Field Name", customFiledModel.getFieldName());
        selectDynamicDropDownByLabelText_SpanText("Screen", customFiledModel.getScreen());
        selectDynamicDropDownByLabelText_SpanText("Type", customFiledModel.getType());
        if (customFiledModel.getType().equals("Drop Down")) {
            inputTextWithLabelText("Select Options", customFiledModel.getSelect());
        }
        clickToButtonWithText(" Save ");
        verifyNoticeSuccessXpath();
        List<String> listStringObject = new ArrayList<>();
        listStringObject = Arrays.asList(customFiledModel.getFieldName(), customFiledModel.getScreen(), customFiledModel.getType());

        //kiem tra xem hang can verify co ton tai hay khong
        verifyElementIsDisplay(String.format(BaseConst.DYNAMIC_LOCATOR_TEXT_FORM, "div", customFiledModel.getFieldName()));
        verifyRowTableInfo(String.format(BaseConst.DYNAMIC_LOCATOR_TABLE_FORM, "columnheader"), rowItemXpath, customFiledModel.getFieldName(), listStringObject);

        clickToElement(String.format(deleteBtnXpath, "div", customFiledModel.getFieldName(), "oxd-icon bi-trash"));
        verifyElementIsDisplay(String.format(BaseConst.DYNAMIC_LOCATOR_TEXT_FORM, "button", " Yes, Delete "));
        clickToButtonWithText(" Yes, Delete ");
        verifyNoticeSuccessXpath();

    }


    public void selectEmployeeList(EmployeeModel employeeModel) {
        clickToElement(BaseConst.DYNAMIC_LOCATOR_TEXT_FORM, "a", "Add Employee");
        verifyHeaderDisplay("h6", "Add Employee");
        inputTextWithName("firstName", employeeModel.getFirstName());
        inputTextWithName("lastName", employeeModel.getLastName());
        uploadFileSendKeys(WebUI.getByXpathDynamic(String.format(BaseConst.DYNAMIC_LOCATOR_INPUT_ATTRIBUTE_FORM, "type", "file")), BaseConst.imgFileAbsolutePath + employeeModel.getImg());
        inputTextWithLabelText("Employee Id", employeeModel.getId());
        clickToButtonWithText(" Save ");
        verifyNoticeSuccessXpath();
        verifyHeaderDisplay("h6", (employeeModel.getFirstName() + " " + employeeModel.getLastName()));
    }

    public void addPersonalDetails(EmployeeModel employeeModel) {
        //add personal details
        WebUI.waitForElementVisibleWithBy(WebUI.getByXpathDynamic(imgInfoXpath));
        //inputTextWithLabelText("Nickname", employeeModel.getNickname());
        inputTextWithLabelText("Other Id", employeeModel.getOtherID());
        inputTextWithLabelText("License Expiry Date", employeeModel.getLicenseExpiryDate());
        inputTextWithLabelText("SSN Number", employeeModel.getNumberSSN());
        inputTextWithLabelText("SIN Number", employeeModel.getNumberSIN());
        selectDynamicDropDownByLabelText_SpanText("Nationality", employeeModel.getNationality());
        selectDynamicDropDownByLabelText_SpanText("Marital Status", employeeModel.getMaritalStatus());
        inputTextWithLabelText("Date of Birth", employeeModel.getDateOfBirth());
        clickToElement(BaseConst.DYNAMIC_LOCATOR_TEXT_FORM, "label", employeeModel.getGender());
//        inputTextWithLabelText("Military Service", employeeModel.getMilitaryService());
//        if (employeeModel.getSmoker().equals("Yes"))
//            clickToElement(BaseConst.DYNAMIC_LOCATOR_TEXT_FORM, "label", " Yes ");
        clickToButtonWithText(" Save " );
        verifyNoticeSuccessXpath();
        //verifyInputTextByLabel("Nickname","input", employeeModel.getNickname());
        verifyInputTextByLabel("Other Id","input", employeeModel.getOtherID());
        verifyInputTextByLabel("License Expiry Date", "input",employeeModel.getLicenseExpiryDate());
        verifyInputTextByLabel("SSN Number","input", employeeModel.getNumberSSN());
        verifyInputTextByLabel("SIN Number","input", employeeModel.getNumberSIN());
        verifyInputTextByLabel("Date of Birth","input", employeeModel.getDateOfBirth());

    }

    public void searchEmployee(String keySearch) {
        clickToElement(BaseConst.DYNAMIC_LOCATOR_TEXT_FORM, "a", "Employee List");
        verifyHeaderDisplay("h5", "Employee Information");
        inputTextWithLabelText("Employee Name", keySearch);
        clickToButtonWithText(" Search ");
        WebUI.waitForElementVisible(WebUI.getByXpathDynamic(String.format(BaseConst.DYNAMIC_GET_INDEX_CELL_TABLE, "First (& Middle) Name")));
        int indexFirstNameHeader = getIndexHeaderTable("First (& Middle) Name", "oxd-table-header-cell");
        int indexLastNameHeader = getIndexHeaderTable("Last Name", "oxd-table-header-cell");
        int totalRowTable = findElements(String.format(BaseConst.DYNAMIC_LOCATOR_TABLE_FORM, "row")).size();
        boolean checkCellData;
        for (int row = 1; row < totalRowTable; row++) {
            checkCellData = false;
            if (getTextOfElement(String.format(BaseConst.DYNAMIC_CELL_TABLE_FORM, row + 1, indexFirstNameHeader)).toLowerCase().contains(keySearch.toLowerCase())) {
                System.out.println(getTextOfElement(String.format(BaseConst.DYNAMIC_CELL_TABLE_FORM, row + 1, indexFirstNameHeader)));
                checkCellData = true;
            }
            if ((getTextOfElement(String.format(BaseConst.DYNAMIC_CELL_TABLE_FORM, row + 1, indexLastNameHeader)).toLowerCase()).contains(keySearch.toLowerCase())) {

                System.out.println(getTextOfElement(String.format(BaseConst.DYNAMIC_CELL_TABLE_FORM, row + 1, indexLastNameHeader)));
                checkCellData = true;
            }
            Assert.assertTrue(checkCellData, "verify cellData contain key search");
        }
        //check all row found
        clickToElement(checkAllItemXpath, "Id");
        verifyHeaderDisplay("button", " Delete Selected ");
        clickToButtonWithText(" Delete Selected ");
        verifyHeaderDisplay("button", " Yes, Delete ");
        clickToButtonWithText(" Yes, Delete ");
        verifyNoticeSuccessXpath();

    }

    public void addReport(ReportModel reportModel) {
        clickToElement(BaseConst.DYNAMIC_LOCATOR_TEXT_FORM, "a", "Reports");
        verifyHeaderDisplay("h5", "Employee Reports");
        clickToButtonWithText(" Add ");
        inputTextWithLabelText("Report Name", reportModel.getReportName());
        selectDynamicDropDownByLabelText_SpanText("Selection Criteria", reportModel.getSelectionCriteria());
        selectDynamicDropDownByLabelText_SpanText("Select Display Field Group", reportModel.getDisplayFieldGroup());
        clickToElement(deleteBtnXpath, "label", "Select Display Field", "oxd-icon bi-plus");
        clickToButtonWithText(" Save ");
        verifyNoticeSuccessXpath();
        verifyHeaderDisplay("h6", reportModel.getReportName());
    }

    public void findReport(ReportModel reportModel) {
        clickToElement(BaseConst.DYNAMIC_LOCATOR_TEXT_FORM, "a", "Reports");
        verifyHeaderDisplay("h5", "Employee Reports");
        inputTextWithLabelText("Report Name", reportModel.getReportName());
        clickToElement(BaseConst.DYNAMIC_LOCATOR_TEXT_FORM, "span", reportModel.getReportName());
        clickToButtonWithText(" Search ");
        WebUI.waitForElementVisible(WebUI.getByXpathDynamic(String.format(BaseConst.DYNAMIC_GET_INDEX_CELL_TABLE, "Name")));
        int totalRowTable = findElements(String.format(BaseConst.DYNAMIC_LOCATOR_TABLE_FORM, "row")).size();
        int indexNameHeader = getIndexHeaderTable("Name");
        verifyDataGetText(String.format(BaseConst.DYNAMIC_CELL_TABLE_FORM, totalRowTable, indexNameHeader), reportModel.getReportName());
        clickToElement(String.format(deleteBtnXpath, "div", reportModel.getReportName(), "oxd-icon bi-pencil-fill"));
        verifyHeaderDisplay("h6", "Edit Report");
        clickToElement(includeHeaderIconXpath);
        clickToButtonWithText(" Save ");
        verifyNoticeSuccessXpath();

    }

    public void verifyNoticeSuccessXpath() {
        String element = String.format(BaseConst.DYNAMIC_LOCATOR_TEXT_FORM, "p", "Success");
        verifyElementIsDisplay(element);
    }



}