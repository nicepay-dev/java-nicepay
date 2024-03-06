package com.nicepay.api;

import com.nicepay.model.AccessToken;
import com.nicepay.model.VirtualAccount;
import com.nicepay.response.NICEPayResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface PostTokenRequest {


    //    @POST("/8bb3f5b2-7d08-48de-a4f3-8aba2ee53388")
    @POST("nicepay/v1.0/access-token/b2b")
    Call<NICEPayResponse>getToken(@Body AccessToken request);








}