package io.github.nicepay.api.v2;

import io.github.nicepay.data.model.Payout;
import io.github.nicepay.data.response.v2.NICEPayResponseV2;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface PayoutRequestV2 {

    @POST("nicepay/api/direct/v2/requestPayout")
    Call<NICEPayResponseV2>requestPayout(@Body Payout request);

    @POST("nicepay/api/direct/v2/balanceInquiry")
    Call<NICEPayResponseV2>balanceInquiry(@Body Payout request);

    @POST("nicepay/api/direct/v2/approvePayout")
    Call<NICEPayResponseV2>approvePayout(@Body Payout request);

    @POST("nicepay/api/direct/v2/rejectPayout")
    Call<NICEPayResponseV2>rejectPayout(@Body Payout request);

    @POST("nicepay/api/direct/v2/cancelPayout")
    Call<NICEPayResponseV2>cancelPayout(@Body Payout request);


}
