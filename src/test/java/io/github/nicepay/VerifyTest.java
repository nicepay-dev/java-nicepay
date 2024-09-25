package io.github.nicepay;

import io.github.nicepay.data.TestingConstants;
import io.github.nicepay.utils.SignatureUtils;
import org.junit.jupiter.api.Test;

public class VerifyTest {
    @Test
    void verify(){
        String signatureString = "";
        String dataString = "TNICEVA023|2024-08-19T17:12:40+07:00";
        String publicKeyString = TestingConstants.PUBLIC_KEY;

        boolean isVerify = SignatureUtils.verifySHA256RSA(dataString, publicKeyString, signatureString);
        System.out.println(isVerify);
    }
}
