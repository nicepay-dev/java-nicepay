package io.github.nicepay.data.response.v1;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonSerialize
public class ObjectData implements Serializable {

    @JsonProperty("tXid")
    private String tXid;
    private String resultCd;
    private String resultMsg;
    private String requestURL;

}
