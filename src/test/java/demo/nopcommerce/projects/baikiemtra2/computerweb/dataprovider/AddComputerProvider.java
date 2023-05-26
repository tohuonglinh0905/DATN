package demo.nopcommerce.projects.baikiemtra2.computerweb.dataprovider;

import demo.nopcommerce.common.BaseConst;
import demo.nopcommerce.helpers.ExcelHelpers;
import demo.nopcommerce.projects.baikiemtra2.computerweb.model.ComputerModel;
import lombok.var;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.List;

public class AddComputerProvider {

    final ExcelHelpers xlsReader = new ExcelHelpers();

    @DataProvider(name = "addComputerProvider")
    public Object[][] AddComputerProvider() {
        String xlsFileName = "computerTest.xlsx";
        String filePath = BaseConst.folderPathBaiKT2 + xlsFileName;

        var data = xlsReader.readXLSData(filePath);
        List<ComputerModel> computerData = new ArrayList<>();
        data.entrySet().stream().filter(v -> !v.getKey().equals("0")).forEach(v -> {
            var rowData = data.get(v.getKey());
            var computerModel = ComputerModel.builder().computerName(rowData.get(1)).introduced(rowData.get(2)).discontinued(rowData.get(3)).company(rowData.get(4)).build();
            computerData.add(computerModel);
        });
        System.out.println("Result" + computerData.get(1));
        Object[][] result = new Object[computerData.size()][1];
        for (int i = 0; i < computerData.size(); i++) {
            result[i][0] = computerData.get(i);
        }
        return result;
    }
}



