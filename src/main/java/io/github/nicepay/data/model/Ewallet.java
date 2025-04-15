package io.github.nicepay.data.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@Builder
public class Ewallet {

//    SNAP
    private String partnerReferenceNo;
    private String merchantId;
    private String subMerchantId;
    private String externalStoreId;
    private String validUpTo;
    private String pointOfInitiation;
    private Map<String, Object> amount;
    private Map<String, Object> additionalInfo;
    private List<Map<String, String>> urlParam ;

//    V2
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
    private String billingAddr;
    private String billingCity;
    private String billingState;
    private String billingPostCd;
    private String billingCountry;
    private String deliveryNm;
    private String deliveryPhone;
    private String deliveryAddr;
    private String deliveryCity;
    private String deliveryState;
    private String deliveryPostCd;
    private String deliveryCountry;
    private String dbProcessUrl;
    private String vat;
    private String fee;
    private String notaxAmt;
    private String description;
    private String merchantToken;
    private String reqDt;
    private String reqTm;
    private String reqDomain;
    private String reqServerIP;
    private String reqClientVer;
    private String userIP;
    private String userSessionID;
    private String userAgent;
    private String userLanguage;
    private String cartData;
    private String mitraCd;

//    PAYMENT
    private String tXid;
    private String returnJsonFormat;
    private String callBackUrl;

    public static class EwalletBuilder {
        private Map<String, Object> totalAmount;
        private List<Map<String, String>> urlParam ;

        public EwalletBuilder merchantToken(String timeStamp, String imid, String reffNo, String amount, String merchantKey) {
            this.merchantToken = timeStamp + imid + reffNo + amount + merchantKey;
            return this;
        }
        public EwalletBuilder amount(String value, String currency) {
            Map<String, Object> amountMap = new HashMap<>();
            amountMap.put("value", value);
            amountMap.put("currency", currency);
            this.amount = amountMap;
            return this;
        }

        public EwalletBuilder urlParam(String[][] urlParams) {
            List<Map<String, String>> urlParamList = new ArrayList<>();
            for (String[] params : urlParams) {
                if (params.length == 3) {
                    Map<String, String> paramListMap = new HashMap<>();
                    paramListMap.put("url", params[0]);
                    paramListMap.put("type", params[1]);
                    paramListMap.put("isDeeplink", params[2]);
                    urlParamList.add(paramListMap);
                }
            }
            this.urlParam = urlParamList;
            return this;
        }
    }


    public Ewallet(String partnerReferenceNo, String merchantId, String subMerchantId, String externalStoreId, String validUpTo, String pointOfInitiation, Map<String, Object> amount, Map<String, Object> additionalInfo, List<Map<String, String>> urlParam, String timeStamp, String iMid, String payMethod, String currency, String amt, String referenceNo, String goodsNm, String billingNm, String billingPhone, String billingEmail, String billingAddr, String billingCity, String billingState, String billingPostCd, String billingCountry, String deliveryNm, String deliveryPhone, String deliveryAddr, String deliveryCity, String deliveryState, String deliveryPostCd, String deliveryCountry, String dbProcessUrl, String vat, String fee, String notaxAmt, String description, String merchantToken, String reqDt, String reqTm, String reqDomain, String reqServerIP, String reqClientVer, String userIP, String userSessionID, String userAgent, String userLanguage, String cartData, String mitraCd, String tXid, String returnJsonFormat, String callBackUrl) {
        this.partnerReferenceNo = partnerReferenceNo;
        this.merchantId = merchantId;
        this.subMerchantId = subMerchantId;
        this.externalStoreId = externalStoreId;
        this.validUpTo = validUpTo;
        this.pointOfInitiation = pointOfInitiation;
        this.amount = amount;
        this.additionalInfo = additionalInfo;
        this.urlParam = urlParam;
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
        this.billingAddr = billingAddr;
        this.billingCity = billingCity;
        this.billingState = billingState;
        this.billingPostCd = billingPostCd;
        this.billingCountry = billingCountry;
        this.deliveryNm = deliveryNm;
        this.deliveryPhone = deliveryPhone;
        this.deliveryAddr = deliveryAddr;
        this.deliveryCity = deliveryCity;
        this.deliveryState = deliveryState;
        this.deliveryPostCd = deliveryPostCd;
        this.deliveryCountry = deliveryCountry;
        this.dbProcessUrl = dbProcessUrl;
        this.vat = vat;
        this.fee = fee;
        this.notaxAmt = notaxAmt;
        this.description = description;
        this.merchantToken = merchantToken;
        this.reqDt = reqDt;
        this.reqTm = reqTm;
        this.reqDomain = reqDomain;
        this.reqServerIP = reqServerIP;
        this.reqClientVer = reqClientVer;
        this.userIP = userIP;
        this.userSessionID = userSessionID;
        this.userAgent = userAgent;
        this.userLanguage = userLanguage;
        this.cartData = cartData;
        this.mitraCd = mitraCd;
        this.tXid = tXid;
        this.returnJsonFormat = returnJsonFormat;
        this.callBackUrl = callBackUrl;
    }
}


