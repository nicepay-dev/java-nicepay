# JAVA - NICEPAY

NICEPAY ❤️Java!

This is the Official Java API client/library for NICEPAY Payment API. Visit [Java Library](https://github.com/nicepay-dev/java-nicepay). 
More information about the product and see documentation at [NICEPAY Docs](https://docs.nicepay.co.id/) for more technical details.
This library provides access to Nicepay BI SNAP APIs.


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
			<version>1.0.1</version>
		</dependency>
```

## 2. Usage
### 2.1 Client Initialization and Configuration
Get your Credentials from [Nicepay Dashboard](https://bo.nicepay.co.id/)
Initialize Nicepay Config

```java
import io.github.utils.nicepay.NICEPay;

NICEPay nicePayConfig = NICEPay.builder()
        .isProduction(false)
        .clientSecret("YOUR_CLIENT_SECRET")
        .partnerId("YOUR_PARTNER_ID")
        .privateKey("YOUR_PRIVATE_KEY")
        .externalID("EXTERNAL_ID -> Unique per request")
        .timestamp("TIMESTAMP -> Request timestamp")
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

String signatureString = "kfCx0gPk9I4JGQu14H2w0hwZgLg6GPc6n1TxEpjC1TYIWsVMpiUGN4gm7k9amRSwKmEzzErarNIcKIit0+9snITgOg5tefc9+7/K8WVHHTxm44OnRCtOXBvNRWita7XQCDDoMI+fJJLAxRjKg0eURXywFupT8dKaDe8DwIa04ENBfNFOKDp+H+zR3OnMjSnWjke3I6Ppt4qMAUU+HZ+pojWPl5kYMzA08qvKQnplkhMb4TUQ80CP2uJWIsJScna1YlGlr+VRezSxOeJ3wv39hgAREorP7AWYkYPDVAbbqW+lnHtQCufRvQygKSgRT5BWHakZOgnRry/QcrCwuO+q2w==\n";
String dataString = "KREDUITEST|2024-08-14T10:03:27+07:00";
String publicKeyString = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAk2OMsu2IJvbRAAgL7hQ5gwuL34tPK0prutIKl4EUXpMcBxwSjy2kX3EX5tW7PN/BoIP0WiO6dMDu/PPiZsmEmWFMYwVFKFdSy1/8y+jkhApip67PcpnrPWGjGL7BHS4VSBdHllKvawNKM8kvZ+xdD0TPyHLhdQG8aw02tZAZz3ldE+cSfruRknWZkiOmJeGHTkoVpRxh60LXfD4dzv2LGJWkKAe+b26IODYQrHBh77Ez2/AICoxSMkXhuAR1/fCQ98aL2d/wqop3YpQ8c8BmUTFdq7SJiWZhNaowS6c/41JJzcIWzZg++IPTHXfoo4BBW6SNTq1fjsKVfuCadx0vjwIDAQAB";

boolean isVerify = SignatureUtils.verifySHA256RSA(dataString, publicKeyString, signatureString);
System.out.println(isVerify);
```

## 3. Other Samples
If you need samples for other payment methods and APIs, 
please refer to the test units on our [Repository](https://github.com/nicepay-dev/java-nicepay/tree/main/src/test/java/com/nicepay/client)

## Notes
#### Not Designed for Frontend Usage
This library is meant to be implemented on your backend server using Java.

## Get help

- [NICEPAY Docs](https://docs.nicepay.co.id/)
- [NICEPAY Dashboard ](https://bo.nicepay.co.id/)
- [SNAP documentation](https://docs.nicepay.co.id/nicepay-api-snap)
- Can't find answer you looking for? email to [cs@nicepay.co.id](mailto:cs@nicepay.co.id)

