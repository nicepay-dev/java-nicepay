# Virtual Account Services

A list of all methods in the `SnapVaService`. Click on the method name to view detailed information about that method.

| Methods                                                   | Description                         |
|:----------------------------------------------------------|:------------------------------------|
| [callGeneratedVA](#1-callGeneratedVAV2)                   | Generated VA Number to NICEPAY API  |
| [callServiceVACheckStatus](#2-callServiceVACheckStatusV2) | Confirmation Transaction Status API |
| [callServiceVACancel](#3-callServiceVACancelV2)             | Canceled VA Number to NICEPAY API   |

### **1. callGeneratedVAV2**

**1.1 Generated VA Number**

| **Property**  | **Value**                                |
|:--------------|------------------------------------------|
| API Endpoint  | /nicepay/direct/v2/registration          |
| HTTP Method   | POST                                     |
| Description   | Performs Transaction Regist to NICEPAY   |
| Content-Type  | application/json                         |

**1.2 Sample Parameters Request**

//HEADER SECTION
````text
Content-Type: application/json
````
        
//BODY SECTION
````json
{
  "timeStamp": "20250131004620",
  "iMid": "IONPAYTEST",
  "payMethod": "02",
  "currency": "IDR",
  "amt": "100",
  "referenceNo": "NICEPAYVA111213",
  "goodsNm": "Goods",
  "billingNm": "NICEPAY Testing",
  "billingPhone": "081363681274",
  "billingEmail": "nicepay@example.com",
  "billingAddr": "Jln. Raya Kasablanka Kav.88",
  "billingCity": "South Jakarta",
  "billingState": "DKI Jakarta",
  "billingPostCd": "15119",
  "billingCountry": "Indonesia",
  "bankCd": "BMRI",
  "vacctValidDt": "",
  "vacctValidTm": "",
  "merFixAcctId": "",
  "dbProcessUrl": "https://webhook.site/912cbdd8-eb28-4e98-be6a-181b806b8110",
  "merchantToken": "60a41b65d95977ba5bb8c360795f5862a58cf09a77777a8f6cae6e2a980f5124"
}
````

//SAMPLE RESPONSE 
````json
{
  "tXid": "IONPAYTEST02202501310046221808",
  "referenceNo": "NICEPAYVA111213",
  "payMethod": "02",
  "amt": "100",
  "transDt": "20250131",
  "transTm": "004622",
  "currency": "IDR",
  "goodsNm": "Goods",
  "billingNm": "NICEPAY Testing",
  "bankCd": "BMRI",
  "vacctNo": "70014000090046221808",
  "vacctValidDt": "20250202",
  "vacctValidTm": "004622",
  "resultCd": "0000",
  "resultMsg": "SUCCESS"
}
````
**1.3 Example Usage Code Snippet**

| Methods                                     | Description                                       |
|:--------------------------------------------|:--------------------------------------------------|
| [setUp()](#131-setUp)                       | Setup Environment                                 |
| [vaCreate()](#132-vaCreateV2)               | Generated Virtual Account Method                  |
| [InquiryStatusVA()](#231-InquiryStatusVAV2) | Confirm Virtual Account Transaction Status Method |
| [CancelVA()](#331-CancelVAV2)               | Cancel Virtual Account Transaction Method         |


### **1.3.1 setUp**
The setUp() method configures the environment, allowing the selection between the Nicepay development or production environment.

The following sample demonstrates how to configure the Nicepay development environment:
````java
package io.github.nicepay;

import io.github.nicepay.data.TestingConstants;
import io.github.nicepay.utils.LoggerPrint;
import io.github.nicepay.utils.NICEPay;
import org.junit.jupiter.api.BeforeAll;


class VirtualAccountTest {
    private static NICEPay config;

    @BeforeAll
    public static void setUp() {
        config = NICEPay.builder()
                .isProduction(false)
                .build();
    }

}
````


### **1.3.2 vaCreateV2()**
This method for `creating a virtual account` in the NICEPay system. It simulates:
- Retrieving an OAuth2 access token for authentication. 
- Build a request for creating a virtual account with necessary details (such as transaction ID, virtual account name, amount, etc.). 
- Calling the `V2VaService.callV2GenerateVA` to actually create the virtual account to the NICEPay Services.

This test ensures that all necessary components are properly integrated and that the system can successfully create a virtual account under expected conditions.
````java
package io.github.nicepay;

import io.github.nicepay.data.TestingConstants;
import io.github.nicepay.data.model.VirtualAccount;
import io.github.nicepay.data.response.snap.NICEPayResponse;
import io.github.nicepay.service.snap.SnapVaService;
import io.github.nicepay.utils.LoggerPrint;
import io.github.nicepay.utils.NICEPay;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.HashMap;
import java.util.Optional;

class VirtualAccountTest {
    private static LoggerPrint print = new LoggerPrint();
    private static NICEPay config;
    private static TestingConstants DATA;

  @Test
  void vaCreateV2() throws IOException
  {
    String timeStamp = TestingConstants.V2_TIMESTAMP;

    VirtualAccount request = VirtualAccount.builder()
            .timeStamp(timeStamp)
            .iMid(TestingConstants.I_MID)
            .merchantToken(timeStamp, TestingConstants.I_MID, "NICEPAYVA111213", "100", TestingConstants.MERCHANT_KEY )
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

  }

}
````

### **2. callServiceVACheckStatusV2**
**2.1 Virtual Account Inquiry Status**

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
  "timeStamp": "20250131005524",
  "tXid": "IONPAYTEST02201607261333495161",
  "iMid": "IONPAYTEST",
  "referenceNo": "OrdNo20166153345260",
  "amt": "1000",
  "merchantToken": "ae81bdc31418ef1d46f737ea93048d80e6daa60d986f9b35562ce19c093b4102"
}
````

Sample Response
```json
{
  "tXid": "IONPAYTEST02201607261333495161",
  "referenceNo": "OrdNo20166153345260",
  "payMethod": "02",
  "amt": "1000",
  "transDt": "20160726",
  "transTm": "133349",
  "currency": "IDR",
  "goodsNm": "T-1000",
  "billingNm": "HongGilDong",
  "bankCd": "BMRI",
  "vacctNo": "1333495161",
  "vacctValidDt": "20160802",
  "vacctValidTm": "235959",
  "reqDt": "20160726",
  "reqTm": "133349",
  "status": "0",
  "instmntType": "1",
  "instmntMon": "1",
  "resultCd": "0000",
  "resultMsg": "paid"
}
```


**2.3 Example Usage Code Snippet**
### **2.3.1 InquiryStatusVAV2()**
This method is to ensure that the `Virtual Account status inquiry` process works as expected. It checks if the system can successfully:

- Build the inquiry Status request parameter.
- Send the inquiry parameter to NICEPay service and receive a response.
````java
 @Test
void InquiryStatusVAV2() throws IOException, InterruptedException {

  String reffNo = "OrdNo20166153345260";

  InquiryStatus requestData = InquiryStatus.builder()
          .timeStamp(V2_TIMESTAMP)
          .tXid("IONPAYTEST02201607261333495161")
          .iMid(TestingConstants.I_MID)
          .merchantToken(V2_TIMESTAMP, TestingConstants.I_MID, reffNo, amount, TestingConstants.MERCHANT_KEY)
          .referenceNo(reffNo)
          .amt(amount)
          .build();

  NICEPayResponseV2 result = V2InquiryStatusService.callV2InquiryStatus(requestData, config);

}
````

### **3. callServiceVACancelV2**
**3.1 Virtual Account Cancel Transaction**

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
  "timeStamp": "20250131010149",
  "tXid": "NORMALTEST02202501241048477051",
  "iMid": "NORMALTEST",
  "payMethod": "02",
  "cancelType": "1",
  "amt": "10000",
  "merchantToken": "9a988de03c0cfe5189af3a53e758ee03361d84127ce599040d1875232df3cdd2",
  "referenceNo": "ordNo20250124100114"
}
````

Sample Response
````json
{
  "tXid": "NORMALTEST02202501241048477051",
  "referenceNo": "ordNo20250124100114",
  "amt": "10000",
  "transDt": "20250124",
  "transTm": "104847",
  "description": "Testing API by Sibedoel",
  "resultCd": "0000",
  "resultMsg": "SUCCESS"
}
````

**3.3 Example Usage Code Snippet**
### **3.3.1 CancelVAV2()**
This method is to request transaction cancellation process on Virtual Account payment method.

- Build the Cancel request parameter.
- Send the Cancel parameter to NICEPay service and receive a response.

````java
@Test
    void CancelVAV2() throws IOException, InterruptedException {

        String txId = "NORMALTEST02202501241048477051";
        reffNo = "ordNo20250124100114";
        amount = "10000";

        Cancel requestCancel = Cancel.builder()
                .timeStamp(timestamp)
                .tXid(txId)
                .iMid(TestingConstants.I_MID_NORMALCLOSED)
                .referenceNo(reffNo)
                .merchantToken(timestamp, TestingConstants.I_MID_NORMALCLOSED, txId, amount, TestingConstants.MERCHANT_KEY )
                .payMethod("02")
                .cancelType("1")
                .amt(amount)
                .build();

        NICEPayResponseV2 result = V2CancelService.callV2CancelTransaction(requestCancel, config);

    }
````