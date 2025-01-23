package io.github.nicepay;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.google.gson.Gson;
import io.github.nicepay.data.TestingConstants;
import io.github.nicepay.data.model.AccessToken;
import io.github.nicepay.data.model.InquiryStatus;
import io.github.nicepay.data.response.snap.BaseNICEPayResponse;
import io.github.nicepay.data.response.snap.NICEPayResponse;
import io.github.nicepay.data.response.v1.NICEPayResponseV1;
import io.github.nicepay.data.response.v2.NICEPayResponseV2;
import io.github.nicepay.service.snap.SnapInquiryStatusService;
import io.github.nicepay.service.snap.SnapTokenService;
import io.github.nicepay.service.v1.V1CardService;
import io.github.nicepay.service.v2.V2InquiryStatusService;
import io.github.nicepay.utils.NICEPay;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

import static io.github.nicepay.data.TestingConstants.V2_TIMESTAMP;
import static org.junit.jupiter.api.Assertions.assertEquals;

class InquiryStatusTest<T extends BaseNICEPayResponse> {
    private static NICEPay config;
    private static TestingConstants DATA ;
    private static NICEPay config2;
    private static NICEPay config3;
    private static String timestamp;
    private static String amount;

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

        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
        String TIMESTAMP = f.format(new Date());
        Random rand = new Random();
        int random = rand.nextInt(10000);
        String externalId = "OrdNo" + TIMESTAMP.substring(0, 10).replace("-","") + TIMESTAMP.substring(11,19).replace(":","") + random;
        config2 =NICEPay.builder()
                .isProduction(false)
                .clientSecret(DATA.CLIENT_SECRET)
                .partnerId(DATA.PARTNER_ID)
                .externalID(externalId)
                .timestamp(DATA.TIMESTAMP)
                .privateKey(DATA.PRIVATE_KEY)
                .build();

        int random2 = rand.nextInt(10000);
        String externalId2 = "OrdNo" + TIMESTAMP.substring(0, 10).replace("-","") + TIMESTAMP.substring(11,19).replace(":","") + random2;
        config3 =NICEPay.builder()
                .isProduction(false)
                .clientSecret(DATA.CLIENT_SECRET)
                .partnerId(DATA.PARTNER_ID)
                .externalID(externalId2)
                .timestamp(DATA.TIMESTAMP)
                .privateKey(DATA.PRIVATE_KEY)
                .build();

        timestamp = TestingConstants.TIMESTAMP;
        amount = "1000";

    }

    private int retrycount = 0;

    public Object getToken() throws IOException {
        Map<String, String> additionalInfo = new HashMap<>();
        AccessToken util =  AccessToken.builder()
                .grantType("client_credentials")
                .additionalInfo(additionalInfo)
                .build();
        return SnapTokenService.callGetAccessToken(util,config);

    }

    @Test
    void InquiryStatusVA() throws IOException, InterruptedException {
        var accessToken = Optional.ofNullable((NICEPayResponse) getToken())
                .map(NICEPayResponse::getAccessToken)
                .orElseThrow(() -> new IllegalArgumentException("Token is null"));

        InquiryStatus requestData = InquiryStatus.builder()
                .partnerServiceId("")
                .customerNo("")
                .virtualAccountNo("447792631104334735")
                .inquiryRequestId("dsfsd43")
                .totalAmount("11000.00", "IDR")
                .trxId("TESTTrxId")
                .tXidVA("IONPAYTEST02202408191104334735")
                .build();
        NICEPayResponse result = SnapInquiryStatusService.callServiceVACheckStatus(requestData,accessToken,config);

    }

    //V2
    @Test
    void InquiryStatusVAV2() throws IOException, InterruptedException {

        String reffNo = "OrdNo20166153345260";

        InquiryStatus requestData = InquiryStatus.builder()
                .timeStamp(V2_TIMESTAMP)
                .tXid("IONPAYTEST02201607261333495161")
                .iMid(TestingConstants.I_MID)
                .merchantToken(V2_TIMESTAMP, TestingConstants.I_MID, reffNo, amount, TestingConstants.MERCHANT_KEY)
                .referenceNo(reffNo)
                .amt(amount)
                .build();

        NICEPayResponseV2 result = V2InquiryStatusService.callV2InquiryStatus(requestData, config);

    }

    @Test
    void InquiryStatusEwallet() throws IOException, InterruptedException {
        NICEPayResponse responseToken = (NICEPayResponse) getToken();
        var accessToken = Optional.ofNullable(responseToken)
                .map(token -> responseToken.getAccessToken())
                .orElseThrow(() -> new IllegalArgumentException("Token is null"));

        InquiryStatus requestData = InquiryStatus.builder()
                .merchantId(TestingConstants.I_MID)
                .subMerchantId("23489182303312")
                .originalPartnerReferenceNo("ref202305081205331683522921")
                .originalReferenceNo("IONPAYTEST05202408221416277395")
                .serviceCode("54")
                .transactionDate("2022-04-28T10:13:40+07:00")
                .externalStoreId("239840198240795109")
                .amount("1.00", "IDR")
                .build();

        NICEPayResponse response =
                SnapInquiryStatusService.callServiceEwalletCheckStatus(requestData,accessToken,config2);

    }

    @Test
    void InquiryStatusPayout() throws IOException, InterruptedException {
        NICEPayResponse responseToken = (NICEPayResponse) getToken();
        var accessToken = Optional.ofNullable(responseToken)
                .map(token -> responseToken.getAccessToken())
                .orElseThrow(() -> new IllegalArgumentException("Token is null"));

        InquiryStatus requestData = InquiryStatus.builder()
                .merchantId(TestingConstants.I_MID)
                .originalPartnerReferenceNo("2020102900000000000001")
                .originalReferenceNo("IONPAYTEST07202404080947007680")
                .beneficiaryAccountNo("5345000060")
                .build();

        NICEPayResponse Result =
                SnapInquiryStatusService.callServicePayoutStatus(requestData,accessToken,config3);

    }

    @Test
    void InquiryStatusQris() throws IOException, InterruptedException {
        NICEPayResponse responseToken = (NICEPayResponse) getToken();
        var accessToken = Optional.ofNullable(responseToken)
                .map(token -> responseToken.getAccessToken())
                .orElseThrow(() -> new IllegalArgumentException("Token is null"));

        InquiryStatus requestData = InquiryStatus.builder()
                .merchantId(TestingConstants.I_MID)
                .originalPartnerReferenceNo("2020102900000000000001")
                .originalReferenceNo("IONPAYTEST08202404180954247527")
                .externalStoreId("NICEPAY")
                .serviceCode("51")
                .build();

        NICEPayResponse Result =
                SnapInquiryStatusService.callServiceQrisCheckStatus(requestData,accessToken,config3);

    }

//    CC V2

    //V2
    @Test
    void InquiryStatusCardV2() throws IOException, InterruptedException {

        String timestamp = V2_TIMESTAMP;
        String imid = TestingConstants.I_MID_PAC;
        String merchantKey = TestingConstants.MERCHANT_KEY;
        String reffNo = "ordNo20240904130813";
        String amount = "1000";


        InquiryStatus requestData = InquiryStatus.builder()
                .timeStamp(timestamp)
                .tXid("TESTMPGS0501202409041308179030")
                .iMid(imid)
                .referenceNo(reffNo)
                .merchantToken(timestamp, imid , reffNo , amount , merchantKey)
                .amt("1000")
                .build();

        NICEPayResponseV2 result = V2InquiryStatusService.callV2InquiryStatus(requestData, config);

//        RESPONSE CODE MUST BE 0000
        assertEquals("0000", result.getResultCd());

    }


    @Test
    void InquiryStatusCardV1() throws IOException, InterruptedException {

        String timestamp = V2_TIMESTAMP;
        String imid = TestingConstants.I_MID_PAC;
        String merchantKey = TestingConstants.MERCHANT_KEY;
        String reffNo = "ordNo20240904130813";
        String amount = "1000";


        InquiryStatus requestData = InquiryStatus.builder()
                .tXid("TESTMPGS0501202409041308179030")
                .iMid(imid)
                .referenceNo(reffNo)
                .merchantTokenV1(imid , reffNo , amount , merchantKey)
                .amt("1000")
                .build();

        NICEPayResponseV1 result = V1CardService.callInquiryStatus(requestData, config);

//        RESPONSE CODE MUST BE 0000
        assertEquals("0000", result.getResultCd());

    }

}





