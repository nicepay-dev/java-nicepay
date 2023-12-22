package com.nicepay.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.nicepay.builder.Payout;
import com.nicepay.builder.TokenUtil;
import com.nicepay.config.NICEPayResponse;
import com.nicepay.retrofit.ApiClient;
import com.nicepay.retrofit.PostPayoutRequest;
import com.nicepay.utils.LoggerPrint;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

public class SnapPayoutService {
    private static LoggerPrint print = new LoggerPrint();
    public static  <S> S callServicePayoutRegist(Payout data, String accessToken) throws IOException {
        Gson gson = new Gson();
        PostPayoutRequest request = ApiClient.createService(PostPayoutRequest.class, TokenUtil.builder().build().getGrantType(), accessToken, gson.toJson(data));
        Call<NICEPayResponse> callSync =  request.registPayout(data);
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
            print.logInfoResponse("Response payoutRegist Origin :" +resClient);
             jsonObject = JsonParser.parseString(resClient.toString()).getAsJsonObject();
            print.logInfoResponse("Response payoutRegist :" +jsonObject);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return (S) nicePayResponse;
    }

}
