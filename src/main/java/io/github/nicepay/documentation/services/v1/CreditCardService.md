# Virtual Account Services

A list of all methods in the `SnapVaService`. Click on the method name to view detailed information about that method.

| Methods                                                 | Description                         |
|:--------------------------------------------------------|:------------------------------------|
| [callCardRedirectRegistration](#1-callCardRedirectRegistration)                   | Generated VA Number to NICEPAY API  |
| [callInquiryStatus](#2-callInquiryStatus) | Confirmation Transaction Status API |

### **1. callCardRedirectRegistration**

**1.1 Register CC Transaction**

| **Property** | **Value**                        |
|:-------------|----------------------------------| 
| API Endpoint | /nicepay/api/orderRegist.do      |
| HTTP Method  | POST                             |
| Description  | Perform Transaction Registration |
| Content-Type | `x-www-form-urlencoded`          |

**1.2 Sample Parameters Request**

HEADER SECTION :
````text
Content-Type: x-www-form-urlencoded
````
BODY SECTION :
````json
{
  "deliveryPhone": "081363681274",
  "amt": "1000",
  "description": "Test Native Java V1 - Card",
  "deliveryEmail": "nicepay@example.com",
  "userLanguage": "en",
  "billingEmail": "nicepay@example.com",
  "payMethod": "01",
  "deliveryAddr": "Jln. Raya Kasablanka Kav.88",
  "userIP": "127.0.0.1",
  "billingCountry": "Indonesia",
  "currency": "IDR",
  "instmntMon": "1",
  "merchantToken": "393f23994ea86ffc60e95fedc17c4fa2640b2c4ad735d36f84c972e7826cafaa",
  "deliveryCity": "South Jakarta",
  "goodsNm": "Goods",
  "callBackUrl": "https://dev.nicepay.co.id//IONPAY_CLIENT/paymentResult.jsp",
  "referenceNo": "ordNo20250120103309",
  "userAgent": "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:47.0) Gecko/20100101 Firefox/47.0",
  "billingState": "DKI Jakarta",
  "instmntType": "1",
  "deliveryNm": "NICEPAY",
  "deliveryPostCd": "15119",
  "iMid": "TESTMPGS05",
  "billingNm": "NICEPAY Testing",
  "timeStamp": "20250120103309",
  "dbProcessUrl": "https://webhook.site/912cbdd8-eb28-4e98-be6a-181b806b8110",
  "recurrOpt": "",
  "deliveryState": "DKI Jakarta",
  "deliveryCountry": "Indonesia",
  "billingPostCd": "15119",
  "billingAddr": "Jln. Raya Kasablanka Kav.88",
  "billingPhone": "081363681274",
  "billingCity": "South Jakarta"
}
````
SAMPLE RESPONSE 
```json
{
  "apiType": "M0",
  "tXid": "TESTMPGS0501202501201033106679",
  "requestDate": "20250120103310",
  "responseDate": "20250120103310",
  "data": {
    "tXid": "TESTMPGS0501202501201033106679",
    "resultCd": "0000",
    "resultMsg": "SUCCESS",
    "requestURL": "https://dev.nicepay.co.id/nicepay/api/orderInquiry.do"
  }
}
```
**1.3 Sample Parameter Response**
**1.4 Example Usage Code Snippet**

| Methods                                             | Description                                       |
|:----------------------------------------------------|:--------------------------------------------------|
| [setUp()](#141-setUp)                               | Setup Credentials Method                          |
| [registerCCRedirectV1()](#142-registerCCRedirectV1) | Generated Virtual Account Method                  |
| [redirectPaymentURL()](#143-redirectPaymentURL)     | Confirm Virtual Account Transaction Status Method |
| [inquiryStatus()](#2-callInquiryStatus)                    | Cancel Virtual Account Transaction Method         |


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
### **1.4.2 registerCCRedirectV1()**
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


  // return NICEPayResponseV1 Object
  public NICEPayResponseV1 registerCCRedirectV1() throws IOException
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
    
    return V1CardService.callCardRedirectRegistration(requestData,config);

  }
}
````

### **1.4.3 redirectPaymentURL**
Upon receiving the NICEPayResponseV1, you will obtain the parameters requestURL and tXid, which are required for front-end integration to redirect users to the URL with the specified parameters.

| Parameter     | Type    | Length | Required | Description           | Example / Notes                |
|:--------------|:--------|:-------|:---------|:----------------------|:-------------------------------|
| tXid          | String  | 30     | ✅        | Transaction ID        | IONPAYTEST02201607291027025291 |
| optDisplayCB  | String  | 2      | ❌        | Display Change Button | show = 0 hide = 1              |
| optDisplayBL  | String  | 2      | ❌         | Display Back Link     | show = 0 hide = 1              |

- To prevent the client from changing the payment method, set `optDisplayCB=1`.

- Example of a complete payment URL:  
  `https://www.nicepay.co.id/nicepay/api/orderInquiry.do?tXid=IONPAYTEST01201608291733081257&optDisplayCB=0&optDisplayBL=0`
- Example of Nicepay CC Payment page : 
![Nicepay CC Payment Page](https://images.archbee.com/ZHvSjR5ZrsoxwKbJa3xmo/6S1QF7TkzME13UoL7fi1k_image.png?format=webp&width=922)


### **2. callInquiryStatus**
**2.1 Credit Card Inquiry Status**

| **Property** | **Value**                                      |
|:-------------|------------------------------------------------| 
| API Endpoint | /nicepay/api/onePassStatus.do                    |
| HTTP Method  | POST                                           |
| Description  | Inquiries transaction status to NICEPay server |
| Content-Type | `x-www-form-urlencoded`                        |

**2.2 Sample Parameters Request**
HEADER SECTION
```text
Content-Type: x-www-form-urlencoded
```

BODY SECTION
````json
{
  "merchantToken": "6c25bcab6911e7fd56fb4a95f26994b57fbdeab14eebae6318c350f929e4b675",
  "referenceNo": "ordNo20240904130813",
  "tXid": "TESTMPGS0501202409041308179030",
  "amt": "1000",
  "iMid": "TESTMPGS05"
}
````

Sample Response 
```json
{
  "reqTm" : "130817",
  "ccTransType" : "3",
  "acquStatus" : null,
  "fee" : "0",
  "amt" : "1000",
  "cancelAmt" : null,
  "notaxAmt" : "0",
  "depositTm" : null,
  "cardNo" : "51234500****0008",
  "issuBankCd" : "BNIA",
  "preauthToken" : "a39cf9aef36b191fa67605021afe0e30d15ccc11ddd2cfeba05ba32192a7a925",
  "cardExpYymm" : "2908",
  "acquBankNm" : "CIMB",
  "payMethod" : "01",
  "currency" : "IDR",
  "instmntMon" : "1",
  "issuBankNm" : "CIMB",
  "resultCd" : "0000",
  "goodsNm" : "Goods",
  "referenceNo" : "ordNo20240904130813",
  "transTm" : "130817",
  "authNo" : null,
  "vat" : "0",
  "instmntType" : "1",
  "resultMsg" : "void",
  "billingNm" : "NICEPAY Testing",
  "acquBankCd" : "BNIA",
  "depositDt" : null,
  "reqDt" : "20240904",
  "transDt" : "20240904",
  "status" : "1",
  "acquirerData" : {
    "rrn" : "424806091032"
  },
  "tXid" : "TESTMPGS0501202409041308179030",
  "iMid" : "TESTMPGS05"
}
```
**2.3 Example Usage Code Snippet**
### **2.3.1 InquiryStatusCC()**
This method is to ensure that the `Credit card status inquiry` process works as expected. It checks if the system can successfully:

- Build the inquiry Status request parameter.
- Send the inquiry parameter to NICEPay service and receive a response.
````java
 @Test
void InquiryStatusCardV1() throws IOException, InterruptedException {

    String timestamp = TestingConstants.V2_TIMESTAMP;
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

}
````

