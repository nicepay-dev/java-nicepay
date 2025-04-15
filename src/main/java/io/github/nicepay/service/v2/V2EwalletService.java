package io.github.nicepay.service.v2;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.github.nicepay.api.v2.EwalletRequestV2;
import io.github.nicepay.data.model.Ewallet;
import io.github.nicepay.data.response.v2.NICEPayResponseV2;
import io.github.nicepay.utils.*;
import okhttp3.ResponseBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.text.MessageFormat;

public class V2EwalletService extends V2CommonService {

    private static final Logger LOGGER = LoggerFactory.getLogger("[V2 - E-WALLET]");

    public static <S> S callEwalletRegistration(Ewallet data, NICEPay config) throws IOException {

        LOGGER.info(LoggerPrint.LOG_DEFAULT, "START CALL V2 E-WALLET REGISTRATION");

        Gson gson = new Gson();
        EwalletRequestV2 request = ApiUtils.createServiceV2(EwalletRequestV2.class, config);

//        Update merchant token
        data.setMerchantToken(SHA256Util.encrypt(data.getMerchantToken()));

        Call<NICEPayResponseV2> callSync = request.registerEwallet(data);

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
            LOGGER.info(LoggerPrint.LOG_RESPONSE, "Response register E-WALLET transaction :" + new GsonBuilder().setPrettyPrinting().create().toJson(jsonObject));
            LOGGER.info(LoggerPrint.LOG_DEFAULT, "END CALL V2 E-WALLET REGISTRATION");

        } catch (Exception ex) {
            LOGGER.error(LoggerPrint.LOG_ERROR, "E-WALLET transaction registration failed :");
            ex.printStackTrace();
        }
        return (S) nicePayResponse;
    }


}
