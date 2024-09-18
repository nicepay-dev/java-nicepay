package io.github.nicepay.data.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@Builder
public class Payout {
    
    private String merchantId;
    private String msId;
    private String beneficiaryAccountNo;
    private String beneficiaryName;;
    private String beneficiaryPhone;
    private String beneficiaryCustomerResidence;
    private String beneficiaryCustomerType;
    private String beneficiaryPostalCode;
    private String payoutMethod;
    private String beneficiaryBankCode;
    private Map<String, Object> amount;
    private String partnerReferenceNo;
    private String reservedDt;
    private String reservedTm;
    private String description;
    private String deliveryName;
    private String deliveryId;
    private String beneficiaryPOE;
    private String beneficiaryDOE;
    private String beneficiaryCoNo;
    private String beneficiaryAddress;
    private String beneficiaryAuthPhoneNumber;
    private String beneficiaryMerCategory;
    private String beneficiaryCoMgmtName;
    private String beneficiaryCoShName;
    private String originalPartnerReferenceNo;
    private String originalReferenceNo;
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
