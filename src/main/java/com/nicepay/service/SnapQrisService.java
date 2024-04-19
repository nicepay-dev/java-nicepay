package com.nicepay.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.nicepay.api.PostPayoutRequest;
import com.nicepay.api.PostQrisRequest;
import com.nicepay.model.AccessToken;
import com.nicepay.model.Payout;
import com.nicepay.model.Qris;
import com.nicepay.response.NICEPayResponse;
import com.nicepay.utils.ApiUtils;
import com.nicepay.utils.LoggerPrint;
import com.nicepay.utils.NICEPay;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

public class SnapQrisService {
    private static LoggerPrint print = new LoggerPrint();

    public static  <S> S callServiceQrisRegist(Qris data, String accessToken, NICEPay config) throws IOException {
        Gson gson = new Gson();
        PostQrisRequest request = ApiUtils.createService(PostQrisRequest.class, AccessToken.builder().build().getGrantType(), accessToken, gson.toJson(data),config);
        Call<NICEPayResponse> callSync =  request.registQris(data);
        Response<NICEPayResponse> response = null;
        NICEPayResponse nicePayResponse = null;
        ResponseBody errorResponse = null;
        Object resClient = null ;
        JsonObject jsonObject = null;
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
            print.logInfoResponse("Response Qris Regist :" +new GsonBuilder().setPrettyPrinting().create().toJson(jsonObject));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return (S) nicePayResponse;
    }
}