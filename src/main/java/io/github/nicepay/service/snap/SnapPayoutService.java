package io.github.nicepay.service.snap;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.github.nicepay.api.snap.PostPayoutRequest;
import io.github.nicepay.data.model.AccessToken;
import io.github.nicepay.data.model.Payout;
import io.github.nicepay.data.response.snap.NICEPayResponse;
import io.github.nicepay.utils.ApiUtils;
import io.github.nicepay.utils.LoggerPrint;
import io.github.nicepay.utils.NICEPay;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

public class SnapPayoutService {
    private static LoggerPrint print = new LoggerPrint();

    public static <S> S callServicePayoutRegist(Payout data, String accessToken, NICEPay config) throws IOException {
        Gson gson = new Gson();
        PostPayoutRequest request = ApiUtils.createService(PostPayoutRequest.class, AccessToken.builder().build().getGrantType(), accessToken, gson.toJson(data), config);
        Call<NICEPayResponse> callSync = request.registPayout(data);
        Response<NICEPayResponse> response = null;
        NICEPayResponse nicePayResponse = null;
        ResponseBody errorResponse = null;
        Object resClient = null;
        JsonObject jsonObject = null;
        try {
            response = callSync.execute();
            nicePayResponse = response.body();
            errorResponse = response.errorBody();

            if (nicePayResponse == null) {
                resClient = errorResponse.string();
                nicePayResponse = gson.fromJson(resClient.toString(), NICEPayResponse.class);

            } else {
                resClient = gson.toJson(nicePayResponse);
            }

            ObjectMapper mapper = new ObjectMapper();
            jsonObject = JsonParser.parseString(resClient.toString()).getAsJsonObject();
            print.logInfoResponse("Response payoutRegist :" + new GsonBuilder().setPrettyPrinting().create().toJson(jsonObject));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return (S) nicePayResponse;
    }

    public static <S> S callServicePayoutApprove(Payout data, String accessToken, NICEPay config) throws IOException {
        Gson gson = new Gson();
        PostPayoutRequest request = ApiUtils.createService(PostPayoutRequest.class, AccessToken.builder().build().getGrantType(), accessToken, gson.toJson(data), config);
        Call<NICEPayResponse> callSync = request.approvePayout(data);
        Response<NICEPayResponse> response = null;
        NICEPayResponse nicePayResponse = null;
        ResponseBody errorResponse = null;
        Object resClient = null;
        JsonObject jsonObject = null;
        try {
            response = callSync.execute();
            nicePayResponse = response.body();
            errorResponse = response.errorBody();

            if (nicePayResponse == null) {
                resClient = errorResponse.string();
                nicePayResponse = gson.fromJson(resClient.toString(), NICEPayResponse.class);

            } else {
                resClient = gson.toJson(nicePayResponse);
            }

            ObjectMapper mapper = new ObjectMapper();
            jsonObject = JsonParser.parseString(resClient.toString()).getAsJsonObject();
            print.logInfoResponse("Response payOutApprove :" + new GsonBuilder().setPrettyPrinting().create().toJson(jsonObject));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return (S) nicePayResponse;
    }

    public static <S> S callServicePayoutCheckBalance(Payout data, String accessToken, NICEPay config) throws IOException {
        Gson gson = new Gson();
        PostPayoutRequest request = ApiUtils.createService(PostPayoutRequest.class, AccessToken.builder().build().getGrantType(), accessToken, gson.toJson(data), config);
        Call<NICEPayResponse> callSync = request.checkBalancePayout(data);
        Response<NICEPayResponse> response = null;
        NICEPayResponse nicePayResponse = null;
        ResponseBody errorResponse = null;
        Object resClient = null;
        JsonObject jsonObject = null;
        try {
            response = callSync.execute();
            print.logInfoResponse("resp :" + response);

            nicePayResponse = response.body();
            errorResponse = response.errorBody();

            if (nicePayResponse == null) {
                resClient = errorResponse.string();
                nicePayResponse = gson.fromJson(resClient.toString(), NICEPayResponse.class);
            } else {
                resClient = gson.toJson(nicePayResponse);
            }

            ObjectMapper mapper = new ObjectMapper();
            jsonObject = JsonParser.parseString(resClient.toString()).getAsJsonObject();
            print.logInfoResponse("Response payoutCheckBalance :" + new GsonBuilder().setPrettyPrinting().create().toJson(jsonObject));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return (S) nicePayResponse;
    }
}
