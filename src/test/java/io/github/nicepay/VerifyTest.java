package io.github.nicepay;

import io.github.nicepay.data.TestingConstants;
import io.github.nicepay.utils.SignatureUtils;
import org.junit.jupiter.api.Test;

public class VerifyTest {
    @Test
    void verify(){
        String signatureString = "DPhvJEFY6tENPpAI9NtxkJtAgtlyHJupWwNWYgxcj0mK8275dszQ65PpyEKUjiHOzqSYjJc4cpGCmpbwiNlHh6f0CtQeqeqJm0yC2oTNvNf8GmYih8DYWZw841I20gp+N5W/EW7h6tKQ/GcLUb+gxqxwg26y0vV1CjEiLb61g8Q=";
//        String dataString = "TNICEVA023|2024-08-19T17:12:40+07:00";
        String dataString = "IONPAYTEST|2024-12-10T14:14:15+07:00";
        String publicKeyString = TestingConstants.PUBLIC_KEY;

        boolean isVerify = SignatureUtils.verifySHA256RSA(dataString, publicKeyString, signatureString);
        System.out.println(isVerify);
    }


    @Test
    void testGenerateSignature() {

        String dataString = "";
        String privateKey = "";

        String signature = SignatureUtils.signSHA256RSA(dataString, privateKey);
        System.out.println(signature);
    }
}
