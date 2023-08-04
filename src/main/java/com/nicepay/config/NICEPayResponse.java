package com.nicepay.config;


import lombok.*;

import java.util.Map;

public class NICEPayResponse {
    @Getter
    @Setter
    private String responseCode;
    @Getter
    @Setter
    private String responseMessage;
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
    private String latestTransactionStatus;

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


    public NICEPayResponse(String responseCode, String responseMessage, String accessToken,String tokenType,String expiresIn) {
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
        this.accessToken = accessToken;
        this.tokenType = tokenType;
        this.expiresIn = expiresIn;

    }

    public NICEPayResponse(String responseCode, String responseMessage) {
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
    }

    //    public NICEPayResponse(String responseCode, String responseMessage) {
//        this.responseCode = responseCode;
//        this.responseMessage = responseMessage;
//    }
//
    public NICEPayResponse(String responseCode, String responseMessage, Map<String, Object> virtualAccountData, Map<String, Object> totalAmount, Map<String, String> additionalInfo) {
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
        this.virtualAccountData = virtualAccountData;
        this.virtualAccountData = totalAmount;
//        this.totalAmount = totalAmount;
        this.additionalInfo = additionalInfo;

    }


    //Ewallet
    public NICEPayResponse (String responseCode, String responseMessage, String partnerReferenceNo, String originalReferenceNo,String webRedirectUrl,String originalPartnerReferenceNo
            ,String refundNo,String partnerRefundNo,String Value,String Currency,String refundTime, String refundType,Map<String, Object> refundAmount,String latestTransactionStatus
            ,Map<String, Object> transAmount, String appRedirectUrl){
        this.responseCode =responseCode;
        this.responseMessage = responseMessage;
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
