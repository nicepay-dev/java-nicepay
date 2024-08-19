package io.github.nicepay.service.snap;

import io.github.nicepay.api.snap.PostEwalletRequest;
import io.github.nicepay.api.snap.PostPayoutRequest;
import io.github.nicepay.api.snap.PostQrisRequest;
import io.github.nicepay.api.snap.PostVaRequest;
import io.github.nicepay.model.AccessToken;
import io.github.nicepay.model.InquiryStatus;
import io.github.nicepay.utils.ApiUtils;
import io.github.nicepay.utils.LoggerPrint;
import io.github.nicepay.utils.NICEPay;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.github.nicepay.exception.snap.NicepayErrorResponse;
import io.github.nicepay.response.snap.NICEPayResponse;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.net.SocketTimeoutException;

public class SnapInquiryStatusService {
    private static LoggerPrint print = new LoggerPrint();
    private static int retryCount = 0;

    public static  <S> S callServiceVACheckStatus(InquiryStatus data, String accessToken, NICEPay config) throws IOException, InterruptedException {
        Gson gson = new Gson();
        Response<NICEPayResponse> response = null;
        NICEPayResponse nicePayResponse = null;
        ResponseBody errorResponse = null;
        Object resClient = null ;
        JsonObject jsonObject = null;
        PostVaRequest request = ApiUtils.createService(PostVaRequest.class, AccessToken.builder().build().getGrantType(), accessToken, gson.toJson(data),config);
        Call<NICEPayResponse> callSync =  request.inquiryStatusVa(data);
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
            print.logInfoResponse("Response CheckStatusVA :" +new GsonBuilder().setPrettyPrinting().create().toJson(jsonObject));
        } catch (SocketTimeoutException e) {
            System.out.println("wait 2sec");
            Thread.sleep(2000);
            System.out.println("error " + e.getMessage());
            return (S) new NicepayErrorResponse("1004",e.getMessage());
        }
        catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("wait 2sec");
            Thread.sleep(2000);
        }
        return (S) nicePayResponse;
    }

    public static <S> S callServiceEwalletCheckStatus(InquiryStatus data, String accessToken,NICEPay config) throws IOException {
        Gson gson = new Gson();
        PostEwalletRequest request = ApiUtils.createService(PostEwalletRequest.class, AccessToken.builder().build().getGrantType(), accessToken, gson.toJson(data),config);
        Call<NICEPayResponse> callSync = request.inquiryStatusEwallet(data);
        Response<NICEPayResponse> response = null;
        NICEPayResponse nicePayResponse = null;
        ResponseBody errorResponse = null;
        Object resClient = null ;
        JsonObject jsonObject = null;
        try{
            response = callSync.execute();
            nicePayResponse = response.body();
            errorResponse = response.errorBody();

            if (nicePayResponse == null){
                resClient = errorResponse.string();
            }else {
                resClient = gson.toJson(nicePayResponse);
            }

            ObjectMapper mapper = new ObjectMapper();
            jsonObject = JsonParser.parseString(resClient.toString()).getAsJsonObject();
            print.logInfoResponse("Response EwalletCheckStatus :" +new GsonBuilder().setPrettyPrinting().create().toJson(jsonObject));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return (S) nicePayResponse;
    }

    public static <S> S callServicePayoutStatus(InquiryStatus data, String accessToken, NICEPay config) throws IOException {
        Gson gson = new Gson();
        PostPayoutRequest request = ApiUtils.createService(PostPayoutRequest.class, AccessToken.builder().build().getGrantType(), accessToken, gson.toJson(data),config);
        Call<NICEPayResponse> callSync = request.checkStatusPayout(data);
        Response<NICEPayResponse> response = null;
        NICEPayResponse nicePayResponse = null;
        ResponseBody errorResponse = null;
        Object resClient = null ;
        JsonObject jsonObject = null;
        try{
            response = callSync.execute();
            nicePayResponse = response.body();
            errorResponse = response.errorBody();

            if (nicePayResponse == null){
                resClient = errorResponse.string();
            }else {
                resClient = gson.toJson(nicePayResponse);
            }

            ObjectMapper mapper = new ObjectMapper();
            jsonObject = JsonParser.parseString(resClient.toString()).getAsJsonObject();
            print.logInfoResponse("Response PayoutCheckStatus :" +new GsonBuilder().setPrettyPrinting().create().toJson(jsonObject));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return (S) nicePayResponse;
    }

    public static <S> S callServiceQrisCheckStatus(InquiryStatus data, String accessToken, NICEPay config) throws IOException {
        Gson gson = new Gson();
        PostQrisRequest request = ApiUtils.createService(PostQrisRequest.class, AccessToken.builder().build().getGrantType(), accessToken, gson.toJson(data),config);
        Call<NICEPayResponse> callSync = request.checkStatusQris(data);
        Response<NICEPayResponse> response = null;
        NICEPayResponse nicePayResponse = null;
        ResponseBody errorResponse = null;
        Object resClient = null ;
        JsonObject jsonObject = null;
        try{
            response = callSync.execute();
            nicePayResponse = response.body();
            errorResponse = response.errorBody();

            if (nicePayResponse == null){
                resClient = errorResponse.string();
            }else {
                resClient = gson.toJson(nicePayResponse);
            }

            ObjectMapper mapper = new ObjectMapper();
            jsonObject = JsonParser.parseString(resClient.toString()).getAsJsonObject();
            print.logInfoResponse("Response QrisCheckStatus :" +new GsonBuilder().setPrettyPrinting().create().toJson(jsonObject));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return (S) nicePayResponse;
    }

}
