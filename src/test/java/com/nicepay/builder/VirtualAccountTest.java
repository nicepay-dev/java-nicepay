package com.nicepay.builder;

import com.nicepay.service.SnapTokenUtilService;
import com.nicepay.utils.LoggerPrint;
import com.nicepay.config.NICEPayResponse;
import com.nicepay.service.SnapVaService;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.*;

class VirtualAccountTest {
    private static LoggerPrint print = new LoggerPrint();

    @Test
    public Object getToken() throws IOException {
        Map<String, String> additionalInfo = new HashMap<>();
        TokenUtil util = TokenUtil.builder()
                .grantType("client_credentials")
                .additionalInfo(additionalInfo)
                .build();
       return  SnapTokenUtilService.callGetToken(util);

    }

    @Test
    void vaCreate() throws IOException
    {
//        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
//        String timeStamp = f.format(new Date());
//        Random rand = new Random();
//        int random = rand.nextInt(100000000);
//
//        String externalID = "OrdNo" + timeStamp.substring(0, 10).replace("-","") + timeStamp.substring(11,19).replace(":","") + random;

        NICEPayResponse responseToken = (NICEPayResponse) getToken();
        var accessToken = Optional.ofNullable(responseToken)
                .map(token -> responseToken.getAccessToken())
                .orElseThrow(() -> new IllegalArgumentException("Token is null"));
        Map<String, String> totalAmount = new HashMap<>();
        totalAmount.put("value","200000.00");
        totalAmount.put("currency","IDR");

        Map<String, String> additionalInfo = new HashMap<>();
        additionalInfo.put("bankCd","BBBA");
        additionalInfo.put("goodsNm","TESTGoodsNm");
        additionalInfo.put("dbProcessUrl","https://merchant.com/test");
        additionalInfo.put("vacctValidDt","");
        additionalInfo.put("vacctValidTm","");
        additionalInfo.put("msId","");
        additionalInfo.put("msFee","");
        additionalInfo.put("msFeeType","");
        additionalInfo.put("mbFee","");
        additionalInfo.put("mbFeeType","");

        VirtualAccount virtualAccount = VirtualAccount.builder()
                .partnerServiceId("TESTPartner")
                .customerNo("")
                .virtualAccountNo("")
                .virtualAccountName("TESTVaName")
                .trxId("TESTTrxId")
                .totalAmount(totalAmount)
                .additionalInfo(additionalInfo)
                .build();

       NICEPayResponse Result =
               SnapVaService.callServiceVA(virtualAccount,accessToken);

//        print.logInfoResponse("Response getData :" +Result.getVirtualAccountData());


    }
    }

