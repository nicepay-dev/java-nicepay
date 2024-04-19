package com.nicepay.api;

import com.nicepay.model.Cancel;
import com.nicepay.model.InquiryStatus;
import com.nicepay.model.Payout;
import com.nicepay.model.Qris;
import com.nicepay.response.NICEPayResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface PostQrisRequest {
    @POST("nicepay/api/v1.0/qr/qr-mpm-generate")
    Call<NICEPayResponse> registQris(@Body Qris request);


    @POST("nicepay/api/v1.0/qr/qr-mpm-query")
    Call<NICEPayResponse>checkStatusQris(@Body InquiryStatus request);

    @POST("nicepay/api/v1.0/qr/qr-mpm-refund")
    Call<NICEPayResponse>refundQris(@Body Cancel request);
}