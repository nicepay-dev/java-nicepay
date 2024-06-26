package io.github.nicepay.service;

import io.github.nicepay.api.PostVaRequest;
import io.github.nicepay.model.AccessToken;
import io.github.nicepay.model.VirtualAccount;
import io.github.nicepay.utils.ApiUtils;
import io.github.nicepay.utils.NICEPay;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.*;
import io.github.nicepay.utils.LoggerPrint;
import io.github.nicepay.response.NICEPayResponse;
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
