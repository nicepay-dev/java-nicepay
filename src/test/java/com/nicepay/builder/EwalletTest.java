package com.nicepay.builder;

import com.nicepay.config.NICEPayResponse;
import com.nicepay.service.SnapEwalletService;
import com.nicepay.service.SnapTokenUtilService;
import com.nicepay.utils.LoggerPrint;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.*;

class EwalletTest {

        private static LoggerPrint print = new LoggerPrint() ;

        @Test
        public Object getToken() throws IOException {
                Map<String, String> additionalInfo = new HashMap<>();
                TokenUtil util =  TokenUtil.builder()
                        .grantType("client_credentials")
                        .additionalInfo(additionalInfo)
                        .build();
                return SnapTokenUtilService.callGetToken(util);

        }

        @Test
        void EwalletPayment() throws IOException {
                NICEPayResponse responseToken = (NICEPayResponse) getToken();
                var accessToken = Optional.ofNullable(responseToken)
                .map(token -> responseToken.getAccessToken())
                        .orElseThrow(() -> new IllegalArgumentException("Token is null"));

                Map<String, String> amount = new HashMap<>();
                amount.put("value","1.00");
                amount.put("currency","IDR");

                Map<String,String> additionalInfo = new HashMap<>();
                additionalInfo.put("mitraCd","DANA");
                additionalInfo.put("goodsNm","Testing Ewallet Snap");
                additionalInfo.put("billingNm","Bale");
                additionalInfo.put("billingPhone","089665542347");
                additionalInfo.put("dbProcessUrl","http://ptsv2.com/t/dbProcess/post");
                additionalInfo.put("callBackUrl","https://www.nicepay.co.id/IONPAY_CLIENT/paymentResult.jsp");
                additionalInfo.put("msId","data");
                additionalInfo.put("cartData","{\"count\":\"2\",\"item\":[{\"img_url\":\"http://img.aaa.com/ima1.jpg\",\"goods_name\":\"Item 1 Name\",\"goods_detail\":\"Item 1 Detail\",\"goods_amt\":\"0.00\",\"goods_quantity\":\"1\"},{\"img_url\":\"http://img.aaa.com/ima2.jpg\",\"goods_name\":\"Item 2 Name\",\"goods_detail\":\"Item 2 Detail\",\"goods_amt\":\"1.00\",\"goods_quantity\":\"1\"}]}");

                List<Map<String, String>> urlParamList = new ArrayList<>();
                Map<String, String> urlParam1 = new HashMap<>();
                urlParam1.put("url","https://test2.bi.go.id/v1/test");
                urlParam1.put("type","PAY_NOTIFY");
                urlParam1.put("isDeeplink","Y");
                urlParamList.add(urlParam1);

                Map<String, String> urlParam2 = new HashMap<>();
                urlParam2.put("url","https://test2.bi.go.id/v1/test");
                urlParam2.put("type","PAY_RETURN");
                urlParam2.put("isDeeplink","Y");
                urlParamList.add(urlParam2);

                Ewallet ewallet = Ewallet.builder()
                        .partnerReferenceNo("ref202305081205331683522921")
                        .merchantId("NORMALTEST")
                        .subMerchantId("")
                        .externalStoreId("")
                        .validUpTo("")
                        .pointOfInitiation("Mobile App")
                        .amount(amount)
                        .additionalInfo(additionalInfo)
                        .urlParam(urlParamList)
                        .build();


                NICEPayResponse Result =
                        SnapEwalletService.callServiceEwalletPayment(ewallet,accessToken);

        }

        }
