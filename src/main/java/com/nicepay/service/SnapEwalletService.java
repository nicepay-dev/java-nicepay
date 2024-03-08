package com.nicepay.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.nicepay.model.AccessToken;
import com.nicepay.model.Cancel;
import com.nicepay.model.Ewallet;
import com.nicepay.response.NICEPayResponse;
import com.nicepay.utils.ApiUtils;
import com.nicepay.api.PostEwalletRequest;
import com.nicepay.utils.LoggerPrint;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.Map;

public class SnapEwalletService {

    private static LoggerPrint print = new LoggerPrint();

    public static <S> S callServiceEwalletPayment(Ewallet data, String accessToken) throws IOException, InterruptedException {

        Gson gson = new Gson();
        PostEwalletRequest request = ApiUtils.createService(PostEwalletRequest.class, AccessToken.builder().build().getGrantType(), accessToken, gson.toJson(data));
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
            print.logInfoResponse("Response EwalletPayment :" +new GsonBuilder().setPrettyPrinting().create().toJson(jsonObject));
        }catch(SocketTimeoutException e){
            Thread.sleep(55000);
            System.out.println(e.getMessage()+" please retry again");
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return (S) nicePayResponse;
        }


//    public static <S> S callServiceEwalletCheckStatus(Map<String, Object> data, String accessToken) throws IOException {
//        Gson gson = new Gson();
//        PostEwalletRequest request = ApiUtils.createService(PostEwalletRequest.class, AccessToken.builder().build().getGrantType(), accessToken, gson.toJson(data));
//        Call<NICEPayResponse> callSync = request.checkStatusEwallet(data);
//        Response<NICEPayResponse> response = null;
//        NICEPayResponse nicePayResponse = null;
//        ResponseBody errorResponse = null;
//        Object resClient = null ;
//        JsonObject jsonObject = null;
//        try{
//            response = callSync.execute();
//            nicePayResponse = response.body();
//            errorResponse = response.errorBody();
//
//            if (nicePayResponse == null){
//                resClient = errorResponse.string();
//            }else {
//                resClient = gson.toJson(nicePayResponse);
//            }
//
//            ObjectMapper mapper = new ObjectMapper();
//            jsonObject = JsonParser.parseString(resClient.toString()).getAsJsonObject();
//            print.logInfoResponse("Response EwalletCheckStatus :" +new GsonBuilder().setPrettyPrinting().create().toJson(jsonObject));
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        return (S) nicePayResponse;
//    }

    public static <S> S callServiceEwalletRefund(Cancel data, String accessToken) throws IOException {
        Gson gson = new Gson();
        PostEwalletRequest request = ApiUtils.createService(PostEwalletRequest.class, AccessToken.builder().build().getGrantType(), accessToken, gson.toJson(data));
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

