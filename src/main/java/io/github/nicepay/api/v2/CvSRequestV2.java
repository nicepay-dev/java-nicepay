package io.github.nicepay.api.v2;

import io.github.nicepay.data.model.ConvenienceStore;
import io.github.nicepay.data.response.v2.NICEPayResponseV2;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface CvSRequestV2 {

    @POST("nicepay/direct/v2/registration")
    Call<NICEPayResponseV2>registCvS(@Body ConvenienceStore request);


}
