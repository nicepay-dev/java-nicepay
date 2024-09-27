# JAVA - NICEPAY

NICEPAY ❤️Java!

This is the Official Java API client/library for NICEPAY Payment API. Visit [Java Library](https://github.com/nicepay-dev/java-nicepay). 
More information about the product and see documentation at [NICEPAY Docs](https://docs.nicepay.co.id/) for more technical details.
This library provides access to Nicepay BI SNAP and V2 APIs.

This documentation provides an overview of accessing Nicepay BI SNAP APIs. For detailed information on V2 APIs, please refer to the [README_V2.md](https://github.com/nicepay-dev/java-nicepay/tree/main/src).

## 1. Installation
### 1.1 Manual
You can clone or [download](https://github.com/nicepay-dev/java-nicepay) our source code and compile it into a Jar, 
then import the jar manually into your project.
### 1.2 Maven Repository
Add our library as a dependency on your pom.xml
```xml
		<dependency>
			<groupId>io.github.nicepay-dev</groupId>
			<artifactId>nicepay-java-client</artifactId>
			<version>1.0.4</version>
		</dependency>
```

## 2. Usage
### 2.1 Client Initialization and Configuration
Get your Credentials from [Nicepay Dashboard](https://bo.nicepay.co.id/)
Initialize Nicepay Config

> **WARNING:** Credentials used here are for testing purposes only.


```java
import io.github.utils.nicepay.NICEPay;

NICEPay nicePayConfig = NICEPay.builder()
        .isProduction(false)
        .clientSecret("33F49GnCMS1mFYlGXisbUDzVf2ATWCl9k3R++d5hDd3Frmuos/XLx8XhXpe+LDYAbpGKZYSwtlyyLOtS/8aD7A==")
        .partnerId("IONPAYTEST")
        .privateKey("MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAInJe1G22R2fMchIE6BjtYRqyMj6lurP/zq6vy79WaiGKt0Fxs4q3Ab4ifmOXd97ynS5f0JRfIqakXDcV/e2rx9bFdsS2HORY7o5At7D5E3tkyNM9smI/7dk8d3O0fyeZyrmPMySghzgkR3oMEDW1TCD5q63Hh/oq0LKZ/4Jjcb9AgMBAAECgYA4Boz2NPsjaE+9uFECrohoR2NNFVe4Msr8/mIuoSWLuMJFDMxBmHvO+dBggNr6vEMeIy7zsF6LnT32PiImv0mFRY5fRD5iLAAlIdh8ux9NXDIHgyera/PW4nyMaz2uC67MRm7uhCTKfDAJK7LXqrNVDlIBFdweH5uzmrPBn77foQJBAMPCnCzR9vIfqbk7gQaA0hVnXL3qBQPMmHaeIk0BMAfXTVq37PUfryo+80XXgEP1mN/e7f10GDUPFiVw6Wfwz38CQQC0L+xoxraftGnwFcVN1cK/MwqGS+DYNXnddo7Hu3+RShUjCz5E5NzVWH5yHu0E0Zt3sdYD2t7u7HSr9wn96OeDAkEApzB6eb0JD1kDd3PeilNTGXyhtIE9rzT5sbT0zpeJEelL44LaGa/pxkblNm0K2v/ShMC8uY6Bbi9oVqnMbj04uQJAJDIgTmfkla5bPZRR/zG6nkf1jEa/0w7i/R7szaiXlqsIFfMTPimvRtgxBmG6ASbOETxTHpEgCWTMhyLoCe54WwJATmPDSXk4APUQNvX5rr5OSfGWEOo67cKBvp5Wst+tpvc6AbIJeiRFlKF4fXYTb6HtiuulgwQNePuvlzlt2Q8hqQ==")
        .externalID("ordNo20240926110022") // unique per request
        .timestamp("2024-09-25T17:02:46+07:00")
        .build();
```

### 2.2 Request for Access-Token

```java

import io.github.model.nicepay.AccessToken;
import io.github.response.nicepay.NICEPayResponse;
import io.github.service.nicepay.SnapTokenService;

//Your previously initialized config
//NICEPay nicePayConfig = NICEPay.builder()...build();
Map<String, String> additionalInfo = new HashMap<>();
AccessToken accessTokenRequest = AccessToken.builder()
        .grantType("client_credentials")
        .additionalInfo(additionalInfo)
        .build();
NICEPayResponse nicePayResponse = SnapTokenService.callGetAccessToken(accessTokenRequest, nicePayConfig);

var accessToken = Optional.ofNullable(nicePayResponse)
        .map(NICEPayResponse::getAccessToken)
        .orElseThrow(() -> new IllegalArgumentException("Token is null"));
```

### 2.3 Request for Payment (i.e. Virtual Account)

```java
//Your previously initialized config
//NICEPay nicePayConfig = NICEPay.builder()...build();

//Previously requested access-token
//var accessToken = ...


import io.github.model.nicepay.VirtualAccount;
import io.github.nicepay.service.v2.V2VaService;
import io.github.response.nicepay.NICEPayResponse;
import io.github.service.nicepay.SnapVaService;

VirtualAccount virtualAccount = VirtualAccount.builder()
        .partnerServiceId("1234")
        .customerNo("")
        .virtualAccountNo("")
        .virtualAccountName("TESTVaName")
        .trxId("TESTTrxId")
        .totalAmount("11000.00", "IDR")
        .additionalInfo(new HashMap<String, Object>() {
            {
                put("bankCd", "BBBA");
                put("goodsNm", "TESTGoodsNm");
                put("dbProcessUrl", "https://merchant.com/test");
                put("vacctValidDt", "");
                put("vacctValidTm", "");
                put("msId", "");
                put("msFee", "");
                put("msFeeType", "");
                put("mbFee", "");
                put("mbFeeType", "");
            }
        })
        .build();

NICEPayResponse response = SnapVaService.callGeneratedVA(virtualAccount, accessToken, nicePayConfig);
String yourVirtualAccountNumber = response.getVirtualAccountNo();
```

### 2.4 Verify signature notif

```java
import io.github.nicepay.utils.SignatureUtils;

String signatureString = "VoxMPjbcV9pro4YyHGQgoRj4rDVJgYk2Ecxn+95B90w47Wnabtco35BfhGpR7a5RukUNnAdeOEBNczSFk4B9uYyu3jc+ceX+Dvz5OYSgSnw5CiMHtGiVnTAqCM/yHZ2MRpIEqekBc4BWMLVtexSWp0YEJjLyo9dZPrSkSbyLVuD7jkUbvmEpVdvK0uK15xb8jueCcDA6LYVXHkq/OMggS1/5mrLNriBhCGLuR7M7hBUJbhpOXSJJEy7XyfItTBA+3MRC2FLcvUpMDrn/wz1uH1+b9A6FP7mG0bRSBOm2BTLyf+xJR5+cdd88RhF70tNQdQxhqr4okVo3IFqlCz2FFg==";
String dataString = "TNICEVA023|2024-08-19T17:12:40+07:00";
String publicKeyString = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEApizrKJl/1Legp3Zj8f0oTIjKnUWe2HJCBSoRsVLxtpf0Dr1MI+23y+AMNKKxVXxbvReZq/sD91uN4GFYMUr16LY9oX7nJXh9C1JlI4/Xb/Q9MF30o1XYvogHLATtvTR/KQ8hxrf6Nlj/yuzeqrT+PiQMZt1CaKiE6UMn36kq11DmDq4ocwcNhChKDudNZSZ4YYIFn5IgH05K+VsRjehpa0szbO8qHmvnprXVVcqvk7ZSS+6fYwDynOq0f552aL0LWX0glNhh9F0oJqmTreW4lM0mdhNDq4GhlJZl5IpaUiaGRM2Rz/t6spgwR7nqUhI9aE2kjzaorgP4ZWUGm3wlTwIDAQAB";

boolean isVerify = SignatureUtils.verifySHA256RSA(dataString, publicKeyString, signatureString);
System.out.println(isVerify);
```

## 3. Other Samples
If you need samples for other payment methods and APIs, 
please refer to the test units on our [Repository](https://github.com/nicepay-dev/java-nicepay/tree/main/src/test/java/io/github/nicepay)

## Notes
#### Not Designed for Frontend Usage
This library is meant to be implemented on your backend server using Java.

## Get help

- [NICEPAY Docs](https://docs.nicepay.co.id/)
- [NICEPAY Dashboard ](https://bo.nicepay.co.id/)
- [SNAP documentation](https://docs.nicepay.co.id/nicepay-api-snap)
- [README_V2 For V2 Overview](https://github.com/nicepay-dev/java-nicepay/blob/dev/README_V2.md)
- Can't find answer you looking for? email to [cs@nicepay.co.id](mailto:cs@nicepay.co.id)

