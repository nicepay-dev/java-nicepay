package com.nicepay.api;

import com.nicepay.model.Cancel;
import com.nicepay.model.InquiryStatus;
import com.nicepay.model.VirtualAccount;
import com.nicepay.response.NICEPayResponse;
import com.nicepay.model.AccessToken;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.HTTP;
import retrofit2.http.POST;

public interface PostVaRequest {


    //    @POST("/8bb3f5b2-7d08-48de-a4f3-8aba2ee53388")
    @POST("nicepay/v1.0/access-token/b2b")
    Call<NICEPayResponse>getToken(@Body AccessToken request);

//    @POST("/8bb3f5b2-7d08-48de-a4f3-8aba2ee53388")
    @POST("nicepay/api/v1.0/transfer-va/create-va")
    Call<NICEPayResponse>createVa(@Body VirtualAccount request);

    @POST("nicepay/api/v1.0/transfer-va/status")
    Call<NICEPayResponse> inquiryStatusVa(@Body InquiryStatus request);

    @HTTP(method = "DELETE", path ="nicepay/api/v1.0/transfer-va/delete-va", hasBody = true)
    Call<NICEPayResponse>cancelVa(@Body Cancel request);






}