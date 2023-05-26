package demo.nopcommerce.projects.baikiemtra2.themeforest.dataprovider;

import demo.nopcommerce.common.BaseConst;
import demo.nopcommerce.helpers.ExcelHelpers;
import demo.nopcommerce.projects.baikiemtra2.themeforest.model.UserModel;
import lombok.var;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.List;

public class AddUserProvider {

    final ExcelHelpers xlsReader = new ExcelHelpers();

    @DataProvider(name = "addUser")
    public Object[][] AddUserProvider() {
        String xlsFileName = "userTest.xlsx";
        String filePath = BaseConst.folderPathBaiKT2 + xlsFileName;

        var data = xlsReader.readXLSData(filePath);
        List<UserModel> userData = new ArrayList<>();
        data.entrySet().stream().filter(v -> !v.getKey().equals("0")).forEach(v -> {
            var rowData = data.get(v.getKey());
            var userModel = UserModel.builder().firstName(rowData.get(1)).lastName(rowData.get(2)).email(rowData.get(3)).userName(rowData.get(4)).password(rowData.get(5)).build();
            userData.add(userModel);
        });
        System.out.println("Result" + userData.get(1));
        Object[][] result = new Object[userData.size()][1];
        for (int i = 0; i < userData.size(); i++) {
            result[i][0] = userData.get(i);
        }
        return result;
    }
}





