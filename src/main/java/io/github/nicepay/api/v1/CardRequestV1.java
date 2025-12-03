package io.github.nicepay.api.v1;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

import java.util.Map;

public interface CardRequestV1 {

    @FormUrlEncoded
    @POST("/nicepay/api/onePassToken.do")
    Call<ResponseBody> onePassToken(@FieldMap Map<String, Object> fields);

    @FormUrlEncoded
    @POST("/nicepay/api/recurringToken.do")
    Call<ResponseBody> recurringToken(@FieldMap Map<String, Object> fields);

    @FormUrlEncoded
    @POST("/nicepay/api/recurringTrans.do")
    Call<ResponseBody> recurringTrans(@FieldMap Map<String, String> fields);

    @FormUrlEncoded
    @POST("/nicepay/api/tokenize.do")
    Call<ResponseBody> tokenize(@FieldMap Map<String, String> fields);

    @FormUrlEncoded
    @POST("/nicepay/api/checkToken.do")
    Call<ResponseBody> checkToken(@FieldMap Map<String, String> fields);

    @FormUrlEncoded
    @POST("/nicepay/api/removeToken.do")
    Call<ResponseBody> removeToken(@FieldMap Map<String, String> fields);

    @FormUrlEncoded
    @POST("/nicepay/api/captureTrans.do")
    Call<ResponseBody> captureTrans(@FieldMap Map<String, String> fields);

}
