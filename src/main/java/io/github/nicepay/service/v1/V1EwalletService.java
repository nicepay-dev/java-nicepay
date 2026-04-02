package io.github.nicepay.service.v1;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.github.nicepay.api.v1.EwalletRequestV1;
import io.github.nicepay.data.model.Ewallet;
import io.github.nicepay.data.response.v1.NICEPayResponseV1;
import io.github.nicepay.utils.*;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

public class V1EwalletService extends V1CommonService {

    private static LoggerPrint logger = new LoggerPrint();

    public static <S> S callRegistration(Ewallet data, NICEPay config) throws IOException {

        logger.logInfoV1("START CALL V1 REGISTRATION E-WALLET REQUEST");
        Gson gson = new Gson();

        EwalletRequestV1 requestV1 = ApiUtils.createServiceV1(EwalletRequestV1.class, config);

        // Encrypt the merchant token
        data.setMerchantToken(SHA256Util.encrypt(data.getMerchantToken()));

        // Prepare the API call
        Call<NICEPayResponseV1> callSync = requestV1.ewalletTrans(ModelUtils.toMap(data));

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

            logger.logInfoV1("END CALL V1 REGISTRATION E-WALLET REQUEST");

        } catch (IOException ex) {
            logger.logErrorV1("IOException during V1 Registration E-WALLET Request: " + ex.getMessage());
            throw ex; // Re-throw IOException to ensure the caller is aware of the failure
        } catch (Exception ex) {
            logger.logErrorV1("Unexpected error during V1 Registration E-WALLET Request: " + ex.getMessage());
            ex.printStackTrace();
        }

        JsonObject jsonObject = JsonParser.parseString(resClient.toString()).getAsJsonObject();
        logger.logInfoResponseV1("Response Registration E-WALLET V1 :" + new GsonBuilder().setPrettyPrinting().create().toJson(jsonObject));
        // Return the response as a generic type
        return (S) nicePayResponse;
    }
}
