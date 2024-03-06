package com.nicepay.client;

import com.nicepay.model.AccessToken;
import com.nicepay.model.VirtualAccount;
import com.nicepay.service.SnapTokenService;
import com.nicepay.utils.LoggerPrint;
import com.nicepay.response.NICEPayResponse;
import com.nicepay.service.SnapVaService;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.*;

class VirtualAccountTest {
    private static LoggerPrint print = new LoggerPrint();



// return Token Object
    public Object getToken() throws IOException {
        Map<String, String> additionalInfo = new HashMap<>();
        AccessToken accessToken = AccessToken.builder()
                .grantType("client_credentials")
                .additionalInfo(additionalInfo)
                .build();

       return  SnapTokenService.callGetAccessToken(accessToken);


    }

    @Test
    void vaCreate() throws IOException
    {

        NICEPayResponse responseToken = (NICEPayResponse) getToken();
        var accessToken = Optional.ofNullable(responseToken)
                .map(token -> responseToken.getAccessToken())
                .orElseThrow(() -> new IllegalArgumentException("Token is null"));

        VirtualAccount virtualAccount = VirtualAccount.builder()
                .partnerServiceId("")
                .customerNo("")
                .virtualAccountNo("")
                .virtualAccountName("TESTVaName")
                .trxId("TESTTrxId")
                .totalAmount(new HashMap<String, String>() {
                    {
                        put("value","11000.00");
                        put("currency", "IDR");
                    }
                })
                .additionalInfo( new HashMap<String, Object>() {
                    {
                        put("bankCd","BBBA");
                        put("goodsNm", "TESTGoodsNm");
                        put("dbProcessUrl", "https://merchant.com/test");
                        put("vacctValidDt", "");
                        put("vacctValidTm", "");
                        put("msId", "");
                        put("msFee", "");
                        put("msFeeType", "");
                        put("mbFee", "");
                        put("mbFeeType", "");
                    }
                })
                .build();

       NICEPayResponse Result =
               SnapVaService.callGeneratedVA(virtualAccount,accessToken);



    }


//    @Test
//    void VirtualAccountCheckStatus() throws IOException, InterruptedException {
//
//        NICEPayResponse responseToken = (NICEPayResponse) getToken();
//        var accessToken = Optional.ofNullable(responseToken)
//                .map(token -> responseToken.getAccessToken())
//                .orElseThrow(() -> new IllegalArgumentException("Token is null"));
//
//        Map<String, Object> requestData = new InquiryStatus()
//                .partnerServiceId("")
//                .customerNo("")
//                .virtualAccountNo("8625000002002166")
//                .inquiryRequestId("dsfsd43")
//                .totalAmount("11000.00", "IDR")
//                .trxId("TESTTrxId")
//                .tXidVA("NORMALTEST02202403041600418638")
//                .build();
//
////        VirtualAccount virtualaccount = VirtualAccount.builder()
////                .partnerServiceId("")
////                .customerNo("")
////                .virtualAccountNo("8625000002002166")
////                .inquiryRequestId("dsfsd43")
////                .additionalInfo(new HashMap<String, Object>() {
////                    {
//////                        put("totalAmount", totalAmountMap);
////                        put("totalAmount",  new HashMap<String, Object>() {
////                            {
////                                put("value", "11000.00");
////                                put("currency", "IDR");
////                            }
////                        });
////                        put("trxId", "TESTTrxId");
////                        put("tXidVA", "NORMALTEST02202403041600418638");
////                    }
////                })
////                .build();
//
//        NICEPayResponse result = SnapVaService.callServiceVACheckStatus(requestData,accessToken);
//
//    }

}

