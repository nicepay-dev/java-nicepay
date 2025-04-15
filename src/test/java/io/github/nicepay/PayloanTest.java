package io.github.nicepay;

import io.github.nicepay.data.model.Payloan;
import io.github.nicepay.data.model.Payment;
import io.github.nicepay.data.response.v2.NICEPayResponseV2;
import io.github.nicepay.service.v2.V2PayloanService;
import io.github.nicepay.utils.ApiUtils;
import io.github.nicepay.utils.NICEPay;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.UUID;

import static io.github.nicepay.data.TestingConstants.*;

class PayloanTest {
        private static NICEPay config;
        private static NICEPay configCloud;

        @BeforeAll
        public  static void setUp() {
                config =NICEPay.builder()
                        .isProduction(false)
                        .isCloudServer(false)
                        .build();

                configCloud =NICEPay.builder()
                        .isProduction(false)
                        .isCloudServer(true)
                        .build();
        }


//        V2

        @Test
        void payloanRegistV2() throws IOException, InterruptedException {

                String timeStamp = V2_TIMESTAMP;
                String reffNo = "regPayloan"+timeStamp;
                String iMid = I_MID;
                String amount = "10000";

                Payloan request = Payloan.builder()
                        .timeStamp(timeStamp)
                        .iMid(iMid)
                        .payMethod("06")
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
                        .cartData( "{\"count\":3,\"item\":[{\"goods_id\":30,\"goods_name\":\"Beanie\",\"goods_type\":\"Accessories\",\"goods_amt\":5000,\"goods_sellers_id\":\"NICEPAY-NamaMerchant\",\"goods_sellers_name\":\"NICEPAYSHOP\",\"goods_quantity\":1,\"goods_url\":\"http://www.nicestore.com/product/beanie/\"},{\"goods_id\":31,\"goods_name\":\"Belt\",\"goods_type\":\"Accessories\",\"goods_amt\":4500,\"goods_sellers_id\":\"NICEPAY-NamaMerchant\",\"goods_sellers_name\":\"NICEPAYSHOP\",\"goods_quantity\":1,\"goods_url\":\"http://www.nicestore.store/product/belt/\"},{\"img_url\":\"http://www.jamgora.com/media/avatar/noimage.png\",\"goods_name\":\"Shipping Fee\",\"goods_id\":\"Shipping for Ref. No. 278\",\"goods_detail\":\"Flat rate\",\"goods_type\":\"Shipping with Flat rate\",\"goods_amt\":\"500\",\"goods_sellers_id\":\"NICEPAY-NamaMerchant\",\"goods_sellers_name\":\"NICEPAYSHOP\",\"goods_quantity\":\"1\",\"goods_url\":\"https://wwww.nicestore.store\"}]}")
                        .sellers("[{\"sellersId\":\"NICEPAY-NamaMerchant\",\"sellersNm\":\"NICEPAYSHOP\",\"sellersUrl\":\"http://nicestore.store/product/beanie/\",\"sellersEmail\":\"Nicepay@nicepay.co.id\",\"sellersAddress\":{\"sellerNm\":\"NICEPAYSHOP\",\"sellerLastNm\":\"NICEPAYSHOP\",\"sellerAddr\":\"Jln. Kasablanka Kav 88\",\"sellerCity\":\"Jakarta\",\"sellerPostCd\":\"14350\",\"sellerPhone\":\"082111111111\",\"sellerCountry\":\"ID\"}}]")
                        .mitraCd("KDVI")
                        .instmntType("2")
                        .instmntMon("1")
                        .build();

                NICEPayResponseV2 response = V2PayloanService.callV2PayloanRegistration(request,config);

        }

        @Test
        void payloanRegistV2Cloud() throws IOException, InterruptedException {

                config = configCloud;

                String timeStamp = V2_TIMESTAMP;
                String reffNo = "regPayloan"+timeStamp;
                String iMid = I_MID;
                String amount = "10000";

                Payloan request = Payloan.builder()
                        .timeStamp(timeStamp)
                        .iMid(iMid)
                        .payMethod("06")
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
                        .cartData( "{\"count\":3,\"item\":[{\"goods_id\":30,\"goods_name\":\"Beanie\",\"goods_type\":\"Accessories\",\"goods_amt\":5000,\"goods_sellers_id\":\"NICEPAY-NamaMerchant\",\"goods_sellers_name\":\"NICEPAYSHOP\",\"goods_quantity\":1,\"goods_url\":\"http://www.nicestore.com/product/beanie/\"},{\"goods_id\":31,\"goods_name\":\"Belt\",\"goods_type\":\"Accessories\",\"goods_amt\":4500,\"goods_sellers_id\":\"NICEPAY-NamaMerchant\",\"goods_sellers_name\":\"NICEPAYSHOP\",\"goods_quantity\":1,\"goods_url\":\"http://www.nicestore.store/product/belt/\"},{\"img_url\":\"http://www.jamgora.com/media/avatar/noimage.png\",\"goods_name\":\"Shipping Fee\",\"goods_id\":\"Shipping for Ref. No. 278\",\"goods_detail\":\"Flat rate\",\"goods_type\":\"Shipping with Flat rate\",\"goods_amt\":\"500\",\"goods_sellers_id\":\"NICEPAY-NamaMerchant\",\"goods_sellers_name\":\"NICEPAYSHOP\",\"goods_quantity\":\"1\",\"goods_url\":\"https://wwww.nicestore.store\"}]}")
                        .sellers("[{\"sellersId\":\"NICEPAY-NamaMerchant\",\"sellersNm\":\"NICEPAYSHOP\",\"sellersUrl\":\"http://nicestore.store/product/beanie/\",\"sellersEmail\":\"Nicepay@nicepay.co.id\",\"sellersAddress\":{\"sellerNm\":\"NICEPAYSHOP\",\"sellerLastNm\":\"NICEPAYSHOP\",\"sellerAddr\":\"Jln. Kasablanka Kav 88\",\"sellerCity\":\"Jakarta\",\"sellerPostCd\":\"14350\",\"sellerPhone\":\"082111111111\",\"sellerCountry\":\"ID\"}}]")
                        .mitraCd("KDVI")
                        .instmntType("2")
                        .instmntMon("1")
                        .build();

                NICEPayResponseV2 response = V2PayloanService.callV2PayloanRegistration(request,config);

        }


        @Test
        void payloanPaymentV2Test() throws IOException, InterruptedException {

                NICEPayResponseV2 payloanTrans = generatePayloanTrans(config);
                String tXid = payloanTrans.getTXid();
                String reffNo = payloanTrans.getReferenceNo();
                String amount = payloanTrans.getAmt();
                String timeStamp = V2_TIMESTAMP;
                String iMid = I_MID;


                Payment request = Payment.builder()
                        .tXid(tXid)
                        .timeStamp(timeStamp)
                        .merchantToken(timeStamp, iMid, reffNo, amount, MERCHANT_KEY)
                        .callBackUrl(config.getNICEPayBaseUrl()+"IONPAY_CLIENT/paymentResult.jsp?order_id="+tXid)
                        .build();

                String response = ApiUtils.generateDirectV2PaymentUrl(request,config);

        }

        @Test
        void payloanPaymentV2TestAws() throws IOException, InterruptedException {

                config.setCloudServer(true);
                NICEPayResponseV2 payloanTrans = generatePayloanTrans(config);
                String tXid = payloanTrans.getTXid();
                String reffNo = payloanTrans.getReferenceNo();
                String amount = payloanTrans.getAmt();
                String timeStamp = V2_TIMESTAMP;
                String iMid = I_MID;


                Payment request = Payment.builder()
                        .tXid(tXid)
                        .timeStamp(timeStamp)
                        .merchantToken(timeStamp, iMid, reffNo, amount, MERCHANT_KEY)
                        .callBackUrl(config.getNICEPayBaseUrl()+"IONPAY_CLIENT/paymentResult.jsp?order_id="+tXid)
                        .build();

                String response = ApiUtils.generateDirectV2PaymentUrl(request,config);

        }

        private NICEPayResponseV2 generatePayloanTrans(NICEPay config) throws IOException, InterruptedException {

                String timeStamp = V2_TIMESTAMP;
                String reffNo = "regPayloan"+timeStamp;
                String iMid = I_MID;
                String amount = "10000";

                Payloan request = Payloan.builder()
                        .timeStamp(timeStamp)
                        .iMid(iMid)
                        .payMethod("06")
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
                        .cartData( "{\"count\":3,\"item\":[{\"goods_id\":30,\"goods_name\":\"Beanie\",\"goods_type\":\"Accessories\",\"goods_amt\":5000,\"goods_sellers_id\":\"NICEPAY-NamaMerchant\",\"goods_sellers_name\":\"NICEPAYSHOP\",\"goods_quantity\":1,\"goods_url\":\"http://www.nicestore.com/product/beanie/\"},{\"goods_id\":31,\"goods_name\":\"Belt\",\"goods_type\":\"Accessories\",\"goods_amt\":4500,\"goods_sellers_id\":\"NICEPAY-NamaMerchant\",\"goods_sellers_name\":\"NICEPAYSHOP\",\"goods_quantity\":1,\"goods_url\":\"http://www.nicestore.store/product/belt/\"},{\"img_url\":\"http://www.jamgora.com/media/avatar/noimage.png\",\"goods_name\":\"Shipping Fee\",\"goods_id\":\"Shipping for Ref. No. 278\",\"goods_detail\":\"Flat rate\",\"goods_type\":\"Shipping with Flat rate\",\"goods_amt\":\"500\",\"goods_sellers_id\":\"NICEPAY-NamaMerchant\",\"goods_sellers_name\":\"NICEPAYSHOP\",\"goods_quantity\":\"1\",\"goods_url\":\"https://wwww.nicestore.store\"}]}")
                        .sellers("[{\"sellersId\":\"NICEPAY-NamaMerchant\",\"sellersNm\":\"NICEPAYSHOP\",\"sellersUrl\":\"http://nicestore.store/product/beanie/\",\"sellersEmail\":\"Nicepay@nicepay.co.id\",\"sellersAddress\":{\"sellerNm\":\"NICEPAYSHOP\",\"sellerLastNm\":\"NICEPAYSHOP\",\"sellerAddr\":\"Jln. Kasablanka Kav 88\",\"sellerCity\":\"Jakarta\",\"sellerPostCd\":\"14350\",\"sellerPhone\":\"082111111111\",\"sellerCountry\":\"ID\"}}]")
                        .mitraCd("KDVI")
                        .instmntType("2")
                        .instmntMon("1")
                        .build();

                return V2PayloanService.callV2PayloanRegistration(request,config);

        }

}
