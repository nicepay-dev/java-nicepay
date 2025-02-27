package io.github.nicepay.utils;

public class NICEPayConstants {


    private static final String SANDBOX_BASE_URL = "https://dev.nicepay.co.id/";
    private static final String PRODUCTION_BASE_URL = "https://www.nicepay.co.id/";

    private static final String AWS_SANDBOX_BASE_URL = "https://dev-services.nicepay.co.id";
    private static final String AWS_PRODUCTION_BASE_URL = "https://services.nicepay.co.id";

    public static String getSandboxBaseUrl(boolean isCloudServer) {
        if (isCloudServer) {
            return AWS_SANDBOX_BASE_URL;
        } else {
            return SANDBOX_BASE_URL;
        }
    }

    public static String getProductionBaseUrl(boolean isCloudServer) {
        if (isCloudServer) {
            return AWS_PRODUCTION_BASE_URL;
        } else {
            return PRODUCTION_BASE_URL;

        }
    }

}
