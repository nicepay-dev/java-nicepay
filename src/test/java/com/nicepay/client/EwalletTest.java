package com.nicepay.client;

import com.nicepay.response.NICEPayResponse;
import com.nicepay.model.AccessToken;
import com.nicepay.model.Ewallet;
import com.nicepay.service.SnapEwalletService;
import com.nicepay.service.SnapTokenService;
import com.nicepay.utils.LoggerPrint;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.*;

class EwalletTest {
        private static LoggerPrint print = new LoggerPrint() ;

        @Test
        public Object getToken() throws IOException {
                Map<String, String> additionalInfo = new HashMap<>();
                AccessToken util =  AccessToken.builder()
                        .grantType("client_credentials")
                        .additionalInfo(additionalInfo)
                        .build();
                return SnapTokenService.callGetAccessToken(util);

        }

        @Test
        void EwalletPayment() throws IOException, InterruptedException {
                NICEPayResponse responseToken = (NICEPayResponse) getToken();
                var accessToken = Optional.ofNullable(responseToken)
                .map(token -> responseToken.getAccessToken())
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
                                        put("mitraCd","OVOE");
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
                NICEPayResponse Result =
                        SnapEwalletService.callServiceEwalletPayment(ewallet,accessToken);

        }

        }
