package com.nicepay.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nicepay.model.AccessToken;
import com.nicepay.response.NICEPayResponse;
import com.nicepay.utils.ApiUtils;
import com.nicepay.api.PostVaRequest;
import com.nicepay.utils.LoggerPrint;
import com.nicepay.utils.NICEPay;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

public class SnapTokenService {

    private static LoggerPrint print = new LoggerPrint();
    public static  <S> S callGetAccessToken(AccessToken util, NICEPay config) throws IOException {
        PostVaRequest request = ApiUtils.createService(PostVaRequest.class, util.getGrantType(),null,null,config);
        Call<NICEPayResponse> callSync = request.getToken(util);

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


