package demo.nopcommerce.projects.demo.dataprovider;

import demo.nopcommerce.common.BaseConst;
import demo.nopcommerce.helpers.ExcelHelpers;
import demo.nopcommerce.projects.demo.model.EmployeeModel;
import lombok.var;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.List;

public class AddEmployeeProvider {

    final ExcelHelpers xlsReader = new ExcelHelpers();

    @DataProvider(name = "addEmployee")
    public Object[][] addCandidateProvider() {
        String xlsFileName = "employeeTest.xlsx";

        String filePath = BaseConst.xlsxFileAbsolutePath + xlsFileName;

        var data = xlsReader.readXLSData(filePath);
        List<EmployeeModel> employeeData = new ArrayList<>();
        data.entrySet().stream().filter(v -> !v.getKey().equals("0")).forEach(v -> {
            var rowData = data.get(v.getKey());
            var employeeModel = EmployeeModel.builder().firstName(rowData.get(0)).lastName(rowData.get(2)).img(rowData.get(3)).id(rowData.get(4)).nickname(rowData.get(5)).otherID(rowData.get(6))
                    .licenseNumber(rowData.get(7)).licenseExpiryDate(rowData.get(8)).numberSSN(rowData.get(9)).numberSIN(rowData.get(10)).
                    nationality(rowData.get(11)).maritalStatus(rowData.get(12)).dateOfBirth(rowData.get(13)).gender(rowData.get(14)).militaryService(rowData.get(15)).smoker(rowData.get(16)).build();
            employeeData.add(employeeModel);
        });
        Object[][] result = new Object[employeeData.size()][1];
        for (int i = 0; i < employeeData.size(); i++) {
            result[i][0] = employeeData.get(i);
        }
        return result;
    }
}

