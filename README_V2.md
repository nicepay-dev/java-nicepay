# JAVA - NICEPAY

NICEPAY ❤️Java!

This is the Official Java API client/library for NICEPAY Payment API. Visit [Java Library](https://github.com/nicepay-dev/java-nicepay). 
More information about the product and see documentation at [NICEPAY Docs](https://docs.nicepay.co.id/) for more technical details.
This library provides access to Nicepay BI SNAP and V2 APIs.

This documentation provides an overview of accessing Nicepay V2 APIs. For detailed information on BI SNAP APIs, please refer to the [README.md](https://github.com/nicepay-dev/java-nicepay/tree/main/src).

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
			<version>1.2.0</version>
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

#### 2.2.1 Virtual Account (Generate VA)

```java

import io.github.nicepay.model.VirtualAccount;
import io.github.response.nicepay.data.response.v2.NICEPayResponseV2;
import io.github.nicepay.service.v2.V2VaService;


String timestamp = "20240919101010";

VirtualAccount request = VirtualAccount.builder()
        .timeStamp(timestamp)
        .iMid("IONPAYTEST")
        .merchantToken(timestamp, TestingConstants.I_MID, "NICEPAYVA111213", "100", TestingConstants.MERCHANT_KEY )
        .payMethod("02")
        .currency("IDR")
        .bankCd("BMRI")
        .amt("100")
        .referenceNo("NICEPAYVA111213")
        .vacctValidDt("")
        .vacctValidTm("")
        .goodsNm("Goods")
        .billingNm("NICEPAY Testing")
        .billingPhone("081363681274")
        .billingEmail("nicepay@example.com")
        .billingAddr("Jln. Raya Kasablanka Kav.88")
        .billingCity("South Jakarta")
        .billingState("DKI Jakarta")
        .billingPostCd("15119")
        .billingCountry("Indonesia")
        .merFixAcctId("")
        .dbProcessUrl("https://webhook.site/912cbdd8-eb28-4e98-be6a-181b806b8110")
        .build();

NICEPayResponseV2 response = V2VaService.callV2GenerateVA(request, config);
```

Sample response : 

```json
{
  "tXid": "IONPAYTEST02202409060158330631",
  "referenceNo": "NICEPAYVA111213",
  "payMethod": "02",
  "amt": "100",
  "transDt": "20240906",
  "transTm": "015833",
  "currency": "IDR",
  "goodsNm": "Goods",
  "billingNm": "NICEPAY Testing",
  "bankCd": "BMRI",
  "vacctNo": "70014000090158330631",
  "vacctValidDt": "20240908",
  "vacctValidTm": "015833",
  "resultCd": "0000",
  "resultMsg": "SUCCESS"
}
```

#### 2.2.2 Credit Card 

##### 2.2.2.1 CC - Registration

```java
import io.github.nicepay.data.model.Card;
import io.github.nicepay.service.v2.V2CardService;
import io.github.nicepay.utils.SHA256Util;

Card requestData = Card.builder()
        .timeStamp("20240906004727") // format yyyyMMddHHmmss
        .iMid("TESTMPGS05")
        .payMethod("01")
        .merchantToken("20240906004727", "TESTMPGS05", "ordNo20240906004727", "1000", TestingConstants.MERCHANT_KEY )
        .currency("IDR")
        .amt("1000")
        .referenceNo("ordNo20240906004727")
        .goodsNm("Goods name")
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
        .build();


NICEPayCardResponseV2 cardRegistResponse = V2CardService.callV2CardRegistration(requestData, config);
```

Sample response : 

```json
{
  "tXid": "TESTMPGS0501202409060047287070",
  "referenceNo": "ordNo20240906004727",
  "payMethod": "01",
  "amt": "1000",
  "transDt": "20240906",
  "transTm": "004728",
  "resultCd": "0000",
  "resultMsg": "SUCCESS"
}
```
##### 2.2.2.2 CC - Request Payment

```java

import io.github.nicepay.data.model.Card;
import io.github.nicepay.service.v2.V2CardService;
import io.github.nicepay.utils.SHA256Util;

//      CONSTRUCT REQUEST BODY
Card requestData = Card.builder()
        .timeStamp("20240906004912")
        .tXid("TESTMPGS0501202409060047287070")
        .referenceNo("ordNo20240906004727")
        .merchantToken("20240906004912", "TESTMPGS05", "ordNo20240906004727", "1000", TestingConstants.MERCHANT_KEY )
        .cardNo("5123450000000008")
        .cardExpYymm("2908")
        .cardCvv("100")
        .cardHolderNm("NICEPAY TEST")
        .callBackUrl("https://dev.nicepay.co.id/IONPAY_CLIENT/paymentResult.jsp")
        .recurringToken("")
        .preauthToken("")
        .isEncryptedCard("0")
        .publicKey("")
        .build();


//        CALL CARD PAYMENT REQUEST METHOD
String responseHtml = V2CardService.callV2CardPaymentRequest(requestData, config);
```

The responseHtml string will contain the HTML content, which should be rendered and processed on your front-end for the 3DS (3D Secure) flow.

Sample HTML Response for 3DS Flow

Here’s an example of the HTML response your front-end should handle. It contains an iframe for 3DS authentication via Mastercard, with the form automatically submitting to the 3DS method URL.

```html

<head>
	<style>
		body {min-width: 100%; height: auto; min-height: 500px; overflow: none; border: none; background: url("/nicepay/images/rotate.gif") no-repeat center;
	</style>
	</head>
	<body onload='javascript:getBrowserInfo();'>
		<div style='visibility:hidden'>
			<div id="initiate3dsSimpleRedirect" xmlns="http://www.w3.org/1999/html"> <iframe id="methodFrame" name="methodFrame" height="100" width="200" > </iframe> <form id ="initiate3dsSimpleRedirectForm" method="POST" action="https://mtf.gateway.mastercard.com/acs/mastercard/v2/method" target="methodFrame"> <input type="hidden" name="threeDSMethodData" value="eyJ0aHJlZURTTWV0aG9kTm90aWZpY2F0aW9uVVJMIjoiaHR0cHM6Ly9tdGYuZ2F0ZXdheS5tYXN0ZXJjYXJkLmNvbS9jYWxsYmFja0ludGVyZmFjZS9nYXRld2F5LzNjMTlhMzU0NjgwMWU2ZDMwMTYwZWJiMDllMjAxYzg2NTU0MzZkMzRjYjgwOWFjNTIyMmMxMzIwZDFhMjI3ZDUiLCJ0aHJlZURTU2VydmVyVHJhbnNJRCI6ImM2YTI5MTllLTdlNWMtNDcyMi1hYmJlLTY5YmVlZDAyM2IzZiJ9" /> </form> <script id="initiate-authentication-script"> var e=document.getElementById("initiate3dsSimpleRedirectForm"); if (e) { e.submit(); if (e.parentNode !== null) { e.parentNode.removeChild(e); } } </script> </div> 
		</div>
		<form name="mpgsAuthFrm" id="mpgsAuthFrm" method="post" action='https://dev.nicepay.co.id/nicepay/api/mpgsAuth.do'>
			<input type='hidden' name='tXid' value='TESTMPGS0501202409060047287070'>
			<input type='hidden' name='cardNo' value='5123450000000008'>
			<input type='hidden' name='cardExpYymm' value='2908'>
			<input type='hidden' name='cardCvv' value='100'>
			<input type='hidden' name='callbackUrl' value='https://dev.nicepay.co.id/IONPAY_CLIENT/paymentResult.jsp'>
			<input type='hidden' name='registered' value='true'>
			<input type='hidden' name='directV1' value='false'>
			<input type='hidden' name='authResult' value='false'>
			<input type='hidden' name='browser' value=''>
			<input type='hidden' name='browserJavaEnabled' value='false'>
			<input type='hidden' name='browserJavascriptEnabled' value='2'>
			<input type='hidden' name='browserLanguage' value=''>
			<input type='hidden' name='browserScreenColorDepth' value=''>
			<input type='hidden' name='browserScreenHeight' value='0'>
			<input type='hidden' name='browserScreenWidth' value='0'>
			<input type='hidden' name='browserTimeZone' value='0'>
		</form>
	<script>
		function getBrowserInfo() {
			document.mpgsAuthFrm.browser.value = navigator.userAgent;
			document.mpgsAuthFrm.browserJavaEnabled.value = window.navigator.javaEnabled() === true ? 'true' : 'false';
			document.mpgsAuthFrm.browserJavascriptEnabled.value = "1"; 
			document.mpgsAuthFrm.browserLanguage.value = navigator.language; 
			document.mpgsAuthFrm.browserScreenColorDepth.value = screen.colorDepth; 
			document.mpgsAuthFrm.browserScreenHeight.value = screen.height; 
			document.mpgsAuthFrm.browserScreenWidth.value = screen.width; 
			document.mpgsAuthFrm.browserTimeZone.value = new Date().getTimezoneOffset(); 
			document.mpgsAuthFrm.submit(); 
		}
	</script>
	</body>

```

##### Front-End Integration
1. When the front-end receives the responseHtml, render it as an HTML page.
2. The page will automatically post to the 3DS method URL, redirecting the user to a page where they can enter the OTP sent to their registered device.
3. After completing the 3DS flow, the user is redirected back to your callback URL.

Expected Result :

Once the user completes the 3DS authentication, they will be redirected to your defined callbackUrl, where you can process the result.


#### 2.2.2.3 Common (e.g Inquiry Status)

```java

//      CONSTRUCT REQUEST BODY
InquiryStatus requestData = InquiryStatus.builder()
                .timeStamp("20240906015226")
                .tXid("TESTMPGS0501202409041308179030")
                .iMid("TESTMPGS05")
                .merchantToken("20240906015226", "TESTMPGS05" , "ordNo20240904130813" , "1000" , TestingConstants.MERCHANT_KEY)
                .referenceNo("ordNo20240904130813")
                .amt("1000")
                .build();


//        CALL V2 INQUIRY STATUS SERVICE
        NICEPayResponseV2 result = V2InquiryStatusService.callV2InquiryStatus(requestData, config);

```

Sample response : 
```json
{
  "tXid": "TESTMPGS0501202409041308179030",
  "referenceNo": "ordNo20240904130813",
  "payMethod": "01",
  "amt": "1000",
  "transDt": "20240904",
  "transTm": "130817",
  "currency": "IDR",
  "goodsNm": "Goods",
  "billingNm": "NICEPAY Testing",
  "reqDt": "20240904",
  "reqTm": "130817",
  "resultCd": "0000",
  "resultMsg": "preauthed"
}
```

### 2.3 Payment URL Utilities

This utility provides methods to generate payment URLs for NICEPay V2 Direct and V2 Redirect integrations.

### ✳️ Methods

#### 2.3.1 generateDirectV2PaymentUrl(Payment data, NICEPay config)

Generates a Direct V2 Payment URL used for payment method Payloan and Ewallet using the provided `Payment` data and `NICEPay` configuration.

Parameters:
- `Payment data` – contains transaction information such as timestamp, token, callback URL, and transaction ID (`tXid`).
- `NICEPay config` – contains base URL and other NICEPay-specific configuration.

Returns:
- A fully constructed URL to initiate Direct V2 payments.

Note:
- `merchantToken` will be encrypted using `SHA256Util.encrypt(...)` before being appended to the URL.

---

#### 2.3.2 generateRedirectV2PaymentUrl(Payment data, NICEPay config)

Generates a Redirect V2 Payment URL using the transaction ID from `Payment`.

Parameters:
- `Payment data` – should contain the `tXid`.
- `NICEPay config` – provides the base NICEPay API URL.

Returns:
- A fully constructed URL for redirect-based payments.

---

####  Example Usage

```java

void ewalletPaymentV2Test() throws IOException, InterruptedException {


                NICEPayResponseV2 regist = generateEwalletTrans(config);

                String timeStamp = V2_TIMESTAMP;
                String iMid = I_MID_EWALLET;


                Payment request = Payment.builder()
                        .tXid("TNICEEW05105202504151406524860")
                        .timeStamp("20250415140651")
                        .merchantToken(timeStamp, iMid, regist.getReferenceNo(), regist.getAmt(), MERCHANT_KEY)
                        .callBackUrl("https://dev.nicepay.co.id/IONPAY_CLIENT/paymentResult.jsp?order_id=TNICEEW05105202504151406524860")
                        .build();

                String response = ApiUtils.generateDirectV2PaymentUrl(request,config);

        }

```
Sample response = "https://dev.nicepay.co.id/nicepay/direct/v2/payment?timeStamp=20250415140651&merchantToken=1ccbe4a9ca050f4dbf08b5d14a5ddc3006f2015f24d963b6111f9f4a9db73a2c&callBackUrl=https://dev.nicepay.co.id/IONPAY_CLIENT/paymentResult.jsp?order_id=TNICEEW05105202504151406524860&tXid=TNICEEW05105202504151406524860"

---
#### 2.3.2 generateRedirectV2PaymentUrl(Payment data, NICEPay config)

Encrypt Card Detail using Library Java

```java
import io.github.nicepay.utils.SHA256Util;

String encryptedCardNo = SHA256Util.encryptWithPublicKeyString("5123450000000008","MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCihYcT862uLW7U3xEIZT62CeVYHzE9t0/Q8g+y5S88bzqdM0bAG4C25q6CMC6QI8OliNJ2mgXO+WOMRG+dFK61cgvJPz2XzITqy2ey0cQTPig9ydVBbPphtcg3TJTPchh0lAt7bQZqguqZQdajUebTFI1p/b6lT+XJNC31AxvzlQIDAQAB");
String encryptedCardCvv = SHA256Util.encryptWithPublicKeyString("100","MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCihYcT862uLW7U3xEIZT62CeVYHzE9t0/Q8g+y5S88bzqdM0bAG4C25q6CMC6QI8OliNJ2mgXO+WOMRG+dFK61cgvJPz2XzITqy2ey0cQTPig9ydVBbPphtcg3TJTPchh0lAt7bQZqguqZQdajUebTFI1p/b6lT+XJNC31AxvzlQIDAQAB");
String encryptedCardExpYymm = SHA256Util.encryptWithPublicKeyString("2908","MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCihYcT862uLW7U3xEIZT62CeVYHzE9t0/Q8g+y5S88bzqdM0bAG4C25q6CMC6QI8OliNJ2mgXO+WOMRG+dFK61cgvJPz2XzITqy2ey0cQTPig9ydVBbPphtcg3TJTPchh0lAt7bQZqguqZQdajUebTFI1p/b6lT+XJNC31AxvzlQIDAQAB");
String encryptedCardHolderNm = SHA256Util.encryptWithPublicKeyString("NICEPAY TEST","MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCihYcT862uLW7U3xEIZT62CeVYHzE9t0/Q8g+y5S88bzqdM0bAG4C25q6CMC6QI8OliNJ2mgXO+WOMRG+dFK61cgvJPz2XzITqy2ey0cQTPig9ydVBbPphtcg3TJTPchh0lAt7bQZqguqZQdajUebTFI1p/b6lT+XJNC31AxvzlQIDAQAB");
String hashPublicKey = SHA256Util.encrypt("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCihYcT862uLW7U3xEIZT62CeVYHzE9t0/Q8g+y5S88bzqdM0bAG4C25q6CMC6QI8OliNJ2mgXO+WOMRG+dFK61cgvJPz2XzITqy2ey0cQTPig9ydVBbPphtcg3TJTPchh0lAt7bQZqguqZQdajUebTFI1p/b6lT+XJNC31AxvzlQIDAQAB");

```

---
#### ⚠️ Additional Information

The generated URLs are intended to be passed to the **front-end** for redirection.  
Front-end applications should handle the navigation or redirection to the generated payment URL.


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


