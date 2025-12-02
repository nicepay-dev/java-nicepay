package io.github.nicepay.service.v1;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.github.nicepay.api.v1.CardRequestV1;
import io.github.nicepay.api.v1.RequestV1;
import io.github.nicepay.data.model.Card;
import io.github.nicepay.data.response.v1.NICEPayResponseV1;
import io.github.nicepay.utils.*;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class V1CardService extends V1CommonService {

    private static LoggerPrint logger = new LoggerPrint();

    private static final Gson gson = new Gson();


    public static <S> S callCardRedirectRegistration(Card data, NICEPay config) throws IOException {

        RequestV1 cardV1 = ApiUtils.createServiceV1(RequestV1.class, config);

        // Encrypt the merchant token
        data.setMerchantToken(SHA256Util.encrypt(data.getMerchantToken()));

        // Prepare the API call
        Call<ResponseBody> callSync = cardV1.orderRegist(ModelUtils.toMap(data));

        Response<ResponseBody> responseExecute;
        NICEPayResponseV1 nicePayResponse = new NICEPayResponseV1();
        String responseContent = null;

        try {
            // Execute the call and retrieve the response
            responseExecute = callSync.execute();

            // Handle successful response
            if (responseExecute.isSuccessful() && responseExecute.body() != null) {
                responseContent = responseExecute.body().string();
                nicePayResponse = ApiUtils.getApiMessageObject(responseContent, nicePayResponse);
                logger.logInfoResponseV1("Response Card Regist V1: " + responseContent);
            } else {
                // Handle error response
                ResponseBody errorResponse = responseExecute.errorBody();
                if (errorResponse != null) {
                    responseContent = errorResponse.string();
                    logger.logErrorV1("Error Response Card Regist V1: " + responseContent);
                }
            }

            logger.logInfoV1("END CALL V1 CARD REGISTRATION REQUEST");
        } catch (IOException ex) {
            logger.logErrorV1("IOException during V1 Card Registration Request: " + ex.getMessage());
            throw ex; // Re-throw IOException to ensure the caller is aware of the failure
        } catch (Exception ex) {
            logger.logErrorV1("Unexpected error during V1 Card Registration Request: " + ex.getMessage());
            ex.printStackTrace();
        }

        // Return the response as a generic type
        return (S) nicePayResponse;
    }


    public static <S> S callRequestToken(Card data, NICEPay config) throws IOException {

        CardRequestV1 cardV1 = ApiUtils.createServiceV1(CardRequestV1.class, config);

        // Encrypt merchant token
        data.setMerchantToken(SHA256Util.encrypt(data.getMerchantToken()));

        // Prepare API call
        Map<String, Object> map = new HashMap<>();
        map.put("jsonData", gson.toJson(data));

        Call<ResponseBody> callSync = cardV1.onePassToken(map);

        Response<ResponseBody> responseExecute;
        NICEPayResponseV1 nicePayResponse = new NICEPayResponseV1();
        String rawResponse = null;

        try {
            responseExecute = callSync.execute();

            if (responseExecute.isSuccessful() && responseExecute.body() != null) {

                rawResponse = responseExecute.body().string();
                logger.logInfoResponseV1("Raw Response: " + rawResponse);

                // Clean → extract only valid JSON
                String cleanedJson = cleanJson(rawResponse);
                logger.logInfoResponseV1("Cleaned JSON: " + cleanedJson);

                nicePayResponse =
                        gson.fromJson(cleanedJson, NICEPayResponseV1.class);

                logger.logInfoResponseV1("NICEPayResponseV1 : " + gson.toJson(nicePayResponse));

            } else {
                ResponseBody errorResponse = responseExecute.errorBody();
                if (errorResponse != null) {
                    rawResponse = errorResponse.string();
                    logger.logErrorV1("Error Raw Response: " + rawResponse);
                }
            }

            logger.logInfoV1("END CALL V1 CARD REQUEST TOKEN REQUEST");

        } catch (IOException ex) {
            logger.logErrorV1("IOException during V1 Card Request Token Request: " + ex.getMessage());
            throw ex;
        } catch (Exception ex) {
            logger.logErrorV1("Unexpected error during V1 Card Request Token Request: " + ex.getMessage());
            ex.printStackTrace();
        }

        return (S) nicePayResponse;
    }

    private static String cleanJson(String raw) {

        if (raw == null) return "{}";

        raw = raw.trim();

        // 1. Remove surrounding parentheses/slashes: ({ ... })
        if (raw.startsWith("(") && raw.endsWith(")")) {
            raw = raw.substring(1, raw.length() - 1).trim();
        }

        // 2. Extract first JSON object inside ANY garbage
        int start = raw.indexOf("{");
        int end = raw.lastIndexOf("}");

        if (start != -1 && end != -1 && end > start) {
            return raw.substring(start, end + 1).trim();
        }

        // 3. Fallback: return empty JSON instead of breaking mapping
        return "{}";
    }

    public static String generate3DSRequestUrl(Card data, NICEPay config) throws UnsupportedEncodingException {

        String url3DSNicepay = config.getNICEPayBaseUrl() + "nicepay/api/secureVeRequest.do";

        Map<String, String> params = ModelUtils.toMap(data);
        params.put("callbackUrl", data.getCallBackUrl());
        params.remove("callBackUrl");

        String fullUrl = ApiUtils.buildUrl(url3DSNicepay, params);
        logger.logInfoV1("Nicepay 3DS URL V1 : " + fullUrl);

        return fullUrl;
    }


    public static String generateMigsUrl(Card data, NICEPay config) throws UnsupportedEncodingException {

        String url3DSNicepay = config.getNICEPayBaseUrl() + "nicepay/api/migsRequest.do";

        Map<String, String> params = ModelUtils.toMap(data);
        params.put("callbackUrl", data.getCallBackUrl());
        params.remove("callBackUrl");

        String fullUrl = ApiUtils.buildUrl(url3DSNicepay, params);
        logger.logInfoV1("Nicepay MIGS URL V1 : " + fullUrl);

        return fullUrl;
    }

    public static <S> S callRegistration(Card data, NICEPay config) throws IOException {

        logger.logInfoV1("START CALL V1 REGISTRATION CARD REQUEST");
        Gson gson = new Gson();

        RequestV1 requestV1 = ApiUtils.createServiceV1(RequestV1.class, config);

        // Encrypt the merchant token
        data.setMerchantToken(SHA256Util.encrypt(data.getMerchantToken()));

        // Prepare the API call
        Call<NICEPayResponseV1> callSync = requestV1.onePass(ModelUtils.toMap(data));

        Response<NICEPayResponseV1> response;
        NICEPayResponseV1 nicePayResponse = new NICEPayResponseV1();
        ResponseBody errorResponse;
        Object resClient = null;

        try {
            response = callSync.execute();
            nicePayResponse = response.body();
            errorResponse = response.errorBody();

            if (nicePayResponse == null) {
                resClient = errorResponse.string();
            } else {
                resClient = gson.toJson(nicePayResponse);
            }

            logger.logInfoV1("END CALL V1 REGISTRATION CARD REQUEST");

        } catch (IOException ex) {
            logger.logErrorV1("IOException during V1 Registration CARD Request: " + ex.getMessage());
            throw ex; // Re-throw IOException to ensure the caller is aware of the failure
        } catch (Exception ex) {
            logger.logErrorV1("Unexpected error during V1 Registration CARD Request: " + ex.getMessage());
            ex.printStackTrace();
        }

        JsonObject jsonObject = JsonParser.parseString(resClient.toString()).getAsJsonObject();
        logger.logInfoResponseV1("Response Registration CARD V1 :" + new GsonBuilder().setPrettyPrinting().create().toJson(jsonObject));
        // Return the response as a generic type
        return (S) nicePayResponse;
    }


    public static <S> S callRecurringIssueV1(Card data, NICEPay config) throws IOException {

        CardRequestV1 cardV1 = ApiUtils.createServiceV1(CardRequestV1.class, config);

        // Encrypt merchant token
        data.setMerchantToken(SHA256Util.encrypt(data.getMerchantToken()));

        // Prepare API call
        Map<String, Object> map = new HashMap<>();
        map.put("jsonData", gson.toJson(data));

        Call<ResponseBody> callSync = cardV1.recurringToken(map);

        Response<ResponseBody> responseExecute;
        NICEPayResponseV1 nicePayResponse = new NICEPayResponseV1();
        String rawResponse = null;

        try {
            responseExecute = callSync.execute();

            if (responseExecute.isSuccessful() && responseExecute.body() != null) {

                rawResponse = responseExecute.body().string();
                logger.logInfoResponseV1("Raw Response: " + rawResponse);

                // Clean → extract only valid JSON
                String cleanedJson = cleanJson(rawResponse);
                logger.logInfoResponseV1("Cleaned JSON: " + cleanedJson);

                nicePayResponse =
                        gson.fromJson(cleanedJson, NICEPayResponseV1.class);

                logger.logInfoResponseV1("NICEPayResponseV1 : " + gson.toJson(nicePayResponse));

            } else {
                ResponseBody errorResponse = responseExecute.errorBody();
                if (errorResponse != null) {
                    rawResponse = errorResponse.string();
                    logger.logErrorV1("Error Raw Response: " + rawResponse);
                }
            }

            logger.logInfoV1("END CALL V1 CARD RECURRING ISSUE REQUEST");

        } catch (IOException ex) {
            logger.logErrorV1("IOException during V1 Card Recurring Issue Request: " + ex.getMessage());
            throw ex;
        } catch (Exception ex) {
            logger.logErrorV1("Unexpected error during V1 Card Recurring Issue Request: " + ex.getMessage());
            ex.printStackTrace();
        }

        return (S) nicePayResponse;
    }


    public static <S> S callRecurringPayment(Card data, NICEPay config) throws IOException {

        CardRequestV1 cardV1 = ApiUtils.createServiceV1(CardRequestV1.class, config);

        // Encrypt the merchant token
        data.setMerchantToken(SHA256Util.encrypt(data.getMerchantToken()));

        // Prepare the API call
        Call<ResponseBody> callSync = cardV1.recurringTrans(ModelUtils.toMap(data));

        Response<ResponseBody> responseExecute;
        NICEPayResponseV1 nicePayResponse = new NICEPayResponseV1();
        String responseContent = null;

        try {
            // Execute the call and retrieve the response
            responseExecute = callSync.execute();

            // Handle successful response
            if (responseExecute.isSuccessful() && responseExecute.body() != null) {
                responseContent = responseExecute.body().string();
                logger.logInfoResponseV1("Response Card Recurring Payment V1: " + responseContent);

                nicePayResponse = ApiUtils.getApiMessageObject(responseContent, nicePayResponse);
            } else {
                // Handle error response
                ResponseBody errorResponse = responseExecute.errorBody();
                if (errorResponse != null) {
                    responseContent = errorResponse.string();
                    logger.logErrorV1("Error Response Card Recurring Payment V1: " + responseContent);
                }
            }

            logger.logInfoV1("END CALL V1 CARD TOKENIZE REQUEST");
        } catch (IOException ex) {
            logger.logErrorV1("IOException during V1 Card Recurring Payment Request: " + ex.getMessage());
            throw ex; // Re-throw IOException to ensure the caller is aware of the failure
        } catch (Exception ex) {
            logger.logErrorV1("Unexpected error during V1 Recurring Payment Request: " + ex.getMessage());
            ex.printStackTrace();
        }

        // Return the response as a generic type
        return (S) nicePayResponse;
    }


    public static <S> S callTokenizeCard(Card data, NICEPay config) throws IOException {

        CardRequestV1 cardV1 = ApiUtils.createServiceV1(CardRequestV1.class, config);

        // Encrypt the merchant token
        data.setMerchantToken(SHA256Util.encrypt(data.getMerchantToken()));

        // Prepare the API call
        Call<ResponseBody> callSync = cardV1.tokenize(ModelUtils.toMap(data));

        Response<ResponseBody> responseExecute;
        NICEPayResponseV1 nicePayResponse = new NICEPayResponseV1();
        String responseContent = null;

        try {
            // Execute the call and retrieve the response
            responseExecute = callSync.execute();

            // Handle successful response
            if (responseExecute.isSuccessful() && responseExecute.body() != null) {
                responseContent = responseExecute.body().string();
                logger.logInfoResponseV1("Response Card Tokenize V1: " + responseContent);

                nicePayResponse = ApiUtils.getApiMessageObject(responseContent, nicePayResponse);
            } else {
                // Handle error response
                ResponseBody errorResponse = responseExecute.errorBody();
                if (errorResponse != null) {
                    responseContent = errorResponse.string();
                    logger.logErrorV1("Error Response Card Tokenize V1: " + responseContent);
                }
            }

            logger.logInfoV1("END CALL V1 CARD TOKENIZE REQUEST");
        } catch (IOException ex) {
            logger.logErrorV1("IOException during V1 Card Tokenize Request: " + ex.getMessage());
            throw ex; // Re-throw IOException to ensure the caller is aware of the failure
        } catch (Exception ex) {
            logger.logErrorV1("Unexpected error during V1 Card Tokenize Request: " + ex.getMessage());
            ex.printStackTrace();
        }

        // Return the response as a generic type
        return (S) nicePayResponse;
    }

    public static <S> S callCheckToken(Card data, NICEPay config) throws IOException {

        CardRequestV1 cardV1 = ApiUtils.createServiceV1(CardRequestV1.class, config);

        // Encrypt the merchant token
        data.setMerchantToken(SHA256Util.encrypt(data.getMerchantToken()));

        // Prepare the API call
        Call<ResponseBody> callSync = cardV1.checkToken(ModelUtils.toMap(data));

        Response<ResponseBody> responseExecute;
        NICEPayResponseV1 nicePayResponse = new NICEPayResponseV1();
        String responseContent = null;

        try {
            // Execute the call and retrieve the response
            responseExecute = callSync.execute();

            // Handle successful response
            if (responseExecute.isSuccessful() && responseExecute.body() != null) {
                responseContent = responseExecute.body().string();
                logger.logInfoResponseV1("Response Card Check Token V1: " + responseContent);

                nicePayResponse = ApiUtils.getApiMessageObject(responseContent, nicePayResponse);
            } else {
                // Handle error response
                ResponseBody errorResponse = responseExecute.errorBody();
                if (errorResponse != null) {
                    responseContent = errorResponse.string();
                    logger.logErrorV1("Error Response Card Check Token V1: " + responseContent);
                }
            }

            logger.logInfoV1("END CALL V1 CARD TOKENIZE REQUEST");
        } catch (IOException ex) {
            logger.logErrorV1("IOException during V1 Card Tokenize Request: " + ex.getMessage());
            throw ex; // Re-throw IOException to ensure the caller is aware of the failure
        } catch (Exception ex) {
            logger.logErrorV1("Unexpected error during V1 Card Tokenize Request: " + ex.getMessage());
            ex.printStackTrace();
        }

        // Return the response as a generic type
        return (S) nicePayResponse;
    }

    public static <S> S callRemoveToken(Card data, NICEPay config) throws IOException {

        CardRequestV1 cardV1 = ApiUtils.createServiceV1(CardRequestV1.class, config);

        // Encrypt the merchant token
        data.setMerchantToken(SHA256Util.encrypt(data.getMerchantToken()));

        // Prepare the API call
        Call<ResponseBody> callSync = cardV1.removeToken(ModelUtils.toMap(data));

        Response<ResponseBody> responseExecute;
        NICEPayResponseV1 nicePayResponse = new NICEPayResponseV1();
        String responseContent = null;

        try {
            // Execute the call and retrieve the response
            responseExecute = callSync.execute();

            // Handle successful response
            if (responseExecute.isSuccessful() && responseExecute.body() != null) {
                responseContent = responseExecute.body().string();
                logger.logInfoResponseV1("Response Card Remove Token V1: " + responseContent);

                nicePayResponse = ApiUtils.getApiMessageObject(responseContent, nicePayResponse);
            } else {
                // Handle error response
                ResponseBody errorResponse = responseExecute.errorBody();
                if (errorResponse != null) {
                    responseContent = errorResponse.string();
                    logger.logErrorV1("Error Response Card Remove Token V1: " + responseContent);
                }
            }

            logger.logInfoV1("END CALL V1 CARD REMOVE TOKEN REQUEST");
        } catch (IOException ex) {
            logger.logErrorV1("IOException during V1 Card Remove Token Request: " + ex.getMessage());
            throw ex; // Re-throw IOException to ensure the caller is aware of the failure
        } catch (Exception ex) {
            logger.logErrorV1("Unexpected error during V1 Card Remove Token Request: " + ex.getMessage());
            ex.printStackTrace();
        }

        // Return the response as a generic type
        return (S) nicePayResponse;
    }

    public static <S> S callCaptureTrans(Card data, NICEPay config) throws IOException {

        CardRequestV1 cardV1 = ApiUtils.createServiceV1(CardRequestV1.class, config);

        // Encrypt the merchant token
        data.setMerchantToken(SHA256Util.encrypt(data.getMerchantToken()));

        // Prepare the API call
        Call<ResponseBody> callSync = cardV1.captureTrans(ModelUtils.toMap(data));

        Response<ResponseBody> responseExecute;
        NICEPayResponseV1 nicePayResponse = new NICEPayResponseV1();
        String responseContent = null;

        try {
            // Execute the call and retrieve the response
            responseExecute = callSync.execute();

            // Handle successful response
            if (responseExecute.isSuccessful() && responseExecute.body() != null) {
                responseContent = responseExecute.body().string();
                logger.logInfoResponseV1("Response Card Capture Payment V1: " + responseContent);

                nicePayResponse = ApiUtils.getApiMessageObject(responseContent, nicePayResponse);
            } else {
                // Handle error response
                ResponseBody errorResponse = responseExecute.errorBody();
                if (errorResponse != null) {
                    responseContent = errorResponse.string();
                    logger.logErrorV1("Error Response Card Capture Payment V1: " + responseContent);
                }
            }

            logger.logInfoV1("END CALL V1 CARD TOKENIZE REQUEST");
        } catch (IOException ex) {
            logger.logErrorV1("IOException during V1 Capture Payment Request: " + ex.getMessage());
            throw ex; // Re-throw IOException to ensure the caller is aware of the failure
        } catch (Exception ex) {
            logger.logErrorV1("Unexpected error during V1 Capture Payment Request: " + ex.getMessage());
            ex.printStackTrace();
        }

        // Return the response as a generic type
        return (S) nicePayResponse;
    }
}
