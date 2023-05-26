package demo.nopcommerce.nopcommerce.dataprovider;

import demo.nopcommerce.common.BaseProvider;
import demo.nopcommerce.helpers.ExcelHelpers;
import demo.nopcommerce.projects.amisaccounting.models.LoginModel;
import org.testng.annotations.DataProvider;

public class LoginDataProvider extends BaseProvider {
    final ExcelHelpers xlsReader = new ExcelHelpers();
    @DataProvider(name = "LoginFunction")
    public Object[][] loginDataProvider() {
        return new Object[][]{{LoginModel.builder().username("test").password("test_pass").build()},
                {LoginModel.builder().username("admin").password("admin_pass").build()}};
    }
}
