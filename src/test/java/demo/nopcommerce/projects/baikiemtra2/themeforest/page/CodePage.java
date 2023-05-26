package demo.nopcommerce.projects.baikiemtra2.themeforest.page;

import demo.nopcommerce.common.BaseConst;
import demo.nopcommerce.common.BasePage;
import demo.nopcommerce.constants.FailureHandling;
import demo.nopcommerce.projects.baikiemtra2.themeforest.model.ProductModel;
import demo.nopcommerce.utils.WebUI;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import java.util.*;
import java.util.stream.Collectors;

public class CodePage extends BasePage {
    private String addToCartXpath="//div[contains(@class,'component__list')]/div[contains(@class,'component__root')][%s]//a[contains(@class,'root shared-item_cards-add_to_cart_button_component__compact')]/span";
    private String productListXpath="//div[contains(@class,'shared-item_cards-card')]";
    private String numOfSellItemXpath=String.format("%s[%s]//div[contains(@class,'cards-sales_component__root')]",productListXpath,"%s");
    private String nameItemXpath=String.format("%s[%s]//a[contains(@class,'_itemNameLink')]",productListXpath,"%s");

    public CodePage(WebDriver driver) {
        webDriver = driver;
    }

    public void searchValue(String keyValue) {
        inputTextWithNameThemeWeb("term", keyValue);
        //enter to search
        Actions actions = new Actions(webDriver);
        actions.sendKeys(Keys.ENTER).perform();
        List<WebElement> elementList = findElements(WebUI.getByXpathDynamic(String.format(BaseConst.DYNAMIC_LOCATOR_CLASS_CONTAINS_TEXT_FORM_THEMEWEB, "mark", "shared-item_cards-item_name_component__highlight")));
        for (int i = 0; i < elementList.size(); i++) {
            WebUI.assertTrueCondition(elementList.get(i), (elementList.get(i).getText()).toLowerCase().contains(keyValue.toLowerCase()), FailureHandling.CONTINUE_ON_FAILURE, "verify item name has contains keysearch");
        }
    }
    public ProductModel setProduct(int indexItem){
        ProductModel productModel=new ProductModel();
        String nameProduct=findElement(String.format(nameItemXpath,indexItem)).getText();
        productModel.setProductName(nameProduct);
        String numOfSellProduct=findElement(String.format(numOfSellItemXpath,indexItem)).getText().replace(" Sales","");
        float numSell;
        if(numOfSellProduct.contains("K")){
            numOfSellProduct.replace("K","");
            numSell=Float.parseFloat(numOfSellProduct)*1000;
        }
        else{
            numSell=Float.parseFloat(numOfSellProduct);
        }
        productModel.setNumOfSale(numSell);
        return productModel;
    }
    public List<ProductModel> getListProducts(){
        List<WebElement> listProductElement=findElements(WebUI.getByXpathDynamic(productListXpath));
        List<ProductModel> listProductModel=new ArrayList<>();
        for( int i=0;i<listProductElement.size();i++){
            listProductModel.add(setProduct(i+1));
        }
        return listProductModel;
    }


    public void sortByBestSeller() {
        clickToElement(BaseConst.DYNAMIC_LOCATOR_TEXT_FORM_THEMEWEB, "a","Best sellers");
        //1,get list after sort in web
        List<ProductModel> listProduct =getListProducts();
        //2, shuffle the list just sort in web
        List<ProductModel> shuffleList =listProduct;
        Collections.shuffle(shuffleList);
        //3, sort list in step 2
        List<ProductModel>expList=shuffleList.stream().sorted(Comparator.comparing(ProductModel::getNumOfSale).reversed()).collect(Collectors.toList());
        //compare to list
        Assert.assertEquals(expList.stream().map(ProductModel::getProductName).collect(Collectors.toList()),listProduct.stream().map(ProductModel::getProductName).collect(Collectors.toList()));
    }
    public void addToCart(){
        for(int i=1;i<=5;i++){
            clickToElement(addToCartXpath,i+"");
            verifyHeaderDisplayThemeForest("h2","Item added to your cart");
            clickToElement(BaseConst.DYNAMIC_LOCATOR_TEXT_FORM_THEMEWEB,"button","Keep Browsing");
        }
    }
    public CartPage goToCart(){
        clickToElement(BaseConst.DYNAMIC_LOCATOR_CLASS_CONTAINS_TEXT_FORM_THEMEWEB,"a","cart_link_component__root");
        verifyHeaderDisplayThemeForest("h1","      Shopping Cart");
        return new CartPage(webDriver);
    }




    /**
     * input text to input tag with name
     *
     * @param title
     * @param text
     */
    public void inputTextWithNameThemeWeb(String title, String text) {
        String xpath = String.format(BaseConst.DYNAMIC_LOCATOR_INPUT_NAME, title);
        WebElement element = findElement(xpath);
        inputTextTo(element, title, text);
    }

    public void shuffleList(List<Object> listItem) {
        for(int i=0;i<listItem.size();i++){
            int indexRandom=(int)(Math.random()*(listItem.size()-i)+i);
            System.out.println(indexRandom+"- indexrandom\n");
            Object temp=listItem.get(i);
            listItem.set(i,listItem.get(indexRandom));
            listItem.set(indexRandom,temp);

        }

    }

}
