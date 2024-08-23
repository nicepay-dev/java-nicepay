package io.github.nicepay.data.response.v2;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class BaseNICEPayResponseV2 {
    private String resultCd;
    private String resultMsg;
}
