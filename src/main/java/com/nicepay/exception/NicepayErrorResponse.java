package com.nicepay.exception;


import com.nicepay.response.BaseNICEPayResponse;
import lombok.ToString;

@ToString
public class NicepayErrorResponse extends BaseNICEPayResponse {

    public NicepayErrorResponse(String responseCode, String responseMessage) {
        super(responseCode, responseMessage);
    }
}
