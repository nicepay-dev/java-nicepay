package io.github.nicepay.utils;

public class NICEPayConstants {


    private static final String SANDBOX_BASE_URL = "https://dev.nicepay.co.id/";
    private static final String PRODUCTION_BASE_URL = "https://www.nicepay.co.id/";

    public static String getSandboxBaseUrl() {
        return SANDBOX_BASE_URL;
    }

    public static String getProductionBaseUrl() {
        return PRODUCTION_BASE_URL;
    }

}
