package io.github.nicepay.response;


import lombok.*;

import java.util.Map;

public class NICEPayResponse extends BaseNICEPayResponse {

    @Getter
    @Setter
    private String accessToken;
    @Getter
    @Setter
    private String expiresIn;
    @Getter
    @Setter
    private String tokenType;
    @Getter
    @Setter
    private Map<String, Object> virtualAccountData;
    @Getter
    @Setter
    private Map<String, String> additionalInfo;
    @Getter
    @Setter
    private Map<String, String> totalAmount;

    //Virtual Account
    @Getter
    @Setter
    private String partnerServiceId;

    @Getter
    @Setter
    private String customerNo;

    @Getter
    @Setter
    private String inquiryRequestId;

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
    private String transactionStatusDesc;

    @Getter
    @Setter
    private String latestTransactionStatus;

    @Getter
    @Setter
    private String bankCd;

    @Getter
    @Setter
    private String tXidVA;

    @Getter
    @Setter
    private String goodsNm;

    @Getter
    @Setter
    private String vacctValidTm;

    @Getter
    @Setter
    private String vacctValidDt;


    //Ewallet
    @Getter
    @Setter
    private Map<String, String> amount;

    @Getter
    @Setter
    private Map<String, Object> ewalletData;

    @Getter
    @Setter
    private String partnerReferenceNo;

    @Getter
    @Setter
    private String originalReferenceNo;

    @Getter
    @Setter
    private String refundNo;

    @Getter
    @Setter
    private String Value;

    @Getter
    @Setter
    private String refundType;

    @Getter
    @Setter
    private String refundTime;

    @Getter
    @Setter
    private String Currency;

    @Getter
    @Setter
    private String partnerRefundNo;

    @Getter
    @Setter
    private String webRedirectUrl;

    @Getter
    @Setter
    private String originalPartnerReferenceNo;

    @Getter
    @Setter
    private String appRedirectUrl;

    @Getter
    @Setter
    private Map<String, Object> refundAmount;

    @Getter
    @Setter
    private Map<String, Object> transAmount;

//    QRIS
    @Getter
    @Setter
    private String qrContent;

    @Getter
    @Setter
    private String qrUrl;

    @Getter
    @Setter
    private String referenceNo;

    public NICEPayResponse(String responseCode, String responseMessage, String accessToken,String tokenType,String expiresIn) {
        super(responseCode, responseMessage);
        this.accessToken = accessToken;
        this.tokenType = tokenType;
        this.expiresIn = expiresIn;

    }

    public NICEPayResponse(String responseCode, String responseMessage) {
        super(responseCode, responseMessage);
    }

    //virtual account
    public NICEPayResponse(String responseCode, String responseMessage, Map<String, Object> virtualAccountData, Map<String, Object> totalAmount, Map<String, String> additionalInfo
    ,String virtualAccountNo,String virtualAccountName, String trxId,String transactionStatusDesc,String bankCd,String tXidVA,String goodsNm,String vacctValidTm,String vacctValidDt) {
        super(responseCode, responseMessage);
        this.virtualAccountData = virtualAccountData;
        this.virtualAccountData = totalAmount;
//        this.totalAmount = totalAmount;
        this.additionalInfo = additionalInfo;
        this.virtualAccountNo = virtualAccountNo;
        this.virtualAccountName = virtualAccountName;
        this.trxId = trxId;
        this.transactionStatusDesc = transactionStatusDesc;
        this.bankCd = bankCd;
        this.tXidVA = tXidVA;
        this.goodsNm = goodsNm;
        this.vacctValidTm = vacctValidTm;
        this.vacctValidDt = vacctValidDt;

    }


    //Ewallet
    public NICEPayResponse (String responseCode, String responseMessage, String partnerReferenceNo, String originalReferenceNo,String webRedirectUrl,String originalPartnerReferenceNo
            ,String refundNo,String partnerRefundNo,String Value,String Currency,String refundTime, String refundType,Map<String, Object> refundAmount,String latestTransactionStatus
            ,Map<String, Object> transAmount, String appRedirectUrl){
        super(responseCode, responseMessage);
        this.partnerReferenceNo = partnerReferenceNo;
        this.originalReferenceNo = originalReferenceNo;
        this.webRedirectUrl = webRedirectUrl;
        this.originalPartnerReferenceNo = originalPartnerReferenceNo;
        this.refundNo = refundNo;
        this.partnerRefundNo = partnerRefundNo;
        this.Value = Value;
        this.Currency = Currency;
        this.refundTime = refundTime;
        this.refundType = refundType;
        this.refundAmount = refundAmount;
        this.latestTransactionStatus = latestTransactionStatus;
        this.transAmount = transAmount;
        this.appRedirectUrl = appRedirectUrl;

    }


//

//
//    @Override
//    public String toString() {
//       if(accessToken == null){
//           return "{" +
//                   "responseCode='" + responseCode + '\'' +
//                   ", responseMessage='" + responseMessage + '\'' +
//                   ", virtualAccountData=" + virtualAccountData +
//                   ", additionalInfo=" + additionalInfo +
//                   ", totalAmount=" + totalAmount +
//                   '}';
//       }else{
//           return "{" +
//                   "responseCode='" + responseCode + '\'' +
//                   ", responseMessage='" + responseMessage + '\'' +
//                   ", accessToken='" + accessToken + '\'' +
//                   ", expiresIn='" + expiresIn + '\'' +
//                   ", tokenType='" + tokenType + '\'' +
//                   '}';
//       }
//    }
}
