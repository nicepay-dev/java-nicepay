package com.nicepay.api;

import com.nicepay.model.Ewallet;
import com.nicepay.response.NICEPayResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

import java.util.Map;

public interface PostEwalletRequest {

    @POST("nicepay/api/v1.0/debit/payment-host-to-host")
    Call<NICEPayResponse> paymentEwallet(@Body Ewallet request);

    @POST("nicepay/api/v1.0/debit/status")
    Call<NICEPayResponse> checkStatusEwallet(@Body Map<String, Object> request);

    @POST("nicepay/api/v1.0/debit/refund")
    Call<NICEPayResponse> refundEwallet(@Body Map<String, Object> request);

}
