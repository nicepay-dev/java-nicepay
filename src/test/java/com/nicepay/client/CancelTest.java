package com.nicepay.client;

import com.nicepay.model.AccessToken;
import com.nicepay.model.Cancel;
import com.nicepay.model.InquiryStatus;
import com.nicepay.response.BaseNICEPayResponse;
import com.nicepay.response.NICEPayResponse;
import com.nicepay.service.SnapCancelService;
import com.nicepay.service.SnapInquiryStatusService;
import com.nicepay.service.SnapTokenService;
import com.nicepay.utils.LoggerPrint;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

class CancelTest<T extends BaseNICEPayResponse> {

    private static LoggerPrint print = new LoggerPrint() ;
    private int retrycount = 0;

    @Test
    public Object getToken() throws IOException {
        Map<String, String> additionalInfo = new HashMap<>();
        AccessToken util =  AccessToken.builder()
                .grantType("client_credentials")
                .additionalInfo(additionalInfo)
                .build();
        return SnapTokenService.callGetAccessToken(util);

    }

    @Test
    void CancelVA() throws IOException, InterruptedException {

        NICEPayResponse responseToken = (NICEPayResponse) getToken();
        var accessToken = Optional.ofNullable(responseToken)
                .map(token -> responseToken.getAccessToken())
                .orElseThrow(() -> new IllegalArgumentException("Token is null"));
//
        LinkedHashMap<String, Object> requestData = new Cancel()
                .partnerServiceId("")
                .customerNo("")
                .virtualAccountNo("8625000002002170")
                .totalAmount("11000.00", "IDR")
                .trxId("TESTTrxId")
                .tXidVA("NORMALTEST02202403061432020351")
                .cancelMessage("test cancel")
                .build();

        NICEPayResponse result = SnapCancelService.callServiceVACancel(requestData,accessToken);

    }

    @Test
    void ewalletRefund() throws IOException, InterruptedException {
        NICEPayResponse responseToken = (NICEPayResponse) getToken();
        var accessToken = Optional.ofNullable(responseToken)
                .map(token -> responseToken.getAccessToken())
                .orElseThrow(() -> new IllegalArgumentException("Token is null"));

        Map<String, Object> requestData = new Cancel()
                .merchantId("NORMALTEST")
                .subMerchantId("23489182303312")
                .originalPartnerReferenceNo("ref202305081205331683522921")
                .originalReferenceNo("NORMALTEST05202308031752031674")
                .partnerRefundNo("ref202305081205331683522921")
                .refundAmount("1.00", "IDR")
                .externalStoreId("239840198240795109")
                .reason("test refund")
                .refundType("1")
                .build();

        NICEPayResponse Result =
                SnapCancelService.callServiceEwalletCancel(requestData,accessToken);

    }

    }





