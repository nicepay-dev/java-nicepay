# JAVA - NICEPAY

NICEPAY ❤️Java!

This is the Official Java API client/library for NICEPAY Payment API. Visit [Java Library](https://github.com/nicepay-dev/java-nicepay). 
More information about the product and see documentation at [NICEPAY Docs](https://docs.nicepay.co.id/) for more technical details.
This library provides access to Nicepay BI SNAP, V2, and V1 APIs.

# Nicepay API Documentation

This documentation offers an overview of accessing Nicepay V1 APIs.

- For detailed information on BI SNAP APIs, see the [README.md](https://github.com/nicepay-dev/java-nicepay/blob/main/README.md).
- For V2 APIs, refer to the [README_V2.md](https://github.com/nicepay-dev/java-nicepay/blob/main/README_V2.md).

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
			<version>1.1.0</version>
		</dependency>
```

## 2. Usage
### 2.1 Client Initialization and Configuration

The configuration specifies the environment you intend to use.

```java
import io.github.utils.nicepay.NICEPay;

NICEPay nicePayConfig = NICEPay.builder()
        .isProduction(false)
        .build();
```

### 2.2 Request Transaction

#### 2.2.1 Registration Credit Card Transaction V1 Professional 

```java

import io.github.nicepay.model.VirtualAccount;
import io.github.response.nicepay.data.response.v2.NICEPayResponseV2;
import io.github.nicepay.service.v2.V2VaService;


Card requestData = Card.builder()
        .timeStamp(timeStamp)
        .iMid(iMid)
        .payMethod("01")
        .currency("IDR")
        .amt(amount)
        .referenceNo(reffNo)
        .goodsNm("Goods")
        .billingNm("NICEPAY Testing")
        .billingPhone("081363681274")
        .billingEmail("nicepay@example.com")
        .billingAddr("Jln. Raya Kasablanka Kav.88")
        .billingCity("South Jakarta")
        .billingState("DKI Jakarta")
        .billingPostCd("15119")
        .billingCountry("Indonesia")
        .dbProcessUrl("https://webhook.site/912cbdd8-eb28-4e98-be6a-181b806b8110")
        .userIP("127.0.0.1")
        .userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:47.0) Gecko/20100101 Firefox/47.0")
        .userLanguage("en")
        .instmntType("1")
        .instmntMon("1")
        .recurrOpt("")
        .merchantTokenV1(iMid, reffNo, amount, merchantKey )
        .description("Test Native Java V1 - Card")
        .deliveryNm("NICEPAY")
        .deliveryPhone("081363681274")
        .deliveryEmail("nicepay@example.com")
        .deliveryAddr("Jln. Raya Kasablanka Kav.88")
        .deliveryCity("South Jakarta")
        .deliveryState("DKI Jakarta")
        .deliveryPostCd("15119")
        .deliveryCountry("Indonesia")
        .callBackUrl(NICEPayConstants.getSandboxBaseUrl() + "/IONPAY_CLIENT/paymentResult.jsp")
        .build();

NICEPayResponseV2 cardRegistResponse = V1CardService.callCardRedirectRegistration(requestData,config);
```

Sample response : 

```json
{
  "apiType": "M0",
  "tXid": "TESTMPGS0501202501232330503098",
  "requestDate": "20250123233050",
  "responseDate": "20250123233050",
  "data": {
    "tXid": "TESTMPGS0501202501232330503098",
    "resultCd": "0000",
    "resultMsg": "SUCCESS",
    "requestURL": "https://dev.nicepay.co.id/nicepay/api/orderInquiry.do"
  }
}
```


##### 2.2.2 CC - Request Payment Redirect

Upon receiving the registration response, you will obtain the `requestURL` and `tXid` parameters. These are essential for front-end integration to redirect users to the appropriate URL with the required parameters.

Additionally, optional parameters such as `optDisplayBL` (to display a back link) and `optDisplayCB` (to display a change payment button) can be included. These parameters accept the values `0` (show) or `1` (hide).

### Example of a Complete Payment URL:

- Example of a complete payment URL:  
  `https://www.nicepay.co.id/nicepay/api/orderInquiry.do?tXid=IONPAYTEST01201608291733081257&optDisplayCB=0&optDisplayBL=0`
- Example of Nicepay CC Payment page :
  ![Nicepay CC Payment Page](https://images.archbee.com/ZHvSjR5ZrsoxwKbJa3xmo/6S1QF7TkzME13UoL7fi1k_image.png?format=webp&width=922)

#### 2.2.3 Inquiry Status

```java

//      CONSTRUCT REQUEST BODY
String timestamp = V2_TIMESTAMP;
String imid = TestingConstants.I_MID_PAC;
String merchantKey = TestingConstants.MERCHANT_KEY;
String reffNo = "ordNo20240904130813";
String amount = "1000";


InquiryStatus requestData = InquiryStatus.builder()
        .tXid("TESTMPGS0501202409041308179030")
        .iMid(imid)
        .referenceNo(reffNo)
        .merchantTokenV1(imid , reffNo , amount , merchantKey)
        .amt("1000")
        .build();

NICEPayResponseV1 result = V1CardService.callInquiryStatus(requestData, config);

```

Sample response : 
```json
{
  "tXid": "TESTMPGS0501202409041308179030",
  "reqTm": "130817",
  "ccTransType": "3",
  "fee": "0",
  "amt": "1000",
  "notaxAmt": "0",
  "cardNo": "51234500****0008",
  "issuBankCd": "BNIA",
  "preauthToken": "a39cf9aef36b191fa67605021afe0e30d15ccc11ddd2cfeba05ba32192a7a925",
  "cardExpYymm": "2908",
  "acquBankNm": "CIMB",
  "payMethod": "01",
  "currency": "IDR",
  "instmntMon": "1",
  "issuBankNm": "CIMB",
  "resultCd": "0000",
  "goodsNm": "Goods",
  "referenceNo": "ordNo20240904130813",
  "transTm": "130817",
  "vat": "0",
  "instmntType": "1",
  "resultMsg": "void",
  "iMid": "TESTMPGS05",
  "billingNm": "NICEPAY Testing",
  "acquBankCd": "BNIA",
  "reqDt": "20240904",
  "transDt": "20240904",
  "status": "1",
  "acquirerData": {
    "rrn": "424806091032"
  }
}
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
- Can't find answer you looking for? email to [cs@nicepay.co.id](mailto:cs@nicepay.co.id)

