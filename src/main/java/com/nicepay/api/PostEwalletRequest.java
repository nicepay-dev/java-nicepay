package com.nicepay.api;

import com.nicepay.model.Cancel;
import com.nicepay.model.Ewallet;
import com.nicepay.model.InquiryStatus;
import com.nicepay.response.NICEPayResponse;
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
