package com.nicepay.utils;

import org.apache.commons.codec.binary.Hex;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

public class SignatureUtils {
    private static LoggerPrint print = new LoggerPrint();

    public static String signSHA256RSA(String stringTosign,String privateKey) {
        byte[] s = null;
        String hex = "";
        try {
            byte[] b1 = Base64.getDecoder().decode(privateKey);
            KeyFactory kf = KeyFactory.getInstance("RSA");
            PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(b1);
            Signature privateSignature = Signature.getInstance("SHA256withRSA");
            privateSignature.initSign(kf.generatePrivate(spec));
            privateSignature.update(stringTosign.getBytes(StandardCharsets.UTF_8));
            s = privateSignature.sign();
            hex = Base64.getEncoder().encodeToString(s);
        } catch (Exception e) {
            print.logError("Error Generate Signature = "+e.getMessage());
        }
        return hex;
    }

    public static String getSignature(String httpMethod,String accessToken,String requestBody,String url,String timeStamp,String staticKey) {
        String sign ="";

        String data = httpMethod+":"+url+":"+accessToken+":"+requestBody+":"+timeStamp; //RU4/FMF2KItWkzD9z3vWYSf/RlP9gfoN8rCLQVzpDgAsOu7jwi0sYvzxzO14QtngWAUnwfWP6uD5JGmRanBBXw==
        print.logInfo("String Data Sign :"+data);

        try {
            sign = hmacSha512encodeBase64(staticKey, data);
            print.logInfo("This Signature :"+sign);

        } catch (Exception e) {
            print.logError("Error Generate Signature = "+e.getMessage());

        }

        return sign;
    }

    public static String hmacSha512encodeBase64(String key, String data) throws Exception {
        Mac sha256_HMAC = Mac.getInstance("HmacSHA512");
        SecretKeySpec secret_key = new SecretKeySpec(key.getBytes(), "HmacSHA512");
        sha256_HMAC.getInstance("HmacSHA512");
        sha256_HMAC.init(secret_key);

        byte[] strbyte = sha256_HMAC.doFinal(data.getBytes("UTF-8"));

        String str = Base64.getEncoder().encodeToString( strbyte );
        return str;
    }

    public static String sha256EncodeHex( String data) throws Exception {
        MessageDigest sh = MessageDigest.getInstance("SHA-256");
        return Hex.encodeHexString(sh.digest(data.getBytes("UTF-8")));

    }

}
