package io.github.nicepay.data.model;

import lombok.*;

@Builder
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Card {

    private String timeStamp;
    private String iMid;
    private String payMethod;
    private String currency;
    private String merchantToken;
    private String referenceNo;

    private String dbProcessUrl;

    private String instmntType;
    private String instmntMon;
    private String recurrOpt;

    private String userIP;
    private String userLanguage;
    private String userAgent;

    private String amt;
    private String cartData;
    private String goodsNm;
    private String billingNm;
    private String billingPhone;
    private String billingEmail;
    private String billingAddr;
    private String billingCity;
    private String billingState;
    private String billingCountry;
    private String billingPostCd;

//    PAYMENT
    private String tXid;

    private String cardNo;
    private String cardExpYymm;
    private String cardCvv;
    private String cardHolderNm;

    private String callBackUrl;

//    For Recurring and Pre-auth feature
    private String recurringToken;
    private String preauthToken;


}
