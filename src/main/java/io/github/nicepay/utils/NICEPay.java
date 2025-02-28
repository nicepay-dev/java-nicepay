package io.github.nicepay.utils;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class NICEPay {

    private String partnerId;

    private String clientSecret;

    private boolean isProduction;

    private boolean isCloudServer;

    private String externalID;

    private String timestamp;

    private String privateKey;

    public NICEPay(String partnerId, String clientSecret, boolean isProduction, boolean isCloudServer, String externalID, String timestamp, String privateKey) {
        this.partnerId = partnerId;
        this.clientSecret = clientSecret;
        this.isProduction = isProduction;
        this.isCloudServer = isCloudServer;
        this.externalID = externalID;
        this.timestamp = timestamp;
        this.privateKey = privateKey;
    }

    public String getNICEPayBaseUrl() {

        if (isProduction) {
            return NICEPayConstants.getProductionBaseUrl(isCloudServer);
        } else {
            return NICEPayConstants.getSandboxBaseUrl(isCloudServer);
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
