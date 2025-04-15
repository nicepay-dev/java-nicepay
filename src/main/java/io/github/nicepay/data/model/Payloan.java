package io.github.nicepay.data.model;

import lombok.*;


@Builder
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Payloan {

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

    private String callBackUrl;

    private String description;
    private String deliveryNm;
    private String deliveryPhone;
    private String deliveryEmail;
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

    public static class PayloanBuilder {

        public PayloanBuilder merchantToken(String timeStamp, String imid, String reffNo, String amount, String merchantKey) {
            this.merchantToken = timeStamp + imid + reffNo + amount + merchantKey;
            return this;
        }


    }

}
