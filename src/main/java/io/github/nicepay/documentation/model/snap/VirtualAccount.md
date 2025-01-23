# Virtual Account SNAP Parameter

## **Properties**

| **Property**     | **Value**                                       |
|:-------------|-------------------------------------------------|
| Service Code | 27                                              |
| API Endpoint | /api/v1.0/transfer-va/create-va                 |
| HTTP Method  | POST                                            |
| Description  | To request the Generated VA transaction process |
| Content-Type | application/json                                |

## Request Header Parameter

| Parameter     | Type    | Length | Required  | Description                                                                                                                                                               |
|:--------------|:--------|:-------|:--------- |:--------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Content-Type  | String  |        | ✅        | Application / JSON                                                                                                                                                        |
| Authorization | String  |        | ✅        | Bearer “access_token”                                                                                                                                                     |
| X-TIMESTAMP   | String  |        | ✅        | Using ISO 8601 as timestamp format.Transaction date time, in format YYYYMMDDTHH:mm:ss+07:00.  Time must be in GMT+7 (Jakarta time).  `Example: 2022-03-21T10:11:40+07:00.`|
| X-SIGNATURE   | String  |        | ✅        | Request message signature `(EIFYcRuFfFh8nkVhLZrkGKYfk36SDM8VvV3EW/OoKbkCspqMCiU/9ZhCiO29EjUEF0j7vjkQpSidvtIpt/BkLg ==)`                                                   |
| X-PARTNER-ID  | String  | 20     | ✅        | `X-PARTNER-ID = CLIENT_ID`                                                                                                                                                |
| X-EXTERNAL-ID | String  | 40     | ✅        | Unique Reference Number `(41807553358950093184)`                                                                                                                          |
| CHANNEL-ID    | String  |        | ✅        | `Channel Identification with name of Merchant + 2 digits number (mid01)  `                                                                                                |

## **Request Body Parameter**

_For `partnerServiceId` and `virtualAccountNo` parameters can send with an empty value._

| Parameter             | Type   | Length | Required | Description                                                                           |
|:----------------------|:-------|:-------|:---------|:--------------------------------------------------------------------------------------|
| partnerServiceId      | String | 20     | ✅        | Partner Service ID / Prefix                                                           |
| customerNo            | String | 40     | ✅        | Customer Number (Mandatory for Fix type)                                              |
| virtualAccountNo      | String | 25     | ✅        | Virtual Account Number                                                                |
| virtualAccountName    | String | 100    | ✅        | Virtual Account Name                                                                  |
| trxId                 | String | 40     | ✅        | Transaction ID (Reference Number from Merchant)                                       |
| totalAmount           | Object |        | ❌        | "totalAmount": { ...}                                                                 | 												                                                    
| value                 | String | 12     | ✅        | Transaction Amount                                                                    |
| currency              | String | 3      | ✅        | Currency                                                                              |
| additionalInfo        | Object |        | ❌        | "additionalInfo": {...}                                                               |
| bankCd                | String | 4      | ✅        | [Bank Code - SNAP API](https://docs.nicepay.co.id/en/nicepay-code-api-snap-bank-code) |
| goodsNm               | String | 200    | ✅        | Goods Name                                                                            |
| dbProcessUrl          | String |        | ✅        | Payment Result Receive URL (Server Side)                                              |	                                           
| vacctValidDt          | String | 8      | ❌        | Virtual Account Valid Date                                                            |
| vacctValidTm          | String | 6      | ❌        | Virtual Account Valid Time                                                            |
| msId                  | String | 20     | ❌        | Merchant Seller ID                                                                    |
| msFee                 | String | 7      | ❌        | Merchant Seller Fee                                                                   |
| msFeeType             | String | 2      | ❌        | Merchant Seller Fee Type                                                              |
| mbFee                 | String | 7      | ❌        | Merchant Balance Fee                                                                  |
| mbFeeType             | String | 2      | ❌        | Merchant Balance Fee Type                                                             |

## **Response Body Parameter**

| Parameter          | Type   | Length | Required  | Description                                                                                   |
|:-------------------|:-------|:-------|:----------|:----------------------------------------------------------------------------------------------|
| responseCode       | String |        | ✅         | [General Response Code](https://docs.nicepay.co.id/en/nicepay-code-api-snap-general-response) |
| responseMessage    | String |        | ✅         | Response Message                                                                              |
| virtualAccountData | Object |        | ✅         | Object Virtual Account Data                                                                   |
| partnerServiceId   | String | 20     | ❌         | Partner Service ID / Prefix (Mandatory for Fix type)                                          |
| customerNo         | String | 40     | ✅         | Customer Number (Mandatory for Fix type)                                                      |
| virtualAccountNo   | String | 25     | ✅         | Virtual Account Number                                                                        | 												                                                    
| virtualAccountName | String | 100    | ✅         | Virtual Account Name                                                                          |
| trxId              | String | 40     | ✅         | From Create VA Request (Reference Number from Merchant)                                       |
| totalAmount        | Object |        | ❌         | "totalAmount": {...}                                                                          |
| value              | String | 12     | ✅         | Transaction Amount                                                                            |
| currency           | String | 3      | ✅         | Currency                                                                                      |
| additionalInfo     | Object |        | ❌         | "additionalInfo": {...}                                                                       |	                                           
| bankCd             | String | 40     | ✅         | [Bank Code - SNAP API](https://docs.nicepay.co.id/en/nicepay-code-api-snap-bank-code)         |
| tXidVa             | String | 30     | ✅         | Transaction ID of Virtual Account                                                             |
| goodsNm            | String | 200    | ✅         | Goods Name                                                                                    |
| vacctValidDt       | String | 8      | ✅         | Virtual Account Valid Date                                                                    |
| vacctValidTm       | String | 6      | ✅         | Virtual Account Valid Time                                                                    |
| msId               | String | 20     | ❌         | Merchant Seller ID                                                                            |
| msFee              | String | 7      | ❌         | Merchant Seller Fee                                                                           |
| msFeeType          | String | 2      | ❌         | Merchant Seller Fee Type                                                                      |
| mbFee              | String | 7      | ❌         | Merchant Balance Fee                                                                          |
| mbFeeType          | String | 2      | ❌         | Merchant Balance Fee Type                                                                     |

