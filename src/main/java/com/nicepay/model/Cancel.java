package com.nicepay.model;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;


public class Cancel {

//    VA
    private String partnerServiceId;
    private String customerNo;
    private String virtualAccountNo;
    private String trxId;
    private Map<String, Object> additionalInfoMap = new HashMap<>();

//    E-Wallet
    private String merchantId;
    private String subMerchantId;
    private String originalPartnerReferenceNo;
    private String originalReferenceNo;
    private String serviceCode;
    private String transactionDate;
    private String externalStoreId;
    private Map<String, Object> refundAmount = new HashMap<>();

    //refund
    private String partnerRefundNo;

    private String reason ;


    public Cancel reason(String reason) {
        this.reason = reason;
        return this;
    }

    public Cancel partnerServiceId(String partnerServiceId) {
        this.partnerServiceId = partnerServiceId;
        return this;
    }

    public Cancel customerNo(String customerNo) {
        this.customerNo = customerNo;
        return this;
    }

    public Cancel virtualAccountNo(String virtualAccountNo) {
        this.virtualAccountNo = virtualAccountNo;
        return this;
    }

    public Cancel partnerRefundNo(String partnerRefundNo) {
        this.partnerRefundNo = partnerRefundNo;
        return this;
    }

    public Cancel totalAmount(String value, String currency) {
        Map<String, Object> totalAmountMap = new HashMap<>();
        totalAmountMap.put("value", value);
        totalAmountMap.put("currency", currency);
        additionalInfoMap.put("totalAmount", totalAmountMap);
        return this;
    }

    public Cancel trxId(String trxId) {
//        additionalInfoMap.put("trxId", trxId);
        this.trxId = trxId;
        return this;
    }

    public Cancel tXidVA(String tXidVA) {
        additionalInfoMap.put("tXidVA", tXidVA);
        return this;
    }

    public Cancel cancelMessage(String cancelMessage) {
        additionalInfoMap.put("cancelMessage", cancelMessage);
        return this;
    }

    public Cancel refundType(String refundType) {
        additionalInfoMap.put("refundType", refundType);
        return this;
    }
    //E-wallet
    public Cancel merchantId(String merchantId) {
        this.merchantId = merchantId;
        return this;
    }

    public Cancel subMerchantId(String subMerchantId) {
        this.subMerchantId = subMerchantId;
        return this;
    }

    public Cancel originalPartnerReferenceNo(String originalPartnerReferenceNo) {
        this.originalPartnerReferenceNo = originalPartnerReferenceNo;
        return this;
    }

    public Cancel originalReferenceNo(String originalReferenceNo) {
        this.originalReferenceNo = originalReferenceNo;
        return this;
    }

    public Cancel externalStoreId(String externalStoreId) {
        this.externalStoreId = externalStoreId;
        return this;
    }

    public Cancel refundAmount(String value, String currency) {
        Map<String, Object> amountMap = new HashMap<>();
        amountMap.put("value", value);
        amountMap.put("currency", currency);
        this.refundAmount = amountMap;
        return this;
    }


    public LinkedHashMap<String, Object> build() {
        LinkedHashMap<String, Object> requestDataMap = new LinkedHashMap<>();
        //VA
        requestDataMap.put("partnerServiceId", partnerServiceId);
        requestDataMap.put("customerNo", customerNo);
        requestDataMap.put("virtualAccountNo", virtualAccountNo);
        requestDataMap.put("trxId", trxId);
        //E-Wallet
        requestDataMap.put("merchantId", merchantId);
        requestDataMap.put("subMerchantId", subMerchantId);
        requestDataMap.put("originalPartnerReferenceNo", originalPartnerReferenceNo);
        requestDataMap.put("originalReferenceNo", originalReferenceNo);
        requestDataMap.put("serviceCode", serviceCode);
        requestDataMap.put("transactionDate", transactionDate);
        requestDataMap.put("externalStoreId", externalStoreId);
        requestDataMap.put("partnerRefundNo", partnerRefundNo);
        requestDataMap.put("reason", reason);
        if (additionalInfoMap.get("totalAmount")== null){
            requestDataMap.put("refundAmount",refundAmount);
        }
        //AdditionalInfo
        requestDataMap.put("additionalInfo", additionalInfoMap);
        return requestDataMap;
    }




}
