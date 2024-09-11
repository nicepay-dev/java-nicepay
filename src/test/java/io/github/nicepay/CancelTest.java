package io.github.nicepay;

import io.github.nicepay.data.TestingConstants;
import io.github.nicepay.data.model.AccessToken;
import io.github.nicepay.data.model.Cancel;
import io.github.nicepay.data.response.snap.BaseNICEPayResponse;
import io.github.nicepay.data.response.snap.NICEPayResponse;
import io.github.nicepay.data.response.v2.NICEPayResponseV2;
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

import static org.junit.jupiter.api.Assertions.*;

class CancelTest<T extends BaseNICEPayResponse> {

    private static LoggerPrint print = new LoggerPrint() ;
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
                .tXidVA("IONPAYTEST02202408191440205690")
                .cancelMessage("test cancel")
                .build();

        NICEPayResponse result = SnapCancelService.callServiceVACancel(requestData,accessToken,config);

    }

    // V2 CANCEL VA
    @Test
    void CancelVAV2() throws IOException, InterruptedException {
        Cancel requestCancel = Cancel.builder()
                .timeStamp(TestingConstants.V2_TIMESTAMP)
                .tXid("NORMALTEST02202408230923505139")
                .iMid("NORMALTEST")
                .merchantKey(TestingConstants.MERCHANT_KEY)
                .payMethod("02")
                .cancelType("1")
                .amt("100")
                .buildV2();

        NICEPayResponseV2 result = V2CancelService.callV2CancelTransaction(requestCancel, config);

    }

    @Test
    void ewalletRefund() throws IOException, InterruptedException {
        var accessToken = Optional.ofNullable((NICEPayResponse) getToken())
                .map(NICEPayResponse::getAccessToken)
                .orElseThrow(() -> new IllegalArgumentException("Token is null"));

        Cancel requestData = Cancel.builder()
                .merchantId("IONPAYTEST")
                .subMerchantId("23489182303312")
                .originalPartnerReferenceNo("ref202305081205331683522921")
                .originalReferenceNo("IONPAYTEST05202308031752031674")
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
                .merchantId("IONPAYTEST")
                .originalPartnerReferenceNo("ref202305081205331683522921")
                .originalReferenceNo("IONPAYTEST05202308031752031674")
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
                .merchantId("IONPAYTEST")
                .originalPartnerReferenceNo("ref202305081205331683522921")
                .originalReferenceNo("IONPAYTEST05202308031752031674")
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

    @Test
    void cancelPartialCardV2() throws IOException, InterruptedException {
        Cancel requestCancel = Cancel.builder()
                .timeStamp(TestingConstants.V2_TIMESTAMP)
                .tXid("TESTMPGS0401202308171238501496")
                .referenceNo("0rdNo"+TestingConstants.V2_TIMESTAMP) // different reffNo with registered transaction
                .iMid("TESTMPGS04")
                .merchantKey(TestingConstants.MERCHANT_KEY)
                .payMethod("01")
                .cancelType("2")
                .amt("100")
                .cancelMsg("Cancellation Of Transaction Credit Card")
                .cancelUserIp("127.0.0.1")
                .cancelServerIp("127.0.0.1")
                .cancelUserInfo("")
                .cancelRetryCnt("")
                .worker("")
                .buildV2();


        NICEPayResponseV2 result = V2CancelService.callV2CancelTransaction(requestCancel, config);


    }


    @Test
    void cancelFullCardV2() throws IOException, InterruptedException {
        Cancel requestCancel = Cancel.builder()
                .timeStamp(TestingConstants.V2_TIMESTAMP)
                .tXid("TESTMPGS0401202202170931495193")
                .referenceNo("0rdNo"+TestingConstants.V2_TIMESTAMP) // different reffNo with registered transaction
                .iMid("TESTMPGS04")
                .merchantKey(TestingConstants.MERCHANT_KEY)
                .payMethod("01")
                .cancelType("1")
                .amt("5000")
                .cancelMsg("Cancellation Of Transaction Credit Card")
                .cancelUserIp("127.0.0.1")
                .cancelServerIp("127.0.0.1")
                .cancelUserInfo("")
                .cancelRetryCnt("")
                .worker("")
                .buildV2();


        NICEPayResponseV2 result = V2CancelService.callV2CancelTransaction(requestCancel, config);

    }
}





