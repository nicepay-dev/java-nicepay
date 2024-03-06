package com.nicepay.client;

import com.nicepay.model.AccessToken;
import com.nicepay.response.NICEPayResponse;
import com.nicepay.service.SnapTokenService;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

//@SpringBootTest(classes = UtilApplication.class)
class AccessTokenTest {


    @Test
    void getCallToken() throws IOException
    {
        Map<String, String> additionalInfo = new HashMap<>();
        AccessToken accessToken = AccessToken.builder()
                .grantType("client_credentials")
                .additionalInfo(additionalInfo)
                .build();
        NICEPayResponse result = SnapTokenService.callGetAccessToken(accessToken);
    }

}