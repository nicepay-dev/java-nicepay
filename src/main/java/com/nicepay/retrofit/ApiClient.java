package com.nicepay.retrofit;

import com.google.gson.*;
import com.nicepay.config.NICEPayConstants;
import com.nicepay.builder.TokenUtil;
import com.nicepay.utils.LoggerPrint;
import com.nicepay.utils.SignatureUtils;
import okhttp3.*;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Invocation;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import java.text.SimpleDateFormat;
import java.util.*;

public class ApiClient {

    private static TokenUtil util ;
    private static LoggerPrint print = new LoggerPrint();
    private static Retrofit.Builder builder
            = new Retrofit.Builder()
            .baseUrl(NICEPayConstants.getSandboxBaseUrl())
            .addConverterFactory(GsonConverterFactory.create())
            ;

    private static Retrofit retrofit = builder
            .build();

    private static OkHttpClient.Builder httpClient
            = new OkHttpClient
            .Builder();

    private static HttpLoggingInterceptor logging
            = new HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BASIC);

    public static <S> S createService(Class<S> serviceClass,final String grandType,final String accessToken,String data) {
                httpClient.interceptors().clear();
                httpClient.addInterceptor(chain -> {
                    Request original = chain.request();
                    Request.Builder builder1 = null;
                    print.logInfo("generateVA "+"fullUrl :" +chain.request().url());
                    String url = chain.request().url().encodedPath().replace("/nicepay","");
                      Optional.ofNullable(grandType)
                            .ifPresentOrElse(value -> print.logInfo("getToken "+"pathUrl :" +url),
                                    () -> print.logInfo("generateVA "+"pathUrl :" +url)
                            );

                    try {
                        builder1 = original.newBuilder()
                                .headers(getHeaders(grandType,accessToken, data,url));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
//                    Request request = builder1.build();
                    Response response = chain.proceed(builder1.build());
                    String bodyString = response.body().string();
                    MediaType contentType = response.body().contentType();
                    ResponseBody responseBody  = ResponseBody.create(bodyString, contentType);
                    print.logInfoBody("Request Data " + new GsonBuilder().setPrettyPrinting().create().toJson(original.tag(Invocation.class).arguments().get(0)));
                    return response.newBuilder().body(responseBody).build();
                });
                builder.client(httpClient.build());
                retrofit = builder.build();

        return retrofit.create(serviceClass);
    }

    //Get Header
    private static Headers getHeaders(String grandType,String accessToken,String data,String pathUrl) throws Exception {

        Map<String, String> headersMap = new HashMap<>();

        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
        String timeStamp = f.format(new Date());
        String clientKey = NICEPayConstants.getClientKey();
        String privateKey = NICEPayConstants.getPrivatekey();
        String secretKey = NICEPayConstants.getSecretKey();
        Random rand = new Random();
        int random = rand.nextInt(100000000);
        String externalID = "OrdNo" + timeStamp.substring(0, 10).replace("-","") + timeStamp.substring(11,19).replace(":","") + random;

        headersMap.put("Content-Type", "application/json");
        if (grandType != null){
            String stringToSign = clientKey + "|" + timeStamp;
            String signatureAccessToken = SignatureUtils.signSHA256RSA(stringToSign,privateKey);
            headersMap.put("X-TIMESTAMP", timeStamp);
            headersMap.put("X-CLIENT-KEY", clientKey);
            headersMap.put("X-SIGNATURE", signatureAccessToken);
        }else{
            String hashData = SignatureUtils.sha256EncodeHex(data);
            String signature = SignatureUtils.getSignature(accessToken,hashData,pathUrl,timeStamp,secretKey);
            headersMap.put("Authorization",  "Bearer "+accessToken);
            headersMap.put("X-TIMESTAMP", timeStamp);
            headersMap.put("X-SIGNATURE", signature);
            headersMap.put("X-PARTNER-ID", clientKey);
            headersMap.put("X-EXTERNAL-ID", externalID);
            headersMap.put("CHANNEL-ID", clientKey+"01");
        }
        print.logInfoHeader("Request Header " + new GsonBuilder().setPrettyPrinting().create().toJson(headersMap) );

        return Headers.of(headersMap);

    }

}
