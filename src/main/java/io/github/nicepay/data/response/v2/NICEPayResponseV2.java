package io.github.nicepay.data.response.v2;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
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

    //cancel
    private String description ;
}
