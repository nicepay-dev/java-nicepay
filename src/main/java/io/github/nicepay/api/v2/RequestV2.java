package io.github.nicepay.api.v2;

import io.github.nicepay.data.model.Cancel;
import io.github.nicepay.data.model.InquiryStatus;
import io.github.nicepay.data.response.v2.NICEPayResponseV2;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RequestV2 {

    @POST("nicepay/direct/v2/inquiry")
    Call<NICEPayResponseV2> inquiryStatusV2(@Body InquiryStatus request);

    @POST("nicepay/direct/v2/cancel")
    Call<NICEPayResponseV2>cancelTransactionV2(@Body Cancel request);

}
