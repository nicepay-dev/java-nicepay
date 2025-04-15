package io.github.nicepay;

import io.github.nicepay.data.TestingConstants;
import io.github.nicepay.data.model.AccessToken;
import io.github.nicepay.data.model.Cancel;
import io.github.nicepay.data.model.ConvenienceStore;
import io.github.nicepay.data.response.snap.BaseNICEPayResponse;
import io.github.nicepay.data.response.snap.NICEPayResponse;
import io.github.nicepay.data.response.v1.NICEPayResponseV1;
import io.github.nicepay.data.response.v2.NICEPayResponseV2;
import io.github.nicepay.service.snap.SnapCancelService;
import io.github.nicepay.service.snap.SnapTokenService;
import io.github.nicepay.service.v1.V1CardService;
import io.github.nicepay.service.v2.V2CancelService;
import io.github.nicepay.service.v2.V2CvSService;
import io.github.nicepay.service.v2.V2EwalletService;
import io.github.nicepay.service.v2.V2PayloanService;
import io.github.nicepay.utils.LoggerPrint;
import io.github.nicepay.utils.NICEPay;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static io.github.nicepay.data.TestingConstants.*;

class CancelTest<T extends BaseNICEPayResponse> {

    private static final LoggerPrint print = new LoggerPrint();
    private static NICEPay config;
    private static NICEPay configCloud;
    private static NICEPay configQR;

    private static NICEPay configCc;

    private static TestingConstants DATA;

    private static String timestamp;
    private static String amount;
    private static String reffNo;

    @BeforeAll
    public static void setUp() {
        config = NICEPay.builder()
                .isProduction(false)
                .clientSecret(CLIENT_SECRET)
                .partnerId(I_MID)
                .externalID(EXTERNAL_ID)
                .timestamp(TIMESTAMP)
                .privateKey(PRIVATE_KEY)
                .build();

        configCloud = NICEPay.builder()
                .isProduction(false)
                .isCloudServer(true)
                .clientSecret(CLOUD_CLIENT_SECRET)
                .partnerId(I_MID)
                .externalID(EXTERNAL_ID)
                .timestamp(TIMESTAMP)
                .privateKey(CLOUD_PRIVATE_KEY)
                .build();

        configQR = NICEPay.builder()
                .isProduction(false)
                .clientSecret(QRIS_CLIENT_SECRET)
                .partnerId(I_MID_QRIS)
                .externalID(EXTERNAL_ID)
                .timestamp(TIMESTAMP)
                .privateKey(PRIVATE_KEY)
                .build();

        configCc = NICEPay.builder()
                .isProduction(false)
                .isCloudServer(false)
                .clientSecret(INSTLMNT_CLIENT_SECRET)
                .partnerId(I_MID_INSTLMNT)
                .externalID(EXTERNAL_ID)
                .timestamp(TIMESTAMP)
                .privateKey(PRIVATE_KEY)
                .build();

        timestamp = TestingConstants.V2_TIMESTAMP;
        amount = "100";
        reffNo = "0rdNo" + timestamp;
    }

    public Object getToken() throws IOException {
        Map<String, String> additionalInfo = new HashMap<>();
        AccessToken util = AccessToken.builder()
                .grantType("client_credentials")
                .additionalInfo(additionalInfo)
                .build();
        return SnapTokenService.callGetAccessToken(util, configCloud);

    }

    public Object getToken(NICEPay config) throws IOException {
        Map<String, String> additionalInfo = new HashMap<>();
        AccessToken util = AccessToken.builder()
                .grantType("client_credentials")
                .additionalInfo(additionalInfo)
                .build();
        return SnapTokenService.callGetAccessToken(util, config);

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
                .virtualAccountNo("")
                .totalAmount("10000.00", "IDR")
                .trxId("TESTREFNO")
                .tXidVA("")
                .cancelMessage("test cancel")
                .build();

        NICEPayResponse result = SnapCancelService.callServiceVACancel(requestData, accessToken, config);

    }

    @Test
    void CancelVACloud() throws IOException, InterruptedException {
        var accessToken = Optional.ofNullable((NICEPayResponse) getToken(configCloud))
                .map(NICEPayResponse::getAccessToken)
                .orElseThrow(() -> new IllegalArgumentException("Token is null"));
//
        Cancel requestData = Cancel.builder()
                .partnerServiceId("")
                .customerNo("")
                .virtualAccountNo("")
                .totalAmount("11000.00", "IDR")
                .trxId("ordNo20250214145422")
                .tXidVA("")
                .cancelMessage("test cancel")
                .build();

        NICEPayResponse result = SnapCancelService.callServiceVACancel(requestData, accessToken, configCloud);

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
                .merchantToken(timestamp, TestingConstants.I_MID_NORMALCLOSED, txId, amount, TestingConstants.MERCHANT_KEY)
                .payMethod("02")
                .cancelType("1")
                .amt(amount)
                .build();

        NICEPayResponseV2 result = V2CancelService.callV2CancelTransaction(requestCancel, config);

    }

//    V2 QRIS

    @Test
    void CancelQrisV2() throws IOException, InterruptedException {

        String tXid = "";
        String reffNo = "";
        String amount = "500";
        String iMid = I_MID;

        Cancel requestCancel = Cancel.builder()
                .timeStamp(timestamp)
                .tXid(tXid)
                .iMid(iMid)
                .referenceNo(reffNo)
                .merchantToken(timestamp, iMid, tXid, amount, TestingConstants.MERCHANT_KEY)
                .payMethod("08")
                .cancelType("1")
                .amt(amount)
                .build();

        NICEPayResponseV2 result = V2CancelService.callV2CancelTransaction(requestCancel, config);

    }

    @Test
    void CancelQrisV2Cloud() throws IOException, InterruptedException {

        config.setCloudServer(true);

        String tXid = "";
        String reffNo = "";
        String amount = "500";
        String iMid = I_MID;

        Cancel requestCancel = Cancel.builder()
                .timeStamp(timestamp)
                .tXid(tXid)
                .iMid(iMid)
                .referenceNo(reffNo)
                .merchantToken(timestamp, iMid, tXid, amount, TestingConstants.MERCHANT_KEY)
                .payMethod("08")
                .cancelType("1")
                .amt(amount)
                .build();

        NICEPayResponseV2 result = V2CancelService.callV2CancelTransaction(requestCancel, config);

    }

//    V2 CVS

    @Test
    void CancelCvSV2() throws IOException, InterruptedException {

        NICEPayResponseV2 cvsTrans = generateCvsTrans(config);

        String tXid = cvsTrans.getTXid();
        String reffNo = cvsTrans.getReferenceNo();
        String amount = cvsTrans.getAmt();
        String iMid = I_MID;

        Cancel requestCancel = Cancel.builder()
                .timeStamp(timestamp)
                .tXid(tXid)
                .iMid(iMid)
                .referenceNo(reffNo)
                .merchantToken(timestamp, iMid, tXid, amount, TestingConstants.MERCHANT_KEY)
                .payMethod("03")
                .cancelType("1")
                .amt(amount)
                .cancelMessage("Test Cancel CvS Java")
                .build();

        NICEPayResponseV2 result = V2CvSService.callV2CancelTransaction(requestCancel, config);

    }

    @Test
    void CancelCvSV2Cloud() throws IOException, InterruptedException {

        config.setCloudServer(true);

        NICEPayResponseV2 cvsTrans = generateCvsTrans(config);

        String tXid = cvsTrans.getTXid();
        String reffNo = cvsTrans.getReferenceNo();
        String amount = cvsTrans.getAmt();
        String iMid = I_MID;

        Cancel requestCancel = Cancel.builder()
                .timeStamp(timestamp)
                .tXid(tXid)
                .iMid(iMid)
                .referenceNo(reffNo)
                .merchantToken(timestamp, iMid, tXid, amount, TestingConstants.MERCHANT_KEY)
                .payMethod("03")
                .cancelType("1")
                .amt(amount)
                .cancelMessage("Test Cancel CvS Java")
                .build();

        NICEPayResponseV2 result = V2CvSService.callV2CancelTransaction(requestCancel, config);

    }


    NICEPayResponseV2 generateCvsTrans(NICEPay config) throws IOException {
        String timeStamp = V2_TIMESTAMP;
        String iMid = I_MID;
        String reffNo = "regCvs" + timeStamp;
        String amount = "1000";

        ConvenienceStore cvs = ConvenienceStore.builder()
                .timeStamp(timeStamp)
                .iMid(iMid)
                .payMethod("03")
                .currency("IDR")
                .amt(amount)
                .referenceNo(reffNo)
                .goodsNm("Test Lib Java Convenience Store")
                .billingNm("Java Test")
                .billingPhone("081234567890")
                .billingEmail("email@merchant.com")
                .billingCity("Jakarta Selatan")
                .billingState("DKI Jakarta")
                .billingPostCd("12345")
                .billingCountry("Indonesia")
                .dbProcessUrl("https://merchant.com/dbProcessUrl")
                .merchantToken(timeStamp, iMid, reffNo, amount, MERCHANT_KEY)
                .userSessionID("697D6922C961070967D3BA1BA5699C2C")
                .userIP("127.0.0.1")
//                .cartData("{\\\"count\\\":1,\\\"item\\\":[{\\\"img_url\\\":\\\"http:\\/\\/www.jamgora.com\\/media\\/avatar\\/noimage.png\\\",\\\"goods_name\\\":\\\"Hoodie\\\",\\\"goods_detail\\\":\\\"Hoodie\\\",\\\"goods_amt\\\":\\\""+amount+"\\\"}]}")
                .mitraCd("ALMA")
                .build();


        return V2CvSService.callV2CvSRegistration(cvs, config);
    }

    @Test
    void ewalletRefundCloud() throws IOException, InterruptedException {
        var accessToken = Optional.ofNullable((NICEPayResponse) getToken())
                .map(NICEPayResponse::getAccessToken)
                .orElseThrow(() -> new IllegalArgumentException("Token is null"));

        Cancel requestData = Cancel.builder()
                .merchantId(I_MID)
                .subMerchantId("23489182303312")
                .originalPartnerReferenceNo("ordEW20250221135601")
                .originalReferenceNo("")
                .partnerRefundNo("cancelRef" + V2_TIMESTAMP)
                .refundAmount("1.00", "IDR")
                .externalStoreId("239840198240795109")
                .reason("test refund")
                .refundType("1")
                .build();

        NICEPayResponse Result =
                SnapCancelService.callServiceEwalletCancel(requestData, accessToken, configCloud);

    }

    @Test
    void ewalletRefund() throws IOException, InterruptedException {

        var accessToken = Optional.ofNullable((NICEPayResponse) getToken())
                .map(NICEPayResponse::getAccessToken)
                .orElseThrow(() -> new IllegalArgumentException("Token is null"));

        Cancel requestData = Cancel.builder()
                .merchantId(I_MID)
                .subMerchantId("")
                .originalPartnerReferenceNo("")
                .originalReferenceNo("")
                .partnerRefundNo("")
                .refundAmount("1.00", "IDR")
                .externalStoreId("")
                .reason("test refund")
                .refundType("1")
                .build();

        NICEPayResponse Result =
                SnapCancelService.callServiceEwalletCancel(requestData, accessToken, config);

    }

    @Test
    void payoutReject() throws IOException, InterruptedException {
        var accessToken = Optional.ofNullable((NICEPayResponse) getToken())
                .map(NICEPayResponse::getAccessToken)
                .orElseThrow(() -> new IllegalArgumentException("Token is null"));

        Cancel requestData = Cancel.builder()
                .merchantId(TestingConstants.I_MID)
                .originalPartnerReferenceNo("")
                .originalReferenceNo("")
                .build();

        NICEPayResponse Result =
                SnapCancelService.callServicePayoutReject(requestData, accessToken, config);

    }

    @Test
    void payoutCancel() throws IOException, InterruptedException {
        config.setCloudServer(true);
        var accessToken = Optional.ofNullable((NICEPayResponse) getToken())
                .map(NICEPayResponse::getAccessToken)
                .orElseThrow(() -> new IllegalArgumentException("Token is null"));

        Cancel requestData = Cancel.builder()
                .merchantId(I_MID)
                .originalPartnerReferenceNo("")
                .originalReferenceNo("")
                .build();

        requestData.setAdditionalInfo(null);

        NICEPayResponse Result =
                SnapCancelService.callServicePayoutCancel(requestData, accessToken, configCloud);

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
                .originalPartnerReferenceNo("")
                .originalReferenceNo("")
                .partnerRefundNo("refundQr" + V2_TIMESTAMP)
                .externalStoreId("NICEPAY")
                .refundAmount("1000.00", "IDR")
                .reason("Refund Trans")
                .additionalInfo(additionalInfo)
                .build();

        NICEPayResponse Result =
                SnapCancelService.callServiceQrisRefund(requestData, accessToken, config);

    }

    @Test
    void qrisRefundCloud() throws IOException, InterruptedException {
        config.setCloudServer(true);
        var accessToken = Optional.ofNullable((NICEPayResponse) getToken(config))
                .map(NICEPayResponse::getAccessToken)
                .orElseThrow(() -> new IllegalArgumentException("Token is null"));

        Map<String, Object> additionalInfo = new HashMap<>();
        additionalInfo.put("cancelType", "1");

        Cancel requestData = Cancel.builder()
                .merchantId(I_MID)
                .originalPartnerReferenceNo("OrdNo")
                .originalReferenceNo("")
                .partnerRefundNo("refundQr" + V2_TIMESTAMP)
                .externalStoreId("NICEPAY")
                .refundAmount("1000.00", "IDR")
                .reason("Refund Trans")
                .additionalInfo(additionalInfo)
                .build();

        NICEPayResponse Result =
                SnapCancelService.callServiceQrisRefund(requestData, accessToken, configCloud);

    }

    //    payloan
    @Test
    void cancelPayloanV2() throws IOException, InterruptedException {

//    config.setCloudServer(true);

//    NICEPayResponseV2 cvsTrans = generateCvsTrans(config);

        String tXid = "";
        String amount = "10000";
        String iMid = I_MID;

        Cancel requestCancel = Cancel.builder()
                .timeStamp(timestamp)
                .tXid(tXid)
                .iMid(iMid)
                .merchantToken(timestamp, iMid, tXid, amount, TestingConstants.MERCHANT_KEY)
                .payMethod("06")
                .cancelType("1")
                .cancelUserId("admin")
                .cancelMsg("Test Request Cancel Java Lib")
                .amt(amount)
                .build();

        NICEPayResponseV2 result = V2PayloanService.callV2CancelTransaction(requestCancel, config);

    }

    @Test
    void cancelPayloanV2Aws() throws IOException, InterruptedException {

        config.setCloudServer(true);

//    NICEPayResponseV2 cvsTrans = generateCvsTrans(config);

        String tXid = "";
        String amount = "";
        String iMid = I_MID;

        Cancel requestCancel = Cancel.builder()
                .timeStamp(timestamp)
                .tXid(tXid)
                .iMid(iMid)
                .merchantToken(timestamp, iMid, tXid, amount, TestingConstants.MERCHANT_KEY)
                .payMethod("06")
                .cancelType("1")
                .cancelUserId("admin")
                .cancelMsg("Test Request Cancel Java Lib")
                .amt(amount)
                .build();
//    }
        NICEPayResponseV2 result = V2PayloanService.callV2CancelTransaction(requestCancel, config);

    }


    @Test
    void cancelPartialCardV2() throws IOException, InterruptedException {

        String txId = "";

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
                .merchantToken(timestamp, TestingConstants.I_MID_INSTLMNT, txId, amount, TestingConstants.MERCHANT_KEY)
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


    @Test
    void cancelFullCardV1() throws IOException, InterruptedException {

        String txId = "";

        Cancel requestCancel = Cancel.builder()
                .timeStamp(timestamp)
                .tXid(txId)
                .cancelReferenceNo("OrdNo-") // different reffNo with registered transaction
                .merchantTokenV1(TestingConstants.I_MID_INSTLMNT, txId, amount, TestingConstants.MERCHANT_KEY)
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

        NICEPayResponseV1 result = V1CardService.callCancelTransaction(requestCancel, configCc);

    }

    @Test
    void ewalletRefundv2() throws IOException, InterruptedException {

        String timeStamp = V2_TIMESTAMP;
        String tXid = "";
        String iMid = I_MID_EWALLET;

        Cancel requestData = Cancel.builder()
                .timeStamp(timeStamp)
                .tXid(tXid)
                .payMethod("05")
                .cancelType("1")
                .cancelMsg("Test Cancel lib Java")
                .merchantToken(timeStamp, iMid, tXid, amount, MERCHANT_KEY)
                .iMid(iMid)
                .amt("100")
                .cancelUserIp("127.0.0.1")
                .build();

        NICEPayResponseV2 Result = V2EwalletService.callV2CancelTransaction(requestData, config);

    }

    @Test
    void ewalletRefundv2Aws() throws IOException, InterruptedException {

        String timeStamp = V2_TIMESTAMP;
        String tXid = "";
        String iMid = I_MID_EWALLET;

        Cancel requestData = Cancel.builder()
                .timeStamp(timeStamp)
                .tXid(tXid)
                .payMethod("05")
                .cancelType("1")
                .cancelMsg("Test Cancel lib Java")
                .merchantToken(timeStamp, iMid, tXid, amount, MERCHANT_KEY)
                .iMid(iMid)
                .amt("100")
                .cancelUserIp("127.0.0.1")
                .build();

        NICEPayResponseV2 Result = V2EwalletService.callV2CancelTransaction(requestData, config);

    }


}





