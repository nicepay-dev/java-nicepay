package io.github.nicepay.utils;

import javax.crypto.Cipher;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class SHA256Util {
	public static String encrypt(String str){
		String SHA = ""; 
		try{
			MessageDigest sh = MessageDigest.getInstance("SHA-256"); 
			sh.update(str.getBytes()); 
			byte byteData[] = sh.digest();
			StringBuffer sb = new StringBuffer(); 
			for(int i = 0 ; i < byteData.length ; i++){
				sb.append(Integer.toString((byteData[i]&0xff) + 0x100, 16).substring(1));
			}
			SHA = sb.toString();
			
		}catch(NoSuchAlgorithmException e){
			e.printStackTrace(); 
			SHA = null; 
		}
		return SHA;
	}
	
	public static String sha256HMACencode(String key, String data) throws Exception {
		  Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
		  SecretKeySpec secret_key = new SecretKeySpec(key.getBytes("UTF-8"), "HmacSHA256");
		  sha256_HMAC.init(secret_key);

		  return Hex.encodeHexString(sha256_HMAC.doFinal(data.getBytes("UTF-8")));
		}
	
	public static String sha256EncodeHex( String data) throws Exception {
		MessageDigest sh = MessageDigest.getInstance("SHA-256");
		return Hex.encodeHexString(sh.digest(data.getBytes("UTF-8")));
		}
	
	public static String hmacSha256encodeBase64(String key, String data) throws Exception {
		  Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
		  SecretKeySpec secret_key = new SecretKeySpec(key.getBytes(), "HmacSHA256");
		  sha256_HMAC.init(secret_key);
		  
		  byte[] strbyte = sha256_HMAC.doFinal(data.getBytes());
		  
		  String str = Base64.getUrlEncoder().encodeToString( strbyte );
		return str.substring(0, str.length()-1);
	}
			
	public static String encBase64String(String inputStr) throws Exception{
		String str = Base64.getUrlEncoder().encodeToString( inputStr.getBytes() );
		return str.substring(0, str.length()-1);
	}

	public static PublicKey getPublicKey(String base64PublicKey){
		PublicKey publicKey = null;
		try{
			byte[] keyBytes = Base64.getDecoder().decode(base64PublicKey);
			X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			publicKey = keyFactory.generatePublic(keySpec);
			return publicKey;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		}
		return publicKey;
	}

	public static String encryptWithPublicKeyString(String data, String key) throws Exception {
		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		cipher.init(Cipher.ENCRYPT_MODE, getPublicKey(key));

		byte[] encryptedBytes = cipher.doFinal(data.getBytes("UTF-8"));

		return Base64.getEncoder().encodeToString(encryptedBytes);
	}
}