package demo.nopcommerce.projects.demo.dataprovider;

import demo.nopcommerce.common.BaseConst;
import demo.nopcommerce.helpers.ExcelHelpers;
import demo.nopcommerce.projects.demo.model.CustomFiledModel;
import lombok.var;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.List;

public class AddCustomFiledProvider {

    final ExcelHelpers xlsReader = new ExcelHelpers();

    @DataProvider(name = "addCustomField")
    public Object[][] addCandidateProvider() {
        String xlsFileName = "customfield.xlsx";

        String filePath = BaseConst.xlsxFileAbsolutePath + xlsFileName;

        var data = xlsReader.readXLSData(filePath);
        List<CustomFiledModel> customFieldData = new ArrayList<>();
        data.entrySet().stream().filter(v -> !v.getKey().equals("0")).forEach(v -> {
            var rowData = data.get(v.getKey());
            var customFiledModel = CustomFiledModel.builder().fieldName(rowData.get(0)).screen(rowData.get(1)).type(rowData.get(2)).select(rowData.get(3)).build();
            customFieldData.add(customFiledModel);
        });
        System.out.println("Result" + customFieldData.get(1));
        Object[][] result = new Object[customFieldData.size()][1];
        for (int i = 0; i < customFieldData.size(); i++) {
            result[i][0] = customFieldData.get(i);
        }
        return result;
    }
}

