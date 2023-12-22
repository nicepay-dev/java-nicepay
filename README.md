# JAVA - NICEPAY

NICEPAY ❤️Java!

This is the Official Java API client/library for NICEPAY Payment API. Visit [Java Library](https://github.com/nicepay-dev/java-nicepay). More information about the product and see documentation at [NICEPAY Docs](https://docs.nicepay.co.id/) for more technical details.


### 1 Manual Installation

You can clone or [download](https://github.com/nicepay-dev/java-nicepay) this repository. And export to JAR for use.


## 2. Usage

### 2.1 Choose Product/Method

We have one of payment that you can use:

- [Snap](#22A-snap) - Customizable payment popup will appear on **your web/app** (no redirection).



### 2.2 Client Initialization and Configuration


Get your clientSecret from [Nicepay Dashboard](https://bo.nicepay.co.id/)


Change your credentials before export library to jar

NICEPayConstants
```java
private static final String PRIVATE_KEY = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAInJe1G22R2fMchIE6BjtYRqyMj6lurP/zq6vy79WaiGKt0Fxs4q3Ab4ifmOXd97ynS5f0JRfIqakXDcV/e2rx9bFdsS2HORY7o5At7D5E3tkyNM9smI/7dk8d3O0fyeZyrmPMySghzgkR3oMEDW1TCD5q63Hh/oq0LKZ/4Jjcb9AgMBAAECgYA4Boz2NPsjaE+9uFECrohoR2NNFVe4Msr8/mIuoSWLuMJFDMxBmHvO+dBggNr6vEMeIy7zsF6LnT32PiImv0mFRY5fRD5iLAAlIdh8ux9NXDIHgyera/PW4nyMaz2uC67MRm7uhCTKfDAJK7LXqrNVDlIBFdweH5uzmrPBn77foQJBAMPCnCzR9vIfqbk7gQaA0hVnXL3qBQPMmHaeIk0BMAfXTVq37PUfryo+80XXgEP1mN/e7f10GDUPFiVw6Wfwz38CQQC0L+xoxraftGnwFcVN1cK/MwqGS+DYNXnddo7Hu3+RShUjCz5E5NzVWH5yHu0E0Zt3sdYD2t7u7HSr9wn96OeDAkEApzB6eb0JD1kDd3PeilNTGXyhtIE9rzT5sbT0zpeJEelL44LaGa/pxkblNm0K2v/ShMC8uY6Bbi9oVqnMbj04uQJAJDIgTmfkla5bPZRR/zG6nkf1jEa/0w7i/R7szaiXlqsIFfMTPimvRtgxBmG6ASbOETxTHpEgCWTMhyLoCe54WwJATmPDSXk4APUQNvX5rr5OSfGWEOo67cKBvp5Wst+tpvc6AbIJeiRFlKF4fXYTb6HtiuulgwQNePuvlzlt2Q8hqQ==";
private static final String CLIENT_KEY = "IONPAYTEST";
private static final String CLIENT_SECRET = "33F49GnCMS1mFYlGXisbUDzVf2ATWCl9k3R++d5hDd3Frmuos/XLx8XhXpe+LDYAbpGKZYSwtlyyLOtS/8aD7A==";
```
If you want deploy to production, you need change this code into getProductionBaseUrl()
```java
    private static Retrofit.Builder builder
            = new Retrofit.Builder()
            .baseUrl(NICEPayConstants.getProductionBaseUrl())
            .addConverterFactory(GsonConverterFactory.create())
            ;
```


### 2.3 Snap

#### Get Virtual Account with Snap

```java
public Object getToken() throws IOException {
        Map<String, String> additionalInfo = new HashMap<>();
        TokenUtil util =  TokenUtil.builder()
        .grantType("client_credentials")
        .additionalInfo(additionalInfo)
        .build();
        return SnapTokenUtilService.callGetToken(util);
}

NICEPayResponse responseToken = (NICEPayResponse) getToken();
var accessToken = Optional.ofNullable(responseToken)
    .map(token -> responseToken.getAccessToken())
    .orElseThrow(() -> new IllegalArgumentException("Token is null"));
Map<String, String> totalAmount = new HashMap<>();
totalAmount.put("value","11000.00");
totalAmount.put("currency","IDR");

Map<String, String> additionalInfo = new HashMap<>();
additionalInfo.put("bankCd","BBBA");
additionalInfo.put("goodsNm","TESTGoodsNm");
additionalInfo.put("dbProcessUrl","https://merchant.com/test");
additionalInfo.put("vacctValidDt","");
additionalInfo.put("vacctValidTm","");
additionalInfo.put("msId","");
additionalInfo.put("msFee","");
additionalInfo.put("msFeeType","");
additionalInfo.put("mbFee","");
additionalInfo.put("mbFeeType","");

VirtualAccount virtualAccount = VirtualAccount.builder()
    .partnerServiceId("TESTPartner")
    .customerNo("")
    .virtualAccountNo("")
    .virtualAccountName("TESTVaName")
    .trxId("TESTTrxId")
    .totalAmount(totalAmount)
    .additionalInfo(additionalInfo)
    .build();

NICEPayResponse Result =
    SnapVaService.callServiceVA(virtualAccount,accessToken);
```

## Notes

#### Not Designed for Frontend Usage

This library/package is mainly for backend (Java server) usage:

- This is mainly for backend usage, to do **secure server-to-server/backend-to-backend API call**.
- Your API **privateKey may also be exposed to public** if you are using this **on frontend**.

## Get help

- [NICEPAY Docs](https://docs.nicepay.co.id/)
- [NICEPAY Dashboard ](https://bo.nicepay.co.id/)
- [SNAP documentation](https://docs.nicepay.co.id/nicepay-api-snap)
- Can't find answer you looking for? email to [cs@nicepay.co.id](mailto:cs@nicepay.co.id)

