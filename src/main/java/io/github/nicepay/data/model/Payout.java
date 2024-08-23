package io.github.nicepay.data.model;

import lombok.*;

import java.util.HashMap;
import java.util.Map;

@Builder
public class Payout {
    @Getter
    @Setter
    private String merchantId;
    @Getter
    @Setter
    private String msId;
    @Getter
    @Setter
    private String beneficiaryAccountNo;
    @Getter
    @Setter
    private String beneficiaryName;;
    @Getter
    @Setter
    private String beneficiaryPhone;
    @Getter
    @Setter
    private String beneficiaryCustomerResidence;
    @Getter
    @Setter
    private String beneficiaryCustomerType;
    @Getter
    @Setter
    private String beneficiaryPostalCode;
    @Getter
    @Setter
    private String payoutMethod;
    @Getter
    @Setter
    private String beneficiaryBankCode;
    @Getter
    @Setter
    private Map<String, Object> amount;
    @Getter
    @Setter
    private String partnerReferenceNo;
    @Getter
    @Setter
    private String reservedDt;
    @Getter
    @Setter
    private String reservedTm;
    @Getter
    @Setter
    private String description;
    @Getter
    @Setter
    private String deliveryName;
    @Getter
    @Setter
    private String deliveryId;
    @Getter
    @Setter
    private String beneficiaryPOE;
    private String beneficiaryDOE;
    @Getter
    @Setter
    private String beneficiaryCoNo;
    @Getter
    @Setter
    private String beneficiaryAddress;
    @Getter
    @Setter
    private String beneficiaryAuthPhoneNumber;
    @Getter
    @Setter
    private String beneficiaryMerCategory;
    @Getter
    @Setter
    private String beneficiaryCoMgmtName;
    @Getter
    @Setter
    private String beneficiaryCoShName;
    @Getter
    @Setter
    private String originalPartnerReferenceNo;
    @Getter
    @Setter
    private String originalReferenceNo;

    @Getter
    @Setter
    private Map<String, Object> additionalInfo;

    public static class PayoutBuilder {
        private Map<String, Object> amount;
        private Map<String, Object> additionalInfo;

        public PayoutBuilder amount(String value, String currency) {
            Map<String, Object> amountMap = new HashMap<>();
            amountMap.put("value", value);
            amountMap.put("currency", currency);
            this.amount = amountMap;
            return this;
        }

        public PayoutBuilder additionalInfo(String msId){
            Map<String, Object> additonalInfoMap = new HashMap<>();
            additonalInfoMap.put("msId", msId);
            this.additionalInfo = additonalInfoMap;
            return this;
        }
    }

    public Payout(String merchantId, String msId, String beneficiaryAccountNo, String beneficiaryName, String beneficiaryPhone, String beneficiaryCustomerResidence, String beneficiaryCustomerType, String beneficiaryPostalCode, String payoutMethod, String beneficiaryBankCode, Map<String, Object> amount, String partnerReferenceNo, String reservedDt, String reservedTm, String description, String deliveryName, String deliveryId, String beneficiaryPOE, String beneficiaryDOE, String beneficiaryCoNo, String beneficiaryAddress, String beneficiaryAuthPhoneNumber, String beneficiaryMerCategory, String beneficiaryCoMgmtName, String beneficiaryCoShName, String originalPartnerReferenceNo, String originalReferenceNo, Map<String, Object> additionalInfoMap) {
        this.merchantId = merchantId;
        this.msId = msId;
        this.beneficiaryAccountNo = beneficiaryAccountNo;
        this.beneficiaryName = beneficiaryName;
        this.beneficiaryPhone = beneficiaryPhone;
        this.beneficiaryCustomerResidence = beneficiaryCustomerResidence;
        this.beneficiaryCustomerType = beneficiaryCustomerType;
        this.beneficiaryPostalCode = beneficiaryPostalCode;
        this.payoutMethod = payoutMethod;
        this.beneficiaryBankCode = beneficiaryBankCode;
        this.amount = amount;
        this.partnerReferenceNo = partnerReferenceNo;
        this.reservedDt = reservedDt;
        this.reservedTm = reservedTm;
        this.description = description;
        this.deliveryName = deliveryName;
        this.deliveryId = deliveryId;
        this.beneficiaryPOE = beneficiaryPOE;
        this.beneficiaryDOE = beneficiaryDOE;
        this.beneficiaryCoNo = beneficiaryCoNo;
        this.beneficiaryAddress = beneficiaryAddress;
        this.beneficiaryAuthPhoneNumber = beneficiaryAuthPhoneNumber;
        this.beneficiaryMerCategory = beneficiaryMerCategory;
        this.beneficiaryCoMgmtName = beneficiaryCoMgmtName;
        this.beneficiaryCoShName = beneficiaryCoShName;
        this.originalPartnerReferenceNo = originalPartnerReferenceNo;
        this.originalReferenceNo = originalReferenceNo;
        this.additionalInfo = additionalInfoMap;
    }
}
