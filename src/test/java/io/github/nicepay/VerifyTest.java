package io.github.nicepay;

import io.github.nicepay.data.TestingConstants;
import io.github.nicepay.utils.SignatureUtils;
import org.junit.jupiter.api.Test;

public class VerifyTest {
    @Test
    void verify(){
        String signatureString = "Xkts4YNY/aySDxp/FtDvsIizSM6nYau4GNtXbA78CGxefGyivVKYgRNgueKEavPf1hQhORBjcdEyPaSl1FIU3E+Nh6WrtJjWMnP5xg00vujE7MoF2w7wW93S5xOSP5h/DulIWVMv2DowfawfEmvTLmg7e5SvxCwmct/wWUj/ryk=";
        String dataString = "SMILETEST1|2025-02-06T16:24:05+07:00";
        String publicKeyString = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCJyXtRttkdnzHISBOgY7WEasjI+pbqz/86ur8u/VmohirdBcbOKtwG+In5jl3fe8p0uX9CUXyKmpFw3Ff3tq8fWxXbEthzkWO6OQLew+RN7ZMjTPbJiP+3ZPHdztH8nmcq5jzMkoIc4JEd6DBA1tUwg+autx4f6KtCymf+CY3G/QIDAQAB";

        boolean isVerify = SignatureUtils.verifySHA256RSA(dataString, publicKeyString, signatureString);
    }


    @Test
    void testGenerateSignature(){

        String dataString = "SMILETEST1|2025-02-06T16:24:05+07:00";
        String privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAInJe1G22R2fMchIE6BjtYRqyMj6lurP/zq6vy79WaiGKt0Fxs4q3Ab4ifmOXd97ynS5f0JRfIqakXDcV/e2rx9bFdsS2HORY7o5At7D5E3tkyNM9smI/7dk8d3O0fyeZyrmPMySghzgkR3oMEDW1TCD5q63Hh/oq0LKZ/4Jjcb9AgMBAAECgYA4Boz2NPsjaE+9uFECrohoR2NNFVe4Msr8/mIuoSWLuMJFDMxBmHvO+dBggNr6vEMeIy7zsF6LnT32PiImv0mFRY5fRD5iLAAlIdh8ux9NXDIHgyera/PW4nyMaz2uC67MRm7uhCTKfDAJK7LXqrNVDlIBFdweH5uzmrPBn77foQJBAMPCnCzR9vIfqbk7gQaA0hVnXL3qBQPMmHaeIk0BMAfXTVq37PUfryo+80XXgEP1mN/e7f10GDUPFiVw6Wfwz38CQQC0L+xoxraftGnwFcVN1cK/MwqGS+DYNXnddo7Hu3+RShUjCz5E5NzVWH5yHu0E0Zt3sdYD2t7u7HSr9wn96OeDAkEApzB6eb0JD1kDd3PeilNTGXyhtIE9rzT5sbT0zpeJEelL44LaGa/pxkblNm0K2v/ShMC8uY6Bbi9oVqnMbj04uQJAJDIgTmfkla5bPZRR/zG6nkf1jEa/0w7i/R7szaiXlqsIFfMTPimvRtgxBmG6ASbOETxTHpEgCWTMhyLoCe54WwJATmPDSXk4APUQNvX5rr5OSfGWEOo67cKBvp5Wst+tpvc6AbIJeiRFlKF4fXYTb6HtiuulgwQNePuvlzlt2Q8hqQ==";

        String signature = SignatureUtils.signSHA256RSA(dataString, privateKey);
        System.out.println(signature);
    }
}
