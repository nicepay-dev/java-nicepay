package io.github.nicepay.api.v1;

import io.github.nicepay.data.response.v1.NICEPayResponseV1;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

import java.util.Map;

public interface EwalletRequestV1 {

    @FormUrlEncoded
    @POST("/nicepay/api/ewalletTrans.do")
    Call<NICEPayResponseV1> ewalletTrans(@FieldMap Map<String, String> fields);


}
