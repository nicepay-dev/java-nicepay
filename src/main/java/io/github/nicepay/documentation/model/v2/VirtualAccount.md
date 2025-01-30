# Virtual Account SNAP Parameter

## **Properties**

| **Property**     | **Value**                               |
|:-------------|-----------------------------------------|
| API Endpoint | /nicepay/direct/v2/registration         |
| HTTP Method  | POST                                    |
| Description  | Performs Transaction Regist to NICEPAY  |
| Content-Type | application/json                        |

## Request Header Parameter

| Parameter     | Type    | Length | Required  | Description                                                                                                                                                               |
|:--------------|:--------|:-------|:--------- |:--------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Content-Type  | String  |        | ✅        | Application / JSON                                                                                                                                                        |

## **Request Body Parameter**

| Parameter      | Type       | Length | Required                            | Description                                                        | Example / Notes                   |
|:---------------|:-----------|:-------|:------------------------------------|:-------------------------------------------------------------------|:----------------------------------|
| timestamp      | String     | 14     | ✅                                   | Request Timestamp (YYYYMMDDHH24MISS)                               | 20250131155959                    |
| iMid           | String     | 10     | ✅                                   | Merchant ID                                                        | IONPAYTEST                        |
| merchantToken  | String     | 255    | ✅                                   | Merchant Token                                                     | 6cfccfc0046773c1b89d8e98c...      |
| payMethod      | String     | 2      | ✅                                   | [Payment Method](https://docs.nicepay.co.id/nicepay-code-v1#lUEfQ) | 01                                |
| currency       | String     | 3      | ✅                                   | Currency                                                           | IDR                               |
| amt            | String     | 12     | ✅                                   | Transaction Amount                                                 | 1000                              |
| cartData       | JSONstring | 4000   | ✅                                   | Transaction Cart Data                                              | cartData                          |
| bankCd         | String     | 4      | ✅                                   | [Bank Code](https://docs.nicepay.co.id/nicepay-code-v2-bank-code)  | CENA                              |
| vacctValidDt   | String     | 8      | ✅                                   | VA Expiry Date (YYYYMMDD)                                          | 20251231                          |
| vacctValidTm   | String     | 6      | ✅                                   | VA Expiry Time (HH24MISS)                                          | 235959                            |
| merFixAcctId   | String     | 40     | ✅ `Fixed Type`<br/>❌ `Normal Type`  | Merchant Reserved VA ID                                            | 812345678998                      |
| referenceNo    | String     | 40     | ✅                                   | Transaction ID (Reference Number from Merchant)                    | referenceNo1234                   |
| goodsNm        | String     | 100    | ✅                                   | Goods Name                                                         | Merchant Goods 1                  |												                                                    
| dbProcessUrl   | String     | 255    | ✅                                   | Push Notification URL                                              | https://merchant.com/dbProcessUrl |												                                                    
| userIP         | String     | 15     | ✅                                   | User IP Address                                                    | 127.0.0.1                         |												                                                    
| description    | String     | 100    | ❌                                   | Transaction Description                                            | this is test order                |												                                                    
| billingNm      | String     | 30     | ✅                                   | Billing Name                                                       | Buyer Name                        |
| billingEmail   | String     | 40     | ✅                                   | Billing E-Mail                                                     | user@email.com                    |
| billingPhone   | String     | 15     | ✅                                   | Billing Phone                                                      | 081234567890                      |
| billingAddr    | String     | 100    | ✅                                   | Billing Address                                                    | Buyer Addr St                     |
| billingCity    | String     | 50     | ✅                                   | Billing City                                                       | Jakarta Utara                     |
| billingState   | String     | 50     | ✅                                   | Billing State                                                      | DKI Jakarta                       |
| billingPostCd  | String     | 10     | ✅                                   | Billing Postal Code                                                | 10160                             |
| billingCountry | String     | 10     | ✅                                   | Billing Country                                                    | Indonesia                         |
| userSessionID  | String     | 100    | ❌                                   | User Session ID                                                    | userSessionId123                  |                 
| userAgent      | String     | 100    | ❌                                   | User Agent Information                                             | Mozilla                           | 
| userLanguage   | String     | 2      | ❌                                   | User Language                                                      | en-US                             |  
| worker         | String     | 10     | ❌                                   | Worker                                                             | worker                            |



## **Response Body Parameter**

| Parameter    | Type   | Length | Required | Description                                                                         |
|:-------------|:-------|:-------|:---------|:------------------------------------------------------------------------------------|
| resultCd     | String | 14     | ✅        | [Result Code](https://docs.nicepay.co.id/nicepay-code-v2-result-code)               |
| resultMsg    | String | 255    | ✅        | Result message: [Error Code](https://docs.nicepay.co.id/nicepay-code-v2-error-code) |
| tXid         | String | 30     | ✅        | Transaction ID                                                                      |
| referenceNo  | String | 40     | ✅        | Transaction ID (Reference Number from Merchant)                                     | 
| payMethod    | String | 2      | ✅        | [Payment Method](https://docs.nicepay.co.id/nicepay-code-v1#lUEfQ)                  |
| amt          | String | 12     | ✅        | Transaction Amount                                                                  | 
| transDt      | String | 8      | ✅        | Transaction Date (YYYYMMDD)                                                         |
| transTm      | String | 6      | ✅        | Transaction Date (YYYYMMDD)                                                         |
| currency     | String | 3      | ✅        | Currency                                                                            |
| goodsNm      | String | 100    | ✅        | Goods Name                                                                          | 												                                                    
| billingNm    | String | 30     | ✅        | Billing Name                                                                        |
| bankCd       | String | 4      | ✅        | [Bank Code](https://docs.nicepay.co.id/nicepay-code-v2-bank-code)                   | 
| vacctNo      | String | 20     | ✅        | VA Number                                                                           |
| vacctValidDt | String | 8      | ✅        | VA Expiry Date (YYYYMMDD)                                                           | 
| vacctValidTm | String | 6      | ✅        | VA Expiry Time (HH24MISS)                                                           |

