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
        .externalID("EXTERNAL_ID -> Unique per request")
        .timestamp("TIMESTAMP -> Request timestamp")
        .build();
```

### 2.2 Request for Payment (i.e. Virtual Account)

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

NICEPayResponse response = V2VaService.callGeneratedVA(virtualAccount, nicePayConfig);
String yourVirtualAccountNumber = response.getVirtualAccountNo();
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

