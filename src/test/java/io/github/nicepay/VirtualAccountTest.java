package io.github.nicepay;

import io.github.nicepay.data.TestingConstants;
import io.github.nicepay.model.AccessToken;
import io.github.nicepay.model.VirtualAccount;
import io.github.nicepay.service.SnapTokenService;
import io.github.nicepay.utils.LoggerPrint;
import io.github.nicepay.response.NICEPayResponse;
import io.github.nicepay.service.SnapVaService;
import io.github.nicepay.utils.NICEPay;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.*;

class VirtualAccountTest {
    private static LoggerPrint print = new LoggerPrint();
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

// return Token Object
    public Object getToken() throws IOException {
        Map<String, String> additionalInfo = new HashMap<>();
        AccessToken accessToken = AccessToken.builder()
                .grantType("client_credentials")
                .additionalInfo(additionalInfo)
                .build();
       return  SnapTokenService.callGetAccessToken(accessToken,config);
    }
    @Test
    void vaCreate() throws IOException
    {
        var accessToken = Optional.ofNullable((NICEPayResponse) getToken())
                .map(NICEPayResponse::getAccessToken)
                .orElseThrow(() -> new IllegalArgumentException("Token is null"));

        VirtualAccount virtualAccount = VirtualAccount.builder()
                .partnerServiceId("1234")
                .customerNo("")
                .virtualAccountNo("")
                .virtualAccountName("TESTVaName")
                .trxId("TESTTrxId")
                .totalAmount("11000.00","IDR")
                .additionalInfo( new HashMap<String, Object>() {
                    {
                        put("bankCd","BBBA");
                        put("goodsNm", "TESTGoodsNm");
                        put("dbProcessUrl", "https://merchant.com/test");
                        put("vacctValidDt", "");
                        put("vacctValidTm", "");
                        put("msId", "");
                        put("msFee", "");
                        put("msFeeType", "");
                        put("mbFee", "");
                        put("mbFeeType", "");
                    }
                })
                .build();

       NICEPayResponse response = SnapVaService.callGeneratedVA(virtualAccount,accessToken,config);

    }

}

