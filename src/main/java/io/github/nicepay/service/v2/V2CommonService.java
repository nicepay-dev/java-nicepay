package io.github.nicepay.service.v2;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.github.nicepay.api.v2.RequestV2;
import io.github.nicepay.data.model.Cancel;
import io.github.nicepay.data.model.InquiryStatus;
import io.github.nicepay.data.response.v2.NICEPayResponseV2;
import io.github.nicepay.utils.ApiUtils;
import io.github.nicepay.utils.LoggerPrint;
import io.github.nicepay.utils.NICEPay;
import io.github.nicepay.utils.SHA256Util;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

public class V2CommonService {

    private static LoggerPrint print = new LoggerPrint();

    public static <S> S callV2CancelTransaction(Cancel data, NICEPay config) throws IOException {
        Gson gson = new Gson();
        RequestV2 request = ApiUtils.createServiceV2(RequestV2.class, config);

        //        Update merchant token and set additional info null
        data.setMerchantToken(SHA256Util.encrypt(data.getMerchantToken()));
        data.setAdditionalInfo(null);

        Call<NICEPayResponseV2> callSync = request.cancelTransactionV2(data);
        Response<NICEPayResponseV2> response;
        NICEPayResponseV2 nicePayResponse = null;
        ResponseBody errorResponse;
        Object resClient;
        JsonObject jsonObject;
        try {
            response = callSync.execute();
            nicePayResponse = response.body();
            errorResponse = response.errorBody();

            if (nicePayResponse == null) {
                resClient = errorResponse.string();
            } else {
                resClient = gson.toJson(nicePayResponse);
            }


            jsonObject = JsonParser.parseString(resClient.toString()).getAsJsonObject();
            print.logInfoResponseV2("Response Cancel :" + new GsonBuilder().setPrettyPrinting().create().toJson(jsonObject));

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return (S) nicePayResponse;
    }

    public static <S> S callV2InquiryStatus(InquiryStatus data, NICEPay config) throws IOException {
        Gson gson = new Gson();
        RequestV2 request = ApiUtils.createServiceV2(RequestV2.class, config);

        data.setMerchantToken(SHA256Util.encrypt(data.getMerchantToken()));
        data.setAdditionalInfo(null);

        Call<NICEPayResponseV2> callSync = request.inquiryStatusV2(data);
        Response<NICEPayResponseV2> response;
        NICEPayResponseV2 nicePayResponse = null;
        ResponseBody errorResponse;
        Object resClient;
        JsonObject jsonObject;
        try {
            response = callSync.execute();
            nicePayResponse = response.body();
            errorResponse = response.errorBody();

            if (nicePayResponse == null) {
                resClient = errorResponse.string();
            } else {
                resClient = gson.toJson(nicePayResponse);
            }


            jsonObject = JsonParser.parseString(resClient.toString()).getAsJsonObject();
            print.logInfoResponseV2("Response InquiryVA :" + new GsonBuilder().setPrettyPrinting().create().toJson(jsonObject));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return (S) nicePayResponse;
    }

}
