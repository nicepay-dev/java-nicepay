package io.github.nicepay.data.response.snap;



import io.github.nicepay.data.response.snap.nested.ObjectAccountInfo;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Map;


@Getter
@Setter
public class NICEPayResponse extends BaseNICEPayResponse {


    private String accessToken;
    private String expiresIn;
    private String tokenType;
    private Map<String, Object> virtualAccountData;
    private Map<String, String> additionalInfo;
    private Map<String, String> totalAmount;

    //Virtual Account
    private String partnerServiceId;
    private String customerNo;
    private String inquiryRequestId;
    private String virtualAccountNo;
    private String virtualAccountName;
    private String trxId;
    private String transactionStatusDesc;
    private String latestTransactionStatus;
    private String bankCd;
    private String tXidVA;
    private String goodsNm;
    private String vacctValidTm;
    private String vacctValidDt;

    //Ewallet
    private Map<String, String> amount;
    private Map<String, Object> ewalletData;
    private String partnerReferenceNo;
    private String originalReferenceNo;
    private String refundNo;
    private String Value;
    private String refundType;
    private String refundTime;
    private String Currency;
    private String partnerRefundNo;
    private String webRedirectUrl;
    private String originalPartnerReferenceNo;
    private String appRedirectUrl;
    private Map<String, Object> refundAmount;
    private Map<String, Object> transAmount;

//    QRIS

    private String qrContent;
    private String qrUrl;
    private String referenceNo;

//    PAYOUT
    private String accountNo;
    private String beneficiaryaccountNo;
    private String beneficiaryBankCode;
    private String beneficiaryName;
    private String payoutMethod;
    private String beneficiaryAccountNo;
    private String transactionStatus;
    private ArrayList<ObjectAccountInfo> accountInfos;
    private String beneficiaryCustomerResidence;
    private String beneficiaryCustomerType;


    public NICEPayResponse(String responseCode, String responseMessage, String accessToken, String tokenType, String expiresIn) {
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
            , String virtualAccountNo, String virtualAccountName, String trxId, String transactionStatusDesc, String bankCd, String tXidVA, String goodsNm, String vacctValidTm, String vacctValidDt) {
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
    public NICEPayResponse(String responseCode, String responseMessage, String partnerReferenceNo, String originalReferenceNo, String webRedirectUrl, String originalPartnerReferenceNo
            , String refundNo, String partnerRefundNo, String Value, String Currency, String refundTime, String refundType, Map<String, Object> refundAmount, String latestTransactionStatus
            , Map<String, Object> transAmount, String appRedirectUrl) {
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

//    Payout

    public NICEPayResponse(String responseCode, String responseMessage, String accountNo, String beneficiaryaccountNo, String beneficiaryBankCode, String beneficiaryName, String payoutMethod, String beneficiaryAccountNo, String transactionStatus, ArrayList<ObjectAccountInfo> accountInfos, String beneficiaryCustomerResidence, String beneficiaryCustomerType) {
        super(responseCode, responseMessage);
        this.accountNo = accountNo;
        this.beneficiaryaccountNo = beneficiaryaccountNo;
        this.beneficiaryBankCode = beneficiaryBankCode;
        this.beneficiaryName = beneficiaryName;
        this.payoutMethod = payoutMethod;
        this.beneficiaryAccountNo = beneficiaryAccountNo;
        this.transactionStatus = transactionStatus;
        this.accountInfos = accountInfos;
        this.beneficiaryCustomerResidence = beneficiaryCustomerResidence;
        this.beneficiaryCustomerType = beneficiaryCustomerType;
    }

}
