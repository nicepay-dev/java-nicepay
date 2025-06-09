# Credit Card V2 Direct Registration Parameter

## **Properties**

| **Property**     | **Value**                          |
|:-------------|------------------------------------| 
| API Endpoint | /nicepay/direct/v2/registration    |
| HTTP Method  | POST                               |
| Description  | Perform Transaction Registration   |
| Content-Type | application/json                   |


## Request Header Parameter

| Parameter     | Type    | Length | Required | Description         |
|:--------------|:--------|:-------|:---------|:--------------------|
| Content-Type  | String  |        | ✅        | application/json    |

## **Request Body Parameter**

| Parameter      | Type       | Length | Required | Description                                                                | Example / Notes                   |
|:---------------|:-----------|:-------|:---------|:---------------------------------------------------------------------------|:----------------------------------|
| timestamp      | String     | 14     | ✅        | Request Timestamp (YYYYMMDDHH24MISS)                                       | 20250131155959                    |
| iMid           | String     | 10     | ✅        | Merchant ID                                                                | IONPAYTEST                        |
| merchantToken  | String     | 255    | ✅        | Merchant Token                                                             | 6cfccfc0046773c1b89d8e98c...      |
| payMethod      | String     | 2      | ✅        | [Payment Method](https://docs.nicepay.co.id/nicepay-code-v1#lUEfQ)         | 01                                |
| currency       | String     | 3      | ✅        | Currency                                                                   | IDR                               |
| amt            | String     | 12     | ✅        | Transaction Amount                                                         | 1000                              |
| cartData       | JSONstring | 4000   | ✅        | Transaction Cart Data                                                      | cartData                          |
| referenceNo    | String     | 40     | ✅        | Transaction ID (Reference Number from Merchant)                            | referenceNo1234                   |
| goodsNm        | String     | 100    | ✅        | Goods Name                                                                 | Merchant Goods 1                  |												                                                    
| dbProcessUrl   | String     | 255    | ✅        | Push Notification URL                                                      | https://merchant.com/dbProcessUrl |												                                                    
| userIP         | String     | 15     | ✅        | User IP Address                                                            | 127.0.0.1                         |												                                                    
| description    | String     | 100    | ❌        | Transaction Description                                                    | this is test order                |												                                                    
| billingNm      | String     | 30     | ✅        | Billing Name                                                               | Buyer Name                        |
| billingEmail   | String     | 40     | ✅        | Billing E-Mail                                                             | user@email.com                    |
| billingPhone   | String     | 15     | ✅        | Billing Phone                                                              | 081234567890                      |
| billingAddr    | String     | 100    | ✅        | Billing Address                                                            | Buyer Addr St                     |
| billingCity    | String     | 50     | ✅        | Billing City                                                               | Jakarta Utara                     |
| billingState   | String     | 50     | ✅        | Billing State                                                              | DKI Jakarta                       |
| billingPostCd  | String     | 10     | ✅        | Billing Postal Code                                                        | 10160                             |
| billingCountry | String     | 10     | ✅        | Billing Country                                                            | Indonesia                         |
| instmntType    | String     | 2      | ✅        | [Installment Type](https://docs.nicepay.co.id/nicepay-code-v1#EdIdH)       | 1                                 |
| instmntMon     | String     | 2      | ✅        | Installment Month                                                          | 1                                 |
| recurrOpt      | String     | 2      | ✅        | `0` : Automatic Cancel <br/>`1` : Do not cancel <br/>`2` Do not make token | 2                                 |
| userSessionID  | String     | 100    | ❌        | User Session ID                                                            | userSessionId123                  |                 
| userAgent      | String     | 100    | ❌        | User Agent Information                                                     | Mozilla                           | 
| userLanguage   | String     | 2      | ❌        | User Language                                                              | en-US                             |  
| worker         | String     | 10     | ❌        | Worker                                                                     | worker                            |

## **Response Body Parameter**

| Parameter    | Type   | Length | Required | Description                                                                         |
|:-------------|:-------|:-------|:---------|:------------------------------------------------------------------------------------|
| resultCd      | String | 14     | ✅        | [Result Code](https://docs.nicepay.co.id/nicepay-code-v2-result-code)               |
| resultMsg     | String | 255    | ✅        | Result message: [Error Code](https://docs.nicepay.co.id/nicepay-code-v2-error-code) |
| tXid          | String | 30     | ❌        | Transaction ID                                                                      |
| referenceNo   | String | 40     | ❌        | Transaction ID (Reference Number from Merchant)                                     | 
| payMethod     | String | 2      | ❌        | [Payment Method](https://docs.nicepay.co.id/nicepay-code-v1#lUEfQ)                  |
| amt           | String | 12     | ❌        | Transaction Amount                                                                  | 
| transDt       | String | 8      | ❌        | Transaction Date (YYYYMMDD)                                                         |
| transTm       | String | 6      | ❌        | Transaction Date (YYYYMMDD)                                                         |


# Credit Card V2 Direct Payment Parameter

## **Properties**

| **Property**     | **Value**                        |
|:-------------|----------------------------------| 
| API Endpoint | /nicepay/direct/v2/payment       |
| HTTP Method  | POST                             |
| Description  | Perform Transaction Registration |
| Content-Type | x-www-form-urlencoded            |


## Request Header Parameter

| Parameter     | Type    | Length | Required  | Description                                                                                                                                                               |
|:--------------|:--------|:-------|:--------- |:--------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Content-Type  | String  |        | ✅        | x-www-form-urlencoded                                                                                                                                                        |

## **Request Body Parameter**


| Parameter       | Type   | Length | Required | Description                                     | Example / Notes                  |
|:----------------|:-------|:-------|:---------|:------------------------------------------------|:---------------------------------|
| iMid            | String | 10     | ✅        | Merchant ID                                     | IONPAYTEST                       |
| merchantToken   | String | 255    | ✅        | Merchant Token                                  | 6cfccfc0046773c1b89d8e98c...     |
| referenceNo     | String | 40     | ✅        | Transaction ID (Reference Number from Merchant) | referenceNo1234                  |
| callBackUrl     | String | 255    | ✅        | Payment Result Page URL                         | https://merchant.com/callBackUrl |												                                                    
| tXid            | String | 30     | ✅        | Transaction ID                                  | IONPAYTEST01202212021455054652   |
| cardNo          | String | 20     | ✅        | Credit Card Number                              | 4434260000000008                 |
| cardExpYymm     | String | 4      | ✅        | Card Expiry (`YYMM`)                            | 2912                             |
| cardCvv         | String | 4      | ✅        | Card CVV                                        | 101                              |
| cardHolderNm    | String | 45     | ✅        | Card Holder Name                                | John Doe                         |
| cardHolderEmail | String | 100    | ✅        | Card Holder Email                               | Johndoe@email.com                |
| encrypted       | String | 1      | ✅        | Flagging for transaction use encryption or not  | 1                                |
| publicKey       | String | 100    | ✅        | Hashing of used public key                      | a5f3445e7dd166b8c6cfef685....    |

## **Response Body Parameter**

After submitting a payment request and completing the payment process, a response will be sent to your **Callback URL**.

| Parameter     | Type   | Length | Required | Description                                                                                                                                             |
|:--------------|:-------|:-------|:---------|:--------------------------------------------------------------------------------------------------------------------------------------------------------|
| resultCd      | String | 14     | ✅        | [Result Code](https://docs.nicepay.co.id/nicepay-code-v2-result-code)                                                                                   |
| resultMsg     | String | 255    | ✅        | Result message: [Error Code](https://docs.nicepay.co.id/nicepay-code-v2-error-code)                                                                     |
| tXid          | String | 30     | ❌        | Transaction ID                                                                                                                                          |
| referenceNo   | String | 40     | ❌        | Transaction ID (Reference Number from Merchant)                                                                                                         | 
| payMethod     | String | 2      | ❌        | [Payment Method](https://docs.nicepay.co.id/nicepay-code-v1#lUEfQ)                                                                                      |
| currency      | String | 3      | ❌        | Currency                                                                                                                                                |
| amt           | String | 12     | ❌        | Transaction Amount                                                                                                                                      | 
| goodsNm       | String | 100    | ❌        | Goods Name                                                                                                                                              | 											                                                    
| billingNm     | String | 30     | ❌        | Billing Name                                                                                                                                            |
| transDt       | String | 8      | ❌        | Transaction Date (YYYYMMDD)                                                                                                                             |
| transTm       | String | 6      | ❌        | Transaction Date (YYYYMMDD)                                                                                                                             |
| description   | String | 100    | ❌        | Transaction Description                                                                                                                                 |												                                                    
| authNo        | String | 10     | ❌        |                                                                                                                                                         |
| issuBankCd    | String | 4      | ❌        | [Issuing Bank Code](https://docs.nicepay.co.id/nicepay-code-v2-bank-code#iH9hZ)                                                                         |
| acquBankCd    | String | 4      | ❌        | [Acquiring Bank Code](https://docs.nicepay.co.id/nicepay-code-v2-bank-code#VeuRn)                                                                       |
| cardNo        | String | 20     | ❌        | Credit Card Number                                                                                                                                      |
| cardExpYymm   | String | 4      | ❌        | Card Expiry (`YYMM`)                                                                                                                                    |
| instmntType   | String | 2      | ❌        | [Installment Type](https://docs.nicepay.co.id/nicepay-code-v1#EdIdH)                                                                                    | 
| instmntMon    | String | 2      | ❌        | Installment Month                                                                                                                                       |
| ccTransType   | String | 2      | ❌        | [Credit Card Transaction Type](https://docs.nicepay.co.id/undefined#zTGbY)<br/>`1` : Normal <br/>`2` : Recurring <br/>`3` : Pre-Auth <br/>`4` : Capture |



