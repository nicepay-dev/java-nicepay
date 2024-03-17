package com.nicepay.client;

import com.nicepay.model.AccessToken;
import com.nicepay.model.VirtualAccount;
import com.nicepay.service.SnapTokenService;
import com.nicepay.utils.LoggerPrint;
import com.nicepay.response.NICEPayResponse;
import com.nicepay.service.SnapVaService;
import com.nicepay.utils.NICEPay;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.*;

class VirtualAccountTest {
    private static LoggerPrint print = new LoggerPrint();
    private static NICEPay config;

    @BeforeAll
    public  static void setUp() {
        config =NICEPay.builder()
                .isProduction(true)
                .clientSecret("1af9014925cab04606b2e77a7536cb0d5c51353924a966e503953e010234108a")
                .partnerId("NORMALTEST")
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
        NICEPayResponse responseToken = (NICEPayResponse) getToken();
        var accessToken = Optional.ofNullable(responseToken)
                .map(token -> responseToken.getAccessToken())
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

       NICEPayResponse Result =
               SnapVaService.callGeneratedVA(virtualAccount,accessToken,config);



    }

}

