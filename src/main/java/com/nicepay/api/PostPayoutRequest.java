package com.nicepay.api;

import com.nicepay.model.Payout;
import com.nicepay.response.NICEPayResponse;
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


}