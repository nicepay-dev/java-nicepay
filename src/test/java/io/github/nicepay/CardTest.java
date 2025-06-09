package io.github.nicepay;

import io.github.nicepay.data.TestingConstants;
import io.github.nicepay.data.model.Card;
import io.github.nicepay.data.response.v1.NICEPayResponseV1;
import io.github.nicepay.data.response.v2.NICEPayResponseV2;
import io.github.nicepay.service.v1.V1CardService;
import io.github.nicepay.service.v2.V2CardService;
import io.github.nicepay.utils.NICEPay;
import io.github.nicepay.utils.NICEPayConstants;
import io.github.nicepay.utils.SHA256Util;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CardTest {


    private static final Logger log = LoggerFactory.getLogger(CardTest.class);
    private static NICEPay config;
    private static TestingConstants DATA;

    private static String amount;
    private static String iMid;
    private static String timeStamp;
    private static String merchantKey;
    private static String reffNo;

    @BeforeAll
    public static void setUp() {
        config = NICEPay.builder()
                .isProduction(false)
                .build();

//        Set up default data
        amount = "1000";
        iMid = TestingConstants.I_MID;
        timeStamp = TestingConstants.V2_TIMESTAMP;
        merchantKey = TestingConstants.MERCHANT_KEY;
        reffNo = "ordNo" + timeStamp;

    }

    @Test
    void cardRegistrationRequestTest() throws IOException {

        String reffNo = "ordNo" + timeStamp;

        Card requestData = Card.builder()
                .timeStamp(timeStamp)
                .iMid(iMid)
                .payMethod("01")
                .currency("IDR")
                .amt(amount)
                .referenceNo(reffNo)
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
                .merchantToken(timeStamp, iMid, reffNo, amount, merchantKey)
                .build();

        NICEPayResponseV2 cardRegistResponse = V2CardService.callV2CardRegistration(requestData, config);

//        jn

    }

    @Test
    void cardRegistrationRequestTestCloud() throws IOException {
        config.setCloudServer(true);

        String reffNo = "ordNo" + timeStamp;

        Card requestData = Card.builder()
                .timeStamp(timeStamp)
                .iMid(iMid)
                .payMethod("01")
                .currency("IDR")
                .amt(amount)
                .referenceNo(reffNo)
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
                .merchantToken(timeStamp, iMid, reffNo, amount, merchantKey)
                .build();

        NICEPayResponseV2 cardRegistResponse = V2CardService.callV2CardRegistration(requestData, config);

        assertNotNull(cardRegistResponse.getTXid());
        assertEquals("0000", cardRegistResponse.getResultCd());
        assertEquals("SUCCESS", cardRegistResponse.getResultMsg());

    }

    @Test
    void cardPaymentRequestTest() throws Exception {

//        CREATE NEW CARD TRANSACTION
        NICEPayResponseV2 cardRegistResponse = generateCardTransaction(config);

//        START PAYMENT REQUEST
        String timeStamp = TestingConstants.V2_TIMESTAMP;
        Card requestData = Card.builder()
                .timeStamp(timeStamp)
                .tXid(cardRegistResponse.getTXid())
                .iMid(iMid)
                .referenceNo(cardRegistResponse.getReferenceNo())
                .amt(cardRegistResponse.getAmt())
                .merchantToken(timeStamp, iMid, reffNo, amount, merchantKey)
                .cardNo("5123450000000008")
                .cardExpYymm("2908")
                .cardCvv("100")
                .cardHolderNm("NICEPAY TEST")
                .callBackUrl("https://dev.nicepay.co.id/IONPAY_CLIENT/paymentResult.jsp")
                .recurringToken("")
                .preauthToken("")
                .isEncryptedCard("1")
                .publicKey("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCihYcT862uLW7U3xEIZT62CeVYHzE9t0/Q8g+y5S88bzqdM0bAG4C25q6CMC6QI8OliNJ2mgXO+WOMRG+dFK61cgvJPz2XzITqy2ey0cQTPig9ydVBbPphtcg3TJTPchh0lAt7bQZqguqZQdajUebTFI1p/b6lT+XJNC31AxvzlQIDAQAB")
                .build();

        String responseHtml = V2CardService.callV2CardPaymentRequest(requestData, config);

//        RESPONSE MUST NOT NULL
        assertNotNull(responseHtml);

//        FIRST TAG OF HTML CONTENT SHOULD BE <head>
        String firstTag = responseHtml.trim().substring(0, 6);
        assertEquals("<head>", firstTag);


    }

    @Test
    void cardPaymentRequestTestCloud() throws Exception {
        config.setCloudServer(true);
//        CREATE NEW CARD TRANSACTION
        NICEPayResponseV2 cardRegistResponse = generateCardTransaction(config);

//        START PAYMENT REQUEST
        String timeStamp = TestingConstants.V2_TIMESTAMP;
        Card requestData = Card.builder()
                .timeStamp(timeStamp)
                .tXid(cardRegistResponse.getTXid())
                .iMid(iMid)
                .referenceNo(cardRegistResponse.getReferenceNo())
                .amt(cardRegistResponse.getAmt())
                .merchantToken(timeStamp, iMid, reffNo, amount, merchantKey)
                .cardNo("5123450000000008")
                .cardExpYymm("2908")
                .cardCvv("100")
                .cardHolderNm("NICEPAY TEST")
                .callBackUrl("https://dev.nicepay.co.id/IONPAY_CLIENT/paymentResult.jsp")
                .recurringToken("")
                .preauthToken("")
                .isEncryptedCard("0")
                .publicKey("")
                .build();

        String responseHtml = V2CardService.callV2CardPaymentRequest(requestData, config);

//        RESPONSE MUST NOT NULL
        assertNotNull(responseHtml);

//        FIRST TAG OF HTML CONTENT SHOULD BE <head>
        String firstTag = responseHtml.trim().substring(0, 6);
        assertEquals("<head>", firstTag);


    }

//    V1

    @Test
    void cardV1RegistrationRequestTest() throws IOException {

        String reffNo = "ordNo" + timeStamp;

        Card requestData = Card.builder()
                .timeStamp(timeStamp)
                .iMid(iMid)
                .payMethod("01")
                .currency("IDR")
                .amt(amount)
                .referenceNo(reffNo)
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
                .merchantTokenV1(iMid, reffNo, amount, merchantKey)
                .description("Test Native Java V1 - Card")
                .deliveryNm("NICEPAY")
                .deliveryPhone("081363681274")
                .deliveryEmail("nicepay@example.com")
                .deliveryAddr("Jln. Raya Kasablanka Kav.88")
                .deliveryCity("South Jakarta")
                .deliveryState("DKI Jakarta")
                .deliveryPostCd("15119")
                .deliveryCountry("Indonesia")
                .callBackUrl(NICEPayConstants.getSandboxBaseUrl(config.isCloudServer()) + "/IONPAY_CLIENT/paymentResult.jsp")
                .build();

        NICEPayResponseV1 cardRegistResponse = V1CardService.callCardRedirectRegistration(requestData, config);

        assertNotNull(cardRegistResponse.getTXid());
        assertEquals("0000", cardRegistResponse.getData().getResultCd());
        assertEquals("SUCCESS", cardRegistResponse.getData().getResultMsg());

    }


    private NICEPayResponseV2 generateCardTransaction(NICEPay config) throws IOException {
        String reffNo = "ordNo" + timeStamp;

        Card requestData = Card.builder()
                .timeStamp(timeStamp)
                .iMid(iMid)
                .payMethod("01")
                .currency("IDR")
                .amt(amount)
                .referenceNo(reffNo)
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
                .merchantToken(timeStamp, iMid, reffNo, amount, merchantKey)
                .build();

        return V2CardService.callV2CardRegistration(requestData, config);
    }

    private NICEPayResponseV2 generateCardTransactionCloud() throws IOException {
        config.setCloudServer(true);

        String reffNo = "ordNo" + timeStamp;

        Card requestData = Card.builder()
                .timeStamp(timeStamp)
                .iMid(iMid)
                .payMethod("01")
                .currency("IDR")
                .amt(amount)
                .referenceNo(reffNo)
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
                .merchantToken(timeStamp, iMid, reffNo, amount, merchantKey)
                .build();

        return V2CardService.callV2CardRegistration(requestData, config);
    }


    @Test
    void testingEncryptCardDetail() throws Exception {
        String encryptedCard= SHA256Util.encryptWithPublicKeyString("5123450000000008","MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCihYcT862uLW7U3xEIZT62CeVYHzE9t0/Q8g+y5S88bzqdM0bAG4C25q6CMC6QI8OliNJ2mgXO+WOMRG+dFK61cgvJPz2XzITqy2ey0cQTPig9ydVBbPphtcg3TJTPchh0lAt7bQZqguqZQdajUebTFI1p/b6lT+XJNC31AxvzlQIDAQAB");
        System.out.println(encryptedCard);
        assertEquals("kv3qDS9l9Vt+HyA3PJWmE3v4Ga9dWxWVOJkBYuarowe4BpW/U9IQjnm0i7DiEFDMbkAYIqP+B4GZzALGeUVfnTuaszjIM+AQEpntw5t0yxCrsWPYtzl0LEu3X/Huv0VWSujlJKCKkMN2P1JiWY8G8DfEhBn5m4lQBHM7KDEB9ZE=", encryptedCard);
    }
}
