package io.github.nicepay.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.GsonBuilder;
import okhttp3.*;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Invocation;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

public class ApiUtils {

    private static LoggerPrint print = new LoggerPrint();

    private static ObjectMapper mapper = new ObjectMapper();

    private static Retrofit.Builder builder
            = new Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            ;

    private static Retrofit api ;

    private static OkHttpClient.Builder httpClient
            = new OkHttpClient
            .Builder()
            .connectTimeout(Duration.ofMillis(10000));

    public static OkHttpClient.Builder httpClientTimeout
            = new OkHttpClient
            .Builder()
            .connectTimeout(1, TimeUnit.MILLISECONDS)    // Set a very low connection timeout
            .readTimeout(1, TimeUnit.MILLISECONDS)       // Set a very low read timeout
            .writeTimeout(1, TimeUnit.MILLISECONDS);

    private static HttpLoggingInterceptor logging
            = new HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BASIC);

    public static <S> S createService(Class<S> serviceClass,final String grandType,final String accessToken,String data,NICEPay config) {
            httpClient.interceptors().clear();
            httpClient.addInterceptor(chain -> {
                    Request original = chain.request();
                    Request.Builder builder = null;
                    print.logInfo("generate "+"fullUrl :" +chain.request().url());
                    String url = chain.request().url().encodedPath().replace("/nicepay","");
                      Optional.ofNullable(grandType)
                            .ifPresentOrElse(value -> print.logInfo("getToken "+"pathUrl :" +url),
                                    () -> print.logInfo("generate "+"pathUrl :" +url)
                            );

                    try {
                        String httpMethod = original.method();
                        builder = original.newBuilder()
                                .headers(getHeaders(httpMethod,grandType,accessToken, data,url,config));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Response response = chain.proceed(builder.build());
                    String bodyString = response.body().string();
                    MediaType contentType = response.body().contentType();
                    ResponseBody responseBody  = ResponseBody.create(bodyString, contentType);
                    print.logInfoBody("Request Data " + new GsonBuilder().setPrettyPrinting().create().toJson(original.tag(Invocation.class).arguments().get(0)));

                    return response.newBuilder().body(responseBody).build();
                });
                builder.client(httpClient.build());
                builder.baseUrl(config.getNICEPayBaseUrl());
                api = builder.build();

        return api.create(serviceClass);
    }

    public static <S> S createServiceConfig(Class<S> serviceClass,final String grandType,final String accessToken,String data,NICEPay config) {
        httpClient.interceptors().clear();
        httpClient.addInterceptor(chain -> {
            Request original = chain.request();
            Request.Builder builder = null;
            print.logInfo("generate "+"fullUrl :" +chain.request().url());
            String url = chain.request().url().encodedPath().replace("/nicepay","");
            Optional.ofNullable(grandType)
                    .ifPresentOrElse(value -> print.logInfo("getToken "+"pathUrl :" +url),
                            () -> print.logInfo("generate "+"pathUrl :" +url)
                    );

            try {
                String httpMethod = original.method();
                builder = original.newBuilder()
                        .headers(getHeaders(httpMethod,grandType,accessToken, data,url,config));
            } catch (Exception e) {
                e.printStackTrace();
            }
            Response response = chain.proceed(builder.build());
            String bodyString = response.body().string();
            MediaType contentType = response.body().contentType();
            ResponseBody responseBody  = ResponseBody.create(bodyString, contentType);
            print.logInfoBody("Request Data " + new GsonBuilder().setPrettyPrinting().create().toJson(original.tag(Invocation.class).arguments().get(0)));

            return response.newBuilder().body(responseBody).build();
        });
        builder.client(httpClient.build());
        api = builder.build();

        return api.create(serviceClass);
    }

    public static <S> S createTimeoutService(Class<S> serviceClass,final String grandType,final String accessToken,String data,NICEPay config) {
        httpClientTimeout.interceptors().clear();
        httpClientTimeout.addInterceptor(chain -> {
            Request original = chain.request();
            Request.Builder builder = null;
            print.logInfo("generate "+"fullUrl :" +chain.request().url());
            String url = chain.request().url().encodedPath().replace("/nicepay","");
            Optional.ofNullable(grandType)
                    .ifPresentOrElse(value -> print.logInfo("getToken "+"pathUrl :" +url),
                            () -> print.logInfo("generate "+"pathUrl :" +url)
                    );

            try {
                String httpMethod = original.method();
                builder = original.newBuilder()
                        .headers(getHeaders(httpMethod,grandType,accessToken, data,url,config));
            } catch (Exception e) {
                e.printStackTrace();
            }
            Response response = chain.proceed(builder.build());
            String bodyString = response.body().string();
            MediaType contentType = response.body().contentType();
            ResponseBody responseBody  = ResponseBody.create(bodyString, contentType);
            print.logInfoBody("Request Data Timeout" + new GsonBuilder().setPrettyPrinting().create().toJson(original.tag(Invocation.class).arguments().get(0)));
            return response.newBuilder().body(responseBody).build();
        });
        builder.client(httpClientTimeout.build());
        api = builder.build();

        return api.create(serviceClass);
    }

    //Get Header
    private static Headers getHeaders(String httpMethod,String grandType,String accessToken,String data,String pathUrl,NICEPay config) throws Exception {

        Map<String, String> headersMap = new HashMap<>();
        String partnerID = config.getPartnerId();
        String privateKey = config.getPrivateKey();
        String secretKey = config.getClientSecret();
        String externalID = config.getExternalID();
        String timeStamp  = config.getTimestamp();

        headersMap.put("Content-Type", "application/json");
        if (grandType != null){
            String stringToSign = partnerID + "|" + timeStamp;
            String signatureAccessToken = SignatureUtils.signSHA256RSA(stringToSign,privateKey);
            headersMap.put("X-TIMESTAMP", timeStamp);
            headersMap.put("X-CLIENT-KEY", partnerID);
            headersMap.put("X-SIGNATURE", signatureAccessToken);
        }else{
            String hashData = SignatureUtils.sha256EncodeHex(data);
            String signature = SignatureUtils.getSignature(httpMethod,accessToken,hashData,pathUrl,timeStamp,secretKey);
            headersMap.put("Authorization",  "Bearer "+accessToken);
            headersMap.put("X-TIMESTAMP", timeStamp);
            headersMap.put("X-SIGNATURE", signature);
            headersMap.put("X-PARTNER-ID", partnerID);
            headersMap.put("X-EXTERNAL-ID", externalID);
            headersMap.put("CHANNEL-ID", partnerID+"01");
        }
        print.logInfoHeader("Request Header " + new GsonBuilder().setPrettyPrinting().create().toJson(headersMap) );

        return Headers.of(headersMap);

    }

    //V2
    public static <S> S createServiceV2(Class<S> serviceClass, NICEPay config) {
        httpClient.interceptors().clear();
        httpClient.addInterceptor(chain -> {
            Request original = chain.request();
            Request.Builder builder = null;
            print.logInfoV2("generate "+"fullUrl :" +chain.request().url());

            try {
                builder = original.newBuilder();
            } catch (Exception e) {
                e.printStackTrace();
            }

            Response response = chain.proceed(builder.build());
            String bodyString = response.body().string();
            MediaType contentType = response.body().contentType();
            ResponseBody responseBody  = ResponseBody.create(bodyString, contentType);
            print.logInfoBodyV2("Request Data " + new GsonBuilder().setPrettyPrinting().create().toJson(Objects.requireNonNull(original.tag(Invocation.class)).arguments()));

            return response.newBuilder().body(responseBody).build();
        });
        builder.client(httpClient.build());
        builder.baseUrl(config.getNICEPayBaseUrl());
        api = builder.build();

        return api.create(serviceClass);
    }

    //V2
    public static <S> S createServiceV1(Class<S> serviceClass, NICEPay config) {
        httpClient.interceptors().clear();
        httpClient.addInterceptor(chain -> {
            Request original = chain.request();
            Request.Builder builder = null;
            print.logInfoV1("generate "+"fullUrl :" +chain.request().url());

            try {
                builder = original.newBuilder();
            } catch (Exception e) {
                e.printStackTrace();
            }

            Response response = chain.proceed(builder.build());
            String bodyString = response.body().string();
            MediaType contentType = response.body().contentType();
            ResponseBody responseBody  = ResponseBody.create(bodyString, contentType);
            print.logInfoBodyV1("Request Data " + new GsonBuilder().setPrettyPrinting().create().toJson(original.tag(Invocation.class).arguments().get(0)));

            return response.newBuilder().body(responseBody).build();
        });

        builder.client(httpClient.build());
        builder.baseUrl(config.getNICEPayBaseUrl());
        api = builder.build();

        return api.create(serviceClass);
    }

    public static <T> T getApiMessageObject(String message, T object) throws Exception {
        try {
            // Determine where the JSON payload starts
            int jsonStartIndex = message.indexOf("{");
            if (jsonStartIndex == -1) {
                throw new IllegalArgumentException("Invalid message format: JSON payload not found.");
            }

            int messageLen = Integer.parseInt(message.substring(0, jsonStartIndex));
            if (messageLen <= 0) {
                throw new IllegalArgumentException("Invalid message length.");
            }

            String jsonPayload = message.substring(jsonStartIndex);
            Map<String, Object> parsedMap = mapper.readValue(jsonPayload, Map.class);

            return mapper.convertValue(parsedMap, (Class<T>) object.getClass());
        } catch (Exception e) {
            throw new Exception("Failed to parse API message: " + e.getMessage(), e);
        }
    }

}
