package com.nicepay.builder;

import com.nicepay.config.NICEPayResponse;
import com.nicepay.service.SnapPayoutService;
import com.nicepay.service.SnapTokenUtilService;
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
        TokenUtil token = TokenUtil.builder()
                .grantType("client_credentials")
                .additionalInfo(additionalInfo)
                .build();

//       NICEPayResponse response =  SnapTokenUtilService.callGetToken(token);
//        print.logInfoResponse("Response getData :" +response.getAccessToken());
        return  SnapTokenUtilService.callGetToken(token);
    }

    @Test
    void payOutregist() throws IOException
    {
        NICEPayResponse responseToken = (NICEPayResponse) getToken();
        var accessToken = Optional.ofNullable(responseToken)
                .map(token -> responseToken.getAccessToken())
                .orElseThrow(() -> new IllegalArgumentException("Token is null"));

        Map<String, String> amount = new HashMap<>();
        amount.put("value","200000.00");
        amount.put("currency","IDR");
        Payout payout = Payout.builder()
                .merchantId("NORMALTEST")
                .msId("")
                .beneficiaryAccountNo("701075323")
                .beneficiaryName("IONPAY NETWORKS")
                .beneficiaryPhone("08123456789")
                .beneficiaryCustomerResidence("1")
                .beneficiaryCustomerResidence("1")
                .beneficiaryCustomerType("1")
                .beneficiaryPostalCode("123456")
                .payoutMethod ("0")
                .beneficiaryBankCode ("BBBA")
                .amount(amount)
                .partnerReferenceNo ("2020102900000000000001")
                .reservedDt ("")
                .reservedTm ("")
                .description ("This is test Request")
                .deliveryName("Ciki")
                .deliveryId("1234567890234512")
                .beneficiaryPOE("Kota Administrasi Jakarta Selatan")
                .beneficiaryDOE("220101")
                .beneficiaryCoNo("12345JP")
                .beneficiaryAddress("Jl. Hong Gil Dong 88")
                .beneficiaryAuthPhoneNumber("081623516151725378")
                .beneficiaryMerCategory("01")
                .beneficiaryCoMgmtName("John Doe")
                .beneficiaryCoShName("John Doe, John Doe1")
                .build();

        NICEPayResponse Result =
                SnapPayoutService.callServicePayoutRegist(payout,accessToken);
    }

}