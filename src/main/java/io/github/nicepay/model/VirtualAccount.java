package io.github.nicepay.model;

import lombok.*;

import java.util.HashMap;
import java.util.Map;

@Builder
@Data
@AllArgsConstructor
public class VirtualAccount {
    //V2
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
    private String bankCd;
    private String vacctValidDt;
    private String vacctValidTm;
    private String merFixAcctId;
    private String dbProcessUrl;
    private String merchantToken;

    //snap
    private String partnerServiceId;
    private String customerNo;
    private String virtualAccountNo;
    private String virtualAccountName;
    private String trxId;
    private Map<String, Object> totalAmount;
    private Map<String, Object> additionalInfo;
    private String tXidVA;

    public static class VirtualAccountBuilder {
        private Map<String, Object> totalAmount;

        public VirtualAccountBuilder totalAmount(String value, String currency) {
            Map<String, Object> amountMap = new HashMap<>();
            amountMap.put("value", value);
            amountMap.put("currency", currency);
            this.totalAmount = amountMap;
            return this;
        }
    }
}
