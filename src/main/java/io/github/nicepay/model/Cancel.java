package io.github.nicepay.model;
import lombok.*;


import java.util.HashMap;
import java.util.Map;

@Builder
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cancel {

//    VA
    private String partnerServiceId;
    private String customerNo;
    private String virtualAccountNo;
    private String trxId;
    private Map<String, Object> additionalInfoMap ;

// V2 VA
    private String timeStamp;
    private String tXid;
    private String iMid;
    private String payMethod;
    private String cancelType;
    private String amt;
    private String merchantToken;
    private String referenceNo;

//    E-Wallet
    private String merchantId;
    private String subMerchantId;
    private String originalPartnerReferenceNo;
    private String originalReferenceNo;
    private String serviceCode;
    private String transactionDate;
    private String externalStoreId;
    private Map<String, Object> refundAmount ;

    //refund
    private String partnerRefundNo;
    private String reason ;

    private Map<String, Object> totalAmount ;
    private Map<String, Object> additionalInfo;

    public static class CancelBuilder {
        private Map<String, Object> totalAmount;
        private Map<String, Object> additionalInfo;
//        private Map<String, Object> amount;

        public CancelBuilder() {
            this.additionalInfo = new HashMap<>();
        }

        public CancelBuilder totalAmount(String value, String currency) {
            Map<String, Object> totalAmountMap = new HashMap<>();
            totalAmountMap.put("value", value);
            totalAmountMap.put("currency", currency);
            additionalInfo.put("totalAmount", totalAmountMap);
            return this;
        }
        public CancelBuilder additionalInfo(Map<String, Object> additionalInfoMap) {
            additionalInfoMap.put("totalAmount",additionalInfo);
            additionalInfoMap.put("tXidVA",additionalInfo);
            additionalInfoMap.put("cancelMessage",additionalInfo);
            additionalInfoMap.put("refundType",additionalInfo);
            additionalInfoMap.put("cancelType",additionalInfo);

            return this;
        }

        public CancelBuilder tXidVA(String tXidVA) {
            additionalInfo.put("tXidVA", tXidVA);
            return this;
        }
//
        public CancelBuilder cancelMessage(String cancelMessage) {
            additionalInfo.put("cancelMessage", cancelMessage);
            return this;
        }
        public CancelBuilder refundType(String refundType) {
            additionalInfo.put("refundType", refundType);
            return this;
        }
//
        public CancelBuilder refundAmount(String value, String currency) {
            Map<String, Object> refundAmountMap = new HashMap<>();
            refundAmountMap.put("value", value);
            refundAmountMap.put("currency", currency);
            this.refundAmount = refundAmountMap;
            return this;
        }

    }

    public Cancel(String partnerServiceId, String customerNo, String virtualAccountNo, String trxId, Map<String, Object> additionalInfoMap, String merchantId, String subMerchantId, String originalPartnerReferenceNo, String originalReferenceNo, String serviceCode, String transactionDate, String externalStoreId, Map<String, Object> refundAmount, String partnerRefundNo, String reason, Map<String, Object> totalAmount, Map<String, Object> additionalInfo) {
        this.partnerServiceId = partnerServiceId;
        this.customerNo = customerNo;
        this.virtualAccountNo = virtualAccountNo;
        this.trxId = trxId;
        this.additionalInfoMap = additionalInfoMap;
        this.merchantId = merchantId;
        this.subMerchantId = subMerchantId;
        this.originalPartnerReferenceNo = originalPartnerReferenceNo;
        this.originalReferenceNo = originalReferenceNo;
        this.serviceCode = serviceCode;
        this.transactionDate = transactionDate;
        this.externalStoreId = externalStoreId;
        this.refundAmount = refundAmount;
        this.partnerRefundNo = partnerRefundNo;
        this.reason = reason;
        this.totalAmount = totalAmount;
        this.additionalInfo = additionalInfo;
    }
}
