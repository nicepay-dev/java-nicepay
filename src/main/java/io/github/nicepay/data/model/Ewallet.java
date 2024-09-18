package io.github.nicepay.data.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@Builder
public class Ewallet {

    private String partnerReferenceNo;
    private String merchantId;
    private String subMerchantId;
    private String externalStoreId;
    private String validUpTo;
    private String pointOfInitiation;
    private Map<String, Object> amount;
    private Map<String, Object> additionalInfo;
    private List<Map<String, String>> urlParam ;


    public static class EwalletBuilder {
        private Map<String, Object> totalAmount;
        private List<Map<String, String>> urlParam ;

        public EwalletBuilder amount(String value, String currency) {
            Map<String, Object> amountMap = new HashMap<>();
            amountMap.put("value", value);
            amountMap.put("currency", currency);
            this.amount = amountMap;
            return this;
        }

        public EwalletBuilder urlParam(String[][] urlParams) {
            List<Map<String, String>> urlParamList = new ArrayList<>();
            for (String[] params : urlParams) {
                if (params.length == 3) {
                    Map<String, String> paramListMap = new HashMap<>();
                    paramListMap.put("url", params[0]);
                    paramListMap.put("type", params[1]);
                    paramListMap.put("isDeeplink", params[2]);
                    urlParamList.add(paramListMap);
                }
            }
            this.urlParam = urlParamList;
            return this;
        }
    }

    public Ewallet(String partnerReferenceNo, String merchantId, String subMerchantId, String externalStoreId, String validUpTo, String pointOfInitiation, Map<String, Object> amount, Map<String, Object> additionalInfo, List<Map<String, String>> urlParam) {
        this.partnerReferenceNo = partnerReferenceNo;
        this.merchantId = merchantId;
        this.subMerchantId = subMerchantId;
        this.externalStoreId = externalStoreId;
        this.validUpTo = validUpTo;
        this.pointOfInitiation = pointOfInitiation;
        this.amount = amount;
        this.additionalInfo = additionalInfo;
        this.urlParam = urlParam;
    }
}


