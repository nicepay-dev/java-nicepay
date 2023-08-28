package com.nicepay.builder;

import com.nicepay.config.NICEPayResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Builder
public class VirtualAccount {
    @Getter
    @Setter
    private String partnerServiceId;

    @Getter
    @Setter
    private String customerNo;

    @Getter
    @Setter
    private String virtualAccountNo;

    @Getter
    @Setter
    private String virtualAccountName;

    @Getter
    @Setter
    private String trxId;

    @Getter
    @Setter
    private Map<String, String> totalAmount;

    @Getter
    @Setter
    private Map<String, String> additionalInfo;

    @Getter
    @Setter
    private String inquiryRequestId;






    public VirtualAccount(String partnerServiceId, String customerNo, String virtualAccountNo, String virtualAccountName, String trxId, Map<String, String> totalAmount, Map<String, String> additionalInfo
    ,String inquiryRequestId) {
        this.partnerServiceId = partnerServiceId;
        this.customerNo = customerNo;
        this.virtualAccountNo = virtualAccountNo;
        this.virtualAccountName = virtualAccountName;
        this.trxId = trxId;
        this.totalAmount = totalAmount;
        this.additionalInfo = additionalInfo;
        this.inquiryRequestId = inquiryRequestId;
    }

    @Override
    public String toString() {
        return "{" +
                "partnerServiceId='" + partnerServiceId + '\'' +
                ", customerNo='" + customerNo + '\'' +
                ", virtualAccountNo='" + virtualAccountNo + '\'' +
                ", virtualAccountName='" + virtualAccountName + '\'' +
                ", trxId='" + trxId + '\'' +
                ", totalAmount=" + totalAmount +
                ", additionalInfo=" + additionalInfo +
                ", inquiryRequestId=" + inquiryRequestId +
                '}';
    }
}
