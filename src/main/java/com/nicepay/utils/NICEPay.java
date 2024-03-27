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

    @Getter
    @Setter
    private String externalID;
    @Getter
    @Setter
    private String timestamp;
    @Getter
    @Setter
    private String privateKey;

    public NICEPay(String partnerId, String clientSecret, boolean isProduction, String externalID, String timestamp,String privateKey) {
        this.partnerId = partnerId;
        this.clientSecret = clientSecret;
        this.isProduction = isProduction;
        this.externalID = externalID;
        this.timestamp = timestamp;
        this.privateKey = privateKey;
    }

    public static class NICEPayBuilder {
        public String getSnapApiURL() {
        if (isProduction) {
            return NICEPayConstants.getProductionBaseUrl();
        }else{
            return  NICEPayConstants.getSandboxBaseUrl();
        }

    }

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
