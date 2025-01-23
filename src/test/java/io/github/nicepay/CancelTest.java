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
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static io.github.nicepay.data.TestingConstants.*;
import static org.junit.jupiter.api.Assertions.*;

class CancelTest<T extends BaseNICEPayResponse> {

    private static LoggerPrint print = new LoggerPrint() ;
    private static NICEPay config;
    private static NICEPay configQR;

    private static TestingConstants DATA ;

    private static String timestamp;
    private static String amount;
    private static String reffNo;

    @BeforeAll
    public  static void setUp() {
        config =NICEPay.builder()
                .isProduction(false)
                .clientSecret(CLIENT_SECRET)
                .partnerId(I_MID)
                .externalID(DATA.EXTERNAL_ID)
                .timestamp(DATA.TIMESTAMP)
                .privateKey(PRIVATE_KEY)
                .build();

        configQR =NICEPay.builder()
                .isProduction(false)
                .clientSecret(QRIS_CLIENT_SECRET)
                .partnerId(I_MID_QRIS)
                .externalID(EXTERNAL_ID)
                .timestamp(TIMESTAMP)
                .privateKey(PRIVATE_KEY)
                .build();

        timestamp = TestingConstants.V2_TIMESTAMP;
        amount = "1000";
        reffNo = "0rdNo"+timestamp;
    }

    public Object getToken() throws IOException {
        Map<String, String> additionalInfo = new HashMap<>();
        AccessToken util =  AccessToken.builder()
                .grantType("client_credentials")
                .additionalInfo(additionalInfo)
                .build();
        return SnapTokenService.callGetAccessToken(util,config);

    }

    public Object getToken(NICEPay config) throws IOException {
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

        String txId = "";

        Cancel requestCancel = Cancel.builder()
                .timeStamp(timestamp)
                .tXid(txId)
                .iMid(TestingConstants.I_MID_NORMALCLOSED)
                .referenceNo(reffNo)
                .merchantToken(timestamp, TestingConstants.I_MID_NORMALCLOSED, txId, amount, TestingConstants.MERCHANT_KEY )
                .payMethod("02")
                .cancelType("1")
                .amt(amount)
                .build();

        NICEPayResponseV2 result = V2CancelService.callV2CancelTransaction(requestCancel, config);

    }

    @Test
    void ewalletRefund() throws IOException, InterruptedException {
        var accessToken = Optional.ofNullable((NICEPayResponse) getToken())
                .map(NICEPayResponse::getAccessToken)
                .orElseThrow(() -> new IllegalArgumentException("Token is null"));

        Cancel requestData = Cancel.builder()
                .merchantId(I_MID)
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
                .merchantId(TestingConstants.I_MID)
                .originalPartnerReferenceNo("2020102900000000000001")
                .originalReferenceNo("IONPAYTEST07202409201237043080")
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
                .merchantId(I_MID)
                .originalPartnerReferenceNo("2020102900000000000001")
                .originalReferenceNo("IONPAYTEST07202409201254073989")
                .build();

        requestData.setAdditionalInfo(null);

        NICEPayResponse Result =
                SnapCancelService.callServicePayoutCancel(requestData,accessToken,config);

    }

    @Test
    void qrisRefund() throws IOException, InterruptedException {
        var accessToken = Optional.ofNullable((NICEPayResponse) getToken(config))
                .map(NICEPayResponse::getAccessToken)
                .orElseThrow(() -> new IllegalArgumentException("Token is null"));

        Map<String, Object> additionalInfo = new HashMap<>();
        additionalInfo.put("cancelType", "1");

        Cancel requestData = Cancel.builder()
                .merchantId(I_MID)
                .originalPartnerReferenceNo("Ref20250116095220043469000314")
                .originalReferenceNo("IONPAYTEST08202501160952205551")
                .partnerRefundNo("refundQr"+V2_TIMESTAMP)
                .externalStoreId("NICEPAY")
                .refundAmount("1000.00","IDR")
                .reason("Refund Trans")
                .additionalInfo(additionalInfo)
                .build();

        NICEPayResponse Result =
                SnapCancelService.callServiceQrisRefund(requestData,accessToken,config);

    }

    @Test
    void cancelPartialCardV2() throws IOException, InterruptedException {

        String txId = "TESTMPGS0401202308171238501496";

        Cancel requestCancel = Cancel.builder()
                .timeStamp(TestingConstants.V2_TIMESTAMP)
                .tXid(txId)
                .referenceNo(reffNo) // different reffNo with registered transaction
                .iMid(TestingConstants.I_MID_INSTLMNT)
                .merchantToken(timestamp, TestingConstants.I_MID_INSTLMNT, txId, amount, TestingConstants.MERCHANT_KEY)
                .payMethod("01")
                .cancelType("2")
                .amt(amount)
                .cancelMsg("Cancellation Of Transaction Credit Card")
                .cancelUserIp("127.0.0.1")
                .cancelServerIp("127.0.0.1")
                .cancelUserInfo("")
                .cancelRetryCnt("")
                .worker("")
                .build();


        NICEPayResponseV2 result = V2CancelService.callV2CancelTransaction(requestCancel, config);


    }


    @Test
    void cancelFullCardV2() throws IOException, InterruptedException {

        String txId = "TESTMPGS0401202409111455005507";

        Cancel requestCancel = Cancel.builder()
                .timeStamp(timestamp)
                .tXid(txId)
                .referenceNo(reffNo) // different reffNo with registered transaction
                .merchantToken(timestamp, TestingConstants.I_MID_INSTLMNT,  txId, amount, TestingConstants.MERCHANT_KEY)
                .iMid(TestingConstants.I_MID_INSTLMNT)
                .payMethod("01")
                .cancelType("1")
                .amt(amount)
                .cancelMsg("Cancellation Of Transaction Credit Card")
                .cancelUserIp("127.0.0.1")
                .cancelServerIp("127.0.0.1")
                .cancelUserInfo("")
                .cancelRetryCnt("")
                .worker("")
                .build();

        NICEPayResponseV2 result = V2CancelService.callV2CancelTransaction(requestCancel, config);

    }
}





