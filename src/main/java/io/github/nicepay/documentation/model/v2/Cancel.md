# Inquiry Status V1 Professional Param 

## **Properties**

| **Property**     | **Value**                   |
|:-------------|-----------------------------| 
| API Endpoint | /nicepay/direct/v2/cancel   |
| HTTP Method  | POST                        |
| Description  | Cancel Nicepay transaction  |
| Content-Type | application/json            |


## Request Header Parameter

| Parameter     | Type    | Length | Required  | Description       |
|:--------------|:--------|:-------|:--------- |:------------------|
| Content-Type  | String  |        | ✅        | application/json  |

## **Request Body Parameter**

| Parameter      | Type   | Length | Required | Description                                                                     | Example / Notes                |
|:---------------|:-------|:-------|:---------|:--------------------------------------------------------------------------------|:-------------------------------|
| timestamp      | String | 14     | ✅        | Request Timestamp (YYYYMMDDHH24MISS)                                            | 20250131155959                 |
| iMid           | String | 10     | ✅        | Merchant ID                                                                     | IONPAYTEST                     |
| merchantToken  | String | 255    | ✅        | Merchant Token                                                                  | 6cfccfc0046773c1b89d8e98c...   |
| amt            | String | 12     | ✅        | Transaction Amount                                                              | 1000                           |
| referenceNo    | String | 40     | ✅        | Transaction ID (Reference Number from Merchant)                                 | referenceNo1234                |
| tXid           | String | 30     | ✅        | Transaction ID                                                                  | IONPAYTEST01202501202059408369 |
| cancelType     | String | 2      | ✅        | [Cancel Type Code](https://docs.nicepay.co.id/nicepay-code-v2-cancel-type-code) | 1                              |
| cancelMsg      | String | 255    | ❌        | Cancel Message                                                                  | Testing Cancel for VA          |
| cancelServerIp | String | 15     | ❌        | Server IP                                                                       | 127.0.0.1                      |
| cancelUserId   | String | 30     | ❌        | User ID                                                                         |                                |
| cancelUserIp   | String | 15     | ❌        | user IP                                                                         | 127.0.0.1                      |
| cancelUserInfo | String | 100    | ❌        | User Information                                                                |                                |
| cancelRetryCnt | String | 2      | ❌        | Retry count                                                                     |                                |
| worker         | String | 10     | ❌        | Worker                                                                          |                                |

## **Response Body Parameter**

| Parameter      | Type       | Length | Required | Description                                                        |
|:---------------|:-----------|:-------|:---------|:-------------------------------------------------------------------|
| resultCd       | String     | 4      | ✅        | [Result Code](https://docs.nicepay.co.id/nicepay-code-v1#sPc2x)    |
| resultMsg      | String     | 255    | ✅        | [Result Message](https://docs.nicepay.co.id/nicepay-code-v1#sPc2x) |
| tXid           | String     | 30     | ✅        | Transaction ID                                                     |
| referenceNo    | String     | 40     | ✅        | Transaction ID (Reference Number from Merchant)                    |
| amt            | String     | 12     | ✅        | Transaction Amount                                                 | 
| transDt        | String     |        | ❌        | Transaction Date                                                   |
| transTm        | String     |        | ❌        | Transaction time                                                   |
| description    | String     | 100    | ❌        | Transaction Description                                            | 
