package com.nicepay.builder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nicepay.config.BaseNICEPayResponse;
import com.nicepay.config.NICEPayResponse;
import com.nicepay.service.SnapTokenUtilService;
import com.nicepay.service.SnapVaService;
import com.nicepay.utils.LoggerPrint;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

class VirtualAccountCheckStatus<T extends BaseNICEPayResponse> {

    private static LoggerPrint print = new LoggerPrint() ;
    private int retrycount = 0;

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
    void VirtualAccountCheckStatus() throws IOException, InterruptedException {

        NICEPayResponse responseToken = (NICEPayResponse) getToken();
        var accessToken = Optional.ofNullable(responseToken)
                .map(token -> responseToken.getAccessToken())
                .orElseThrow(() -> new IllegalArgumentException("Token is null"));

        Map<String, String> totalAmount = new HashMap<>();
        totalAmount.put("value","11000.00");
        totalAmount.put("currency","IDR");

        Map<String,String> additionalInfo = new HashMap<>();
        additionalInfo.put("tXidVA","NORMALTEST02202308180921298631");

        VirtualAccount virtualaccount = VirtualAccount.builder()
                .partnerServiceId("")
                .customerNo("")
                .virtualAccountNo("8625000002001387")
                .inquiryRequestId("")
                .trxId("TESTTrxId")
                .totalAmount(totalAmount)
                .additionalInfo(additionalInfo)
                .build();

        T result = SnapVaService.callServiceVACheckStatus(virtualaccount,accessToken);
        ObjectMapper mapper = new ObjectMapper();
        System.out.println("Result = " + mapper.writeValueAsString(result));

    }

    }





