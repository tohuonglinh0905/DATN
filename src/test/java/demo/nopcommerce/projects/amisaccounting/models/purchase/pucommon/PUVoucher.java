package demo.nopcommerce.projects.amisaccounting.models.purchase.pucommon;

import demo.nopcommerce.common.BaseModel;
import demo.nopcommerce.helpers.ModelMapper;

public class PUVoucher extends BaseModel {
    public ModelMapper postedDate;
    public ModelMapper refDate;
    public ModelMapper refNo;
    public ModelMapper currencyID;
    public ModelMapper exchangeRate;
    public ModelMapper description;

    public ModelMapper totalAmountOC;
    public ModelMapper totalAmount;
    public ModelMapper totalDiscountOC;
    public ModelMapper totalDiscount;
    public ModelMapper totalVATOC;
    public ModelMapper totalVAT;
    public ModelMapper totalPaymentOC;
    public ModelMapper totalPayment;
    public PUVoucher(){
        postedDate = createModelMapperObj("PostedDate", "PostedDate");
        refDate = createModelMapperObj("RefDate", "RefDate");
        refNo = createModelMapperObj("RefNo", "RefNo");
        currencyID = createModelMapperObj("CurrencyID", "CurrencyID");
        exchangeRate = createModelMapperObj("ExchangeRate", "ExchangeRate");
        description = createModelMapperObj("Description", "Description");

        totalAmountOC = createModelMapperObj("TotalAmountOC", "TotalSaleAmount");
        totalAmount = createModelMapperObj("TotalAmount", "TotalMoneyGoodsForeign");
        totalDiscountOC = createModelMapperObj("TotalDiscountOC", "TotalDiscountAmountOC");
        totalDiscount = createModelMapperObj("TotalDiscount", "TotalDiscountAmount");
        totalVATOC = createModelMapperObj("TotalVATOC", "TotalVatAmountOC");
        totalVAT = createModelMapperObj("TotalVAT", "TotalVatAmount");
        totalPaymentOC = createModelMapperObj("TotalPaymentOC", "PaymentTotalAmount");
        totalPayment = createModelMapperObj("TotalPayment", "TotalAmountForeign");
    }
}
