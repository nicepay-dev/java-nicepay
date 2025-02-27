package io.github.nicepay.data;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public final class TestingConstants {


    private TestingConstants() {}

    static SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
    public static final String TIMESTAMP = f.format(new Date());
    static Random rand = new Random();
    static int random = rand.nextInt(10000);
    public static final String EXTERNAL_ID = "OrdNo" + TIMESTAMP.substring(0, 10).replace("-","") + TIMESTAMP.substring(11,19).replace(":","") + random;
    public static final String PARTNER_ID = "";
    public static final String CLIENT_SECRET  = "";
    public static final String PRIVATE_KEY = "";
    public static final String PUBLIC_KEY = "";


    static SimpleDateFormat v2_format = new SimpleDateFormat("yyyyMMddHHmmss");
    public static final String V2_TIMESTAMP = v2_format.format(new Date());
    public static final String MERCHANT_KEY = "";
    public static final String I_MID_NORMALCLOSED = "";
    public static final String I_MID_INSTLMNT = "";
    public static final String INSTLMNT_CLIENT_SECRET = "";
    public static final String I_MID_RECURRING = "";
    public static final String I_MID_PAC = "";

    public static final String I_MID_QRIS = "";
    public static final String QRIS_CLIENT_SECRET = "";
    public static final String QRIS_STORE_ID = "";

    public static final String I_MID_EWALLET = "";

    public static final String I_MID = "";
    public static final String CLOUD_CLIENT_SECRET  = "";
    public static final String CLOUD_PRIVATE_KEY = "";
    public static final String NORMALTEST_CLOUD_PRIVATE_KEY = "";

}

