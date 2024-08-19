package io.github.nicepay;

import io.github.nicepay.data.TestingConstants;
import io.github.nicepay.response.snap.NICEPayResponse;
import io.github.nicepay.model.AccessToken;
import io.github.nicepay.model.Ewallet;
import io.github.nicepay.service.snap.SnapEwalletService;
import io.github.nicepay.service.snap.SnapTokenService;
import io.github.nicepay.utils.NICEPay;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.*;

class EwalletTest {
        private static NICEPay config;

        private static TestingConstants DATA ;

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
        }

        public Object getToken() throws IOException {
                Map<String, String> additionalInfo = new HashMap<>();
                AccessToken util =  AccessToken.builder()
                        .grantType("client_credentials")
                        .additionalInfo(additionalInfo)
                        .build();
                return SnapTokenService.callGetAccessToken(util,config);

        }

        @Test
        void EwalletPayment() throws IOException, InterruptedException {
                var accessToken = Optional.ofNullable((NICEPayResponse) getToken())
                        .map(NICEPayResponse::getAccessToken)
                        .orElseThrow(() -> new IllegalArgumentException("Token is null"));

                Ewallet ewallet = Ewallet.builder()
                        .partnerReferenceNo("ref202305081205331683522921")
                        .merchantId("IONPAYTEST")
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
                NICEPayResponse response = SnapEwalletService.callServiceEwalletPayment(ewallet,accessToken,config);

        }

}
