package io.github.nicepay.service.v2;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.github.nicepay.api.v2.VaRequestV2;
import io.github.nicepay.model.Cancel;
import io.github.nicepay.model.InquiryStatus;
import io.github.nicepay.response.v2.NICEPayResponseV2;
import io.github.nicepay.utils.ApiUtils;
import io.github.nicepay.utils.LoggerPrint;
import io.github.nicepay.utils.NICEPay;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

public class V2CancelService {

    private static LoggerPrint print = new LoggerPrint();
    private static int retryCount = 0;
    public static  <S> S callV2CancelVA(Cancel data, NICEPay config) throws IOException {
        Gson gson = new Gson();
        VaRequestV2 request = ApiUtils.createServiceV2(VaRequestV2.class, gson.toJson(data), config);
        Call<NICEPayResponseV2> callSync = request.cancelVaV2(data);
        Response<NICEPayResponseV2> response = null;
        NICEPayResponseV2 nicePayResponse = null;
        ResponseBody errorResponse = null;
        Object resClient = null;
        JsonObject jsonObject = null;
        try {
            response = callSync.execute();
            nicePayResponse = response.body();
            errorResponse = response.errorBody();

            if (nicePayResponse == null) {
                resClient = errorResponse.string();
            } else {
                resClient = gson.toJson(nicePayResponse);
            }


            ObjectMapper mapper = new ObjectMapper();
            jsonObject = JsonParser.parseString(resClient.toString()).getAsJsonObject();
            print.logInfoResponseV2("Response Cancel :" + new GsonBuilder().setPrettyPrinting().create().toJson(jsonObject));

        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return (S) nicePayResponse;
    }
}
