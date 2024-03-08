package com.nicepay.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
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
    private Map<String, Object> totalAmount;

    @Getter
    @Setter
    private Map<String, Object> additionalInfo;

    @Getter
    @Setter
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

    public VirtualAccount(String partnerServiceId, String customerNo, String virtualAccountNo, String virtualAccountName, String trxId, Map<String, Object> totalAmount, Map<String, Object> additionalInfo, String tXidVA) {
        this.partnerServiceId = partnerServiceId;
        this.customerNo = customerNo;
        this.virtualAccountNo = virtualAccountNo;
        this.virtualAccountName = virtualAccountName;
        this.trxId = trxId;
        this.totalAmount = totalAmount;
        this.additionalInfo = additionalInfo;
        this.tXidVA = tXidVA;
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
                '}';
    }
}
