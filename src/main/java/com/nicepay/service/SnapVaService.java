package com.nicepay.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.*;
import com.nicepay.builder.VirtualAccount;
import com.nicepay.builder.TokenUtil;
import com.nicepay.retrofit.ApiClient;
import com.nicepay.utils.LoggerPrint;
import com.nicepay.config.NICEPayResponse;
import com.nicepay.retrofit.PostRequest;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import java.io.IOException;

public class SnapVaService {
    private static LoggerPrint print = new LoggerPrint();
    public static  <S> S callServiceVA(VirtualAccount data,String accessToken) throws IOException {
        Gson gson = new Gson();
        PostRequest request = ApiClient.createService(PostRequest.class, TokenUtil.builder().build().getGrantType(), accessToken, gson.toJson(data));
        Call<NICEPayResponse> callSync =  request.createVa(data);
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
            print.logInfoResponse("Response getVA :" +jsonObject);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return (S) nicePayResponse;
    }

}
