package io.github.nicepay.data.response.v2;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
@Getter
@Setter
public class NICEPayResponseV2 extends BaseNICEPayResponseV2{
    //base
    private String tXid;
    private String referenceNo;
    private String payMethod ;
    private String amt;
    private String transDt ;
    private String transTm ;
    private String currency;
    private String goodsNm;
    private String billingNm;

    //va
    private String bankCd ;
    private String vacctNo;
    private String vacctValidDt;
    private String vacctValidTm;

    //inquiry
    private String reqDt;
    private String reqTm;
    private String status;

    //cancel
    private String description ;

    // CC
    private String ccTransType;
    private String cardNo;
    private String issuBankCd;
    private String preauthToken;
    private String cardExpYymm;
    private String acquBankNm;
    private String instmntType;
    private String instmntMon;
    private String issuBankNm;
    private String acquBankCd;
    private JsonObject acquirerData;
    private JsonArray latestFailHistory;
    private String authNo;
    private String recurringToken;
    private String acquStatus;

//    EWALLET

    private String mitraCd;
    private String payNo;
    private String payValidDt;
    private String payValidTm;
    private String requestURL;

//    Qris
    private String paymentExpDt;
    private String paymentExpTm;
    private String qrContent;
    private String qrUrl;



//    PAYMENT
    private String redirectUrlHttp;
    private String redirectUrlApp;
    private String redirectToken;

//    PAYOUT

    private String benefNm;
    private String accountNo;
    private String payoutMethod;
    private String payoutToken;

    private String balance;
    private String scheduled;

    private String validDate;
    private String valiDTime;
    private String cashoutToken;
    private String mCode;

    private String benefStatus;
    private String benefType;
    private String benefId;



}
