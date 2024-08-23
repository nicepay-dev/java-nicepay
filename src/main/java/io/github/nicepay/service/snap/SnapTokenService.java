package io.github.nicepay.service.snap;

import io.github.nicepay.api.snap.PostVaRequest;
import io.github.nicepay.data.model.AccessToken;
import io.github.nicepay.utils.ApiUtils;
import io.github.nicepay.utils.LoggerPrint;
import io.github.nicepay.utils.NICEPay;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.github.nicepay.data.response.snap.NICEPayResponse;
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


