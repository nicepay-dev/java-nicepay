package io.github.nicepay;

import io.github.nicepay.data.TestingConstants;
import io.github.nicepay.model.AccessToken;
import io.github.nicepay.response.NICEPayResponse;
import io.github.nicepay.service.SnapTokenService;
import io.github.nicepay.utils.NICEPay;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

class AccessTokenTest {
    private static NICEPay config;
    private static TestingConstants DATA;


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