package com.nicepay.config;


import lombok.ToString;

@ToString
public class NicepayErrorResponseMessage extends BaseNICEPayResponse{

    public NicepayErrorResponseMessage(String responseCode, String responseMessage) {
        super(responseCode, responseMessage);
    }
}
