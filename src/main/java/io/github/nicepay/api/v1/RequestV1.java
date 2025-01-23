package io.github.nicepay.api.v1;

import io.github.nicepay.data.response.v1.NICEPayResponseV1;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

import java.util.Map;

public interface RequestV1 {

    @FormUrlEncoded
    @POST("/nicepay/api/orderRegist.do")
    Call<ResponseBody> orderRegist(@FieldMap Map<String, String> fields);

    @FormUrlEncoded
    @POST("/nicepay/api/onePassStatus.do")
    Call<NICEPayResponseV1> inquiryStatus(@FieldMap Map<String, String> fields);


}
