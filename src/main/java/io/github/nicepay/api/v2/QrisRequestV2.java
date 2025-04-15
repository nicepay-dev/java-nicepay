package io.github.nicepay.api.v2;

import io.github.nicepay.data.model.Qris;
import io.github.nicepay.data.response.v2.NICEPayResponseV2;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface QrisRequestV2 {

    @POST("nicepay/direct/v2/registration")
    Call<NICEPayResponseV2>registerQris(@Body Qris request);


}
