package demo.nopcommerce.common;

import java.io.File;

public class BaseConst {
    //region xpath folder
    public static String imgFolderPath = "src/test/resources/datatest/DoAn_File/";
    public static String imgFileAbsolutePath = System.getProperty("user.dir") + File.separator + String.format(imgFolderPath, "image");
    public static String xlsxFileAbsolutePath = System.getProperty("user.dir") + File.separator + String.format(imgFolderPath, "file");
    //endregion

    //region DYNAMIC BASE LOCATOR

    public static String DYNAMIC_LOCATOR_INPUT_TEXTAREA_LABEL_TEXT_FORM = "//label[text()='%s']/../following-sibling::div//%s";
    public static String DYNAMIC_LOCATOR_INPUT_ATTRIBUTE_FORM = "//input[@%s='%s']";
    // public static String DYNAMIC_SPAN_TEXT_FORM ="//span[text()='%s']";
    public static String DYNAMIC_SPAN_CONTAINS_TEXT_FORM = "//span[text()[contains(.,'%s')]]";
    public static String DYNAMIC_LOCATOR_TEXT_FORM = "//%s[text()='%s']";
    public static String DYNAMIC_LOCATOR_TEXT_UL_FORM = "//%s[@class = 'top-menu notmobile']//a[text() ='%s']";
    public static String DYNAMIC_LOCATOR_TEXT_H2_FORM = "//h2[@class = 'product-title']//%s[text() ='%s']";
    public static String DYNAMIC_LOCATOR_TEXT_INPUT_FORM = "//label[text() ='%s']//preceding-sibling::input";
    public static String DYNAMIC_DIV_LABEL_TEXT = "//label[text()='%s']/ancestor::div[contains(@class,'oxd-input-group ')]";
    public static String DYNAMIC_CELL_TABLE_FORM = "//div[@role='table']/descendant::div[@role='row'][%s]/div[%s]/div";
    public static String DYNAMIC_LOCATOR_TABLE_FORM = "//div[@role='%s']";
    public static String DYNAMIC_LOCATOR_CLASS_FORM = "//span[@class = '%s']";
    public static String DYNAMIC_LOCATOR_ID_FORM = "//button[contains(@id, '%s')]";
    public static String DYNAMIC_LOCATOR_SELECT_ID_FORM = "//select[@id = '%s']";
    public static String DYNAMIC_GET_INDEX_CELL_TABLE = "//div[text()='%s']/preceding::div[contains (@class,' oxd-padding-cell')]";

    //end region
    //region bai kiem tra 2:
    //region web: https://computer-database.gatling.io/computers

    public static String DYNAMIC_LOCATOR_TEXT_FORM2 = "//%s[text()='%s']";
    public static String DYNAMIC_LOCATOR_INPUT_NAME="//input[@name='%s']";
    public static String DYNAMIC_LOCATOR_INPUT_TYPE="//input[@type='%s']";
    public static String DYNAMIC_LOCATOR_SELECT_NAME="//select[@name='%s']";
    public static String folderPathBaiKT2 =System.getProperty("user.dir") + File.separator + "src/test/resources/datatest/baikiemtra2/computerweb/file/";


    //endregion
    //region web:https://themeforest.net/

    public static String DYNAMIC_LOCATOR_TEXT_FORM_THEMEWEB = "//%s[text()[normalize-space()='%s']]";
    public static String DYNAMIC_LOCATOR_CONTAINS_TEXT_FORM_THEMEWEB = "//%s[contains(text(),'%s')]";

    public static String DYNAMIC_LOCATOR_CLASS_CONTAINS_TEXT_FORM_THEMEWEB = "//%s[contains(@class,'%s')]";

    public static String DYNAMIC_LOCATOR_INPUT_PLACEHOLDER_THEMEWEB="//input[@placeholder='%s']";
    //endregion

    //endregion
    public enum FilterOption {
        EQUAL("opt_equal"), NOT_EQUAL("opt_not_equal"), CONTAIN("opt_contain"), NOT_CONTAIN("opt_not_contain"), EMPTY("opt_empty"), FILLED("opt_filled"), SMALLER("opt_smaller"), SMALLER_EQUAL("opt_smaller_equal"), GREATER("opt_greater"), GREATER_EQUAL("opt_greater_equal"), START_WITH("opt_start_with"), END_WITH("opt_end_with");

        private String name;

        private FilterOption(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}
