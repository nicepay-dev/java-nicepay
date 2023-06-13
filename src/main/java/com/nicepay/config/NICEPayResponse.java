package com.nicepay.config;


import lombok.*;

import java.util.Map;

public class NICEPayResponse {
    @Getter
    @Setter
    private String responseCode;
    @Getter
    @Setter
    private String responseMessage;
    @Getter
    @Setter
    private String accessToken;
    @Getter
    @Setter
    private String expiresIn;
    @Getter
    @Setter
    private String tokenType;
    @Getter
    @Setter
    private Map<String, Object> virtualAccountData;
    @Getter
    @Setter
    private Map<String, String> additionalInfo;
    @Getter
    @Setter
    private Map<String, String> totalAmount;


    public NICEPayResponse(String responseCode, String responseMessage, String accessToken,String tokenType,String expiresIn) {
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
        this.accessToken = accessToken;
        this.tokenType = tokenType;
        this.expiresIn = expiresIn;
    }

    public NICEPayResponse(String responseCode, String responseMessage) {
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
    }

    //    public NICEPayResponse(String responseCode, String responseMessage) {
//        this.responseCode = responseCode;
//        this.responseMessage = responseMessage;
//    }
//
    public NICEPayResponse(String responseCode, String responseMessage, Map<String, Object> virtualAccountData, Map<String, Object> totalAmount, Map<String, String> additionalInfo) {
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
        this.virtualAccountData = virtualAccountData;
        this.virtualAccountData = totalAmount;
//        this.totalAmount = totalAmount;
        this.additionalInfo = additionalInfo;
    }
//

//
//    @Override
//    public String toString() {
//       if(accessToken == null){
//           return "{" +
//                   "responseCode='" + responseCode + '\'' +
//                   ", responseMessage='" + responseMessage + '\'' +
//                   ", virtualAccountData=" + virtualAccountData +
//                   ", additionalInfo=" + additionalInfo +
//                   ", totalAmount=" + totalAmount +
//                   '}';
//       }else{
//           return "{" +
//                   "responseCode='" + responseCode + '\'' +
//                   ", responseMessage='" + responseMessage + '\'' +
//                   ", accessToken='" + accessToken + '\'' +
//                   ", expiresIn='" + expiresIn + '\'' +
//                   ", tokenType='" + tokenType + '\'' +
//                   '}';
//       }
//    }
}
