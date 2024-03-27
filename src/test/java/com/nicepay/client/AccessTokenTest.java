package com.nicepay.client;

import com.nicepay.data.DataTest;
import com.nicepay.model.AccessToken;
import com.nicepay.response.NICEPayResponse;
import com.nicepay.service.SnapTokenService;
import com.nicepay.utils.NICEPay;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

class AccessTokenTest {
    private static NICEPay config;
    private static DataTest DATA;


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

    @Test
    void getCallToken() throws IOException
    {
        Map<String, String> additionalInfo = new HashMap<>();
        AccessToken accessToken = AccessToken.builder()
                .grantType("client_credentials")
                .additionalInfo(additionalInfo)
                .build();
        NICEPayResponse result = SnapTokenService.callGetAccessToken(accessToken,config);
    }
}