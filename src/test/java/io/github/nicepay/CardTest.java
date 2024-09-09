package io.github.nicepay;

import io.github.nicepay.data.TestingConstants;
import io.github.nicepay.data.model.Card;
import io.github.nicepay.data.response.v2.NICEPayResponseV2;
import io.github.nicepay.service.v2.V2CardService;
import io.github.nicepay.utils.NICEPay;
import io.github.nicepay.utils.SHA256Util;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CardTest {


    private static NICEPay config;
    private static TestingConstants DATA ;

    private static String amount;
    private static String iMid;

    @BeforeAll
    public  static void setUp() {
        config =NICEPay.builder()
                .isProduction(false)
                .build();

//        Set up default data
        amount = "1000";
        iMid = TestingConstants.I_MID_PAC;
    }

    @Test
    void cardRegistrationRequestTest() throws IOException
    {

        String timeStamp = TestingConstants.V2_TIMESTAMP;

        Card requestData = Card.builder()
                .timeStamp(TestingConstants.V2_TIMESTAMP)
                .iMid(iMid)
                .payMethod("01")
                .currency("IDR")
                .amt(amount)
                .referenceNo("ordNo"+timeStamp)
                .goodsNm("Goods")
                .billingNm("NICEPAY Testing")
                .billingPhone("081363681274")
                .billingEmail("nicepay@example.com")
                .billingAddr("Jln. Raya Kasablanka Kav.88")
                .billingCity("South Jakarta")
                .billingState("DKI Jakarta")
                .billingPostCd("15119")
                .billingCountry("Indonesia")
                .dbProcessUrl("https://webhook.site/912cbdd8-eb28-4e98-be6a-181b806b8110")
                .userIP("127.0.0.1")
                .userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:47.0) Gecko/20100101 Firefox/47.0")
                .userLanguage("en")
                .instmntType("1")
                .instmntMon("1")
                .recurrOpt("")
                .build();

        String merchantToken = SHA256Util.encrypt(
                requestData.getTimeStamp() + iMid + requestData.getReferenceNo() + amount +
                        TestingConstants.MERCHANT_KEY);

        requestData.setMerchantToken(merchantToken);

        NICEPayResponseV2 cardRegistResponse = V2CardService.callV2CardRegistration(requestData,config);

        assertNotNull(cardRegistResponse.getTXid());
        assertEquals("0000", cardRegistResponse.getResultCd());
        assertEquals("SUCCESS", cardRegistResponse.getResultMsg());

    }

    @Test
    void cardPaymentRequestTest() throws IOException
    {

//        CREATE NEW CARD TRANSACTION
        NICEPayResponseV2 cardRegistResponse = generateCardTransaction();

//        START PAYMENT REQUEST
        String timeStamp = TestingConstants.V2_TIMESTAMP;
        Card requestData = Card.builder()
                .timeStamp(timeStamp)
                .tXid(cardRegistResponse.getTXid())
                .referenceNo(cardRegistResponse.getReferenceNo())
                .cardNo("5123450000000008")
                .cardExpYymm("2908")
                .cardCvv("100")
                .cardHolderNm("NICEPAY TEST")
                .callBackUrl("https://dev.nicepay.co.id/IONPAY_CLIENT/paymentResult.jsp")
                .recurringToken("")
                .preauthToken("")
                .build();

        String merchantToken = SHA256Util.encrypt(
                timeStamp + iMid + requestData.getReferenceNo()+ cardRegistResponse.getAmt() +
                        TestingConstants.MERCHANT_KEY);

        requestData.setMerchantToken(merchantToken);

        String responseHtml = V2CardService.callV2CardPaymentRequest(requestData,config);

//        RESPONSE MUST NOT NULL
        assertNotNull(responseHtml);

//        FIRST TAG OF HTML CONTENT SHOULD BE <head>
        String firstTag = responseHtml.trim().substring(0,6);
        assertEquals("<head>", firstTag);


    }


    private NICEPayResponseV2 generateCardTransaction() throws IOException {
        String timeStamp = TestingConstants.V2_TIMESTAMP;

        Card requestData = Card.builder()
                .timeStamp(timeStamp)
                .iMid(iMid)
                .payMethod("01")
                .currency("IDR")
                .amt(amount)
                .referenceNo("ordNo"+timeStamp)
                .goodsNm("Goods")
                .billingNm("NICEPAY Testing")
                .billingPhone("081363681274")
                .billingEmail("nicepay@example.com")
                .billingAddr("Jln. Raya Kasablanka Kav.88")
                .billingCity("South Jakarta")
                .billingState("DKI Jakarta")
                .billingPostCd("15119")
                .billingCountry("Indonesia")
                .dbProcessUrl("https://webhook.site/912cbdd8-eb28-4e98-be6a-181b806b8110")
                .userIP("127.0.0.1")
                .userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:47.0) Gecko/20100101 Firefox/47.0")
                .userLanguage("en")
                .instmntType("1")
                .instmntMon("1")
                .recurrOpt("")
                .build();

        String merchantToken = SHA256Util.encrypt(
                requestData.getTimeStamp() + iMid + requestData.getReferenceNo() + amount +
                        TestingConstants.MERCHANT_KEY);

        requestData.setMerchantToken(merchantToken);

        return V2CardService.callV2CardRegistration(requestData,config);
    }

}
