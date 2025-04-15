package io.github.nicepay.service.v1;

import io.github.nicepay.api.v1.RequestV1;
import io.github.nicepay.data.model.Card;
import io.github.nicepay.data.response.v1.NICEPayResponseV1;
import io.github.nicepay.utils.*;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

public class V1CardService extends V1CommonService {

    private static LoggerPrint logger = new LoggerPrint();

    public static <S> S callCardRedirectRegistration(Card data, NICEPay config) throws IOException {

        RequestV1 cardV1 = ApiUtils.createServiceV1(RequestV1.class, config);

        // Encrypt the merchant token
        data.setMerchantToken(SHA256Util.encrypt(data.getMerchantToken()));

        // Prepare the API call
        Call<ResponseBody> callSync = cardV1.orderRegist(ModelUtils.toMap(data));

        Response<ResponseBody> responseExecute;
        NICEPayResponseV1 nicePayResponse = new NICEPayResponseV1();
        String responseContent = null;

        try {
            // Execute the call and retrieve the response
            responseExecute = callSync.execute();

            // Handle successful response
            if (responseExecute.isSuccessful() && responseExecute.body() != null) {
                responseContent = responseExecute.body().string();
                nicePayResponse = ApiUtils.getApiMessageObject(responseContent, nicePayResponse);
                logger.logInfoResponseV1("Response Card Regist V1: " + responseContent);
            } else {
                // Handle error response
                ResponseBody errorResponse = responseExecute.errorBody();
                if (errorResponse != null) {
                    responseContent = errorResponse.string();
                    logger.logErrorV1("Error Response Card Regist V1: " + responseContent);
                }
            }

            logger.logInfoV1("END CALL V1 CARD REGISTRATION REQUEST");
        } catch (IOException ex) {
            logger.logErrorV1("IOException during V1 Card Registration Request: " + ex.getMessage());
            throw ex; // Re-throw IOException to ensure the caller is aware of the failure
        } catch (Exception ex) {
            logger.logErrorV1("Unexpected error during V1 Card Registration Request: " + ex.getMessage());
            ex.printStackTrace();
        }

        // Return the response as a generic type
        return (S) nicePayResponse;
    }

}
