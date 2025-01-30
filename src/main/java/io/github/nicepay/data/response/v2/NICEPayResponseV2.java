package io.github.nicepay.data.response.v2;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.checkerframework.checker.units.qual.A;
import org.checkerframework.common.returnsreceiver.qual.This;

import java.util.Currency;

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
}
