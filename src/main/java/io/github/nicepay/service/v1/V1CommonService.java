package io.github.nicepay.service.v1;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.github.nicepay.api.v1.RequestV1;
import io.github.nicepay.data.model.Cancel;
import io.github.nicepay.data.model.InquiryStatus;
import io.github.nicepay.data.response.v1.NICEPayResponseV1;
import io.github.nicepay.utils.*;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

public class V1CommonService {

    private static LoggerPrint logger = new LoggerPrint();

    public static <S> S callInquiryStatus(InquiryStatus data, NICEPay config) throws IOException {

        logger.logInfoV1("START CALL V1 INQUIRY STATUS REQUEST");
        Gson gson = new Gson();

        RequestV1 requestV1 = ApiUtils.createServiceV1(RequestV1.class, config);

        // Encrypt the merchant token
        data.setMerchantToken(SHA256Util.encrypt(data.getMerchantToken()));

        // Prepare the API call
        Call<NICEPayResponseV1> callSync = requestV1.inquiryStatus(ModelUtils.toMap(data));

        Response<NICEPayResponseV1> response;
        NICEPayResponseV1 nicePayResponse = new NICEPayResponseV1();
        ResponseBody errorResponse;
        Object resClient = null;

        try {
            response = callSync.execute();
            nicePayResponse = response.body();
            errorResponse = response.errorBody();

            if (nicePayResponse == null) {
                resClient = errorResponse.string();
            } else {
                resClient = gson.toJson(nicePayResponse);
            }

            logger.logInfoV1("END CALL V1 INQUIRY STATUS REQUEST");

        } catch (IOException ex) {
            logger.logErrorV1("IOException during V1 Inquiry Status Request: " + ex.getMessage());
            throw ex; // Re-throw IOException to ensure the caller is aware of the failure
        } catch (Exception ex) {
            logger.logErrorV1("Unexpected error during V1 Inquiry Status Request: " + ex.getMessage());
            ex.printStackTrace();
        }

        JsonObject jsonObject = JsonParser.parseString(resClient.toString()).getAsJsonObject();
        logger.logInfoResponseV1("Response Inquiry Status V1 :" + new GsonBuilder().setPrettyPrinting().create().toJson(jsonObject));
        // Return the response as a generic type
        return (S) nicePayResponse;
    }


    public static <S> S callCancelTransaction(Cancel data, NICEPay config) throws IOException {

        logger.logInfoV1("START CALL V1 CANCEL TRANSACTION REQUEST");
        Gson gson = new Gson();

        RequestV1 requestV1 = ApiUtils.createServiceV1(RequestV1.class, config);

        // Encrypt the merchant token
        data.setMerchantToken(SHA256Util.encrypt(data.getMerchantToken()));

        // Prepare the API call
        Call<NICEPayResponseV1> callSync = requestV1.cancelTransaction(ModelUtils.toMap(data));

        Response<NICEPayResponseV1> response;
        NICEPayResponseV1 nicePayResponse = new NICEPayResponseV1();
        ResponseBody errorResponse;
        Object resClient = null;

        try {
            response = callSync.execute();
            nicePayResponse = response.body();
            errorResponse = response.errorBody();

            if (nicePayResponse == null) {
                resClient = errorResponse.string();
            } else {
                resClient = gson.toJson(nicePayResponse);
            }

            logger.logInfoV1("END CALL V1 CANCEL TRANSACTION REQUEST");

        } catch (IOException ex) {
            logger.logErrorV1("IOException during V1 Cancel Transaction Request: " + ex.getMessage());
            throw ex; // Re-throw IOException to ensure the caller is aware of the failure
        } catch (Exception ex) {
            logger.logErrorV1("Unexpected error during V1 Cancel Transaction Request: " + ex.getMessage());
            ex.printStackTrace();
        }

        JsonObject jsonObject = JsonParser.parseString(resClient.toString()).getAsJsonObject();
        logger.logInfoResponseV1("Response Cancel Transaction V1 :" + new GsonBuilder().setPrettyPrinting().create().toJson(jsonObject));
        // Return the response as a generic type
        return (S) nicePayResponse;
    }
}
