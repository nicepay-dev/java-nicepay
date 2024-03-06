package com.nicepay.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Builder
public class Ewallet {

    @Getter
    @Setter
    private String partnerReferenceNo;

    @Getter
    @Setter
    private String merchantId;

    @Getter
    @Setter
    private String subMerchantId;

    @Getter
    @Setter
    private String externalStoreId;

    @Getter
    @Setter
    private String validUpTo;

    @Getter
    @Setter
    private String pointOfInitiation;

    @Getter
    @Setter
    private Map<String, String> amount;

    @Getter
    @Setter
    private Map<String, String> additionalInfo;

    @Getter
    @Setter
    private List<Map<String, String>> urlParam ;

    //check status

    @Getter
    @Setter
    private String originalPartnerReferenceNo;

    @Getter
    @Setter
    private String originalReferenceNo;

    @Getter
    @Setter
    private String serviceCode;

    @Getter
    @Setter
    private String transactionDate;

    //Refund
    @Getter
    @Setter
    private String partnerRefundNo;

    @Getter
    @Setter
    private String reason;

    @Getter
    @Setter
    private Map<String, String> refundAmount;



    public Ewallet(String partnerReferenceNo, String merchantId, String subMerchantId, String externalStoreId
    , String validUpTo, String pointOfInitiation, Map<String, String> amount, Map<String, String> additionalInfo, List<Map<String,String>>urlParam, String originalPartnerReferenceNo
    , String originalReferenceNo, String serviceCode, String transactionDate, String partnerRefundNo, String reason, Map<String, String> refundAmount){
        this.partnerReferenceNo= partnerReferenceNo;
        this.merchantId = merchantId;
        this.subMerchantId = subMerchantId;
        this.externalStoreId = externalStoreId;
        this.validUpTo = validUpTo;
        this.pointOfInitiation = pointOfInitiation;
        this.amount = amount;
        this.additionalInfo = additionalInfo;
        this.urlParam = urlParam;
        this.originalPartnerReferenceNo = originalPartnerReferenceNo;
        this.originalReferenceNo = originalReferenceNo;
        this.serviceCode = serviceCode;
        this.transactionDate = transactionDate;
        this.partnerRefundNo = partnerRefundNo;
        this.reason = reason;
        this.refundAmount = refundAmount;


    }

    @Override
    public String toString() {
        return "{" +
                "partnerReferenceNo='" + partnerReferenceNo + '\'' +
                ", merchantId='" + merchantId + '\'' +
                ", subMerchantId='" + subMerchantId + '\'' +
                ", externalStoreId='" + externalStoreId + '\'' +
                ", validUpTo='" + validUpTo + '\'' +
                ", pointOfInitiation=" + pointOfInitiation +
                ", amount=" + amount +
                ", urlParam=" + urlParam +
                ", additionalInfo=" + additionalInfo +
                ", originalPartnerReferenceNo=" + originalPartnerReferenceNo +
                ", originalReferenceNo=" + originalReferenceNo +
                ", serviceCode=" + serviceCode +
                ", transactionDate=" + transactionDate +
                ", partnerRefundNo=" + partnerRefundNo +
                '}';
    }

}


