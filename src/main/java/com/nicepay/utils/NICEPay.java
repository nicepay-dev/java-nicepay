package com.nicepay.utils;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Builder
public class NICEPay {
    @Getter
    @Setter
    private String partnerId;
    @Getter
    @Setter
    private String clientSecret;
    @Getter
    @Setter
    private boolean isProduction;

    public  String getSnapApiURL() {
        if (isProduction) {
            return NICEPayConstants.getProductionBaseUrl();
        }else{
            return  NICEPayConstants.getSandboxBaseUrl();
        }
    }

    public NICEPay(String partnerId, String clientSecret, Boolean isProduction) {
        this.partnerId = partnerId;
        this.clientSecret = clientSecret;
        this.isProduction = isProduction;
    }

    @Override
    public String toString() {
        return "NICEPay{" +
                "partnerId='" + partnerId + '\'' +
                ", clientSecret='" + clientSecret + '\'' +
                ", isProduction=" + isProduction +
                '}';
    }
}
