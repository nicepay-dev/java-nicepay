package com.nicepay.retrofit;

import com.nicepay.builder.VirtualAccount;
import com.nicepay.config.NICEPayResponse;
import com.nicepay.builder.TokenUtil;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface PostRequest {


    //    @POST("/8bb3f5b2-7d08-48de-a4f3-8aba2ee53388")
    @POST("nicepay/v1.0/access-token/b2b")
    Call<NICEPayResponse>getToken(@Body TokenUtil request);

//    @POST("/8bb3f5b2-7d08-48de-a4f3-8aba2ee53388")
    @POST("nicepay/api/v1.0/transfer-va/create-va")
    Call<NICEPayResponse>createVa(@Body VirtualAccount request);






}