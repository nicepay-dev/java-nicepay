package io.github.nicepay;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.nicepay.data.TestingConstants;
import io.github.nicepay.data.model.AccessToken;
import io.github.nicepay.data.model.Ewallet;
import io.github.nicepay.data.model.Payment;
import io.github.nicepay.data.response.snap.NICEPayResponse;
import io.github.nicepay.data.response.v2.NICEPayResponseV2;
import io.github.nicepay.service.snap.SnapEwalletService;
import io.github.nicepay.service.snap.SnapTokenService;
import io.github.nicepay.service.v2.V2EwalletService;
import io.github.nicepay.utils.ApiUtils;
import io.github.nicepay.utils.NICEPay;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static io.github.nicepay.data.TestingConstants.*;

class EwalletTest {
        private static final Logger log = LoggerFactory.getLogger(EwalletTest.class);
        private static NICEPay config;
        private static NICEPay configCloud;

        @BeforeAll
        public  static void setUp() {
                config =NICEPay.builder()
                        .isProduction(false)
                        .isCloudServer(false)
                        .clientSecret(CLIENT_SECRET)
                        .partnerId(PARTNER_ID)
                        .externalID(EXTERNAL_ID)
                        .timestamp(TestingConstants.TIMESTAMP)
                        .privateKey(TestingConstants.PRIVATE_KEY)
                        .build();

                configCloud =NICEPay.builder()
                        .isProduction(false)
                        .isCloudServer(true)
                        .clientSecret(CLOUD_CLIENT_SECRET)
                        .partnerId(I_MID)
                        .externalID(EXTERNAL_ID)
                        .timestamp(TIMESTAMP)
                        .privateKey(CLOUD_PRIVATE_KEY)
                        .build();
        }

        public Object getToken() throws IOException {
                Map<String, String> additionalInfo = new HashMap<>();
                AccessToken util =  AccessToken.builder()
                        .grantType("client_credentials")
                        .additionalInfo(additionalInfo)
                        .build();
                return SnapTokenService.callGetAccessToken(util,configCloud);

        }

        @Test
        void EwalletPayment() throws IOException, InterruptedException {
                configCloud.setCloudServer(false);
//                configCloud.setClientSecret(CLIENT_SECRET);

                var accessToken = Optional.ofNullable((NICEPayResponse) getToken())
                        .map(NICEPayResponse::getAccessToken)
                        .orElseThrow(() -> new IllegalArgumentException("Token is null"));

                Ewallet ewallet = Ewallet.builder()
                        .partnerReferenceNo("ewallet"+V2_TIMESTAMP)
                        .merchantId(configCloud.getPartnerId())
                        .subMerchantId("")
                        .externalStoreId("")
                        .validUpTo("")
                        .pointOfInitiation("Mobile App")
                        .amount("1.00","IDR")
                        .additionalInfo( new HashMap<String, Object>() {
                                {
                                        put("mitraCd","DANA");
                                        put("goodsNm","Testing Ewallet Snap");
                                        put("billingNm","Test Ewallet");
                                        put("billingPhone","089665542347");
                                        put("dbProcessUrl","http://ptsv2.com/t/dbProcess/post");
                                        put("callBackUrl","https://www.nicepay.co.id/IONPAY_CLIENT/paymentResult.jsp");
                                        put("msId","data");
                                        put("cartData","{\"count\":\"2\",\"item\":[{\"img_url\":\"http://img.aaa.com/ima1.jpg\",\"goods_name\":\"Item 1 Name\",\"goods_detail\":\"Item 1 Detail\",\"goods_amt\":\"0.00\",\"goods_quantity\":\"1\"},{\"img_url\":\"http://img.aaa.com/ima2.jpg\",\"goods_name\":\"Item 2 Name\",\"goods_detail\":\"Item 2 Detail\",\"goods_amt\":\"1.00\",\"goods_quantity\":\"1\"}]}");
                                }})
                        .urlParam(new String[][]{
                                        {"https://test2.bi.go.id/v1/test", "PAY_NOTIFY", "Y"},
                                        {"https://test2.bi.go.id/v1/test", "PAY_RETURN", "Y"}
                                }
                        )
                        .build();
                NICEPayResponse response = SnapEwalletService.callServiceEwalletPayment(ewallet,accessToken,configCloud);

        }

        @Test
        void EwalletPaymentCloud() throws IOException, InterruptedException {
                var accessToken = Optional.ofNullable((NICEPayResponse) getToken())
                        .map(NICEPayResponse::getAccessToken)
                        .orElseThrow(() -> new IllegalArgumentException("Token is null"));

                Ewallet ewallet = Ewallet.builder()
                        .partnerReferenceNo("ordEW"+V2_TIMESTAMP)
                        .merchantId(configCloud.getPartnerId())
                        .subMerchantId("")
                        .externalStoreId("")
                        .validUpTo("")
                        .pointOfInitiation("Mobile App")
                        .amount("1.00","IDR")
                        .additionalInfo( new HashMap<String, Object>() {
                                {
                                        put("mitraCd","DANA");
                                        put("goodsNm","Testing Ewallet Snap");
                                        put("billingNm","Test Ewallet");
                                        put("billingPhone","089665542347");
                                        put("dbProcessUrl","http://ptsv2.com/t/dbProcess/post");
                                        put("callBackUrl",configCloud.getNICEPayBaseUrl() + "/IONPAY_CLIENT/paymentResult.jsp");
                                        put("msId","data");
                                        put("cartData","{\"count\":\"2\",\"item\":[{\"img_url\":\"http://img.aaa.com/ima1.jpg\",\"goods_name\":\"Item 1 Name\",\"goods_detail\":\"Item 1 Detail\",\"goods_amt\":\"0.00\",\"goods_quantity\":\"1\"},{\"img_url\":\"http://img.aaa.com/ima2.jpg\",\"goods_name\":\"Item 2 Name\",\"goods_detail\":\"Item 2 Detail\",\"goods_amt\":\"1.00\",\"goods_quantity\":\"1\"}]}");
                                }})
                        .urlParam(new String[][]{
                                        {"https://test2.bi.go.id/v1/test", "PAY_NOTIFY", "Y"},
                                        {"https://test2.bi.go.id/v1/test", "PAY_RETURN", "Y"}
                                }
                        )
                        .build();
                NICEPayResponse response = SnapEwalletService.callServiceEwalletPayment(ewallet,accessToken,configCloud);

        }


//        V2

        @Test
        void ewalletRegistV2DirectTest() throws IOException, InterruptedException {

                String timeStamp = V2_TIMESTAMP;
                String reffNo = "regEWJava"+timeStamp;
                String iMid = I_MID_EWALLET;
                String amount = "10000";

                Ewallet request = Ewallet.builder()
                        .timeStamp(timeStamp)
                        .iMid(iMid)
                        .payMethod("05")
                        .currency("IDR")
                        .amt(amount)
                        .referenceNo(reffNo)
                        .goodsNm("testing native java lib")
                        .billingNm("Java Nicepay")
                        .billingPhone("08123456789")
                        .billingEmail("sampleemail@mail.com")
                        .billingAddr("Jalan Raya Luas")
                        .billingCity("Jakarta")
                        .billingState("DKI Jakarta")
                        .billingPostCd("12170")
                        .billingCountry("Indonesia")
                        .deliveryNm("Nice People")
                        .deliveryPhone("081234567890")
                        .deliveryAddr("Jalan Sebelah Raya")
                        .deliveryCity("Jakarta Selatan")
                        .deliveryState("DKI Jakarta")
                        .deliveryPostCd("12170")
                        .deliveryCountry("Indonesia")
                        .dbProcessUrl("https://merchant.com/api/dbProcessUrl/Notif")
                        .merchantToken(timeStamp, iMid, reffNo, amount, MERCHANT_KEY)
                        .reqDomain("merchant.com")
                        .reqServerIP("127.0.0.1")
                        .reqClientVer("")
                        .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML,like Gecko) Chrome/60.0.3112.101 Safari/537.36")
                        .userIP("127.0.0.1")
                        .userSessionID(UUID.randomUUID().toString())
                        .userLanguage("en")
                        .cartData( "{\"count\":1,\"item\":[{\"img_url\":\"http://www.jamgora.com/media/avatar/noimage.png\",\"goods_name\":\"Hoodie\",\"goods_detail\":\"Hoodie\",\"goods_amt\":\"10000\"}]}")
                        .mitraCd("OVOE")
                        .build();

                NICEPayResponseV2 response = V2EwalletService.callEwalletRegistration(request,config);

        }


        @Test
        void ewalletPaymentV2Test() throws IOException, InterruptedException {


                NICEPayResponseV2 regist = generateEwalletTrans(config);

                String timeStamp = V2_TIMESTAMP;
                String iMid = I_MID_EWALLET;


                Payment request = Payment.builder()
                        .tXid(regist.getTXid())
                        .timeStamp(timeStamp)
                        .merchantToken(timeStamp, iMid, regist.getReferenceNo(), regist.getAmt(), MERCHANT_KEY)
                        .callBackUrl(config.getNICEPayBaseUrl()+"IONPAY_CLIENT/paymentResult.jsp?order_id="+regist.getTXid())
                        .build();

                log.info(new ObjectMapper().writeValueAsString(request));
                String response = ApiUtils.generateDirectV2PaymentUrl(request,config);

        }

        @Test
        void ewalletPaymentV2TestAws() throws IOException, InterruptedException {

                config.setCloudServer(true);
                NICEPayResponseV2 regist = generateEwalletTrans(config);

                String timeStamp = V2_TIMESTAMP;
                String iMid = I_MID_EWALLET;


                Payment request = Payment.builder()
                        .tXid(regist.getTXid())
                        .timeStamp(timeStamp)
                        .merchantToken(timeStamp, iMid, regist.getReferenceNo(), regist.getAmt(), MERCHANT_KEY)
                        .callBackUrl(config.getNICEPayBaseUrl()+"IONPAY_CLIENT/paymentResult.jsp?order_id="+regist.getTXid())
                        .build();

                String response = ApiUtils.generateDirectV2PaymentUrl(request,config);

        }

        NICEPayResponseV2 generateEwalletTrans(NICEPay config) throws IOException {

                String timeStamp = V2_TIMESTAMP;
                String reffNo = "regEWJava"+timeStamp;
                String iMid = I_MID_EWALLET;
                String amount = "100";

                Ewallet request = Ewallet.builder()
                        .timeStamp(timeStamp)
                        .iMid(iMid)
                        .payMethod("05")
                        .currency("IDR")
                        .amt(amount)
                        .referenceNo(reffNo)
                        .goodsNm("testing native java lib")
                        .billingNm("Java Nicepay")
                        .billingPhone("081513982343")
                        .billingEmail("sampleemail@mail.com")
                        .billingAddr("Jalan Raya Luas")
                        .billingCity("Jakarta")
                        .billingState("DKI Jakarta")
                        .billingPostCd("12170")
                        .billingCountry("Indonesia")
                        .deliveryNm("Nice People")
                        .deliveryPhone("081234567890")
                        .deliveryAddr("Jalan Sebelah Raya")
                        .deliveryCity("Jakarta Selatan")
                        .deliveryState("DKI Jakarta")
                        .deliveryPostCd("12170")
                        .deliveryCountry("Indonesia")
                        .dbProcessUrl("https://merchant.com/api/dbProcessUrl/Notif")
                        .merchantToken(timeStamp, iMid, reffNo, amount, MERCHANT_KEY)
                        .reqDomain("merchant.com")
                        .reqServerIP("127.0.0.1")
                        .reqClientVer("")
                        .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML,like Gecko) Chrome/60.0.3112.101 Safari/537.36")
                        .userIP("127.0.0.1")
                        .userSessionID(UUID.randomUUID().toString())
                        .userLanguage("en")
                        .cartData("{\\\"count\\\":1,\\\"item\\\":[{\\\"img_url\\\":\\\"http:\\/\\/www.jamgora.com\\/media\\/avatar\\/noimage.png\\\",\\\"goods_name\\\":\\\"Hoodie\\\",\\\"goods_detail\\\":\\\"Hoodie\\\",\\\"goods_amt\\\":\\\""+amount+"\\\"}]}")
                        .mitraCd("OVOE")
                        .build();

                return V2EwalletService.callEwalletRegistration(request,config);

        }
}
