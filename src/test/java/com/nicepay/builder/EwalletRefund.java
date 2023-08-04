package com.nicepay.builder;

import com.nicepay.config.NICEPayResponse;
import com.nicepay.service.SnapEwalletService;
import com.nicepay.service.SnapTokenUtilService;
import com.nicepay.utils.LoggerPrint;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

class EwalletRefund {

    private static LoggerPrint print = new LoggerPrint() ;

    @Test
    public Object getToken() throws IOException {
        Map<String, String> additionalInfo = new HashMap<>();
        TokenUtil util =  TokenUtil.builder()
                .grantType("client_credentials")
                .additionalInfo(additionalInfo)
                .build();
        return SnapTokenUtilService.callGetToken(util);

    }

    @Test
    void EwalletRefund() throws IOException {
        NICEPayResponse responseToken = (NICEPayResponse) getToken();
        var accessToken = Optional.ofNullable(responseToken)
                .map(token -> responseToken.getAccessToken())
                .orElseThrow(() -> new IllegalArgumentException("Token is null"));

        Map<String, String> refundAmount = new HashMap<>();
        refundAmount.put("value","1.00");
        refundAmount.put("currency","IDR");

        Map<String,String> additionalInfo = new HashMap<>();
        additionalInfo.put("refundType","1");

        Ewallet ewallet = Ewallet.builder()
                .merchantId("NORMALTEST")
                .subMerchantId("23489182303312")
                .originalPartnerReferenceNo("ref202305081205331683522921")
                .originalReferenceNo("NORMALTEST05202308030943029362")
                .partnerRefundNo("239850918204981205183")
                .externalStoreId("239840198240795109")
                .reason("Customer complain")
                .additionalInfo(additionalInfo)
                .refundAmount(refundAmount)
                .build();


        NICEPayResponse Result =
                SnapEwalletService.callServiceEwalletRefund(ewallet,accessToken);

    }
}
