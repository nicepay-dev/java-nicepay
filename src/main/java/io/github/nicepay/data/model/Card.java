package io.github.nicepay.data.model;

import io.github.nicepay.utils.SHA256Util;
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
    private String merchantKey;

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

    public static class CardBuilder {
        private String merchantKey;

        public CardBuilder merchantKey(String merchantKey) {
            this.merchantKey = merchantKey;
            return this;
        }

        // Custom builder
        public Card build() {
            Card card = new Card();
            card.timeStamp = this.timeStamp;
            card.iMid = this.iMid;
            card.payMethod = this.payMethod;
            card.currency = this.currency;
            card.amt = this.amt;
            card.referenceNo = this.referenceNo;
            card.goodsNm = this.goodsNm;
            card.billingNm = this.billingNm;
            card.billingPhone = this.billingPhone;
            card.billingEmail = this.billingEmail;
            card.billingAddr = this.billingAddr;
            card.billingCity = this.billingCity;
            card.billingState = this.billingState;
            card.billingPostCd = this.billingPostCd;
            card.billingCountry = this.billingCountry;
            card.dbProcessUrl = this.dbProcessUrl;
            card.userIP = this.userIP;
            card.userAgent = this.userAgent;
            card.userLanguage = this.userLanguage;
            card.instmntType = this.instmntType;
            card.instmntMon = this.instmntMon;
            card.recurrOpt = this.recurrOpt;

//            Payment
            card.tXid = this.tXid;
            card.cardNo = this.cardNo;
            card.cardExpYymm = this.cardExpYymm;
            card.cardCvv = this.cardCvv;
            card.cardHolderNm = this.cardHolderNm;
            card.callBackUrl = this.callBackUrl;

            card.recurringToken = this.recurringToken;
            card.preauthToken = this.preauthToken;


            if (this.merchantKey != null) {
                card.merchantToken = SHA256Util.encrypt(
                        card.timeStamp + card.iMid + card.referenceNo + card.amt + this.merchantKey
                );
            }
            return card;
        }
    }

}
