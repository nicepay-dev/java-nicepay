package io.github.nicepay.exception.v2;

import io.github.nicepay.response.v2.BaseNICEPayResponseV2;

public class NicepayErrorResponseV2 extends BaseNICEPayResponseV2 {
    public NicepayErrorResponseV2(String resultCode, String resultMsg ){
        super(resultCode, resultMsg);
    }
}
