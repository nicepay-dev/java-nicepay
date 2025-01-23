# Credit Card V1 Professional Parameter

## **Properties**

| **Property**     | **Value**                                       |
|:-------------|-------------------------------------------------| 
| API Endpoint |/nicepay/api/orderRegist.do                 |
| HTTP Method  | POST                                            |
| Description  | Perform Transaction Registration |
| Content-Type | x-www-form-urlencoded                                |


## Request Header Parameter

| Parameter     | Type    | Length | Required  | Description                                                                                                                                                               |
|:--------------|:--------|:-------|:--------- |:--------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Content-Type  | String  |        | ✅        | x-www-form-urlencoded                                                                                                                                                        |

## **Request Body Parameter**


| Parameter      | Type    | Length | Required | Description                                                          | Example / Notes                   |
|:---------------|:--------|:-------|:---------|:---------------------------------------------------------------------|:----------------------------------|
| iMid           | String  | 10     | ✅        | Merchant ID                                                          | IONPAYTEST                        |
| merchantToken  | String  | 255    | ✅        | Merchant Token                                                       | 6cfccfc0046773c1b89d8e98c...      |
| payMethod      | String  | 2      | ✅        | [Payment Method](https://docs.nicepay.co.id/nicepay-code-v1#lUEfQ)   | 01                                |
| currency       | String  | 3      | ✅        | Currency                                                             | IDR                               |
| amt            | String  | 12     | ✅        | Transaction Amount                                                   | 1000                              |
| cartData       | String  | 4000   | ✅        | Transaction Cart Data                                                | cartData                          |
| referenceNo    | String  | 40     | ✅        | Transaction ID (Reference Number from Merchant)                      | referenceNo1234                   |
| goodsNm        | String  | 100    | ✅        | Goods Name                                                           | Merchant Goods 1                  |												                                                    
| callBackUrl    | String  | 255    | ✅        | Payment Result Page URL                                              | https://merchant.com/callBackUrl  |												                                                    
| dbProcessUrl   | String  | 255    | ✅        | Push Notification URL                                                | https://merchant.com/dbProcessUrl |												                                                    
| userIP         | String  | 15     | ✅        | User IP Address                                                      | 127.0.0.1                         |												                                                    
| description    | String  | 100    | ❌        | Transaction Description                                              | this is test order                |												                                                    
| billingNm      | String  | 30     | ✅        | Billing Name                                                         | Buyer Name                        |
| billingEmail   | String  | 40     | ✅        | Billing E-Mail                                                       | user@email.com                    |
| billingPhone   | String  | 15     | ✅        | Billing Phone                                                        | 081234567890                      |
| billingAddr    | String  | 100    | ✅        | Billing Address                                                      | Buyer Addr St                     |
| billingCity    | String  | 50     | ✅        | Billing City                                                         | Jakarta Utara                     |
| billingState   | String  | 50     | ✅        | Billing State                                                        | DKI Jakarta                       |
| billingPostCd  | String  | 10     | ✅        | Billing Postal Code                                                  | 10160                             |
| billingCountry | String  | 10     | ✅        | Billing Country                                                      | Indonesia                         |
| instmntType    | String  | 2      | ✅        | [Installment Type](https://docs.nicepay.co.id/nicepay-code-v1#EdIdH) | 1                                 |
| instmntMon     | String  | 2      | ✅        | Installment Month                                                    | 1                                 |
| deliveryNm     | String  | 30     | ❌        | Delivery Name                                                        | Buyer Name                        |
| deliveryPhone | String | 15     | ❌        | Delivery Phone Number                                                | 081234567890                      |
| deliveryAddr   | String  | 100    | ❌        | Delivery Address                                                     | Jl Buyer                          |
| deliveryCity   | String  | 50     | ❌        | Delivery City                                                        | Jakarta Selatan                   |
| deliveryState  | String  | 50     | ❌        | Delivery State                                                       | DKI Jakarta                       |
| deliveryPostCd | String  | 10     | ❌        | Delivery Postal Code                                                 | 12170                             |
| deliveryCountry | String  | 10     | ❌        | Delivery Country                                                     | Indonesia                         |
| vat            | String  | 12     | ❌        | VAT Number                                                           | 0                                 | 
| fee            | String  | 12     | ❌        | Service Fee                                                          | 0                                 |
| notaxAmt       | String  | 12     | ❌        | Tax Free Amount                                                      | 0                                 |
| reqDt          | String  | 8      | ❌        | Request Date                                                         | 20251231                          |
| reqTm          | String  | 6      | ❌        | Request Time                                                         | 135959                            |
| reqDomain      | String  | 100    | ❌        | Request Domain                                                       | merchant.com                      |
| reqServerIP    | String  | 15     | ❌        | Request Server IP Address                                            | 127.0.0.1                         |
| reqClientVer   | String  | 50     | ❌        | Request Client Version                                               | 1                                 |
| userSessionID  | String  | 100    | ❌        | User Session ID                                                      | userSessionId123                  |                                   |
| userAgent      | String  | 100    | ❌ | User Agent Information                                               | Mozilla                           |                                                                      |                                   | 
| userLanguage   | String  | 2      | ❌ | User Language                                                        | en-US                             |                                                                      |                                   |
| worker         | String  | 10     | ❌ | Worker | worker                            |                                                                      |                                   | |        |        |          |                                                                           |              | 

## **Response Body Parameter**

| Parameter    | Type   | Length | Required | Description                                                        |
|:-------------|:-------|:-------|:---------|:-------------------------------------------------------------------|
| apiType      | String | 2      | ✅        | Api Type Request                                                   |
| tXid         | String | 30     | ✅        | Transaction ID                                                     |
| requestDate  | String | 14     | ✅        | Request timestamp                                                  |
| responseDate | String | 14     | ✅        | Response timestamp                                                 |
| data         | Object |        | ✅        | "data":{...}                                                       |

## **Data Parameter**
| Parameter    | Type   | Length | Required | Description                                                        |
|:-------------|:-------|:-------|:---------|:-------------------------------------------------------------------|
| tXid         | String | 30     | ✅        | Object Virtual Account Data                                        |
| resultCd     | String | 4      | ✅        | [Result Code](https://docs.nicepay.co.id/nicepay-code-v1#sPc2x)    |
| resultMsg    | String | 255    | ✅        | [Result Message](https://docs.nicepay.co.id/nicepay-code-v1#sPc2x) |
| requestURL   | Object |        | ✅        | Request Payment URL                                                |


