package io.github.nicepay.data.model;

import lombok.*;


@Builder
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Payment {

    private String timeStamp;
    private String iMid;
    private String tXid;
    private String callBackUrl;
    private String merchantToken;

    public static class PaymentBuilder {

        public PaymentBuilder merchantToken(String timeStamp, String imid, String reffNo, String amount, String merchantKey) {
            this.merchantToken = timeStamp + imid + reffNo + amount + merchantKey;
            return this;
        }


    }

}
