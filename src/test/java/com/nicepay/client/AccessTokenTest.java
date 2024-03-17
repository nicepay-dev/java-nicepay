package com.nicepay.client;

import com.nicepay.model.AccessToken;
import com.nicepay.response.NICEPayResponse;
import com.nicepay.service.SnapTokenService;
import com.nicepay.utils.NICEPay;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

class AccessTokenTest {
    private static NICEPay config;

    @BeforeAll
    public  static void setUp() {
        config =NICEPay.builder()
                .isProduction(true)
                .clientSecret("1af9014925cab04606b2e77a7536cb0d5c51353924a966e503953e010234108a")
                .partnerId("NORMALTEST")
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