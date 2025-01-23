package io.github.nicepay.data.response.v1;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class NICEPayResponseV1 implements Serializable {

    private String apiType;

    @JsonProperty("tXid")
    private String tXid;

    private String requestDate;
    private String responseDate;

    private ObjectData data;

//    Inquiry Status

    private String reqTm;
    private String ccTransType;
    private String acquStatus;
    private String fee;
    private String amt;
    private String cancelAmt;
    private String notaxAmt;
    private String depositTm;
    private String cardNo;
    private String issuBankCd;
    private String preauthToken;
    private String cardExpYymm;
    private String acquBankNm;
    private String payMethod;
    private String currency;
    private String instmntMon;
    private String issuBankNm;
    private String resultCd;
    private String goodsNm;
    private String referenceNo;
    private String transTm;
    private String authNo;
    private String vat;
    private String instmntType;
    private String resultMsg;

    @JsonProperty("iMid")
    private String iMid;
    private String billingNm;
    private String acquBankCd;
    private String depositDt;
    private String reqDt;
    private String transDt;
    private String status;
    private ObjectAquirerData acquirerData;

}
