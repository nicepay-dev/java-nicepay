package com.nicepay.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.nicepay.api.PostEwalletRequest;
import com.nicepay.api.PostPayoutRequest;
import com.nicepay.api.PostQrisRequest;
import com.nicepay.api.PostVaRequest;
import com.nicepay.exception.NicepayErrorResponse;
import com.nicepay.model.AccessToken;
import com.nicepay.model.Cancel;
import com.nicepay.response.NICEPayResponse;
import com.nicepay.utils.ApiUtils;
import com.nicepay.utils.LoggerPrint;
import com.nicepay.utils.NICEPay;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.net.SocketTimeoutException;

public class SnapCancelService {
    private static LoggerPrint print = new LoggerPrint();
    private static int retryCount = 0;

    public static  <S> S callServiceVACancel(Cancel data, String accessToken,NICEPay config) throws IOException, InterruptedException {
        Gson gson = new Gson();
        Response<NICEPayResponse> response = null;
        NICEPayResponse nicePayResponse = null;
        ResponseBody errorResponse = null;
        Object resClient = null ;
        JsonObject jsonObject = null;
        PostVaRequest request = ApiUtils.createService(PostVaRequest.class, AccessToken.builder().build().getGrantType(), accessToken, gson.toJson(data), config);
        Call<NICEPayResponse> callSync =  request.cancelVa(data);
        try {
            response = callSync.execute();
            nicePayResponse = response.body();
            errorResponse = response.errorBody();

            if (nicePayResponse == null){
                resClient = errorResponse.string();
            }else{
                resClient = gson.toJson(nicePayResponse);
            }

            ObjectMapper mapper = new ObjectMapper();
            jsonObject = JsonParser.parseString(resClient.toString()).getAsJsonObject();
            print.logInfoResponse("Response CancelVA :" +new GsonBuilder().setPrettyPrinting().create().toJson(jsonObject));
        } catch (SocketTimeoutException e) {
            System.out.println("wait 2sec");
            Thread.sleep(2000);
            System.out.println("error " + e.getMessage());
            return (S) new NicepayErrorResponse("1004",e.getMessage());
        }
        catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("wait 2sec");
            Thread.sleep(2000);
        }
        return (S) nicePayResponse;
    }

    public static <S> S callServiceEwalletCancel(Cancel data, String accessToken,NICEPay config) throws IOException {
        Gson gson = new Gson();
        PostEwalletRequest request = ApiUtils.createService(PostEwalletRequest.class, AccessToken.builder().build().getGrantType(), accessToken, gson.toJson(data),config);
        Call<NICEPayResponse> callSync = request.refundEwallet(data);
        Response<NICEPayResponse> response = null;
        NICEPayResponse nicePayResponse = null;
        ResponseBody errorResponse = null;
        Object resClient = null ;
        JsonObject jsonObject = null;
        try{
            response = callSync.execute();
            nicePayResponse = response.body();
            errorResponse = response.errorBody();

            if (nicePayResponse == null){
                resClient = errorResponse.string();
            }else {
                resClient = gson.toJson(nicePayResponse);
            }

            ObjectMapper mapper = new ObjectMapper();
            jsonObject = JsonParser.parseString(resClient.toString()).getAsJsonObject();
            print.logInfoResponse("Response EwalletCancel :" +new GsonBuilder().setPrettyPrinting().create().toJson(jsonObject));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return (S) nicePayResponse;
    }


    public static <S> S callServicePayoutCancel(Cancel data, String accessToken,NICEPay config) throws IOException {
        Gson gson = new Gson();
        PostPayoutRequest request = ApiUtils.createService(PostPayoutRequest.class, AccessToken.builder().build().getGrantType(), accessToken, gson.toJson(data),config);
        Call<NICEPayResponse> callSync = request.cancelPayout(data);
        Response<NICEPayResponse> response = null;
        NICEPayResponse nicePayResponse = null;
        ResponseBody errorResponse = null;
        Object resClient = null ;
        JsonObject jsonObject = null;
        try{
            response = callSync.execute();
            nicePayResponse = response.body();
            errorResponse = response.errorBody();

            if (nicePayResponse == null){
                resClient = errorResponse.string();
            }else {
                resClient = gson.toJson(nicePayResponse);
            }

            ObjectMapper mapper = new ObjectMapper();
            jsonObject = JsonParser.parseString(resClient.toString()).getAsJsonObject();
            print.logInfoResponse("Response PayoutCancel :" +new GsonBuilder().setPrettyPrinting().create().toJson(jsonObject));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return (S) nicePayResponse;
    }

    public static <S> S callServicePayoutReject(Cancel data, String accessToken,NICEPay config) throws IOException {
        Gson gson = new Gson();
        PostPayoutRequest request = ApiUtils.createService(PostPayoutRequest.class, AccessToken.builder().build().getGrantType(), accessToken, gson.toJson(data),config);
        Call<NICEPayResponse> callSync = request.rejectPayout(data);
        Response<NICEPayResponse> response = null;
        NICEPayResponse nicePayResponse = null;
        ResponseBody errorResponse = null;
        Object resClient = null ;
        JsonObject jsonObject = null;
        try{
            response = callSync.execute();
            nicePayResponse = response.body();
            errorResponse = response.errorBody();

            if (nicePayResponse == null){
                resClient = errorResponse.string();
            }else {
                resClient = gson.toJson(nicePayResponse);
            }

            ObjectMapper mapper = new ObjectMapper();
            jsonObject = JsonParser.parseString(resClient.toString()).getAsJsonObject();
            print.logInfoResponse("Response PayoutReject :" +new GsonBuilder().setPrettyPrinting().create().toJson(jsonObject));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return (S) nicePayResponse;
    }

    public static <S> S callServiceQrisRefund(Cancel data, String accessToken,NICEPay config) throws IOException {
        Gson gson = new Gson();
        PostQrisRequest request = ApiUtils.createService(PostQrisRequest.class, AccessToken.builder().build().getGrantType(), accessToken, gson.toJson(data),config);
        Call<NICEPayResponse> callSync = request.refundQris(data);
        Response<NICEPayResponse> response = null;
        NICEPayResponse nicePayResponse = null;
        ResponseBody errorResponse = null;
        Object resClient = null ;
        JsonObject jsonObject = null;
        try{
            response = callSync.execute();
            nicePayResponse = response.body();
            errorResponse = response.errorBody();

            if (nicePayResponse == null){
                resClient = errorResponse.string();
            }else {
                resClient = gson.toJson(nicePayResponse);
            }

            ObjectMapper mapper = new ObjectMapper();
            jsonObject = JsonParser.parseString(resClient.toString()).getAsJsonObject();
            print.logInfoResponse("Response QrisRefund :" +new GsonBuilder().setPrettyPrinting().create().toJson(jsonObject));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return (S) nicePayResponse;
    }
}