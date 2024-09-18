package io.github.nicepay.data.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InquiryStatus {

//    VA
    private String partnerServiceId;
    private String customerNo;
    private String virtualAccountNo;
    private String inquiryRequestId;
    private Map<String, Object> additionalInfo ;

// V2
    private String timeStamp;
    private String tXid;
    private String iMid;
    private String referenceNo;
    private String amt;
    private String merchantToken;

//    E-Wallet
    private String merchantId;
    private String subMerchantId;
    private String originalPartnerReferenceNo;
    private String originalReferenceNo;
    private String serviceCode;
    private String transactionDate;
    private String externalStoreId;
    private Map<String, Object> amount ;
    private Map<String, Object> totalAmount ;
    private Map<String, Object> additionalInfoMap;

    //   Payout
    private String beneficiaryAccountNo;

    public static class InquiryStatusBuilder {
        private Map<String, Object> totalAmount;
        private Map<String, Object> additionalInfo;
        private Map<String, Object> amount;

        //        V2

        public InquiryStatusBuilder() {
            this.additionalInfo = new HashMap<>();
        }

        public InquiryStatusBuilder merchantToken(String timestamp, String imid, String reffNo, String amount, String merchantKey){
          this.merchantToken = timestamp+imid+reffNo+amount+merchantKey;
          return this;
        }

        public InquiryStatusBuilder totalAmount(String value, String currency) {
            Map<String, Object> totalAmountMap = new HashMap<>();
            totalAmountMap.put("value", value);
            totalAmountMap.put("currency", currency);
            additionalInfo.put("totalAmount", totalAmountMap);
            return this;
        }
        public InquiryStatusBuilder trxId(String trxId) {
            additionalInfo.put("trxId", trxId);
            return this;
        }
        //
        public InquiryStatusBuilder tXidVA(String tXidVA) {
            additionalInfo.put("tXidVA", tXidVA);
            return this;
        }

        public InquiryStatusBuilder additionalInfo(Map<String, Object> additionalInfoMap) {
            additionalInfoMap.put("totalAmount",additionalInfo);
            additionalInfoMap.put("trxId",additionalInfo);
            additionalInfoMap.put("tXidVA",additionalInfo);
            return this;
        }

        public InquiryStatusBuilder amount(String value, String currency) {
            Map<String, Object> amountMap = new HashMap<>();
            amountMap.put("value", value);
            amountMap.put("currency", currency);
            this.amount = amountMap;
            return this;
        }
    }

    public InquiryStatus(String partnerServiceId, String customerNo, String virtualAccountNo, String inquiryRequestId, Map<String, Object> additionalInfo, String merchantId, String subMerchantId, String originalPartnerReferenceNo, String originalReferenceNo, String serviceCode, String transactionDate, String externalStoreId, Map<String, Object> amount, Map<String, Object> totalAmount, Map<String, Object> additionalInfoMap, String beneficiaryAccountNo) {
        this.partnerServiceId = partnerServiceId;
        this.customerNo = customerNo;
        this.virtualAccountNo = virtualAccountNo;
        this.inquiryRequestId = inquiryRequestId;
        this.additionalInfo = additionalInfo;
        this.merchantId = merchantId;
        this.subMerchantId = subMerchantId;
        this.originalPartnerReferenceNo = originalPartnerReferenceNo;
        this.originalReferenceNo = originalReferenceNo;
        this.serviceCode = serviceCode;
        this.transactionDate = transactionDate;
        this.externalStoreId = externalStoreId;
        this.amount = amount;
        this.totalAmount = totalAmount;
        this.additionalInfoMap = additionalInfoMap;
        this.beneficiaryAccountNo = beneficiaryAccountNo;
    }
}
