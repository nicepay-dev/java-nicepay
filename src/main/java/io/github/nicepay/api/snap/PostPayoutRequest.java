package io.github.nicepay.api.snap;

import io.github.nicepay.data.model.Cancel;
import io.github.nicepay.data.model.InquiryStatus;
import io.github.nicepay.data.model.Payout;
import io.github.nicepay.data.response.snap.NICEPayResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface PostPayoutRequest {


    //    @POST("/8bb3f5b2-7d08-48de-a4f3-8aba2ee53388")
    @POST("nicepay/api/v1.0/transfer/registration")
    Call<NICEPayResponse>registPayout(@Body Payout request);

//    @POST("/8bb3f5b2-7d08-48de-a4f3-8aba2ee53388")
    @POST("nicepay/api/v1.0/transfer/approve")
    Call<NICEPayResponse>approvePayout(@Body Payout request);

    @POST("nicepay/api/v1.0/transfer/inquiry")
    Call<NICEPayResponse>checkStatusPayout(@Body InquiryStatus request);

    @POST("nicepay/v1.0/api/transfer/cancel")
    Call<NICEPayResponse>cancelPayout(@Body Cancel request);

    @POST("nicepay/api/v1.0/transfer/balance-inquiry")
    Call<NICEPayResponse>checkBalancePayout(@Body Payout request);

    @POST("nicepay/v1.0/api/transfer/reject")
    Call<NICEPayResponse>rejectPayout(@Body Cancel request);
}