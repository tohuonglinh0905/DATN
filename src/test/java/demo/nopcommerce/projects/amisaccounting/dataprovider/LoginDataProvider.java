package demo.nopcommerce.projects.amisaccounting.dataprovider;

import demo.nopcommerce.common.BaseProvider;
import demo.nopcommerce.helpers.ExcelHelpers;
import demo.nopcommerce.projects.amisaccounting.models.LoginModel;
import lombok.var;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.List;

public class LoginDataProvider extends BaseProvider {
    final ExcelHelpers xlsReader = new ExcelHelpers();
    @DataProvider(name = "LoginFunction")
    public Object[][] loginDataProvider() {
        return new Object[][]{{LoginModel.builder().username("test").password("test_pass").build()},
                {LoginModel.builder().username("admin").password("admin_pass").build()}};
    }

    @DataProvider(name = "LoginFunction2")
    public Object[][] loginDataProvider2() {
        String xlsFilePath = "src/test/resources/datatest/vincent/loginDataTest.xlsx";
        // String filePath = System.getProperty("user.dir") + File.separator + xlsFilePath;
        String filePath = "/Users/vincent/Work/workspace/testek/autoweb/misa_fresher01/selenium-training/src/test/resources/datatest/vincent/loginDataTest.xlsx";

        var data = xlsReader.readXLSData(filePath);
        List<LoginModel> loginData = new ArrayList<>();
        data.entrySet().stream().filter(v -> !v.getKey().equals("0")).forEach(v -> {
            var rowData = data.get(v.getKey());
            var loginModel = LoginModel.builder().username(rowData.get(1)).password(rowData.get(2)).build();
            loginData.add(loginModel);
        });
        System.out.println("Result");
        Object[][] result = new Object[loginData.size()][1];
        for (int i = 0; i < loginData.size(); i++) {
            result[i][0]= loginData.get(i);
        }
        return result;
//        return new Object[][]{{LoginModel.builder().username("test").password("test_pass").build()},
//                {LoginModel.builder().username("admin").password("admin_pass").build()}};
    }
}