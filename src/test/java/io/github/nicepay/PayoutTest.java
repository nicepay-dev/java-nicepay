package io.github.nicepay;

import io.github.nicepay.data.TestingConstants;
import io.github.nicepay.data.model.AccessToken;
import io.github.nicepay.data.model.Payout;
import io.github.nicepay.data.response.snap.NICEPayResponse;
import io.github.nicepay.service.snap.SnapPayoutService;
import io.github.nicepay.service.snap.SnapTokenService;
import io.github.nicepay.utils.NICEPay;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

import static io.github.nicepay.data.TestingConstants.V2_TIMESTAMP;

class PayoutTest {
    private static NICEPay config;
    private static NICEPay configCloud;
    private static TestingConstants DATA ;
    private static NICEPay config2;
    private static NICEPay config3;
    @BeforeAll
    public  static void setUp() {
        config =NICEPay.builder()
                .isProduction(false)
                .clientSecret(TestingConstants.CLOUD_CLIENT_SECRET)
                .partnerId(TestingConstants.I_MID)
                .externalID(TestingConstants.EXTERNAL_ID)
                .timestamp(TestingConstants.TIMESTAMP)
                .privateKey(TestingConstants.PRIVATE_KEY)
                .build();

        configCloud =NICEPay.builder()
                .isProduction(false)
                .isCloudServer(true)
                .clientSecret(TestingConstants.CLOUD_CLIENT_SECRET)
                .partnerId(TestingConstants.I_MID)
                .externalID(TestingConstants.EXTERNAL_ID)
                .timestamp(TestingConstants.TIMESTAMP)
                .privateKey(TestingConstants.CLOUD_PRIVATE_KEY)
                .build();

        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
        String TIMESTAMP = f.format(new Date());
        Random rand = new Random();
        int random = rand.nextInt(10000);
        String externalId = "OrdNo" + TIMESTAMP.substring(0, 10).replace("-","") + TIMESTAMP.substring(11,19).replace(":","") + random;
        config2 =NICEPay.builder()
                .isProduction(false)
                .clientSecret(TestingConstants.CLIENT_SECRET)
                .partnerId(TestingConstants.PARTNER_ID)
                .externalID(externalId)
                .timestamp(TestingConstants.TIMESTAMP)
                .privateKey(TestingConstants.PRIVATE_KEY)
                .build();
        int random2 = rand.nextInt(10000);
        String externalId2 = "OrdNo" + TIMESTAMP.substring(0, 10).replace("-","") + TIMESTAMP.substring(11,19).replace(":","") + random2;
        config3 =NICEPay.builder()
                .isProduction(false)
                .clientSecret(TestingConstants.CLIENT_SECRET)
                .partnerId(TestingConstants.PARTNER_ID)
                .externalID(externalId2)
                .timestamp(TestingConstants.TIMESTAMP)
                .privateKey(TestingConstants.PRIVATE_KEY)
                .build();
    }

    public Object getToken() throws IOException {
        Map<String, String> additionalInfo = new HashMap<>();
        AccessToken token = AccessToken.builder()
                .grantType("client_credentials")
                .additionalInfo(additionalInfo)
                .build();
        return  SnapTokenService.callGetAccessToken(token,config);
    }

    public Object getToken(NICEPay config) throws IOException {
        Map<String, String> additionalInfo = new HashMap<>();
        AccessToken token = AccessToken.builder()
                .grantType("client_credentials")
                .additionalInfo(additionalInfo)
                .build();
        return  SnapTokenService.callGetAccessToken(token,config);
    }

    @Test
    void payOutregist() throws IOException
    {
        var accessToken = Optional.ofNullable((NICEPayResponse) getToken())
                .map(NICEPayResponse::getAccessToken)
                .orElseThrow(() -> new IllegalArgumentException("Token is null"));


        Payout payout = Payout.builder()
                .merchantId(TestingConstants.I_MID)
                .beneficiaryAccountNo("5345000060")
                .beneficiaryName("IONPAY NETWORKS")
                .beneficiaryPhone("08123456789")
                .beneficiaryCustomerResidence("1")
                .beneficiaryCustomerType("1")
                .beneficiaryPostalCode("123456")
                .payoutMethod ("0")
                .beneficiaryBankCode ("BDIN")
                .amount("11000.00","IDR")
                .partnerReferenceNo ("2020102900000000000001")
                .description ("This is test Request")
                .deliveryName("Ciki")
                .deliveryId("1234567890234512")
                .reservedDt("")
                .reservedTm("")
                .additionalInfo("")
                .build();

        NICEPayResponse response =
                SnapPayoutService.callServicePayoutRegist(payout,accessToken,config);
    }

    @Test
    void payOutregistCloud() throws IOException
    {
         NICEPay config = configCloud;

        var accessToken = Optional.ofNullable((NICEPayResponse) getToken(config))
                .map(NICEPayResponse::getAccessToken)
                .orElseThrow(() -> new IllegalArgumentException("Token is null"));


        Payout payout = Payout.builder()
                .merchantId(config.getPartnerId())
                .beneficiaryAccountNo("800152779200")
                .beneficiaryName("IONPAY NETWORKS")
                .beneficiaryPhone("08123456789")
                .beneficiaryCustomerResidence("1")
                .beneficiaryCustomerType("1")
                .beneficiaryPostalCode("123456")
                .payoutMethod ("0")
                .beneficiaryBankCode ("BNIA")
                .amount("11000.00","IDR")
                .partnerReferenceNo ("payout"+V2_TIMESTAMP)
                .description ("This is test Request")
                .deliveryName("Ciki")
                .deliveryId("1234567890234512")
                .reservedDt("")
                .reservedTm("")
                .additionalInfo("")
                .build();

        NICEPayResponse response =
                SnapPayoutService.callServicePayoutRegist(payout,accessToken,config);
    }


    @Test
    void payOutApprove() throws IOException {
        var accessToken = Optional.ofNullable((NICEPayResponse) getToken())
                .map(NICEPayResponse::getAccessToken)
                .orElseThrow(() -> new IllegalArgumentException("Token is null"));

        NICEPayResponse responseRegist = registPayout(accessToken);

        Payout payout = Payout.builder()
                .merchantId(TestingConstants.I_MID)
                .originalReferenceNo(responseRegist.getOriginalReferenceNo())
                .originalPartnerReferenceNo(responseRegist.getPartnerReferenceNo())
                .build();

        NICEPayResponse response =
                SnapPayoutService.callServicePayoutApprove(payout,accessToken,config2);

    }

    @Test
    void payOutCheckBalance() throws IOException {
        var accessToken = Optional.ofNullable((NICEPayResponse) getToken())
                .map(NICEPayResponse::getAccessToken)
                .orElseThrow(() -> new IllegalArgumentException("Token is null"));

        Payout payout = Payout.builder()
                .accountNo(TestingConstants.I_MID)
                .additionalInfo("")
                .build();

        NICEPayResponse response =
                SnapPayoutService.callServicePayoutCheckBalance(payout,accessToken,config2);

        System.out.println("Response mess :" + response.getResponseMessage() );
    }

    @Test
    void payOutCheckBalanceCloud() throws IOException {
        var accessToken = Optional.ofNullable((NICEPayResponse) getToken())
                .map(NICEPayResponse::getAccessToken)
                .orElseThrow(() -> new IllegalArgumentException("Token is null"));

        Payout payout = Payout.builder()
                .accountNo(TestingConstants.PARTNER_ID)
                .additionalInfo("")
                .build();

        NICEPayResponse response =
                SnapPayoutService.callServicePayoutCheckBalance(payout,accessToken,config);

        System.out.println("Response mess :" + response.getResponseMessage() );
    }

    NICEPayResponse registPayout(String accessToken) throws IOException {

        Payout payout = Payout.builder()
                .merchantId(TestingConstants.I_MID)
                .beneficiaryAccountNo("5345000060")
                .beneficiaryName("IONPAY NETWORKS")
                .beneficiaryPhone("08123456789")
                .beneficiaryCustomerResidence("1")
                .beneficiaryCustomerType("1")
                .beneficiaryPostalCode("123456")
                .payoutMethod ("0")
                .beneficiaryBankCode ("BDIN")
                .amount("11000.00","IDR")
                .partnerReferenceNo ("2020102900000000000001")
                .description ("This is test Request")
                .deliveryName("Ciki")
                .deliveryId("1234567890234512")
                .reservedDt("20240921")
                .reservedTm("125222")
                .additionalInfo("")
                .build();

        return SnapPayoutService.callServicePayoutRegist(payout,accessToken,config);
    }

}