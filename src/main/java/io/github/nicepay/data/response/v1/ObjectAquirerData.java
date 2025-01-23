package io.github.nicepay.data.response.v1;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonSerialize
public class ObjectAquirerData {

    private String rrn;

}
