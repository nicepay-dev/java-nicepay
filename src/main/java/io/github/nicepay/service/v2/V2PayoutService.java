package io.github.nicepay.service.v2;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.github.nicepay.api.v2.PayoutRequestV2;
import io.github.nicepay.data.model.Payout;
import io.github.nicepay.data.response.v2.NICEPayResponseV2;
import io.github.nicepay.utils.ApiUtils;
import io.github.nicepay.utils.LoggerPrint;
import io.github.nicepay.utils.NICEPay;
import io.github.nicepay.utils.SHA256Util;
import okhttp3.ResponseBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

public class V2PayoutService extends V2CommonService {

    private static final Logger LOGGER = LoggerFactory.getLogger("[V2 - Payout]");

    public static <S> S callV2PayoutRequest(Payout data, NICEPay config) throws IOException {

        LOGGER.info(LoggerPrint.LOG_DEFAULT, "START CALL V2 PAYOUT REGISTRATION");

        Gson gson = new Gson();
        PayoutRequestV2 request = ApiUtils.createServiceV2(PayoutRequestV2.class, config);

//        Update merchant token
        data.setMerchantToken(SHA256Util.encrypt(data.getMerchantToken()));

        Call<NICEPayResponseV2> callSync = request.requestPayout(data);

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
            LOGGER.info(LoggerPrint.LOG_RESPONSE, "Response Request Payout V2 Payout :" + new GsonBuilder().setPrettyPrinting().create().toJson(jsonObject));
            LOGGER.info(LoggerPrint.LOG_DEFAULT, "END CALL V2 PAYOUT REGISTRATION");

        } catch (Exception ex) {
            LOGGER.error(LoggerPrint.LOG_ERROR, "PAYOUT transaction registration failed :");
            ex.printStackTrace();
        }
        return (S) nicePayResponse;
    }


    public static <S> S callV2BalanceInquiry(Payout data, NICEPay config) throws IOException {

        LOGGER.info(LoggerPrint.LOG_DEFAULT, "START CALL V2 PAYOUT REGISTRATION");

        Gson gson = new Gson();
        PayoutRequestV2 request = ApiUtils.createServiceV2(PayoutRequestV2.class, config);

//        Update merchant token
        data.setMerchantToken(SHA256Util.encrypt(data.getMerchantToken()));

        Call<NICEPayResponseV2> callSync = request.balanceInquiry(data);

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
            LOGGER.info(LoggerPrint.LOG_RESPONSE, "Response Request Payout V2 Payout :" + new GsonBuilder().setPrettyPrinting().create().toJson(jsonObject));
            LOGGER.info(LoggerPrint.LOG_DEFAULT, "END CALL V2 PAYOUT REGISTRATION");

        } catch (Exception ex) {
            LOGGER.error(LoggerPrint.LOG_ERROR, "PAYOUT transaction registration failed :");
            ex.printStackTrace();
        }
        return (S) nicePayResponse;
    }

    public static <S> S callV2ApprovePayout(Payout data, NICEPay config) throws IOException {

        LOGGER.info(LoggerPrint.LOG_DEFAULT, "START CALL V2 PAYOUT REGISTRATION");

        Gson gson = new Gson();
        PayoutRequestV2 request = ApiUtils.createServiceV2(PayoutRequestV2.class, config);

//        Update merchant token
        data.setMerchantToken(SHA256Util.encrypt(data.getMerchantToken()));

        Call<NICEPayResponseV2> callSync = request.approvePayout(data);

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
            LOGGER.info(LoggerPrint.LOG_RESPONSE, "Response Request Payout V2 Payout :" + new GsonBuilder().setPrettyPrinting().create().toJson(jsonObject));
            LOGGER.info(LoggerPrint.LOG_DEFAULT, "END CALL V2 PAYOUT REGISTRATION");

        } catch (Exception ex) {
            LOGGER.error(LoggerPrint.LOG_ERROR, "PAYOUT transaction registration failed :");
            ex.printStackTrace();
        }
        return (S) nicePayResponse;
    }

    public static <S> S callV2RejectPayout(Payout data, NICEPay config) throws IOException {

        LOGGER.info(LoggerPrint.LOG_DEFAULT, "START CALL V2 PAYOUT REGISTRATION");

        Gson gson = new Gson();
        PayoutRequestV2 request = ApiUtils.createServiceV2(PayoutRequestV2.class, config);

//        Update merchant token
        data.setMerchantToken(SHA256Util.encrypt(data.getMerchantToken()));

        Call<NICEPayResponseV2> callSync = request.rejectPayout(data);

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
            LOGGER.info(LoggerPrint.LOG_RESPONSE, "Response Request Payout V2 Payout :" + new GsonBuilder().setPrettyPrinting().create().toJson(jsonObject));
            LOGGER.info(LoggerPrint.LOG_DEFAULT, "END CALL V2 PAYOUT REGISTRATION");

        } catch (Exception ex) {
            LOGGER.error(LoggerPrint.LOG_ERROR, "PAYOUT transaction registration failed :");
            ex.printStackTrace();
        }
        return (S) nicePayResponse;
    }

    public static <S> S callV2CancelPayout(Payout data, NICEPay config) throws IOException {

        LOGGER.info(LoggerPrint.LOG_DEFAULT, "START CALL V2 PAYOUT REGISTRATION");

        Gson gson = new Gson();
        PayoutRequestV2 request = ApiUtils.createServiceV2(PayoutRequestV2.class, config);

//        Update merchant token
        data.setMerchantToken(SHA256Util.encrypt(data.getMerchantToken()));

        Call<NICEPayResponseV2> callSync = request.cancelPayout(data);

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
            LOGGER.info(LoggerPrint.LOG_RESPONSE, "Response Request Payout V2 Payout :" + new GsonBuilder().setPrettyPrinting().create().toJson(jsonObject));
            LOGGER.info(LoggerPrint.LOG_DEFAULT, "END CALL V2 PAYOUT REGISTRATION");

        } catch (Exception ex) {
            LOGGER.error(LoggerPrint.LOG_ERROR, "PAYOUT transaction registration failed :");
            ex.printStackTrace();
        }
        return (S) nicePayResponse;
    }


}
