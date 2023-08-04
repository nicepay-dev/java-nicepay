package com.nicepay.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.nicepay.builder.Ewallet;
import com.nicepay.builder.TokenUtil;
import com.nicepay.config.NICEPayResponse;
import com.nicepay.retrofit.ApiClient;
import com.nicepay.retrofit.PostEwalletRequest;
import com.nicepay.utils.LoggerPrint;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

public class SnapEwalletService {

    private static LoggerPrint print = new LoggerPrint();

    public static <S> S callServiceEwalletPayment(Ewallet data, String accessToken) throws IOException {
        Gson gson = new Gson();
        PostEwalletRequest request = ApiClient.createService(PostEwalletRequest.class, TokenUtil.builder().build().getGrantType(), accessToken, gson.toJson(data));
        Call<NICEPayResponse> callSync = request.paymentEwallet(data);
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
            print.logInfoResponse("Response EwalletPayment :" +jsonObject);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return (S) nicePayResponse;
        }


    public static <S> S callServiceEwalletCheckStatus(Ewallet data, String accessToken) throws IOException {
        Gson gson = new Gson();
        PostEwalletRequest request = ApiClient.createService(PostEwalletRequest.class, TokenUtil.builder().build().getGrantType(), accessToken, gson.toJson(data));
        Call<NICEPayResponse> callSync = request.checkStatusEwallet(data);
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
            print.logInfoResponse("Response EwalletCheckStatus :" +jsonObject);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return (S) nicePayResponse;
    }

    public static <S> S callServiceEwalletRefund(Ewallet data, String accessToken) throws IOException {
        Gson gson = new Gson();
        PostEwalletRequest request = ApiClient.createService(PostEwalletRequest.class, TokenUtil.builder().build().getGrantType(), accessToken, gson.toJson(data));
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
            print.logInfoResponse("Response EwalletRefund :" +jsonObject);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return (S) nicePayResponse;
    }

    }

