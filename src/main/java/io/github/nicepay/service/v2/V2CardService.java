package io.github.nicepay.service.v2;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.github.nicepay.api.v2.CardRequestV2;
import io.github.nicepay.data.model.Card;
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

import static io.github.nicepay.utils.SHA256Util.encrypt;
import static io.github.nicepay.utils.SHA256Util.encryptWithPublicKeyString;

public class V2CardService extends V2CommonService {

    private static final Logger LOGGER = LoggerFactory.getLogger("[V2 - Card]");

    public static <S> S callV2CardRegistration(Card data, NICEPay config) throws IOException {

        LOGGER.info(LoggerPrint.LOG_DEFAULT, "START CALL V2 CARD REGISTRATION");

        Gson gson = new Gson();
        CardRequestV2 request = ApiUtils.createServiceV2(CardRequestV2.class, config);

//        Update merchant token
        data.setMerchantToken(SHA256Util.encrypt(data.getMerchantToken()));

        Call<NICEPayResponseV2> callSync = request.registerCardV2(data);
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

            jsonObject = JsonParser.parseString(resClient.toString()).getAsJsonObject();
            LOGGER.info(LoggerPrint.LOG_RESPONSE, "Response register card transaction :" + new GsonBuilder().setPrettyPrinting().create().toJson(jsonObject));
            LOGGER.info(LoggerPrint.LOG_DEFAULT, "END CALL V2 CARD REGISTRATION");

        } catch (Exception ex) {
            LOGGER.error(LoggerPrint.LOG_ERROR, "Card transaction registration failed :");
            ex.printStackTrace();
        }
        return (S) nicePayResponse;
    }

    public static <S> S callV2CardPaymentRequest(Card data, NICEPay config) throws Exception {
        LOGGER.info(LoggerPrint.LOG_DEFAULT, "START CALL V2 CARD PAYMENT REQUEST");

        Gson gson = new Gson();
        CardRequestV2 request = ApiUtils.createServiceV2(CardRequestV2.class, config);

        //        Update merchant token
        data.setMerchantToken(SHA256Util.encrypt(data.getMerchantToken()));

        //Check if card detail want to be encrypted or not
        if(data.getIsEncryptedCard().equals("1")){
            data.setCardNo(SHA256Util.encryptWithPublicKeyString(data.getCardNo(), data.getPublicKey()));
            data.setCardCvv(SHA256Util.encryptWithPublicKeyString(data.getCardCvv(), data.getPublicKey()));
            data.setCardExpYymm(SHA256Util.encryptWithPublicKeyString(data.getCardExpYymm(), data.getPublicKey()));
            data.setCardHolderNm(SHA256Util.encryptWithPublicKeyString(data.getCardHolderNm(), data.getPublicKey()));
            data.setPublicKey(SHA256Util.encrypt(data.getPublicKey()));
        }

        Call<ResponseBody> callSync = request.requestPaymentCardV2(
                data.getTimeStamp(),
                data.getTXid(),
                data.getReferenceNo(),
                data.getMerchantToken(),
                data.getCardNo(),
                data.getCardExpYymm(),
                data.getCardCvv(),
                data.getCardHolderNm(),
                data.getCallBackUrl(),
                data.getRecurringToken(),
                data.getPreauthToken(),
                data.getIsEncryptedCard(),
                data.getPublicKey()
        );
        Response<ResponseBody> response;
        ResponseBody nicePayResponse;
        ResponseBody errorResponse;
        String resClient = null;

        try {
            response = callSync.execute();
            nicePayResponse = response.body();
            errorResponse = response.errorBody();

            if (nicePayResponse == null) {
                resClient = errorResponse.string();
            } else {
                resClient = nicePayResponse.string();
            }

            LOGGER.info(LoggerPrint.LOG_RESPONSE, "Response Payment Request :" + resClient);
            LOGGER.info(LoggerPrint.LOG_DEFAULT, "END CALL V2 CARD PAYMENT REQUEST");

        } catch (Exception ex) {
            LOGGER.error(LoggerPrint.LOG_ERROR, "Card request payment failed :");
            ex.printStackTrace();
        }
        return (S) resClient;
    }


}
