package io.github.nicepay.data.model;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Builder
public class AccessToken {
    @Getter
    @Setter
    private String grantType;

    @Getter
    @Setter
    private Map<String, String> additionalInfo ;

    public AccessToken(String grantType, Map<String, String> additionalInfo) {
        this.grantType = grantType;
        this.additionalInfo = additionalInfo;
    }

}
