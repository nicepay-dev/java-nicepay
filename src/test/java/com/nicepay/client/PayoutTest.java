package com.nicepay.client;

import com.nicepay.response.NICEPayResponse;
import com.nicepay.model.AccessToken;
import com.nicepay.model.Payout;
import com.nicepay.service.SnapPayoutService;
import com.nicepay.service.SnapTokenService;
import com.nicepay.utils.LoggerPrint;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

class PayoutTest {
    private static LoggerPrint print = new LoggerPrint();
    @Test
    public Object getToken() throws IOException {
        Map<String, String> additionalInfo = new HashMap<>();
        AccessToken token = AccessToken.builder()
                .grantType("client_credentials")
                .additionalInfo(additionalInfo)
                .build();
        return  SnapTokenService.callGetAccessToken(token);
    }

    @Test
    void payOutregist() throws IOException
    {
        NICEPayResponse responseToken = (NICEPayResponse) getToken();
        var accessToken = Optional.ofNullable(responseToken)
                .map(token -> responseToken.getAccessToken())
                .orElseThrow(() -> new IllegalArgumentException("Token is null"));


        Payout payout = Payout.builder()
                .merchantId("IONPAYTEST")
                .beneficiaryAccountNo("5345000060")
                .beneficiaryName("IONPAY NETWORKS")
                .beneficiaryPhone("08123456789")
                .beneficiaryCustomerResidence("1")
                .beneficiaryCustomerType("1")
                .beneficiaryPostalCode("123456")
                .payoutMethod ("0")
                .beneficiaryBankCode ("BDIN")
                .amount("11000.00","IDR")
                .partnerReferenceNo ("2020102900000000000001")
                .description ("This is test Request")
                .deliveryName("Ciki")
                .deliveryId("1234567890234512")
                .reservedDt("")
                .reservedTm("")
                .build();

        NICEPayResponse Result =
                SnapPayoutService.callServicePayoutRegist(payout,accessToken);
    }


    @Test
    void payOutApprove() throws IOException {
        NICEPayResponse responseToken = (NICEPayResponse) getToken();
        var accessToken = Optional.ofNullable(responseToken)
                .map(token -> responseToken.getAccessToken())
                .orElseThrow(() -> new IllegalArgumentException("Token is null"));


        Payout payout = Payout.builder()
                .merchantId("IONPAYTEST")
                .originalReferenceNo("IONPAYTEST07202403080926575337")
                .originalPartnerReferenceNo("2020102900000000000001")
                .build();

        NICEPayResponse Result =
                SnapPayoutService.callServicePayoutApprove(payout,accessToken);

    }

}