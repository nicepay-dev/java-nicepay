package com.nicepay.client;

import com.nicepay.data.DataTest;
import com.nicepay.response.NICEPayResponse;
import com.nicepay.model.AccessToken;
import com.nicepay.model.Payout;
import com.nicepay.service.SnapPayoutService;
import com.nicepay.service.SnapTokenService;
import com.nicepay.utils.NICEPay;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

class PayoutTest {
    private static NICEPay config;
    private static DataTest DATA ;
    private static NICEPay config2;
    private static NICEPay config3;
    @BeforeAll
    public  static void setUp() {
        config =NICEPay.builder()
                .isProduction(false)
                .clientSecret(DATA.CLIENT_SECRET)
                .partnerId(DATA.PARTNER_ID)
                .externalID(DATA.EXTERNAL_ID)
                .timestamp(DATA.TIMESTAMP)
                .privateKey(DATA.PRIVATE_KEY)
                .build();

        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
        String TIMESTAMP = f.format(new Date());
        Random rand = new Random();
        int random = rand.nextInt(10000);
        String externalId = "OrdNo" + TIMESTAMP.substring(0, 10).replace("-","") + TIMESTAMP.substring(11,19).replace(":","") + random;
        config2 =NICEPay.builder()
                .isProduction(false)
                .clientSecret(DATA.CLIENT_SECRET)
                .partnerId(DATA.PARTNER_ID)
                .externalID(externalId)
                .timestamp(DATA.TIMESTAMP)
                .privateKey(DATA.PRIVATE_KEY)
                .build();
        int random2 = rand.nextInt(10000);
        String externalId2 = "OrdNo" + TIMESTAMP.substring(0, 10).replace("-","") + TIMESTAMP.substring(11,19).replace(":","") + random2;
        config3 =NICEPay.builder()
                .isProduction(false)
                .clientSecret(DATA.CLIENT_SECRET)
                .partnerId(DATA.PARTNER_ID)
                .externalID(externalId2)
                .timestamp(DATA.TIMESTAMP)
                .privateKey(DATA.PRIVATE_KEY)
                .build();
    }
    @Test
    public Object getToken() throws IOException {
        Map<String, String> additionalInfo = new HashMap<>();
        AccessToken token = AccessToken.builder()
                .grantType("client_credentials")
                .additionalInfo(additionalInfo)
                .build();
        return  SnapTokenService.callGetAccessToken(token,config);
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
                .additionalInfo("")
                .build();

        NICEPayResponse Result =
                SnapPayoutService.callServicePayoutRegist(payout,accessToken,config);
    }


    @Test
    void payOutApprove() throws IOException {
        NICEPayResponse responseToken = (NICEPayResponse) getToken();
        var accessToken = Optional.ofNullable(responseToken)
                .map(token -> responseToken.getAccessToken())
                .orElseThrow(() -> new IllegalArgumentException("Token is null"));


        Payout payout = Payout.builder()
                .merchantId("IONPAYTEST")
                .originalReferenceNo("IONPAYTEST07202404080947007680")
                .originalPartnerReferenceNo("2020102900000000000001")
                .build();

        NICEPayResponse Result =
                SnapPayoutService.callServicePayoutApprove(payout,accessToken,config2);

    }

    @Test
    void payOutCheckBalance() throws IOException {
        NICEPayResponse responseToken = (NICEPayResponse) getToken();
        var accessToken = Optional.ofNullable(responseToken)
                .map(token -> responseToken.getAccessToken())
                .orElseThrow(() -> new IllegalArgumentException("Token is null"));


        Payout payout = Payout.builder()
                .merchantId("IONPAYTEST")
                .additionalInfo("")
                .build();

        NICEPayResponse Result =
                SnapPayoutService.callServicePayoutCheckBalance(payout,accessToken,config2);

    }

}