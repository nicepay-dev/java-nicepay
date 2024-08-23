package io.github.nicepay;

import io.github.nicepay.data.TestingConstants;
import io.github.nicepay.data.model.AccessToken;
import io.github.nicepay.data.model.Qris;
import io.github.nicepay.data.response.snap.BaseNICEPayResponse;
import io.github.nicepay.data.response.snap.NICEPayResponse;
import io.github.nicepay.service.snap.SnapQrisService;
import io.github.nicepay.service.snap.SnapTokenService;
import io.github.nicepay.utils.NICEPay;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.*;

class QrisTest <T extends BaseNICEPayResponse> {
    private static NICEPay config;
    private static TestingConstants DATA ;

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
    }

    public Object getToken() throws IOException {
        Map<String, String> additionalInfo = new HashMap<>();
        AccessToken token = AccessToken.builder()
                .grantType("client_credentials")
                .additionalInfo(additionalInfo)
                .build();
        return  SnapTokenService.callGetAccessToken(token,config);
    }

    @Test
    void qrisRegist() throws IOException
    {
        var accessToken = Optional.ofNullable((NICEPayResponse) getToken())
                .map(NICEPayResponse::getAccessToken)
                .orElseThrow(() -> new IllegalArgumentException("Token is null"));

        Qris qris = Qris.builder()
                .merchantId("IONPAYTEST")
                .storeId("NICEPAY")
                .amount("11000.00","IDR")
                .partnerReferenceNo ("2020102900000000000001")
                .validityPeriod ("")
                .additionalInfo("Test SNAP Transaction Nicepay","John Doe","08123456789"
                        ,"email@merchant.com","Jakarta","Jalan Bukit Berbunga 22","DKI Jakarta","12345","Indonesia"
                        ,"https://ptsv2.com/t/jhon/post" , "https://ptsv2.com/t/jhon/post","127.0.0.1",
                        "{\\\"count\\\":1,\\\"item\\\":[{\\\"img_url\\\":\\\"https://d3nevzfk7ii3be.cloudfront.net/igi/vOrGHXlovukA566A.medium\\\",\\\"goods_name\\\":\\\"Nokia 3360\\\",\\\"goods_detail\\\":\\\"Old Nokia 3360\\\",\\\"goods_amt\\\":\\\"100\\\",\\\"goods_quantity\\\":\\\"1\\\"}]}",
                        "QSHP","","","",
                        "","")
                .build();

        NICEPayResponse response =
                SnapQrisService.callServiceQrisRegist(qris,accessToken,config);
    }

}
