package com.nicepay.client;

import com.nicepay.response.BaseNICEPayResponse;
import com.nicepay.model.AccessToken;
import com.nicepay.model.InquiryStatus;
import com.nicepay.response.NICEPayResponse;
import com.nicepay.service.SnapInquiryStatusService;
import com.nicepay.service.SnapTokenService;
import com.nicepay.utils.LoggerPrint;
import com.nicepay.utils.NICEPay;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

class InquiryStatusTest<T extends BaseNICEPayResponse> {

    private static LoggerPrint print = new LoggerPrint() ;
    private static NICEPay config;

    @BeforeAll
    public  static void setUp() {
        config =NICEPay.builder()
                .isProduction(false)
                .clientSecret("1af9014925cab04606b2e77a7536cb0d5c51353924a966e503953e010234108a")
                .partnerId("NORMALTEST")
                .build();
    }
    private int retrycount = 0;

    @Test
    public Object getToken() throws IOException {
        Map<String, String> additionalInfo = new HashMap<>();
        AccessToken util =  AccessToken.builder()
                .grantType("client_credentials")
                .additionalInfo(additionalInfo)
                .build();
        return SnapTokenService.callGetAccessToken(util,config);

    }

    @Test
    void InquiryStatusVA() throws IOException, InterruptedException {

        NICEPayResponse responseToken = (NICEPayResponse) getToken();
        var accessToken = Optional.ofNullable(responseToken)
                .map(token -> responseToken.getAccessToken())
                .orElseThrow(() -> new IllegalArgumentException("Token is null"));
        InquiryStatus requestData = InquiryStatus.builder()
                .partnerServiceId("")
                .customerNo("")
                .virtualAccountNo("8625000002002166")
                .inquiryRequestId("dsfsd43")
                .totalAmount("11000.00", "IDR")
                .trxId("TESTTrxId")
                .tXidVA("NORMALTEST02202403041600418638")
                .build();
        NICEPayResponse result = SnapInquiryStatusService.callServiceVACheckStatus(requestData,accessToken,config);

    }

    @Test
    void InquiryStatusEwallet() throws IOException, InterruptedException {
        NICEPayResponse responseToken = (NICEPayResponse) getToken();
        var accessToken = Optional.ofNullable(responseToken)
                .map(token -> responseToken.getAccessToken())
                .orElseThrow(() -> new IllegalArgumentException("Token is null"));

        InquiryStatus requestData = InquiryStatus.builder()
                .merchantId("NORMALTEST")
                .subMerchantId("23489182303312")
                .originalPartnerReferenceNo("ref202305081205331683522921")
                .originalReferenceNo("NORMALTEST05202308031752031674")
                .serviceCode("54")
                .transactionDate("2022-04-28T10:13:40+07:00")
                .externalStoreId("239840198240795109")
                .amount("1.00", "IDR")
                .build();

        NICEPayResponse Result =
                SnapInquiryStatusService.callServiceEwalletCheckStatus(requestData,accessToken,config);

    }

    }





