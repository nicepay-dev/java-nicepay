package io.github.nicepay.data.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@Builder
public class Qris {
    
    
    private String partnerReferenceNo;
    private String merchantId;
    private String storeId;
    private String validityPeriod;
    private Map<String, Object> amount;
    private Map<String, Object> additionalInfo;

//    v2
    private String timeStamp;
    private String iMid;
    private String payMethod;
    private String currency;
    private String amt;
    private String referenceNo;
    private String goodsNm;
    private String billingNm;
    private String billingPhone;
    private String billingEmail;
    private String billingCity;
    private String billingState;
    private String billingPostCd;
    private String billingCountry;
    private String dbProcessUrl;
    private String merchantToken;
    private String paymentExpDt;
    private String paymentExpTm;
    private String userIP;
    private String cartData;
    private String mitraCd;
    private String shopId;



    public static class QrisBuilder {
        private Map<String, Object> amount;
        private Map<String, Object> additionalInfo;

        public QrisBuilder amount(String value, String currency) {
            Map<String, Object> amountMap = new HashMap<>();
            amountMap.put("value", value);
            amountMap.put("currency", currency);
            this.amount = amountMap;
            return this;
        }

        public QrisBuilder additionalInfo(String goodsNm, String billingNm, String billingPhone
        , String billingEmail, String billingCity,String billingAddr, String billingState, String billingPostCd,
        String billingCountry, String callBackUrl, String dbProcessUrl, String userIP, String cartData,
        String mitraCd, String msId, String msFee, String msFeeType, String mbFee, String mbFeeType){
            Map<String, Object> additonalInfoMap = new HashMap<>();

            additonalInfoMap.put("goodsNm", goodsNm);
            additonalInfoMap.put("billingNm", billingNm);
            additonalInfoMap.put("billingPhone", billingPhone);
            additonalInfoMap.put("billingEmail", billingEmail);
            additonalInfoMap.put("billingCity", billingCity);
            additonalInfoMap.put("billingAddr", billingAddr);
            additonalInfoMap.put("billingState", billingState);
            additonalInfoMap.put("billingPostCd", billingPostCd);
            additonalInfoMap.put("billingCountry", billingCountry);
            additonalInfoMap.put("callBackUrl", callBackUrl);
            additonalInfoMap.put("dbProcessUrl", dbProcessUrl);
            additonalInfoMap.put("userIP", userIP);
            additonalInfoMap.put("cartData", cartData);
            additonalInfoMap.put("mitraCd", mitraCd);
            additonalInfoMap.put("msId", msId);
            additonalInfoMap.put("msFee", msFee);
            additonalInfoMap.put("msFeeType", msFeeType);
            additonalInfoMap.put("mbFee", mbFee);
            additonalInfoMap.put("mbFeeType", mbFeeType);

            this.additionalInfo = additonalInfoMap;
            return this;
        }

        public QrisBuilder merchantToken(String timeStamp, String imid, String reffNo, String amount, String merchantKey) {
            this.merchantToken = timeStamp + imid + reffNo + amount + merchantKey;
            return this;
        }
    }

    public Qris(String partnerReferenceNo, String merchantId, String storeId, String validityPeriod, Map<String, Object> amount, Map<String, Object> additionalInfo, String timeStamp, String iMid, String payMethod, String currency, String amt, String referenceNo, String goodsNm, String billingNm, String billingPhone, String billingEmail, String billingCity, String billingState, String billingPostCd, String billingCountry, String dbProcessUrl, String merchantToken, String paymentExpDt, String paymentExpTm, String userIP, String cartData, String mitraCd, String shopId) {
        this.partnerReferenceNo = partnerReferenceNo;
        this.merchantId = merchantId;
        this.storeId = storeId;
        this.validityPeriod = validityPeriod;
        this.amount = amount;
        this.additionalInfo = additionalInfo;
        this.timeStamp = timeStamp;
        this.iMid = iMid;
        this.payMethod = payMethod;
        this.currency = currency;
        this.amt = amt;
        this.referenceNo = referenceNo;
        this.goodsNm = goodsNm;
        this.billingNm = billingNm;
        this.billingPhone = billingPhone;
        this.billingEmail = billingEmail;
        this.billingCity = billingCity;
        this.billingState = billingState;
        this.billingPostCd = billingPostCd;
        this.billingCountry = billingCountry;
        this.dbProcessUrl = dbProcessUrl;
        this.merchantToken = merchantToken;
        this.paymentExpDt = paymentExpDt;
        this.paymentExpTm = paymentExpTm;
        this.userIP = userIP;
        this.cartData = cartData;
        this.mitraCd = mitraCd;
        this.shopId = shopId;
    }
}
