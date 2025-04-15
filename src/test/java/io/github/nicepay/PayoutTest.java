package io.github.nicepay;

import io.github.nicepay.data.TestingConstants;
import io.github.nicepay.data.model.AccessToken;
import io.github.nicepay.data.model.Payout;
import io.github.nicepay.data.response.snap.NICEPayResponse;
import io.github.nicepay.data.response.v2.NICEPayResponseV2;
import io.github.nicepay.service.snap.SnapPayoutService;
import io.github.nicepay.service.snap.SnapTokenService;
import io.github.nicepay.service.v2.V2PayoutService;
import io.github.nicepay.utils.NICEPay;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

import static io.github.nicepay.data.TestingConstants.*;

class PayoutTest {
    private static NICEPay config;
    private static NICEPay configCloud;
    private static TestingConstants DATA;
    private static NICEPay config2;
    private static NICEPay config3;

    Logger logger = LoggerFactory.getLogger("Payout test");

    @BeforeAll
    public static void setUp() {
        config = NICEPay.builder()
                .isProduction(false)
                .clientSecret(TestingConstants.CLOUD_CLIENT_SECRET)
                .partnerId(I_MID)
                .externalID(TestingConstants.EXTERNAL_ID)
                .timestamp(TestingConstants.TIMESTAMP)
                .privateKey(TestingConstants.PRIVATE_KEY)
                .build();

        configCloud = NICEPay.builder()
                .isProduction(false)
                .isCloudServer(true)
                .clientSecret(TestingConstants.CLIENT_SECRET)
                .partnerId(TestingConstants.PARTNER_ID)
                .externalID(TestingConstants.EXTERNAL_ID)
                .timestamp(TestingConstants.TIMESTAMP)
                .privateKey(TestingConstants.PRIVATE_KEY_CLOUD)
                .build();

        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
        String TIMESTAMP = f.format(new Date());
        Random rand = new Random();
        int random = rand.nextInt(10000);
        String externalId = "OrdNo" + TIMESTAMP.substring(0, 10).replace("-", "") + TIMESTAMP.substring(11, 19).replace(":", "") + random;
        config2 = NICEPay.builder()
                .isProduction(false)
                .clientSecret(TestingConstants.CLIENT_SECRET)
                .partnerId(TestingConstants.PARTNER_ID)
                .externalID(externalId)
                .timestamp(TestingConstants.TIMESTAMP)
                .privateKey(TestingConstants.PRIVATE_KEY)
                .build();
        int random2 = rand.nextInt(10000);
        String externalId2 = "OrdNo" + TIMESTAMP.substring(0, 10).replace("-", "") + TIMESTAMP.substring(11, 19).replace(":", "") + random2;
        config3 = NICEPay.builder()
                .isProduction(false)
                .clientSecret(TestingConstants.CLIENT_SECRET)
                .partnerId(TestingConstants.PARTNER_ID)
                .externalID(externalId2)
                .timestamp(TestingConstants.TIMESTAMP)
                .privateKey(TestingConstants.PRIVATE_KEY)
                .build();
    }

    public Object getToken() throws IOException {
        Map<String, String> additionalInfo = new HashMap<>();
        AccessToken token = AccessToken.builder()
                .grantType("client_credentials")
                .additionalInfo(additionalInfo)
                .build();
        return SnapTokenService.callGetAccessToken(token, config);
    }

    public Object getToken(NICEPay config) throws IOException {
        Map<String, String> additionalInfo = new HashMap<>();
        AccessToken token = AccessToken.builder()
                .grantType("client_credentials")
                .additionalInfo(additionalInfo)
                .build();
        return SnapTokenService.callGetAccessToken(token, config);
    }

    @Test
    void payOutregist() throws IOException {
        var accessToken = Optional.ofNullable((NICEPayResponse) getToken())
                .map(NICEPayResponse::getAccessToken)
                .orElseThrow(() -> new IllegalArgumentException("Token is null"));


        Payout payout = Payout.builder()
                .merchantId(I_MID)
                .beneficiaryAccountNo("5345000060")
                .beneficiaryName("IONPAY NETWORKS")
                .beneficiaryPhone("08123456789")
                .beneficiaryCustomerResidence("1")
                .beneficiaryCustomerType("1")
                .beneficiaryPostalCode("123456")
                .payoutMethod("0")
                .beneficiaryBankCode("BDIN")
                .amount("11000.00", "IDR")
                .partnerReferenceNo("2020102900000000000001")
                .description("This is test Request")
                .deliveryName("Ciki")
                .deliveryId("1234567890234512")
                .reservedDt("")
                .reservedTm("")
                .additionalInfo("")
                .build();

        NICEPayResponse response =
                SnapPayoutService.callServicePayoutRegist(payout, accessToken, config);
    }

    @Test
    void payOutregistCloud() throws IOException {
        NICEPay config = configCloud;

        var accessToken = Optional.ofNullable((NICEPayResponse) getToken(config))
                .map(NICEPayResponse::getAccessToken)
                .orElseThrow(() -> new IllegalArgumentException("Token is null"));


        Payout payout = Payout.builder()
                .merchantId(config.getPartnerId())
                .beneficiaryAccountNo("800152779200")
                .beneficiaryName("IONPAY NETWORKS")
                .beneficiaryPhone("08123456789")
                .beneficiaryCustomerResidence("1")
                .beneficiaryCustomerType("1")
                .beneficiaryPostalCode("123456")
                .payoutMethod("0")
                .beneficiaryBankCode("BNIA")
                .amount("11000.00", "IDR")
                .partnerReferenceNo("payout" + V2_TIMESTAMP)
                .description("This is test Request")
                .deliveryName("Ciki")
                .deliveryId("1234567890234512")
                .reservedDt("")
                .reservedTm("")
                .additionalInfo("")
                .build();

        NICEPayResponse response =
                SnapPayoutService.callServicePayoutRegist(payout, accessToken, config);
    }


    @Test
    void payOutApprove() throws IOException {
        var accessToken = Optional.ofNullable((NICEPayResponse) getToken())
                .map(NICEPayResponse::getAccessToken)
                .orElseThrow(() -> new IllegalArgumentException("Token is null"));

        NICEPayResponse responseRegist = registPayout(accessToken);

        Payout payout = Payout.builder()
                .merchantId(I_MID)
                .originalReferenceNo(responseRegist.getOriginalReferenceNo())
                .originalPartnerReferenceNo(responseRegist.getPartnerReferenceNo())
                .build();

        NICEPayResponse response =
                SnapPayoutService.callServicePayoutApprove(payout, accessToken, config2);

    }

    @Test
    void payOutCheckBalance() throws IOException {
        var accessToken = Optional.ofNullable((NICEPayResponse) getToken())
                .map(NICEPayResponse::getAccessToken)
                .orElseThrow(() -> new IllegalArgumentException("Token is null"));

        Payout payout = Payout.builder()
                .accountNo(I_MID)
                .additionalInfo("")
                .build();

        NICEPayResponse response =
                SnapPayoutService.callServicePayoutCheckBalance(payout, accessToken, config2);

        System.out.println("Response mess :" + response.getResponseMessage());
    }

    @Test
    void payOutCheckBalanceCloud() throws IOException {
        config = configCloud;
        var accessToken = Optional.ofNullable((NICEPayResponse) getToken(config))
                .map(NICEPayResponse::getAccessToken)
                .orElseThrow(() -> new IllegalArgumentException("Token is null"));

        Payout payout = Payout.builder()
                .accountNo(TestingConstants.PARTNER_ID)
                .additionalInfo("")
                .build();

        NICEPayResponse response =
                SnapPayoutService.callServicePayoutCheckBalance(payout, accessToken, config);

        System.out.println("Response mess :" + response.getResponseMessage());
    }

    NICEPayResponse registPayout(String accessToken) throws IOException {

        Payout payout = Payout.builder()
                .merchantId(TestingConstants.I_MID)
                .beneficiaryAccountNo("5345000060")
                .beneficiaryName("IONPAY NETWORKS")
                .beneficiaryPhone("08123456789")
                .beneficiaryCustomerResidence("1")
                .beneficiaryCustomerType("1")
                .beneficiaryPostalCode("123456")
                .payoutMethod("0")
                .beneficiaryBankCode("BDIN")
                .amount("11000.00", "IDR")
                .partnerReferenceNo("2020102900000000000001")
                .description("This is test Request")
                .deliveryName("Ciki")
                .deliveryId("1234567890234512")
                .reservedDt("20240921")
                .reservedTm("125222")
                .additionalInfo("")
                .build();

        return SnapPayoutService.callServicePayoutRegist(payout, accessToken, config);
    }


//    V2

    @Test
    void payOutregistV2Test() throws IOException {

        String timeStamp = V2_TIMESTAMP;
        String reffNo = "regPOJava" + timeStamp;
        String iMid = I_MID;
        String amount = "10000";
        String accountNo = "5345000060";
//
        Payout payout = Payout.builder()
                .iMid(iMid)
                .timeStamp(timeStamp)
                .msId("")
                .merchantToken(timeStamp, iMid, amount, accountNo, MERCHANT_KEY)
                .accountNo(accountNo)
                .benefNm("testing Java Lib")
                .benefType("1")
                .benefStatus("1")
                .bankCd("BDIN")
                .amt(amount)
                .referenceNo(reffNo)
                .reservedDt("")
                .reservedTm("")
                .benefPhone("082111111111")
                .description("Test Request Java Lib Payout V2")
                .payoutMethod("4")
                .build();

        NICEPayResponseV2 response =
                V2PayoutService.callV2PayoutRequest(payout, config);
    }

    @Test
    void payOutregistV2TestAws() throws IOException {

        config.setCloudServer(true);
        String timeStamp = V2_TIMESTAMP;
        String reffNo = "regPOJava" + timeStamp;
        String iMid = I_MID;
        String amount = "1000";
        String accountNo = "5345000060";
//
        Payout payout = Payout.builder()
                .iMid(iMid)
                .timeStamp(timeStamp)
                .msId("")
                .merchantToken(timeStamp, iMid, amount, accountNo, MERCHANT_KEY)
                .accountNo(accountNo)
                .benefNm("testing Java Lib")
                .benefType("1")
                .benefStatus("1")
                .bankCd("BDIN")
                .amt(amount)
                .referenceNo(reffNo)
                .reservedDt("")
                .reservedTm("")
                .benefPhone("082111111111")
                .description("Test Request Java Lib Payout V2")
                .payoutMethod("4")
                .build();

        NICEPayResponseV2 response =
                V2PayoutService.callV2PayoutRequest(payout, config);
    }

    @Test
    void payCheckBalanceV2TestAws() throws IOException {

        config.setCloudServer(true);
        String timeStamp = V2_TIMESTAMP;
        String iMid = I_MID;


        Payout payout = Payout.builder()
                .iMid(iMid)
                .timeStamp(timeStamp)
                .merchantTokenInquiryBalance(timeStamp, iMid, MERCHANT_KEY)
                .build();

        NICEPayResponseV2 response =
                V2PayoutService.callV2BalanceInquiry(payout, config);
    }

    @Test
    void payCheckBalanceV2Test() throws IOException {

        String timeStamp = V2_TIMESTAMP;
        String iMid = I_MID;


        Payout payout = Payout.builder()
                .iMid(iMid)
                .timeStamp(timeStamp)
                .merchantTokenInquiryBalance(timeStamp, iMid, MERCHANT_KEY)
                .build();

        NICEPayResponseV2 response =
                V2PayoutService.callV2BalanceInquiry(payout, config);
    }

    @Test
    void payoutApproveV2TestAws() throws IOException {

        config.setCloudServer(true);

        NICEPayResponseV2 payoutTrans = generatePayoutTrans(config);

        String timeStamp = V2_TIMESTAMP;
        String iMid = I_MID;
        String tXid = payoutTrans.getTXid();

        Payout payout = Payout.builder()
                .iMid(iMid)
                .timeStamp(timeStamp)
                .tXid(tXid)
                .merchantTokenApprovePayout(timeStamp, iMid, tXid, MERCHANT_KEY)
                .build();

        NICEPayResponseV2 response =
                V2PayoutService.callV2ApprovePayout(payout, config);
    }

    @Test
    void payoutRejectV2Test() throws IOException {

        NICEPayResponseV2 payoutTrans = generatePayoutTrans(config);

        String timeStamp = V2_TIMESTAMP;
        String iMid = I_MID;
        String tXid = payoutTrans.getTXid();

        Payout payout = Payout.builder()
                .iMid(iMid)
                .timeStamp(timeStamp)
                .tXid(tXid)
                .merchantTokenApprovePayout(timeStamp, iMid, tXid, MERCHANT_KEY)
                .build();

        NICEPayResponseV2 response =
                V2PayoutService.callV2RejectPayout(payout, config);
    }

    @Test
    void payoutRejectV2TestAws() throws IOException {

        config.setCloudServer(true);

        NICEPayResponseV2 payoutTrans = generatePayoutTrans(config);

        String timeStamp = V2_TIMESTAMP;
        String iMid = I_MID;
        String tXid = payoutTrans.getTXid();

        Payout payout = Payout.builder()
                .iMid(iMid)
                .timeStamp(timeStamp)
                .tXid(tXid)
                .merchantTokenApprovePayout(timeStamp, iMid, tXid, MERCHANT_KEY)
                .build();

        NICEPayResponseV2 response =
                V2PayoutService.callV2RejectPayout(payout, config);
    }

    NICEPayResponseV2 generatePayoutTrans(NICEPay config) throws IOException {
        String timeStamp = V2_TIMESTAMP;
        String reffNo = "regPOJava" + timeStamp;
        String iMid = config.getPartnerId();
        String amount = "1000";
        String accountNo = "5345000060";
//
        Payout payout = Payout.builder()
                .iMid(iMid)
                .timeStamp(timeStamp)
                .msId("")
                .merchantToken(timeStamp, iMid, amount, accountNo, MERCHANT_KEY)
                .accountNo(accountNo)
                .benefNm("testing Java Lib")
                .benefType("1")
                .benefStatus("1")
                .bankCd("BDIN")
                .amt(amount)
                .referenceNo(reffNo)
                .reservedDt("20250416")
                .reservedTm("202020")
                .benefPhone("082111111111")
                .description("Test Request Java Lib Payout V2")
                .payoutMethod("0")
                .build();

        return V2PayoutService.callV2PayoutRequest(payout, config);
    }

    @Test
    void payoutCancelV2TestAws() throws IOException {

        config.setCloudServer(true);
        config.setPartnerId("IONPAYTEST");


        NICEPayResponseV2 payoutTrans = generatePayoutTrans(config);
        approvePayoutTrans(payoutTrans);


        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String iMid = config.getPartnerId();
        String tXid = payoutTrans.getTXid();

        Payout payout = Payout.builder()
                .iMid(iMid)
                .timeStamp(timeStamp)
                .tXid(tXid)
                .merchantTokenApprovePayout(timeStamp, iMid, tXid, MERCHANT_KEY)
                .build();

        NICEPayResponseV2 cancelResponse =
                V2PayoutService.callV2CancelPayout(payout, config);


    }

    @Test
    void payoutCancelV2Test() throws IOException {

        config.setPartnerId(I_MID);

        NICEPayResponseV2 payoutTrans = generatePayoutTrans(config);
        approvePayoutTrans(payoutTrans);


        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String iMid = config.getPartnerId();
        String tXid = payoutTrans.getTXid();

        Payout payout = Payout.builder()
                .iMid(iMid)
                .timeStamp(timeStamp)
                .tXid(tXid)
                .merchantTokenApprovePayout(timeStamp, iMid, tXid, MERCHANT_KEY)
                .build();

        NICEPayResponseV2 cancelResponse =
                V2PayoutService.callV2CancelPayout(payout, config);


    }

    private static NICEPayResponseV2 approvePayoutTrans(NICEPayResponseV2 payoutTrans) throws IOException {
        String timeStamp = V2_TIMESTAMP;
        String iMid = config.getPartnerId();
        String tXid = payoutTrans.getTXid();

        Payout payout = Payout.builder()
                .iMid(iMid)
                .timeStamp(timeStamp)
                .tXid(tXid)
                .merchantTokenApprovePayout(timeStamp, iMid, tXid, MERCHANT_KEY)
                .build();

        NICEPayResponseV2 response =
                V2PayoutService.callV2ApprovePayout(payout, config);
        return response;
    }
}