package io.github.nicepay;

import io.github.nicepay.data.TestingConstants;
import io.github.nicepay.utils.SignatureUtils;
import org.junit.jupiter.api.Test;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class VerifyTest {
    @Test
    void verify() {
        String signatureString =
                "";
        String dataString = "";
        String publicKeyString =
                "";

        boolean isVerify = SignatureUtils.verifySHA256RSA(dataString, publicKeyString, signatureString);
    }


    @Test
    void testGenerateSignature() {

        String dataString = "";
        String privateKey = "";

        String signature = SignatureUtils.signSHA256RSA(dataString, privateKey);
        System.out.println(signature);

    }


    @Test
    void testGenerateSignaturePayment() throws Exception {

        String dataString = "";
        String secretKey = "";


        String hmacAsBase64 = SignatureUtils.hmacSha512encodeBase64(secretKey, dataString);

        System.out.println(hmacAsBase64);

    }
}
