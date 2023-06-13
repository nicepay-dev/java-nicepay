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
//            hex = Hex.encodeHexString(s);
            hex = Base64.getEncoder().encodeToString(s);
        } catch (Exception e) {
            System.out.println("Error Generate Signature = "+e.getMessage());
        }
        return hex;
    }

    public static String getSignature(String accessToken,String requestBody,String url,String timeStamp,String staticKey) {
        String sign ="";

        String data = "POST:"+url+":"+accessToken+":"+requestBody+":"+timeStamp; //RU4/FMF2KItWkzD9z3vWYSf/RlP9gfoN8rCLQVzpDgAsOu7jwi0sYvzxzO14QtngWAUnwfWP6uD5JGmRanBBXw==
//		String data = "POST:/api/v1.0/transfer-va/create-va:eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJUTklDRVZBMDIzIiwiaXNzIjoiTklDRVBBWSIsIm5hbWUiOiJUTklDRVZBMDIzIiwiZXhwIjoiMjAyMy0wMi0wMVQxNDowNTozOVoifQ==.DMVKpGwypKKZRvX28KkacZdRNWHhr8C4FbrSkuIykjg=:1a2ba59ec7a78d01cddb5468452855778196044efe1e4846a9d0b6303626c27b:2023-02-01T20:50:40+07:00";
        print.logInfo("String Data Sign :"+data);

//        SHA512Util sha512 = new SHA512Util();

        try {
            sign = hmacSha512encodeBase64(staticKey, data);
            print.logInfo("This Signature :"+sign);

        } catch (Exception e) {
            // TODO: handle exception
        }

        return sign;
    }

    public static String hmacSha512encodeBase64(String key, String data) throws Exception {
        Mac sha256_HMAC = Mac.getInstance("HmacSHA512");
        SecretKeySpec secret_key = new SecretKeySpec(key.getBytes(), "HmacSHA512");
        sha256_HMAC.getInstance("HmacSHA512");
        sha256_HMAC.init(secret_key);

        byte[] strbyte = sha256_HMAC.doFinal(data.getBytes("UTF-8"));
//		  byte[] hex = sha256_HMAC.doFinal(data.getBytes("UTF-8"));

        String str = Base64.getEncoder().encodeToString( strbyte );
        return str;
//		  return Hex.encodeHexString(hex);
    }

    public static String sha256EncodeHex( String data) throws Exception {
        MessageDigest sh = MessageDigest.getInstance("SHA-256");
        return Hex.encodeHexString(sh.digest(data.getBytes("UTF-8")));

    }

}
