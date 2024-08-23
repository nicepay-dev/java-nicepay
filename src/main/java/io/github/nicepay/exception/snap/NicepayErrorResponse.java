package io.github.nicepay.exception.snap;


import io.github.nicepay.data.response.snap.BaseNICEPayResponse;
import lombok.ToString;

@ToString
public class NicepayErrorResponse extends BaseNICEPayResponse {

    public NicepayErrorResponse(String responseCode, String responseMessage) {
        super(responseCode, responseMessage);
    }
}
