package io.github.nicepay.exception;


import io.github.nicepay.response.BaseNICEPayResponse;
import lombok.ToString;

@ToString
public class NicepayErrorResponse extends BaseNICEPayResponse {

    public NicepayErrorResponse(String responseCode, String responseMessage) {
        super(responseCode, responseMessage);
    }
}
