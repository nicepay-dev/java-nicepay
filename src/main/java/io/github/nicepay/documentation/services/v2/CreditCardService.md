# Credit Card Direct V2 Services

A list of all methods in the `V2CardService`. Click on the method name to view detailed information about that method.

| Methods                                             | Description                                          |
|:----------------------------------------------------|:-----------------------------------------------------|
| [callV2CardRegistration](#1-callV2CardRegistration) | Register credit card transaction to NICEPAY API      |
| [callV2CardPaymentRequest](#143-requestPaymentCcV2) | Request payment credit card to NICEPAY API V2 Direct |
| [inquiryStatus()](#2-callInquiryStatus)             | Confirmation Transaction Status NICEPAY API          |
| [cancelCardV2()](#3-cancelCCTransaction)            | Cancellation / refund of credit card transaction     |

### **1. callV2CardRegistration**

**1.1 Register CC Transaction**

| **Property**     | **Value**                          |
|:-------------|------------------------------------| 
| API Endpoint | /nicepay/direct/v2/registration    |
| HTTP Method  | POST                               |
| Description  | Perform Transaction Registration   |
| Content-Type | application/json                   |

**1.2 Sample Parameters Request**

HEADER SECTION :
````text
Content-Type: application/json 
````
BODY SECTION :
````json
{
  "timeStamp": "20250130145551",
  "iMid": "TESTMPGS05",
  "payMethod": "01",
  "currency": "IDR",
  "merchantToken": "e60b1e813aeec85c67a1a36ac8bb48db6c89ec6fb49d4db9d28ccb54add8409d",
  "referenceNo": "ordNo20250130145551",
  "dbProcessUrl": "https://webhook.site/912cbdd8-eb28-4e98-be6a-181b806b8110",
  "instmntType": "1",
  "instmntMon": "1",
  "recurrOpt": "",
  "userIP": "127.0.0.1",
  "userLanguage": "en",
  "userAgent": "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:47.0) Gecko/20100101 Firefox/47.0",
  "amt": "1000",
  "goodsNm": "Goods",
  "billingNm": "NICEPAY Testing",
  "billingPhone": "081363681274",
  "billingEmail": "nicepay@example.com",
  "billingAddr": "Jln. Raya Kasablanka Kav.88",
  "billingCity": "South Jakarta",
  "billingState": "DKI Jakarta",
  "billingCountry": "Indonesia",
  "billingPostCd": "15119"
}
````
**1.3 Sample Parameter Response**

```json
{
  "tXid": "TESTMPGS0501202501301455502158",
  "referenceNo": "ordNo20250130145551",
  "payMethod": "01",
  "amt": "1000",
  "transDt": "20250130",
  "transTm": "145550",
  "resultCd": "0000",
  "resultMsg": "SUCCESS"
} 
```

**1.4 Example Usage Code Snippet**

| Methods                                         | Description                               |
|:------------------------------------------------|:------------------------------------------|
| [setUp()](#141-setUp)                           | Setup Environment                         |
| [registerCCDirectV2()](#142-registerCCDirectV2) | Register credit card transaction          |
| [requestPaymentCcV2()](#143-requestPaymentCcV2) | Request Credit Card Payment               |


### **1.4.1 setUp**
The setUp() method configures the environment, allowing the selection between the Nicepay development or production environment.

The following sample demonstrates how to configure the Nicepay development environment:
````java
package io.github.nicepay;

import io.github.nicepay.data.TestingConstants;
import io.github.nicepay.utils.LoggerPrint;
import io.github.nicepay.utils.NICEPay;
import org.junit.jupiter.api.BeforeAll;


class CardTest {
    private static NICEPay config;

    @BeforeAll
    public static void setUp() {
        config = NICEPay.builder()
                .isProduction(false)
                .build();
    }

}
````
### **1.4.2 registerCCDirectV2()**
- HTTP Method : POST
- Return Type : Object

This method facilitates card transaction registration using the Nicepay Card V1 Professional API.
````java
package io.github.nicepay;

import io.github.nicepay.data.TestingConstants;
import io.github.nicepay.data.model.AccessToken;
import io.github.nicepay.service.snap.SnapTokenService;
import io.github.nicepay.utils.LoggerPrint;
import io.github.nicepay.utils.NICEPay;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

class CardTest {
    private static LoggerPrint print = new LoggerPrint();
    private static NICEPay config;
    private static TestingConstants DATA;


  // return NICEPayResponseV2 Object
  public NICEPayResponseV2 registerCCDirectV2() 
  {

    String reffNo = "ordNo"+timeStamp;

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
            .merchantToken(timeStamp, iMid, reffNo, amount, merchantKey )
            .build();

    NICEPayResponseV2 cardRegistResponse = V2CardService.callV2CardRegistration(requestData,config);
    return cardRegistResponse;
  }
}
  
````

### **1.4.3 requestPaymentCcV2**

- HTTP Method : POST
- Return Type : String HTML

This method facilitates credit card payment requests using Nicepay Card V2 Direct Payment.

````java
package io.github.nicepay;

import io.github.nicepay.data.TestingConstants;
import io.github.nicepay.data.model.AccessToken;
import io.github.nicepay.service.snap.SnapTokenService;
import io.github.nicepay.utils.LoggerPrint;
import io.github.nicepay.utils.NICEPay;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

class CardTest {
    private static LoggerPrint print = new LoggerPrint();
    private static NICEPay config;
    private static TestingConstants DATA;


  // return NICEPayResponseV2 Object
  public String requestPaymentCcV2() 
  {

    String timeStamp = TestingConstants.V2_TIMESTAMP;
    Card requestData = Card.builder()
            .timeStamp(timeStamp)
            .tXid(cardRegistResponse.getTXid())
            .iMid(iMid)
            .referenceNo(cardRegistResponse.getReferenceNo())
            .amt(cardRegistResponse.getAmt())
            .merchantToken(timeStamp, iMid, reffNo, amount, merchantKey )
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

    String responseHtml = V2CardService.callV2CardPaymentRequest(requestData,config);
    
    return responseHtml;
  }
}
  
````

### **1.4.3 redirectPaymentPage**

Upon receiving the payment request response, you will obtain an HTML string that needs to be integrated with the front end. This HTML must be processed to proceed with the payment flow, such as displaying the OTP page for the 3DS card system.

sample of the HTML response : 

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
Once the user completes the 3DS authentication, they will be redirected to your defined callbackUrl, where you can process the result.

### **2. callInquiryStatus**
**2.1 Credit Card Inquiry Status**

| **Property**     | **Value**                                               |
|:-------------|---------------------------------------------------------| 
| API Endpoint | /nicepay/direct/v2/inquiry                              |
| HTTP Method  | POST                                                    |
| Description  | Performs Inquiry Request to NICEPAY for Status Checking |
| Content-Type | application/json                                        |


**2.2 Sample Parameters Request**
HEADER SECTION
```text
application/json
```

BODY SECTION
````json
{
  "timeStamp": "20250131000903",
  "tXid": "TESTMPGS0501202409041308179030",
  "iMid": "TESTMPGS05",
  "referenceNo": "ordNo20240904130813",
  "amt": "1000",
  "merchantToken": "0f934c9efaf2d3b8d278c5689cce00c8c4aa05190c965f2b6032e5cba52081f1"
}
````

Sample Response 
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
  "status": "1",
  "ccTransType": "3",
  "cardNo": "51234500****0008",
  "issuBankCd": "BNIA",
  "preauthToken": "a39cf9aef36b191fa67605021afe0e30d15ccc11ddd2cfeba05ba32192a7a925",
  "cardExpYymm": "2908",
  "acquBankNm": "CIMB",
  "instmntType": "1",
  "instmntMon": "1",
  "issuBankNm": "CIMB",
  "acquBankCd": "BNIA",
  "acquirerData": {
    "rrn": "424806091032"
  },
  "latestFailHistory": [],
  "resultCd": "0000",
  "resultMsg": "void"
}
```
**2.3 Example Usage Code Snippet**
### **2.3.1 InquiryStatusCC()**
This method is to ensure that the `Credit card status inquiry` process works as expected. It checks if the system can successfully:

- Build the inquiry Status request parameter.
- Send the inquiry parameter to NICEPay service and receive a response.
````java

public NICEPayResponseV2 InquiryStatusCardV2() throws IOException, InterruptedException {

  String timestamp = V2_TIMESTAMP;
  String imid = TestingConstants.I_MID_PAC;
  String merchantKey = TestingConstants.MERCHANT_KEY;
  String reffNo = "ordNo20240904130813";
  String amount = "1000";


  InquiryStatus requestData = InquiryStatus.builder()
          .timeStamp(timestamp)
          .tXid("TESTMPGS0501202409041308179030")
          .iMid(imid)
          .referenceNo(reffNo)
          .merchantToken(timestamp, imid , reffNo , amount , merchantKey)
          .amt("1000")
          .build();

  NICEPayResponseV2 result = V2InquiryStatusService.callV2InquiryStatus(requestData, config);

}
````

### **3. cancelCCTransaction**
**3.1 Credit Card Cancel Transaction**

| **Property**     | **Value**                   |
|:-------------|-----------------------------| 
| API Endpoint | /nicepay/direct/v2/cancel   |
| HTTP Method  | POST                        |
| Description  | Cancel Nicepay transaction  |
| Content-Type | application/json            |

**3.2 Sample Parameters Request**
HEADER SECTION
```text
application/json
```

BODY SECTION
````json
{
  "timeStamp": "20250131002853",
  "tXid": "TESTMPGS0401202501280956442870",
  "iMid": "TESTMPGS04",
  "payMethod": "01",
  "cancelType": "1",
  "amt": "100",
  "merchantToken": "cf77a831670673d6ad4bcb4e4be96b0bc454978b1a11e28ebab41be1c0bd3937",
  "referenceNo": "OrdNo-20250128090158",
  "cancelMsg": "Cancellation Of Transaction Credit Card",
  "cancelServerIp": "127.0.0.1",
  "cancelUserIp": "127.0.0.1",
  "cancelUserInfo": "",
  "cancelRetryCnt": "",
  "worker": ""
}
````

Sample Response
```json
{
  "tXid": "TESTMPGS0401202501280956442870",
  "referenceNo": "OrdNo-20250128090158",
  "amt": "100",
  "transDt": "20250128",
  "transTm": "095644",
  "description": "This is Testing Transaction",
  "acquirerData": {
    "rrn": "503017275312"
  },
  "resultCd": "0000",
  "resultMsg": "SUCCESS"
}
```
**3.3 Example Usage Code Snippet**
### **3.3.1 InquiryStatusCC()**
This method is to ensure that the `Credit Card Cancel / Refund` process works as expected. It checks if the system can successfully:

- Build the Cancel request parameter.
- Send the Cancel parameter to NICEPay service and receive a response.
````java

public NICEPayResponseV2 cancelFullCardV2() throws IOException, InterruptedException {

    // Paid transaction data 
  String txId = "TESTMPGS0401202501280956442870";
  String reffNo = "OrdNo-20250128090158";
  String amount = "100";

  Cancel requestCancel = Cancel.builder()
          .timeStamp(timestamp)
          .tXid(txId)
          .referenceNo(reffNo) 
          .merchantToken(timestamp, TestingConstants.I_MID_INSTLMNT,  txId, amount, TestingConstants.MERCHANT_KEY)
          .iMid(TestingConstants.I_MID_INSTLMNT)
          .payMethod("01")
          .cancelType("1")
          .amt(amount)
          .cancelMsg("Cancellation Of Transaction Credit Card")
          .cancelUserIp("127.0.0.1")
          .cancelServerIp("127.0.0.1")
          .cancelUserInfo("")
          .cancelRetryCnt("")
          .worker("")
          .build();

  NICEPayResponseV2 result = V2CancelService.callV2CancelTransaction(requestCancel, config);

}
````
