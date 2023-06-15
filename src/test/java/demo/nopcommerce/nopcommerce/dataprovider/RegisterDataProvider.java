package demo.nopcommerce.nopcommerce.dataprovider;

import demo.nopcommerce.common.BaseConst;
import demo.nopcommerce.helpers.ExcelHelpers;
import demo.nopcommerce.nopcommerce.models.LoginModel;
import lombok.var;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.List;

public class RegisterDataProvider {
    final ExcelHelpers xlsReader = new ExcelHelpers();

    @DataProvider(name = "register")
    public Object[][] registerProvider() {
        String xlsFileName = "Book1.xlsx";
        String filePath = BaseConst.imgFolderPath + xlsFileName;

        var data = xlsReader.readXLSData(filePath);
        List<LoginModel> loginData = new ArrayList<>();
        data.entrySet().stream().filter(v -> !v.getKey().equals("0")).forEach(v -> {
            var rowData = data.get(v.getKey());
            var loginModel = LoginModel.builder().firstName(rowData.get(1)).lastName(rowData.get(2)).email(rowData.get(3)).password(rowData.get(4)).company(rowData.get(5)).build();
            loginData.add(loginModel);
        });
        //System.out.println("Result" + loginData.get(1));
        Object[][] result = new Object[loginData.size()][1];
        for (int i = 0; i < loginData.size(); i++) {
            result[i][0] = loginData.get(i);
        }
        return result;
    }
}
