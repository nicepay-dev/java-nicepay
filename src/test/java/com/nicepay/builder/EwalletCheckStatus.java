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

class EwalletCheckStatus {

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
    void EwalletCheckStatus() throws IOException {
        NICEPayResponse responseToken = (NICEPayResponse) getToken();
        var accessToken = Optional.ofNullable(responseToken)
                .map(token -> responseToken.getAccessToken())
                .orElseThrow(() -> new IllegalArgumentException("Token is null"));

                Map<String, String> amount = new HashMap<>();
                amount.put("value","1.00");
                amount.put("currency","IDR");

        Map<String,String> additionalInfo = new HashMap<>();

        Ewallet ewallet = Ewallet.builder()
                .merchantId("NORMALTEST")
                .subMerchantId("23489182303312")
                .originalPartnerReferenceNo("ref202305081205331683522921")
                .originalReferenceNo("NORMALTEST05202308031752031674")
                .serviceCode("54")
                .transactionDate("2022-04-28T10:13:40+07:00")
                .externalStoreId("239840198240795109")
                .additionalInfo(additionalInfo)
                .amount(amount)
                .build();


        NICEPayResponse Result =
                SnapEwalletService.callServiceEwalletCheckStatus(ewallet,accessToken);

    }
}
