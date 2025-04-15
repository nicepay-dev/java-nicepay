package io.github.nicepay.api.v2;

import io.github.nicepay.data.model.Redirect;
import io.github.nicepay.data.response.v2.NICEPayResponseV2;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RedirectRequestV2 {

    @POST("nicepay/redirect/v2/registration")
    Call<NICEPayResponseV2>registRedirectV2(@Body Redirect request);

}
