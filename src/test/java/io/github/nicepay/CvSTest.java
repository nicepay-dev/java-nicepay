package io.github.nicepay;

import io.github.nicepay.data.TestingConstants;
import io.github.nicepay.data.model.ConvenienceStore;
import io.github.nicepay.data.response.snap.BaseNICEPayResponse;
import io.github.nicepay.data.response.v2.NICEPayResponseV2;
import io.github.nicepay.service.v2.V2CvSService;
import io.github.nicepay.utils.NICEPay;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static io.github.nicepay.data.TestingConstants.*;

class CvSTest<T extends BaseNICEPayResponse> {
    private static NICEPay config;
    private static NICEPay configCloud;
    private static TestingConstants DATA ;

    @BeforeAll
    public  static void setUp() {
        config =NICEPay.builder()
                .isProduction(false)
                .isCloudServer(false)
                .build();


        configCloud = NICEPay.builder()
                .isProduction(false)
                .isCloudServer(true)
                .build();
    }

    @Test
    void cvSRegistTest() throws IOException
    {
        String timeStamp = V2_TIMESTAMP;
        String iMid = I_MID;
        String reffNo = "regCvs"+timeStamp;
        String amount = "1000";

        ConvenienceStore cvs = ConvenienceStore.builder()
                .timeStamp(timeStamp)
                .iMid(iMid)
                .payMethod("03")
                .currency("IDR")
                .amt(amount)
                .referenceNo(reffNo)
                .goodsNm("Test Lib Java Convenience Store")
                .billingNm("Java Test")
                .billingPhone("081234567890")
                .billingEmail("email@merchant.com")
                .billingCity("Jakarta Selatan")
                .billingState("DKI Jakarta")
                .billingPostCd("12345")
                .billingCountry("Indonesia")
                .dbProcessUrl("https://merchant.com/dbProcessUrl")
                .merchantToken(timeStamp, iMid, reffNo, amount, MERCHANT_KEY)
                .userSessionID("697D6922C961070967D3BA1BA5699C2C")
                .userIP("127.0.0.1")
//                .cartData("{\\\"count\\\":1,\\\"item\\\":[{\\\"img_url\\\":\\\"http:\\/\\/www.jamgora.com\\/media\\/avatar\\/noimage.png\\\",\\\"goods_name\\\":\\\"Hoodie\\\",\\\"goods_detail\\\":\\\"Hoodie\\\",\\\"goods_amt\\\":\\\""+amount+"\\\"}]}")
                .mitraCd("ALMA")
                .build();


        NICEPayResponseV2 response =
                V2CvSService.callV2CvSRegistration(cvs,config);
    }

    @Test
    void cvSRegistTestCloud() throws IOException
    {
        config = configCloud;
        String timeStamp = V2_TIMESTAMP;
        String iMid = I_MID;
        String reffNo = "regCvs"+timeStamp;
        String amount = "1000";

        ConvenienceStore cvs = ConvenienceStore.builder()
                .timeStamp(timeStamp)
                .iMid(iMid)
                .payMethod("03")
                .currency("IDR")
                .amt(amount)
                .referenceNo(reffNo)
                .goodsNm("Test Lib Java Convenience Store")
                .billingNm("Java Test")
                .billingPhone("081234567890")
                .billingEmail("email@merchant.com")
                .billingCity("Jakarta Selatan")
                .billingState("DKI Jakarta")
                .billingPostCd("12345")
                .billingCountry("Indonesia")
                .dbProcessUrl("https://merchant.com/dbProcessUrl")
                .merchantToken(timeStamp, iMid, reffNo, amount, MERCHANT_KEY)
                .userSessionID("697D6922C961070967D3BA1BA5699C2C")
                .userIP("127.0.0.1")
//                .cartData("{\\\"count\\\":1,\\\"item\\\":[{\\\"img_url\\\":\\\"http:\\/\\/www.jamgora.com\\/media\\/avatar\\/noimage.png\\\",\\\"goods_name\\\":\\\"Hoodie\\\",\\\"goods_detail\\\":\\\"Hoodie\\\",\\\"goods_amt\\\":\\\""+amount+"\\\"}]}")
                .mitraCd("ALMA")
                .build();


        NICEPayResponseV2 response =
                V2CvSService.callV2CvSRegistration(cvs,config);
    }



}
