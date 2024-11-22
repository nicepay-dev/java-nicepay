# Virtual Account Services

A list of all methods in the `SnapVaService`. Click on the method name to view detailed information about that method.

| Methods                                                 | Description                         |
|:--------------------------------------------------------|:------------------------------------|
| [callGeneratedVA](#1-callGeneratedVA)                   | Generated VA Number to NICEPAY API  |
| [callServiceVACheckStatus](#2-callServiceVACheckStatus) | Confirmation Transaction Status API |
| [callServiceVACancel](#3-callServiceVACancel)           | Canceled VA Number to NICEPAY API   |

### **1. callGeneratedVA**

**1.1 Generated VA Number**

  | Property      | Value                                        |
  |:--------------|:---------------------------------------------|
  | Service Code  | 27                                           |
  | HTTP Method   | POST                                         |
  | Endpoint      | /api/v1.0/transfer-va/create-va              |
  | Description   | Request the Generated VA transaction process |
  | Content-Type  | `application/json`                           |

**1.2 Sample Parameters Request**
````json
//HEADER SECTION
Content-Type: application/json
Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJJT05QQVlURVNUIiwiaXNzIjoiTklDRVBBWSIsIm5hbWUiOiJCUklOIiwiZXhwIjoiMjAyNC0wMi0xMVQwODowOTozNVoifQ==.aIybLX36dYwfFvW7dcoenXkOe2EAwIYq84ZtPuIf3K8=
X-TIMESTAMP: 2024-04-30T10:11:40+07:00
X-SIGNATURE: ufBw7R8RhW79IOiV/Hlv607M60qzAYBv8uZmbSrYb6/Thlo/FhQVP4xRyelUehoSS9o+QS6vWTKb/ygtucC+tQ==
X-PARTNER-ID:IONPAYTEST
X-EXTERNAL-ID: eXid202402111402271707638093
CHANNEL-ID: IONPAYTEST
        
//BODY SECTION        
{
  "partnerServiceId": "",
  "customerNo": "",
  "virtualAccountNo": "",
  "virtualAccountName": "John Doe",
  "trxId": "2022020100000000000001",
  "totalAmount": {
    "value": "10000.00",
    "currency": "IDR"
  },
  "additionalInfo": {
    "bankCd": "BMRI",
    "goodsNm": "John Doe",
    "dbProcessUrl": "https://testing.co.id/",
    "vacctValidDt": "",
    "vacctValidTm": "",
    "msId": "",
    "msFee": "",
    "msFeeType": "",
    "mbFee": "",
    "mbFeeType": ""
  }
}
````
**1.3 Example Usage Code Snippet**

| Methods                                     | Description                                       |
|:--------------------------------------------|:--------------------------------------------------|
| [setUp()](#131-setUp)                       | Setup Credentials Method                          |
| [getToken()](#132-getToken)                 | Generated Access Token Method                     |
| [vaCreate()](#133-vaCreate)                 | Generated Virtual Account Method                  |
| [InquiryStatusVA()](#231-InquiryStatusVA)   | Confirm Virtual Account Transaction Status Method |
| [CancelVA()](#331-CancelVA)                 | Cancel Virtual Account Transaction Method         |


### **1.3.1 setUp**
Method setup() ensures that all the `credentials` required parameters for communicating with NICEPay’s API are correctly initialized before any tests are run, preparing the environment for consistent and valid test execution.

````java
package io.github.nicepay;

import io.github.nicepay.data.TestingConstants;
import io.github.nicepay.utils.LoggerPrint;
import io.github.nicepay.utils.NICEPay;
import org.junit.jupiter.api.BeforeAll;


class VirtualAccountTest {
    private static LoggerPrint print = new LoggerPrint();
    private static NICEPay config;
    private static TestingConstants DATA;

    @BeforeAll
    public static void setUp() {
        config = NICEPay.builder()
                .isProduction(false)
                .clientSecret(DATA.CLIENT_SECRET)
                .partnerId(DATA.PARTNER_ID)
                .externalID(DATA.EXTERNAL_ID)
                .timestamp(DATA.TIMESTAMP)
                .privateKey(DATA.PRIVATE_KEY)
                .build();
    }

}
````
### **1.3.2 getToken()**
- HTTP Method : POST
- Return Type : Object

This method is used to obtain an OAuth2 access token, which can be used in subsequent API calls to authenticate or authorize actions on behalf of the system. The token is fetched by making a call to the `SnapTokenService.callGetAccessToken` using merchant credentials and any additional information provided.

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

class VirtualAccountTest {
    private static LoggerPrint print = new LoggerPrint();
    private static NICEPay config;
    private static TestingConstants DATA;


  // return Token Object
  public Object getToken() throws IOException {
    Map<String, String> additionalInfo = new HashMap<>();
    AccessToken accessToken = AccessToken.builder()
            .grantType("client_credentials")
            .additionalInfo(additionalInfo)
            .build();
    return  SnapTokenService.callGetAccessToken(accessToken,config);
  }
}
````

### **1.3.3 vaCreate()**
This method for `creating a virtual account` in the NICEPay system. It simulates:
- Retrieving an OAuth2 access token for authentication. 
- Build a request for creating a virtual account with necessary details (such as transaction ID, virtual account name, amount, etc.). 
- Calling the `SnapVaService.callGeneratedVA` to actually create the virtual account to the NICEPay Services.

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
  void vaCreate() throws IOException
  {
    var accessToken = Optional.ofNullable((NICEPayResponse) getToken())
            .map(NICEPayResponse::getAccessToken)
            .orElseThrow(() -> new IllegalArgumentException("Token is null"));

    VirtualAccount virtualAccount = VirtualAccount.builder()
            .partnerServiceId("1234")
            .customerNo("")
            .virtualAccountNo("")
            .virtualAccountName("TESTVaName")
            .trxId("TESTTrxId")
            .totalAmount("11000.00","IDR")
            .additionalInfo( new HashMap<String, Object>() {
              {
                put("bankCd","BBBA");
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

    NICEPayResponse response = SnapVaService.callGeneratedVA(virtualAccount,accessToken,config);

  }

}
````

### **2. callServiceVACheckStatus**
**2.1 Virtual Account Inquiry Status**

| Property      | Value                                     |
|:--------------|:------------------------------------------|
| Service Code  | 26                                        |
| HTTP Method   | POST                                      |
| Endpoint      | /api/v1.0/transfer-va/status              |
| Description   | Request status inquiry to NICEPAY         |
| Content-Type  | `application/json`                        |

**2.2 Sample Parameters Request**
````json
//HEADER SECTION
Content-Type: application/json
Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJJT05QQVlURVNUIiwiaXNzIjoiTklDRVBBWSIsIm5hbWUiOiJCUklOIiwiZXhwIjoiMjAyNC0wMi0xMVQwODowOTozNVoifQ==.aIybLX36dYwfFvW7dcoenXkOe2EAwIYq84ZtPuIf3K8=
X-TIMESTAMP: 2024-04-30T10:11:40+07:00
X-SIGNATURE: ufBw7R8RhW79IOiV/Hlv607M60qzAYBv8uZmbSrYb6/Thlo/FhQVP4xRyelUehoSS9o+QS6vWTKb/ygtucC+tQ==
X-PARTNER-ID:IONPAYTEST
X-EXTERNAL-ID: eXid202402111402271707638093
CHANNEL-ID: IONPAYTEST

//BODY SECTION
{
  "partnerServiceId": "",
  "customerNo": "",
  "virtualAccountNo": "",
  "virtualAccountName": "John Doe",
  "trxId": "2022020100000000000001",
  "totalAmount": {
    "value": "10000.00",
    "currency": "IDR"
  },
  "additionalInfo": {
    "bankCd": "BMRI",
    "goodsNm": "John Doe",
    "dbProcessUrl": "https://testing.co.id/",
    "vacctValidDt": "",
    "vacctValidTm": "",
    "msId": "",
    "msFee": "",
    "msFeeType": "",
    "mbFee": "",
    "mbFeeType": ""
  }
}
````
**2.3 Example Usage Code Snippet**
### **2.3.1 InquiryStatusVA()**
This method is to ensure that the `Virtual Account status inquiry` process works as expected. It checks if the system can successfully:

- Retrieve a valid access token.
- Build the inquiry Status request parameter.
- Send the inquiry parameter to NICEPay service and receive a response.
````java
 @Test
    void InquiryStatusVA() throws IOException, InterruptedException {
        var accessToken = Optional.ofNullable((NICEPayResponse) getToken())
                .map(NICEPayResponse::getAccessToken)
                .orElseThrow(() -> new IllegalArgumentException("Token is null"));

        InquiryStatus requestData = InquiryStatus.builder()
                .partnerServiceId("")
                .customerNo("")
                .virtualAccountNo("447792631104334735")
                .inquiryRequestId("dsfsd43")
                .totalAmount("11000.00", "IDR")
                .trxId("TESTTrxId")
                .tXidVA("IONPAYTEST02202408191104334735")
                .build();
        NICEPayResponse result = SnapInquiryStatusService.callServiceVACheckStatus(requestData,accessToken,config);

    }
````

### **3. callServiceVACancel**
**3.1 Virtual Account Cancel Transaction**

| Property      | Value                                           |
|:--------------|:------------------------------------------------|
| Service Code  | 31                                              |
| HTTP Method   | DELETE                                          |
| Endpoint      | /api/v1.0/transfer-va/delete-va                 |
| Description   | To request VA transaction cancellation process  |
| Content-Type  | `application/json`                              |

**3.2 Sample Parameters Request** 

````json
//HEADER SECTION
Content-Type: application/json
Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJJT05QQVlURVNUIiwiaXNzIjoiTklDRVBBWSIsIm5hbWUiOiJCUklOIiwiZXhwIjoiMjAyNC0wMi0xMVQwODowOTozNVoifQ==.aIybLX36dYwfFvW7dcoenXkOe2EAwIYq84ZtPuIf3K8=
X-TIMESTAMP: 2024-12-31T10:11:40+07:00
X-SIGNATURE: ufBw7R8RhW79IOiV/Hlv607M60qzAYBv8uZmbSrYb6/Thlo/FhQVP4xRyelUehoSS9o+QS6vWTKb/ygtucC+tQ==
X-PARTNER-ID: NORMALTEST
X-EXTERNAL-ID: eXid202402111402271707638095
CHANNEL-ID: NORMALTEST

{
  "partnerServiceId": "08889",
  "customerNo": "12345678901234567890",
  "virtualAccountNo": "9912304000007307",
  "trxId": "2024123000000000000001",
  "additionalInfo": {
    "totalAmount": {
      "value": "10000.00",
      "currency": "IDR"
    },
    "tXidVA": "NORMALTEST02202401181001033629",
    "cancelMessage": "Cancel Virtual Account"
  }
}
````
**3.3 Example Usage Code Snippet**
### **3.3.1 CancelVA()**
This method is to request transaction cancellation process on Virtual Account payment method.
- Retrieve a valid access token.
- Build the Cancel request parameter.
- Send the Cancel parameter to NICEPay service and receive a response.
  ````java
    @Test
    void CancelVA() throws IOException, InterruptedException {
        var accessToken = Optional.ofNullable((NICEPayResponse) getToken())
                .map(NICEPayResponse::getAccessToken)
                .orElseThrow(() -> new IllegalArgumentException("Token is null"));
        		Cancel requestData = Cancel.builder()
                .partnerServiceId("")
                .customerNo("")
                .virtualAccountNo("7001400002009191")
                .totalAmount("10000.00", "IDR")
                .trxId("TESTREFNO")
                .tXidVA("IONPAYTEST02202408191440205690")
                .cancelMessage("test cancel")
                .build();

        NICEPayResponse result = SnapCancelService.callServiceVACancel(requestData,accessToken,config);
