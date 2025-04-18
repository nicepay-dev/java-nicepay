package io.github.nicepay;

import io.github.nicepay.data.TestingConstants;
import io.github.nicepay.data.model.AccessToken;
import io.github.nicepay.data.model.VirtualAccount;
import io.github.nicepay.data.response.snap.NICEPayResponse;
import io.github.nicepay.data.response.v2.NICEPayResponseV2;
import io.github.nicepay.service.snap.SnapTokenService;
import io.github.nicepay.service.snap.SnapVaService;
import io.github.nicepay.service.v2.V2VaService;
import io.github.nicepay.utils.LoggerPrint;
import io.github.nicepay.utils.NICEPay;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static io.github.nicepay.data.TestingConstants.*;

class VirtualAccountTest {
    private static LoggerPrint print = new LoggerPrint();
    private static NICEPay config;
    private static NICEPay configCloud;
    private static TestingConstants DATA ;

    @BeforeAll
    public  static void setUp() {
        config =NICEPay.builder()
                .isProduction(false)
                .clientSecret(DATA.CLIENT_SECRET)
                .partnerId(PARTNER_ID)
                .externalID(DATA.EXTERNAL_ID)
                .timestamp(DATA.TIMESTAMP)
                .privateKey(DATA.PRIVATE_KEY)
                .build();

        configCloud =NICEPay.builder()
                .isProduction(false)
                .isCloudServer(true)
                .clientSecret(DATA.CLIENT_SECRET)
                .partnerId(PARTNER_ID)
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
       return  SnapTokenService.callGetAccessToken(accessToken,configCloud);
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
                        put("bankCd","BRIN");
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

    @Test
    void vaCreateCloud() throws IOException
    {
        var accessToken = Optional.ofNullable((NICEPayResponse) getToken())
                .map(NICEPayResponse::getAccessToken)
                .orElseThrow(() -> new IllegalArgumentException("Token is null"));

        configCloud.setPrivateKey(CLOUD_PRIVATE_KEY);
        configCloud.setClientSecret(CLOUD_CLIENT_SECRET);

        VirtualAccount virtualAccount = VirtualAccount.builder()
                .partnerServiceId("1234")
                .customerNo("")
                .virtualAccountNo("")
                .virtualAccountName("TESTVaName")
                .trxId("ordNo"+V2_TIMESTAMP)
                .totalAmount("11000.00","IDR")
                .additionalInfo( new HashMap<String, Object>() {
                    {
                        put("bankCd","BMRI");
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

        NICEPayResponse response = SnapVaService.callGeneratedVA(virtualAccount,accessToken,configCloud);

    }

    @Test
    void vaCreateV2() throws IOException
    {
        String timeStamp = TestingConstants.V2_TIMESTAMP;

        VirtualAccount request = VirtualAccount.builder()
                .timeStamp(timeStamp)
                .iMid(TestingConstants.I_MID)
                .merchantToken(timeStamp, TestingConstants.I_MID, "NICEPAYVA111213", "100", TestingConstants.MERCHANT_KEY )
                .payMethod("02")
                .currency("IDR")
                .bankCd("BMRI")
                .amt("100")
                .referenceNo("NICEPAYVA111213")
                .vacctValidDt("")
                .vacctValidTm("")
                .goodsNm("Goods")
                .billingNm("NICEPAY Testing")
                .billingPhone("081363681274")
                .billingEmail("nicepay@example.com")
                .billingAddr("Jln. Raya Kasablanka Kav.88")
                .billingCity("South Jakarta")
                .billingState("DKI Jakarta")
                .billingPostCd("15119")
                .billingCountry("Indonesia")
                .merFixAcctId("")
                .dbProcessUrl("https://webhook.site/912cbdd8-eb28-4e98-be6a-181b806b8110")
                .build();


        NICEPayResponseV2 response = V2VaService.callV2GenerateVA(request, config);
        print.logInfoV2("TXID : " + response.getTXid());
        print.logInfoV2("VA : " + response.getVacctNo());

    }
}

