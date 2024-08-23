package io.github.nicepay.api.snap;

import io.github.nicepay.data.model.Cancel;
import io.github.nicepay.data.model.InquiryStatus;
import io.github.nicepay.data.model.Qris;
import io.github.nicepay.data.response.snap.NICEPayResponse;
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
