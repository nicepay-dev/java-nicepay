package com.nicepay.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class BaseNICEPayResponse {

    private String responseCode;
    private String responseMessage;

}
