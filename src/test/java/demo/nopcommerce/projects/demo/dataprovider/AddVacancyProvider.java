package demo.nopcommerce.projects.demo.dataprovider;

import demo.nopcommerce.common.BaseConst;
import demo.nopcommerce.helpers.ExcelHelpers;
import demo.nopcommerce.projects.demo.model.VacancyModel;
import lombok.var;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.List;

public class AddVacancyProvider {

    final ExcelHelpers xlsReader = new ExcelHelpers();

    @DataProvider(name = "addVacancy")
    public Object[][] addCandidateProvider() {
        String xlsFileName = "Vacancy.xlsx";

        String filePath = BaseConst.xlsxFileAbsolutePath + xlsFileName;

        var data = xlsReader.readXLSData(filePath);
        List<VacancyModel> vacancyData = new ArrayList<>();
        data.entrySet().stream().filter(v -> !v.getKey().equals("0")).forEach(v -> {
            var rowData = data.get(v.getKey());
            var vacancyModel = VacancyModel.builder().vacancyName(rowData.get(0)).jobTitle(rowData.get(1)).description(rowData.get(2)).hiringManager(rowData.get(3)).numOfPosition(rowData.get(4)).build();
            vacancyData.add(vacancyModel);
        });
        System.out.println("Result" + vacancyData.get(0));
        Object[][] result = new Object[vacancyData.size()][1];
        for (int i = 0; i < vacancyData.size(); i++) {
            result[i][0] = vacancyData.get(i);
        }
        return result;
    }
}

