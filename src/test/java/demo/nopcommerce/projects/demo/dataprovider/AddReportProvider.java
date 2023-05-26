package demo.nopcommerce.projects.demo.dataprovider;

import demo.nopcommerce.common.BaseConst;
import demo.nopcommerce.helpers.ExcelHelpers;
import demo.nopcommerce.projects.demo.model.ReportModel;
import lombok.var;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.List;

public class AddReportProvider {

    final ExcelHelpers xlsReader = new ExcelHelpers();

    @DataProvider(name = "addReport")
    public Object[][] addCandidateProvider() {
        String xlsFileName = "Report_HRM_01.xlsx";

        String filePath = BaseConst.xlsxFileAbsolutePath + xlsFileName;

        var data = xlsReader.readXLSData(filePath);
        List<ReportModel> reportData = new ArrayList<>();
        data.entrySet().stream().filter(v -> !v.getKey().equals("0")).forEach(v -> {
            var rowData = data.get(v.getKey());
            var reportModel = ReportModel.builder().reportName(rowData.get(0)).selectionCriteria(rowData.get(1)).include(rowData.get(2)).displayFieldGroup(rowData.get(3)).displayField(rowData.get(4)).build();
            reportData.add(reportModel);
        });
        Object[][] result = new Object[reportData.size()][1];
        for (int i = 0; i < reportData.size(); i++) {
            result[i][0] = reportData.get(i);
        }
        return result;
    }
}

