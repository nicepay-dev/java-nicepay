package io.github.nicepay.data.response.snap;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ObjectAvailableAmount {

    private String value;
    private String currency;

}
