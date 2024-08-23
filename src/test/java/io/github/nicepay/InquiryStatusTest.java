package io.github.nicepay;

import io.github.nicepay.data.TestingConstants;
import io.github.nicepay.data.response.snap.BaseNICEPayResponse;
import io.github.nicepay.data.model.AccessToken;
import io.github.nicepay.data.model.InquiryStatus;
import io.github.nicepay.data.response.snap.NICEPayResponse;
import io.github.nicepay.data.response.v2.NICEPayResponseV2;
import io.github.nicepay.service.snap.SnapInquiryStatusService;
import io.github.nicepay.service.snap.SnapTokenService;
import io.github.nicepay.service.v2.V2InquiryStatusService;
import io.github.nicepay.utils.NICEPay;
import io.github.nicepay.utils.SHA256Util;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

class InquiryStatusTest<T extends BaseNICEPayResponse> {
    private static NICEPay config;
    private static TestingConstants DATA ;
    private static NICEPay config2;
    private static NICEPay config3;
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

        InquiryStatus requestData = InquiryStatus.builder()
                .timeStamp(TestingConstants.V2_TIMESTAMP)
                .tXid("NORMALTEST02202408230923505139")
                .iMid(TestingConstants.I_MID)
                .referenceNo("NICEPAYVA111213")
                .amt("100")
                .build();

        requestData.setAdditionalInfo(null);

        String merchantToken = SHA256Util.encrypt(
                requestData.getTimeStamp() + requestData.getIMid() + requestData.getReferenceNo()+ requestData.getAmt()+
                        TestingConstants.MERCHANT_KEY);

        requestData.setMerchantToken(merchantToken);

        NICEPayResponseV2 result = V2InquiryStatusService.callV2InquiryVA(requestData, config);

    }

    @Test
    void InquiryStatusEwallet() throws IOException, InterruptedException {
        NICEPayResponse responseToken = (NICEPayResponse) getToken();
        var accessToken = Optional.ofNullable(responseToken)
                .map(token -> responseToken.getAccessToken())
                .orElseThrow(() -> new IllegalArgumentException("Token is null"));

        InquiryStatus requestData = InquiryStatus.builder()
                .merchantId("IONPAYTEST")
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
                .merchantId("IONPAYTEST")
//                .subMerchantId("23489182303312")
                .originalPartnerReferenceNo("2020102900000000000001")
                .originalReferenceNo("IONPAYTEST07202404080947007680")
                .beneficiaryAccountNo("5345000060")
//                .serviceCode("54")
//                .transactionDate("2022-04-28T10:13:40+07:00")
//                .externalStoreId("239840198240795109")
//                .amount("11000.00", "IDR")
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
                .merchantId("IONPAYTEST")
                .originalPartnerReferenceNo("2020102900000000000001")
                .originalReferenceNo("IONPAYTEST08202404180954247527")
                .externalStoreId("NICEPAY")
                .serviceCode("51")
                .build();

        NICEPayResponse Result =
                SnapInquiryStatusService.callServiceQrisCheckStatus(requestData,accessToken,config3);

    }

}





