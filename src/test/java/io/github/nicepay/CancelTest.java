package io.github.nicepay;

import io.github.nicepay.data.TestingConstants;
import io.github.nicepay.model.AccessToken;
import io.github.nicepay.model.Cancel;
import io.github.nicepay.response.snap.BaseNICEPayResponse;
import io.github.nicepay.response.snap.NICEPayResponse;
import io.github.nicepay.response.v2.NICEPayResponseV2;
import io.github.nicepay.service.snap.SnapCancelService;
import io.github.nicepay.service.snap.SnapTokenService;
import io.github.nicepay.service.v2.V2CancelService;
import io.github.nicepay.utils.LoggerPrint;
import io.github.nicepay.utils.NICEPay;
import io.github.nicepay.utils.SHA256Util;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

class CancelTest<T extends BaseNICEPayResponse> {

    private static LoggerPrint print = new LoggerPrint() ;
    private int retrycount = 0;
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
        AccessToken util =  AccessToken.builder()
                .grantType("client_credentials")
                .additionalInfo(additionalInfo)
                .build();
        return SnapTokenService.callGetAccessToken(util,config);

    }

    @Test
    void CancelVA() throws IOException, InterruptedException {
        var accessToken = Optional.ofNullable((NICEPayResponse) getToken())
                .map(NICEPayResponse::getAccessToken)
                .orElseThrow(() -> new IllegalArgumentException("Token is null"));
//
        Cancel requestData = Cancel.builder()
                .partnerServiceId("")
                .customerNo("")
                .virtualAccountNo("7001400002009191")
                .totalAmount("10000.00", "IDR")
                .trxId("TESTREFNO")
                .tXidVA("NORMALTEST02202408191440205690")
                .cancelMessage("test cancel")
                .build();

        NICEPayResponse result = SnapCancelService.callServiceVACancel(requestData,accessToken,config);

    }

    // V2 CANCEL VA
    @Test
    void CancelVAV2() throws IOException, InterruptedException {
        Cancel requestCancel = Cancel.builder()
                .timeStamp(TestingConstants.V2_TIMESTAMP)
                .tXid("NORMALTEST02202408191446415996")
                .iMid("NORMALTEST")
                .payMethod("02")
                .cancelType("1")
                .amt("100")
                .build();

        requestCancel.setAdditionalInfo(null);

        String merchantToken = SHA256Util.encrypt(
                requestCancel.getTimeStamp() + requestCancel.getIMid() + requestCancel.getTXid()+ requestCancel.getAmt()+
                        TestingConstants.MERCHANT_KEY);

        requestCancel.setMerchantToken(merchantToken);

        NICEPayResponseV2 result = V2CancelService.callV2CancelVA(requestCancel, config);

    }

    @Test
    void ewalletRefund() throws IOException, InterruptedException {
        var accessToken = Optional.ofNullable((NICEPayResponse) getToken())
                .map(NICEPayResponse::getAccessToken)
                .orElseThrow(() -> new IllegalArgumentException("Token is null"));

        Cancel requestData = Cancel.builder()
                .merchantId("NORMALTEST")
                .subMerchantId("23489182303312")
                .originalPartnerReferenceNo("ref202305081205331683522921")
                .originalReferenceNo("NORMALTEST05202308031752031674")
                .partnerRefundNo("ref202305081205331683522921")
                .refundAmount("1.00", "IDR")
                .externalStoreId("239840198240795109")
                .reason("test refund")
                .refundType("1")
                .build();

        NICEPayResponse Result =
                SnapCancelService.callServiceEwalletCancel(requestData,accessToken,config);

    }

    @Test
    void payoutReject() throws IOException, InterruptedException {
        var accessToken = Optional.ofNullable((NICEPayResponse) getToken())
                .map(NICEPayResponse::getAccessToken)
                .orElseThrow(() -> new IllegalArgumentException("Token is null"));

        Cancel requestData = Cancel.builder()
                .merchantId("NORMALTEST")
                .originalPartnerReferenceNo("ref202305081205331683522921")
                .originalReferenceNo("NORMALTEST05202308031752031674")
                .build();

        NICEPayResponse Result =
                SnapCancelService.callServicePayoutReject(requestData,accessToken,config);

    }

    @Test
    void payoutCancel() throws IOException, InterruptedException {
        var accessToken = Optional.ofNullable((NICEPayResponse) getToken())
                .map(NICEPayResponse::getAccessToken)
                .orElseThrow(() -> new IllegalArgumentException("Token is null"));

        Cancel requestData = Cancel.builder()
                .merchantId("NORMALTEST")
                .originalPartnerReferenceNo("ref202305081205331683522921")
                .originalReferenceNo("NORMALTEST05202308031752031674")
                .build();

        NICEPayResponse Result =
                SnapCancelService.callServicePayoutCancel(requestData,accessToken,config);

    }

    @Test
    void qrisRefund() throws IOException, InterruptedException {
        var accessToken = Optional.ofNullable((NICEPayResponse) getToken())
                .map(NICEPayResponse::getAccessToken)
                .orElseThrow(() -> new IllegalArgumentException("Token is null"));

        Map<String, Object> additionalInfo = new HashMap<>();
        additionalInfo.put("cancelType", "1");

        Cancel requestData = Cancel.builder()
                .merchantId("IONPAYTEST")
                .originalPartnerReferenceNo("2020102900000000000001")
                .originalReferenceNo("IONPAYTEST08202404180954247527")
                .partnerRefundNo("2020102900000000000001")
                .externalStoreId("NICEPAY")
                .refundAmount("11000.00","IDR")
                .reason("Refund Trans")
                .additionalInfo(additionalInfo)
                .build();

        NICEPayResponse Result =
                SnapCancelService.callServiceQrisRefund(requestData,accessToken,config);

    }

}





