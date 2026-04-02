package io.github.nicepay;

import io.github.nicepay.data.TestingConstants;
import io.github.nicepay.data.model.Payment;
import io.github.nicepay.data.model.Redirect;
import io.github.nicepay.data.response.snap.BaseNICEPayResponse;
import io.github.nicepay.data.response.v1.NICEPayResponseV1;
import io.github.nicepay.data.response.v2.NICEPayResponseV2;
import io.github.nicepay.service.v1.V1ProfessionalService;
import io.github.nicepay.service.v2.V2RedirectService;
import io.github.nicepay.utils.ApiUtils;
import io.github.nicepay.utils.NICEPay;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static io.github.nicepay.data.TestingConstants.*;

class RedirectTest<T extends BaseNICEPayResponse> {
    private static NICEPay config;
    private static NICEPay configCloud;
    private static TestingConstants DATA ;
    private static String amount;

    @BeforeAll
    public  static void setUp() {
        config =NICEPay.builder()
                .partnerId(I_MID)
                .isProduction(false)
                .isCloudServer(false)
                .build();


        configCloud = NICEPay.builder()
                .isProduction(false)
                .isCloudServer(true)
                .build();

        amount = "10000";

    }

    @Test
    void redirectV2RegistTest() throws IOException
    {
        String timeStamp = V2_TIMESTAMP;
        String iMid = I_MID;
        String reffNo = "regRedirectV2"+timeStamp;
        String amount = "10000";

        Redirect request = Redirect.builder()
                .timeStamp(timeStamp)
                .iMid(iMid)
                .payMethod("00")
                .currency("IDR")
                .amt(amount)
                .referenceNo(reffNo)
                .goodsNm("Test Lib Java Redirect Regist")
                .billingNm("Java Test")
                .billingPhone("081234567890")
                .billingEmail("email@merchant.com")
                .billingCity("Jakarta Selatan")
                .billingState("DKI Jakarta")
                .billingPostCd("12345")
                .billingCountry("Indonesia")
                .deliveryNm("Java Test")
                .deliveryPhone("081234567890")
                .deliveryCity("Jakarta Selatan")
                .deliveryState("DKI Jakarta")
                .deliveryPostCd("12345")
                .deliveryCountry("Indonesia")
                .dbProcessUrl("https://merchant.com/dbProcessUrl")
                .callBackUrl(config.getNICEPayBaseUrl() + "IONPAY_CLIENT/paymentResult.jsp")
                .vat("")
                .fee("")
                .notaxAmt("")
                .description("Test transaction Java Lib")
                .merchantToken(timeStamp, iMid, reffNo, amount, MERCHANT_KEY)
                .reqTm("")
                .reqDt("")
                .reqDomain("https://www.merchant.com")
                .reqServerIP("127.0.0.1")
                .reqClientVer("")
                .userIP("127.0.0.1")
                .userSessionID("697D6922C961070967D3BA1BA5699C2C")
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML,like Gecko) Chrome/60.0.3112.101 Safari/537.36")
                .userLanguage("ko-KR,en-US;q=0.8,ko;q=0.6,en;q=0.4")
                .cartData("")
                .sellers("[{\"sellersId\": \"SEL123\",\"sellersNm\": \"Sellers 1\",\"sellersEmail\":\"sellers@test.com\",\"sellersAddress\": {\"sellerNm\": \"Sellers\",\"sellerLastNm\": \"1\",\"sellerAddr\": \"jalan berbangsa 1\",\"sellerCity\":\"Jakarta Barat\",\"sellerPostCd\": \"12344\",\"sellerPhone\":\"08123456789\",\"sellerCountry\": \"ID\"}}]")
                .userIP("127.0.0.1")
//                .cartData("{\\\"count\\\":1,\\\"item\\\":[{\\\"img_url\\\":\\\"http:\\/\\/www.jamgora.com\\/media\\/avatar\\/noimage.png\\\",\\\"goods_name\\\":\\\"Hoodie\\\",\\\"goods_detail\\\":\\\"Hoodie\\\",\\\"goods_amt\\\":\\\""+amount+"\\\"}]}")
//                .cartData("{\"count\":\"1\",\"item\":[{\"goods_id\":\"BB12345678\",\"goods_detail\":\"BB12345678\",\"goods_name\":\"Pasar Modern\",\"goods_amt\":\"10000\",\"goods_type\":\"Sembako\",\"goods_url\":\"http://merchant.com/cellphones/iphone5s_64g\,\"goods_quantity\":\"1\",\"goods_sellers_id\":\"SEL123\",\"goods_sellers_name\":\"Sellers 1\"}]}")
                .cartData("{\"count\":\"1\",\"item\":[{\"goods_id\":\"BB12345678\",\"goods_detail\":\"BB12345678\",\"goods_name\":\"Pasar Modern\",\"goods_amt\":\"10000\",\"goods_type\":\"Sembako\",\"goods_url\":\"http://merchant.com/cellphones/iphone5s_64g,\",\"goods_quantity\":\"1\",\"goods_sellers_id\":\"SEL123\",\"goods_sellers_name\":\"Sellers 1\"}]}")
                .mitraCd("ALMA")
                .instmntType("2")
                .instmntMon("1")
                .recurrOpt("1")
                .bankCd("")
                .vacctValidDt("")
                .vacctValidTm("")
                .payValidDt("")
                .paymentExpTm("")
                .merFixAcctId("")
                .paymentExpDt("")
                .payValidTm("")
                .shopId("")
                .build();


        NICEPayResponseV2 response =
                V2RedirectService.callV2RedirectRegistration(request,config);
    }

    @Test
    void redirectV2RegistTestAws() throws IOException
    {
        config = configCloud;
        String timeStamp = V2_TIMESTAMP;
        String iMid = I_MID;
        String reffNo = "regCvs"+timeStamp;
        String amount = "10000";

        Redirect request = Redirect.builder()
                .timeStamp(timeStamp)
                .iMid(iMid)
                .payMethod("00")
                .currency("IDR")
                .amt(amount)
                .referenceNo(reffNo)
                .goodsNm("Test Lib Java Redirect Regist")
                .billingNm("Java Test")
                .billingPhone("081234567890")
                .billingEmail("email@merchant.com")
                .billingCity("Jakarta Selatan")
                .billingState("DKI Jakarta")
                .billingPostCd("12345")
                .billingCountry("Indonesia")
                .deliveryNm("Java Test")
                .deliveryPhone("081234567890")
                .deliveryCity("Jakarta Selatan")
                .deliveryState("DKI Jakarta")
                .deliveryPostCd("12345")
                .deliveryCountry("Indonesia")
                .dbProcessUrl("https://merchant.com/dbProcessUrl")
                .callBackUrl(config.getNICEPayBaseUrl() + "IONPAY_CLIENT/paymentResult.jsp")
                .vat("")
                .fee("")
                .notaxAmt("")
                .description("Test transaction Java Lib")
                .merchantToken(timeStamp, iMid, reffNo, amount, MERCHANT_KEY)
                .reqTm("")
                .reqDt("")
                .reqDomain("https://www.merchant.com")
                .reqServerIP("127.0.0.1")
                .reqClientVer("")
                .userIP("127.0.0.1")
                .userSessionID("697D6922C961070967D3BA1BA5699C2C")
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML,like Gecko) Chrome/60.0.3112.101 Safari/537.36")
                .userLanguage("ko-KR,en-US;q=0.8,ko;q=0.6,en;q=0.4")
                .cartData("")
                .sellers("[{\"sellersId\": \"SEL123\",\"sellersNm\": \"Sellers 1\",\"sellersEmail\":\"sellers@test.com\",\"sellersAddress\": {\"sellerNm\": \"Sellers\",\"sellerLastNm\": \"1\",\"sellerAddr\": \"jalan berbangsa 1\",\"sellerCity\":\"Jakarta Barat\",\"sellerPostCd\": \"12344\",\"sellerPhone\":\"08123456789\",\"sellerCountry\": \"ID\"}}]")
                .userIP("127.0.0.1")
//                .cartData("{\\\"count\\\":1,\\\"item\\\":[{\\\"img_url\\\":\\\"http:\\/\\/www.jamgora.com\\/media\\/avatar\\/noimage.png\\\",\\\"goods_name\\\":\\\"Hoodie\\\",\\\"goods_detail\\\":\\\"Hoodie\\\",\\\"goods_amt\\\":\\\""+amount+"\\\"}]}")
//                .cartData("{\"count\":\"1\",\"item\":[{\"goods_id\":\"BB12345678\",\"goods_detail\":\"BB12345678\",\"goods_name\":\"Pasar Modern\",\"goods_amt\":\"10000\",\"goods_type\":\"Sembako\",\"goods_url\":\"http://merchant.com/cellphones/iphone5s_64g\,\"goods_quantity\":\"1\",\"goods_sellers_id\":\"SEL123\",\"goods_sellers_name\":\"Sellers 1\"}]}")
                .cartData("{\"count\":\"1\",\"item\":[{\"goods_id\":\"BB12345678\",\"goods_detail\":\"BB12345678\",\"goods_name\":\"Pasar Modern\",\"goods_amt\":\"10000\",\"goods_type\":\"Sembako\",\"goods_url\":\"http://merchant.com/cellphones/iphone5s_64g,\",\"goods_quantity\":\"1\",\"goods_sellers_id\":\"SEL123\",\"goods_sellers_name\":\"Sellers 1\"}]}")
                .mitraCd("ALMA")
                .instmntType("2")
                .instmntMon("1")
                .recurrOpt("1")
                .bankCd("")
                .vacctValidDt("")
                .vacctValidTm("")
                .payValidDt("")
                .paymentExpTm("")
                .merFixAcctId("")
                .paymentExpDt("")
                .payValidTm("")
                .shopId("")
                .build();


        NICEPayResponseV2 response =
                V2RedirectService.callV2RedirectRegistration(request,config);
    }


    @Test
    void paymentRedirectV2Test() throws IOException {
        config.setPartnerId(I_MID);
        NICEPayResponseV2 redirectTrans = generateRedirectV2Trans(config);

        Payment request = Payment.builder()
                .tXid(redirectTrans.getTXid())
                .build();

        String paymentTrans = ApiUtils.generateRedirectV2PaymentUrl(request, config);
    }

    @Test
    void redirectV1Test() throws IOException {

        String iMid = config.getPartnerId();
        String reffNo = "REDCARDTEST"+V2_TIMESTAMP;

        Redirect request = Redirect.builder()
                .iMid(iMid)
                .payMethod("01") // mandatory, default is "05" / e-wallet
                .currency("IDR")
                .amt(amount)
                .instmntMon("1")
                .instmntType("1")
                .referenceNo(reffNo)
                .goodsNm("Testing Redirect V1 Card")
                .billingNm("Java Client")
                .billingEmail("mail@test.com")
                .billingPhone("081234567890")
                .billingAddr("Jl. Kesana Kesini no 1")
                .billingCity("Jakarta Selatan")
                .billingState("DKI Jakarta")
                .billingPostCd("12170")
                .billingCountry("Indonesia")
                .deliveryNm("Test redirect")
                .deliveryPhone("081234567890")
                .deliveryAddr("Jl. Kesana Kesini no 1")
                .deliveryCity("Jakarta Selatan")
                .deliveryState("DKI Jakarta")
                .deliveryPostCd("12170")
                .deliveryCountry("Indonesia")
                .callBackUrl(config.getNICEPayBaseUrl() + "IONPAY_CLIENT/paymentResult.jsp")
                .dbProcessUrl("https://webhook.site/7000e1a5-3df5-41d8-979c-2a4ae6f3efb3")
                .vat("0")
                .fee("0")
                .notaxAmt("0")
                .description("Testing V1 Redirect Card with Java client")
                .userIP("127.0.0.1")
                .shopId("NICEPAY")
                .merchantTokenV1(iMid, reffNo, amount, MERCHANT_KEY)
                .cartData("{}")
                .recurrOpt("2")
                .build();

        NICEPayResponseV1 responseV1 = V1ProfessionalService.callRegistration(request, config);

        if (responseV1.getData().getResultCd().equalsIgnoreCase("0000")){

//            set displayChangeButton true if you want to display change payment method button
//            set displayBackLink true if you want to provide back to merchant site link
            String paymentUrlWithChangeButton = ApiUtils.generateRedirectV1PaymentUrl(responseV1, true, true);
        }

    }
    @Test
    void paymentRedirectV2TestAws() throws IOException {
        config = configCloud;

        config.setPartnerId(I_MID);
        NICEPayResponseV2 redirectTrans = generateRedirectV2Trans(config);

        Payment request = Payment.builder()
                .tXid(redirectTrans.getTXid())
                .build();

        String paymentTrans = ApiUtils.generateRedirectV2PaymentUrl(request, config);
    }



    NICEPayResponseV2 generateRedirectV2Trans(NICEPay config) throws IOException
    {
        String timeStamp = V2_TIMESTAMP;
        String iMid = config.getPartnerId();
        String reffNo = "regRedirectV2"+timeStamp;
        String amount = "10000";

        Redirect request = Redirect.builder()
                .timeStamp(timeStamp)
                .iMid(iMid)
                .payMethod("00")
                .currency("IDR")
                .amt(amount)
                .referenceNo(reffNo)
                .goodsNm("Test Lib Java Redirect Regist")
                .billingNm("Java Test")
                .billingPhone("081234567890")
                .billingEmail("email@merchant.com")
                .billingCity("Jakarta Selatan")
                .billingState("DKI Jakarta")
                .billingPostCd("12345")
                .billingCountry("Indonesia")
                .deliveryNm("Java Test")
                .deliveryPhone("081234567890")
                .deliveryCity("Jakarta Selatan")
                .deliveryState("DKI Jakarta")
                .deliveryPostCd("12345")
                .deliveryCountry("Indonesia")
                .dbProcessUrl("https://merchant.com/dbProcessUrl")
                .callBackUrl(config.getNICEPayBaseUrl() + "IONPAY_CLIENT/paymentResult.jsp")
                .vat("")
                .fee("")
                .notaxAmt("")
                .description("Test transaction Java Lib")
                .merchantToken(timeStamp, iMid, reffNo, amount, MERCHANT_KEY)
                .reqTm("")
                .reqDt("")
                .reqDomain("https://www.merchant.com")
                .reqServerIP("127.0.0.1")
                .reqClientVer("")
                .userIP("127.0.0.1")
                .userSessionID("697D6922C961070967D3BA1BA5699C2C")
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML,like Gecko) Chrome/60.0.3112.101 Safari/537.36")
                .userLanguage("ko-KR,en-US;q=0.8,ko;q=0.6,en;q=0.4")
                .cartData("")
                .sellers("[{\"sellersId\": \"SEL123\",\"sellersNm\": \"Sellers 1\",\"sellersEmail\":\"sellers@test.com\",\"sellersAddress\": {\"sellerNm\": \"Sellers\",\"sellerLastNm\": \"1\",\"sellerAddr\": \"jalan berbangsa 1\",\"sellerCity\":\"Jakarta Barat\",\"sellerPostCd\": \"12344\",\"sellerPhone\":\"08123456789\",\"sellerCountry\": \"ID\"}}]")
                .userIP("127.0.0.1")
//                .cartData("{\\\"count\\\":1,\\\"item\\\":[{\\\"img_url\\\":\\\"http:\\/\\/www.jamgora.com\\/media\\/avatar\\/noimage.png\\\",\\\"goods_name\\\":\\\"Hoodie\\\",\\\"goods_detail\\\":\\\"Hoodie\\\",\\\"goods_amt\\\":\\\""+amount+"\\\"}]}")
//                .cartData("{\"count\":\"1\",\"item\":[{\"goods_id\":\"BB12345678\",\"goods_detail\":\"BB12345678\",\"goods_name\":\"Pasar Modern\",\"goods_amt\":\"10000\",\"goods_type\":\"Sembako\",\"goods_url\":\"http://merchant.com/cellphones/iphone5s_64g\,\"goods_quantity\":\"1\",\"goods_sellers_id\":\"SEL123\",\"goods_sellers_name\":\"Sellers 1\"}]}")
                .cartData("{\"count\":\"1\",\"item\":[{\"goods_id\":\"BB12345678\",\"goods_detail\":\"BB12345678\",\"goods_name\":\"Pasar Modern\",\"goods_amt\":\"10000\",\"goods_type\":\"Sembako\",\"goods_url\":\"http://merchant.com/cellphones/iphone5s_64g,\",\"goods_quantity\":\"1\",\"goods_sellers_id\":\"SEL123\",\"goods_sellers_name\":\"Sellers 1\"}]}")
                .mitraCd("ALMA")
                .instmntType("2")
                .instmntMon("1")
                .recurrOpt("1")
                .bankCd("")
                .vacctValidDt("")
                .vacctValidTm("")
                .payValidDt("")
                .paymentExpTm("")
                .merFixAcctId("")
                .paymentExpDt("")
                .payValidTm("")
                .shopId("")
                .build();


        return V2RedirectService.callV2RedirectRegistration(request,config);
    }

}
