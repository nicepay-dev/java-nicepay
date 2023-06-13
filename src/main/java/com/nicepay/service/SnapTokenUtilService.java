package com.nicepay.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nicepay.builder.TokenUtil;
import com.nicepay.config.NICEPayResponse;
import com.nicepay.retrofit.ApiClient;
import com.nicepay.retrofit.PostRequest;
import com.nicepay.utils.LoggerPrint;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

public class SnapTokenUtilService {

    private static LoggerPrint print = new LoggerPrint();
    public static  <S> S callGetToken(TokenUtil util) throws IOException {
        PostRequest request = ApiClient.createService(PostRequest.class, util.getGrantType(),null,null);
        Call<NICEPayResponse> callSync = request.getToken(util);
//        NICEPayResponse response = callSync.execute().body();

        Response<NICEPayResponse> response = null;
        NICEPayResponse nicePayResponse = null;
        ResponseBody errorResponse = null;
        Object resClient = null ;
        try {
            response = callSync.execute();
            nicePayResponse = response.body();
            errorResponse = response.errorBody();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (nicePayResponse == null){
            resClient = errorResponse.string() ;
        }else{
            resClient = (NICEPayResponse)nicePayResponse;
        }

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
        String jsonResponse = gson.toJson(resClient);
        print.logInfoResponse("Response getToken :" +jsonResponse.replaceAll("\\\\",""));
        return (S) nicePayResponse;
    }

}


