
package demo.nopcommerce.nopcommerce.dataprovider;
import demo.nopcommerce.common.BaseConst;
import demo.nopcommerce.helpers.ExcelHelpers;
import demo.nopcommerce.nopcommerce.models.LoginModel;
import lombok.var;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.List;

public class LoginDataProvider {

    final ExcelHelpers xlsReader = new ExcelHelpers();

    @DataProvider(name = "login")
    public Object[][] loginProvider() {
        String xlsFileName = "login1.xlsx";
        String filePath = BaseConst.imgFolderPath + xlsFileName;

        var data = xlsReader.readXLSData(filePath);
        List<LoginModel> loginData = new ArrayList<>();
        data.entrySet().stream().filter(v -> !v.getKey().equals("0")).forEach(v -> {
            var rowData = data.get(v.getKey());
            var loginModel = LoginModel.builder().username(rowData.get(1)).password(rowData.get(2)).build();
            loginData.add(loginModel);
        });
        System.out.println("Result" + loginData.get(0));
        Object[][] result = new Object[loginData.size()][0];
        for (int i = 0; i < loginData.size(); i++) {
            result[i][0] = loginData.get(i);
        }
        return result;
    }
}



