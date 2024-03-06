package com.nicepay.utils;

import com.google.gson.*;
import okhttp3.*;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Invocation;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class ApiUtils {

//    private static AccessToken util ;
    private static LoggerPrint print = new LoggerPrint();
    private static Retrofit.Builder builder
            = new Retrofit.Builder()
            .baseUrl(NICEPayConstants.getSandboxBaseUrl())
            .addConverterFactory(GsonConverterFactory.create())
            ;

    private static Retrofit api = builder
            .build();

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

    public static <S> S createService(Class<S> serviceClass,final String grandType,final String accessToken,String data) {
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
                                .headers(getHeaders(httpMethod,grandType,accessToken, data,url));
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

    public static <S> S createTimeoutService(Class<S> serviceClass,final String grandType,final String accessToken,String data) {
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
                        .headers(getHeaders(httpMethod,grandType,accessToken, data,url));
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
    private static Headers getHeaders(String httpMethod,String grandType,String accessToken,String data,String pathUrl) throws Exception {

        Map<String, String> headersMap = new HashMap<>();

        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
        String timeStamp = f.format(new Date());
        String partnerID = NICEPayConstants.getPartnerId();
        String privateKey = NICEPayConstants.getPrivatekey();
        String secretKey = NICEPayConstants.getSecretKey();
        Random rand = new Random();
        int random = rand.nextInt(100000000);
        String externalID = "OrdNo" + timeStamp.substring(0, 10).replace("-","") + timeStamp.substring(11,19).replace(":","") + random;

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

}
