package com.nicepay.builder;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Builder
public class TokenUtil {
    @Getter
    @Setter
    private String grantType;

    @Getter
    @Setter
    private Map<String, String> additionalInfo ;

    public TokenUtil(String grantType, Map<String, String> additionalInfo) {
        this.grantType = grantType;
        this.additionalInfo = additionalInfo;
    }

}
