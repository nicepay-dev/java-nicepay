package io.github.nicepay;

import io.github.nicepay.utils.SignatureUtils;
import org.junit.jupiter.api.Test;

public class VerifyTest {
    @Test
    void verify() {
        String signatureString = "";
        String dataString = "";
        String publicKeyString = "";

        boolean isVerify = SignatureUtils.verifySHA256RSA(dataString, publicKeyString, signatureString);
    }


    @Test
    void testGenerateSignature() {

        String dataString = "";
        String privateKey = "";

        String signature = SignatureUtils.signSHA256RSA(dataString, privateKey);
        System.out.println(signature);
    }
}
