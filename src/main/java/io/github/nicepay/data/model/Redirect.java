package io.github.nicepay.data.model;

import lombok.*;


@Builder
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Redirect {

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

    private String callBackUrl;

    private String description;
    private String deliveryNm;
    private String deliveryPhone;
    private String deliveryAddr;
    private String deliveryCity;
    private String deliveryState;
    private String deliveryPostCd;
    private String deliveryCountry;

    private String reqDomain;
    private String reqServerIP;
    private String reqClientVer;
    private String userSessionID;
    private String sellers;
    private String mitraCd;

    private String vat;
    private String fee;
    private String notaxAmt;
    private String reqDt;
    private String reqTm;
    private String bankCd;
    private String vacctValidDt;
    private String vacctValidTm;
    private String payValidDt;
    private String payValidTm;
    private String merFixAcctId;
    private String paymentExpDt;
    private String paymentExpTm;
    private String shopId;


    public static class RedirectBuilder {

        public RedirectBuilder merchantToken(String timeStamp, String imid, String reffNo, String amount, String merchantKey) {
            this.merchantToken = timeStamp + imid + reffNo + amount + merchantKey;
            return this;
        }


    }

}
