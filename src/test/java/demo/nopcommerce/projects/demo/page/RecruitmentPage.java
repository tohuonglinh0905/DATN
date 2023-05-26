package demo.nopcommerce.projects.demo.page;

import demo.nopcommerce.common.BaseConst;
import demo.nopcommerce.common.BasePage;
import demo.nopcommerce.projects.demo.model.VacancyModel;
import demo.nopcommerce.utils.WebUI;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class RecruitmentPage extends BasePage {
    private String sortDropDownXpath="//div[text()='%s']/descendant::div[@class='oxd-table-header-sort']";
    private String deleteBtnXpath = "//%s[text()='%s']/following::i[contains(@class,'%s')]";
    private String sortTypeXpath="//div[text()='%s']//span[text()='%s'] ";

    public RecruitmentPage(WebDriver driver) {
        webDriver=driver;

    }
    public void clickToVacancies(){
        clickToElement(BaseConst.DYNAMIC_LOCATOR_TEXT_FORM,"a","Vacancies");
        verifyHeaderDisplay("h5","Vacancies");
    }
    public void addVacancy(VacancyModel vacancyModel){
        clickToButtonWithText(" Add ");
        verifyHeaderDisplay("h6","Add Vacancy");
        inputTextWithLabelText("Vacancy Name",vacancyModel.getVacancyName());
        selectDynamicDropDownByLabelText_SpanText("Job Title",vacancyModel.getJobTitle());
        inputTextWithLabelTextToTextarea("Description",vacancyModel.getDescription());
        inputTextWithLabelText("Hiring Manager",vacancyModel.getHiringManager());
        clickToElement(BaseConst.DYNAMIC_LOCATOR_TEXT_FORM,"span",vacancyModel.getHiringManager());
        inputTextWithLabelText("Number of Positions",vacancyModel.getNumOfPosition());
        clickToButtonWithText(" Save ");
        //verifyNoticeSuccessXpath();
        //verify data
        verifyHeaderDisplay("label","Vacancy Name");
        verifyInputTextByLabel("Vacancy Name","input",vacancyModel.getVacancyName());

        verifyHeaderDisplay("div",vacancyModel.getJobTitle());
        verifyDataGetText(String.format(BaseConst.DYNAMIC_LOCATOR_INPUT_TEXTAREA_LABEL_TEXT_FORM,"Job Title","div[@class='oxd-select-text-input']"),vacancyModel.getJobTitle());
        verifyInputTextByLabel("Description","textarea",vacancyModel.getDescription());
        verifyInputTextByLabel("Hiring Manager","input",vacancyModel.getHiringManager());
        verifyInputTextByLabel("Number of Positions","input",vacancyModel.getNumOfPosition());

    }
    public void sortVacancyByJobTitle(String sortType){
       List<VacancyModel>beforeSortList= getAllVacancyItemInTable();
       WebUI.waitForElementVisible(WebUI.getByXpathDynamic(String.format(sortDropDownXpath,"Job Title")));
       clickToElement(String.format(sortDropDownXpath,"Job Title"));
       clickToElement(sortTypeXpath,"Job Title",sortType);
        beforeSortList=beforeSortList.stream().sorted(Comparator.comparing(VacancyModel::getJobTitle)).collect(Collectors.toList());
       if(sortType.equals("Decending")){
            Collections.reverse(beforeSortList);
       }
       List<VacancyModel>afterSortList=getAllVacancyItemInTable();
       verifySortListItem(beforeSortList,afterSortList);
    }
    public void deleteVacancyItem(String vacancyName){
        clickToElement(String.format(deleteBtnXpath, "div", vacancyName, "oxd-icon bi-trash"));
        verifyElementIsDisplay(String.format(BaseConst.DYNAMIC_LOCATOR_TEXT_FORM, "button", " Yes, Delete "));
        clickToButtonWithText(" Yes, Delete ");
        verifyNoticeSuccessXpath();
    }
    public List<VacancyModel> getAllVacancyItemInTable(){
        List<VacancyModel> vacancyModelList=new ArrayList<>();
        List<WebElement> listRow=findElements(String.format(BaseConst.DYNAMIC_LOCATOR_TABLE_FORM,"row"));
        for(int i=1;i<listRow.size();i++){
            VacancyModel vacancyModel=setVacancyData(i+1);
            vacancyModelList.add(vacancyModel);
        }

        return vacancyModelList;
    }
    public VacancyModel setVacancyData(int indexRow){
        int indexVacancyName=getIndexHeaderTable("Vacancy");
        int indexJobTitle=getIndexHeaderTable("Job Title");
        int indexHiringManager=getIndexHeaderTable("Hiring Manager");
        int indexStatus=getIndexHeaderTable("Status");
        VacancyModel vacancyModel=new VacancyModel();
        vacancyModel.setVacancyName(getTextOfElement(String.format(BaseConst.DYNAMIC_CELL_TABLE_FORM,indexRow,indexVacancyName)));
        vacancyModel.setJobTitle(getTextOfElement(String.format(BaseConst.DYNAMIC_CELL_TABLE_FORM,indexRow,indexJobTitle)));
        vacancyModel.setHiringManager(getTextOfElement(String.format(BaseConst.DYNAMIC_CELL_TABLE_FORM,indexRow,indexHiringManager)));
        vacancyModel.setActive(getTextOfElement(String.format(BaseConst.DYNAMIC_CELL_TABLE_FORM,indexRow,indexStatus)));
        return  vacancyModel;

    }
    public boolean compareToTwoList(List<VacancyModel> listBeforeSorting, List<VacancyModel> listAfterSorting)
    {
        // Assert.a(listBeforeSorting,listAfterSorting);
        if(listAfterSorting.size()!=listAfterSorting.size())
            return false;
        else {
            for(int i=0;i<listBeforeSorting.size();i++){
                if(listBeforeSorting.get(i).getVacancyName().equals(listAfterSorting.get(i).getVacancyName())&&
                        listBeforeSorting.get(i).getJobTitle().equals(listAfterSorting.get(i).getJobTitle())&&
                        listBeforeSorting.get(i).getHiringManager().equals(listAfterSorting.get(i).getHiringManager())&&
                        listBeforeSorting.get(i).getActive().equals(listAfterSorting.get(i).getActive()))
                {
                    continue;
                }
                else{
                    return false;
                }
            }
        }
        return true;
    }
    public void verifySortListItem(List<VacancyModel> listBeforeSorting, List<VacancyModel> listAfterSorting){
        Assert.assertTrue(compareToTwoList(listBeforeSorting, listAfterSorting));
    }
    public void verifyNoticeSuccessXpath() {
        String element = String.format(BaseConst.DYNAMIC_LOCATOR_TEXT_FORM, "p", "Success");
        verifyElementIsDisplay(element);
    }

}
