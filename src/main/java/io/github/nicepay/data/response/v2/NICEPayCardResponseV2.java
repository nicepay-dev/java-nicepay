package io.github.nicepay.data.response.v2;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NICEPayCardResponseV2 extends BaseNICEPayResponseV2 {


    private String tXid;
    private String referenceNo;
    private String payMethod;
    private String amt;
    private String transDt;
    private String transTm;

}
