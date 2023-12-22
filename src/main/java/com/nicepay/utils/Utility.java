package com.nicepay.utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Properties;

public final class Utility {

    public static String encodeBase64(String serverKey) {
        return "Basic " + Base64.getEncoder().encodeToString((serverKey + ":").getBytes(StandardCharsets.UTF_8));
    }


    public static String getLibraryVersion()  {
        InputStream resourceAsStream = Utility.class.getResourceAsStream("/version.properties");
        Properties properties = new Properties();
        try {
            properties.load(resourceAsStream);
        } catch (IOException e) {
            return "Unable to reach version";
        }
        return (properties.getProperty("version") == null) ? "unable to reach" : properties.getProperty("version");
    }


    public static boolean hasOwnProperty(String jsonString, String jsonKey) {
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            return jsonObject.has(jsonKey);
        } catch (JSONException ex) {
            return false;
        }
    }


}
