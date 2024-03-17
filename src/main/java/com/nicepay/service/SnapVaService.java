package com.nicepay.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.*;
import com.nicepay.model.AccessToken;
import com.nicepay.model.VirtualAccount;
import com.nicepay.utils.ApiUtils;
import com.nicepay.utils.LoggerPrint;
import com.nicepay.response.NICEPayResponse;
import com.nicepay.api.PostVaRequest;
import com.nicepay.utils.NICEPay;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import java.io.IOException;

public class SnapVaService {
    private static LoggerPrint print = new LoggerPrint();
    private static int retryCount = 0;
    public static  <S> S callGeneratedVA(VirtualAccount data, String accessToken, NICEPay config) throws IOException {
        Gson gson = new Gson();
        PostVaRequest request = ApiUtils.createService(PostVaRequest.class, AccessToken.builder().build().getGrantType(), accessToken, gson.toJson(data),config);
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
            print.logInfoResponse("Response getVA :" +new GsonBuilder().setPrettyPrinting().create().toJson(jsonObject));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return (S) nicePayResponse;
    }




}
