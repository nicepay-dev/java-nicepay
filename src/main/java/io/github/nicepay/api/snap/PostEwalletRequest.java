package io.github.nicepay.api.snap;

import io.github.nicepay.data.model.Cancel;
import io.github.nicepay.data.model.Ewallet;
import io.github.nicepay.data.model.InquiryStatus;
import io.github.nicepay.data.response.snap.NICEPayResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface PostEwalletRequest {

    @POST("nicepay/api/v1.0/debit/payment-host-to-host")
    Call<NICEPayResponse> paymentEwallet(@Body Ewallet request);

    @POST("nicepay/api/v1.0/debit/status")
    Call<NICEPayResponse> inquiryStatusEwallet(@Body InquiryStatus request);

    @POST("nicepay/api/v1.0/debit/refund")
    Call<NICEPayResponse> refundEwallet(@Body Cancel request);

}
