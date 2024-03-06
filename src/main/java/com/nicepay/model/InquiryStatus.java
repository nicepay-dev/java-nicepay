package com.nicepay.model;

import lombok.Builder;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


public class InquiryStatus {

//    VA
    private String partnerServiceId;
    private String customerNo;
    private String virtualAccountNo;
    private String inquiryRequestId;
    private Map<String, Object> additionalInfoMap = new HashMap<>();

//    E-Wallet
    private String merchantId;
    private String subMerchantId;
    private String originalPartnerReferenceNo;
    private String originalReferenceNo;
    private String serviceCode;
    private String transactionDate;
    private String externalStoreId;
    private Map<String, Object> amount = new HashMap<>();


    public InquiryStatus partnerServiceId(String partnerServiceId) {
        this.partnerServiceId = partnerServiceId;
        return this;
    }

    public InquiryStatus customerNo(String customerNo) {
        this.customerNo = customerNo;
        return this;
    }

    public InquiryStatus virtualAccountNo(String virtualAccountNo) {
        this.virtualAccountNo = virtualAccountNo;
        return this;
    }

    public InquiryStatus inquiryRequestId(String inquiryRequestId) {
        this.inquiryRequestId = inquiryRequestId;
        return this;
    }

    public InquiryStatus totalAmount(String value, String currency) {
        Map<String, Object> totalAmountMap = new HashMap<>();
        totalAmountMap.put("value", value);
        totalAmountMap.put("currency", currency);
        additionalInfoMap.put("totalAmount", totalAmountMap);
        return this;
    }

    public InquiryStatus trxId(String trxId) {
        additionalInfoMap.put("trxId", trxId);
        return this;
    }

    public InquiryStatus tXidVA(String tXidVA) {
        additionalInfoMap.put("tXidVA", tXidVA);
        return this;
    }

    //E-wallet
    public InquiryStatus merchantId(String merchantId) {
        this.merchantId = merchantId;
        return this;
    }

    public InquiryStatus subMerchantId(String subMerchantId) {
        this.subMerchantId = subMerchantId;
        return this;
    }

    public InquiryStatus originalPartnerReferenceNo(String originalPartnerReferenceNo) {
        this.originalPartnerReferenceNo = originalPartnerReferenceNo;
        return this;
    }

    public InquiryStatus originalReferenceNo(String originalReferenceNo) {
        this.originalReferenceNo = originalReferenceNo;
        return this;
    }

    public InquiryStatus serviceCode(String serviceCode) {
        this.serviceCode = serviceCode;
        return this;
    }

    public InquiryStatus transactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
        return this;
    }

    public InquiryStatus externalStoreId(String externalStoreId) {
        this.externalStoreId = externalStoreId;
        return this;
    }

    public InquiryStatus amount(String value, String currency) {
        Map<String, Object> amountMap = new HashMap<>();
        amountMap.put("value", value);
        amountMap.put("currency", currency);
        this.amount = amountMap;
        return this;
    }


    public Map<String, Object> build() {
        Map<String, Object> requestDataMap = new HashMap<>();
        //VA
        requestDataMap.put("partnerServiceId", partnerServiceId);
        requestDataMap.put("customerNo", customerNo);
        requestDataMap.put("virtualAccountNo", virtualAccountNo);
        requestDataMap.put("inquiryRequestId", inquiryRequestId);
        //E-Wallet
        requestDataMap.put("merchantId", merchantId);
        requestDataMap.put("subMerchantId", subMerchantId);
        requestDataMap.put("originalPartnerReferenceNo", originalPartnerReferenceNo);
        requestDataMap.put("originalReferenceNo", originalReferenceNo);
        requestDataMap.put("serviceCode", serviceCode);
        requestDataMap.put("transactionDate", transactionDate);
        requestDataMap.put("externalStoreId", externalStoreId);

        if (additionalInfoMap.get("totalAmount")== null){
            requestDataMap.put("amount",amount);
        }
        //AdditionalInfo
        requestDataMap.put("additionalInfo", additionalInfoMap);
        return requestDataMap;
    }




}
