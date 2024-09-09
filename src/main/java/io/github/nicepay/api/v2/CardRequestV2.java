package io.github.nicepay.api.v2;

import io.github.nicepay.data.model.Card;
import io.github.nicepay.data.response.v2.NICEPayResponseV2;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface CardRequestV2 {

    @POST("nicepay/direct/v2/registration")
    Call<NICEPayResponseV2> registerCardV2(@Body Card request);

    @FormUrlEncoded
    @POST("nicepay/direct/v2/payment")
    Call<ResponseBody> requestPaymentCardV2(
            @Field("timeStamp") String timeStamp,
            @Field("tXid") String tXid,
            @Field("referenceNo") String referenceNo,
            @Field("merchantToken") String merchantToken,
            @Field("cardNo") String cardNo,
            @Field("cardExpYymm") String cardExpYymm,
            @Field("cardCvv") String cardCvv,
            @Field("cardHolderNm") String cardHolderNm,
            @Field("callBackUrl") String callBackUrl,
            @Field("recurringToken") String recurringToken,
            @Field("preauthToken") String preauthToken
    );
}
