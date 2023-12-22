package com.nicepay.builder;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

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
    private String beneficiaryName;

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
    private Map<String, String> amount;

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

    @Getter
    @Setter
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

}
