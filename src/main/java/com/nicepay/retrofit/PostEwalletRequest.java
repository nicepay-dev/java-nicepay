package com.nicepay.retrofit;

import com.nicepay.builder.Ewallet;
import com.nicepay.config.NICEPayResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface PostEwalletRequest {

    @POST("nicepay/api/v1.0/debit/payment-host-to-host")
    Call<NICEPayResponse> paymentEwallet(@Body Ewallet request);

    @POST("nicepay/api/v1.0/debit/status")
    Call<NICEPayResponse> checkStatusEwallet(@Body Ewallet request);

    @POST("nicepay/api/v1.0/debit/refund")
    Call<NICEPayResponse> refundEwallet(@Body Ewallet request);

}
