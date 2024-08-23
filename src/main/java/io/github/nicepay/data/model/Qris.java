package io.github.nicepay.data.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Builder
public class Qris {
    @Getter
    @Setter
    private String partnerReferenceNo;

    @Getter
    @Setter
    private String merchantId;

    @Getter
    @Setter
    private String storeId;

    @Getter
    @Setter
    private String validityPeriod;

    @Getter
    @Setter
    private Map<String, Object> amount;

    @Getter
    @Setter
    private Map<String, Object> additionalInfo;

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
    }

    public Qris(String partnerReferenceNo, String merchantId, String storeId, String validityPeriod,Map<String, Object> amount, Map<String, Object> additionalInfo) {
        this.partnerReferenceNo = partnerReferenceNo;
        this.merchantId = merchantId;
        this.storeId = storeId;
        this.validityPeriod = validityPeriod;
        this.amount = amount;
        this.additionalInfo = additionalInfo;
    }
}
