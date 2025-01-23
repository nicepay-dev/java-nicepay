# Inquiry Status V1 Professional Param 

## **Properties**

| **Property**     | **Value**                                      |
|:-------------|------------------------------------------------| 
| API Endpoint | /nicepay/api/onePassStatus.do                  |
| HTTP Method  | POST                                           |
| Description  | Inquiries transaction status to NICEPAY server |
| Content-Type | x-www-form-urlencoded                          |


## Request Header Parameter

| Parameter     | Type    | Length | Required  | Description           |
|:--------------|:--------|:-------|:--------- |:----------------------|
| Content-Type  | String  |        | ✅        | x-www-form-urlencoded |

## **Request Body Parameter**

| Parameter     | Type   | Length | Required | Description                                     | Example / Notes                |
|:--------------|:-------|:-------|:---------|:------------------------------------------------|:-------------------------------|
| iMid          | String | 10     | ✅        | Merchant ID                                     | IONPAYTEST                     |
| merchantToken | String | 255    | ✅        | Merchant Token                                  | 6cfccfc0046773c1b89d8e98c...   |
| amt           | String | 12     | ✅        | Transaction Amount                              | 1000                           |
| referenceNo   | String | 40     | ✅        | Transaction ID (Reference Number from Merchant) | referenceNo1234                |
| tXid          | String | 30     | ✅        | Transaction ID                                  | IONPAYTEST01202501202059408369 |

## **Response Body Parameter**

| Parameter    | Type   | Length | Required | Description                                                          |
|:-------------|:-------|:-------|:---------|:---------------------------------------------------------------------|
| resultCd     | String | 4      | ✅        | [Result Code](https://docs.nicepay.co.id/nicepay-code-v1#sPc2x)      |
| resultMsg    | String | 255    | ✅        | [Result Message](https://docs.nicepay.co.id/nicepay-code-v1#sPc2x)   |
| iMid         | String | 10     | ✅        | Merchant ID                                                          |
| tXid         | String | 30     | ✅        | Transaction ID                                                       |
| referenceNo  | String | 40     | ✅        | Transaction ID (Reference Number from Merchant)                      |
| payMethod    | String | 2      | ✅        | [Payment Method](https://docs.nicepay.co.id/nicepay-code-v1#lUEfQ)   |
| amt          | String | 12     | ✅        | Transaction Amount                                                   | 
| reqDt        | String | 8      | ❌        | Request Date                                                         | 
| reqTm        | String | 6      | ❌        | Request Time                                                         | 
| currency     | String | 3      | ✅        | Currency                                                             |
| goodsNm      | String | 100    | ✅        | Goods Name                                                           |
| ccTransType  | String | 2      | ❌        | Credit Card Transaction Type                                         |
| fee          | String | 12     | ❌        | Service Fee                                                          |
| notaxAmt     | String | 12     | ❌        | Tax Free Amount                                                      |
| cardNo       | String | 20     | ❌        | Masked Card Number                                                   |
| issuBankCd   | String | 5      | ❌        | Issuer Card Bank Code                                                |
| preauthToken | String |        | ❌        | Pre-Authorization Token                                              |
| cardExpYymm  | String | 4      | ❌        | Card Expiry Date                                                     |
| acquBankNm   | String |        | ❌        | Acquiring Bank Name                                                  |
| instmntType  | String | 2      | ✅        | [Installment Type](https://docs.nicepay.co.id/nicepay-code-v1#EdIdH) |
| instmntMon   | String | 2      | ✅        | Installment Month                                                    |
| issuBankNm   | String |        | ❌        | Issuer Card Bank Name                                                |
| transDt      | String |        | ❌        | Transaction Date                                                     |
| transTm      | String |        | ❌        | Transaction time                                                     |
| vat          | String | 12     | ❌        | VAT Number                                                           | 
| billingNm    | String | 30     | ✅        | Billing Name                                                         |
| acquBankCd   | String |        | ❌        | Acquiring Bank Code                                                  |
| status       | String |        | ✅        | Transaction Status                                                   |
| acquirerData | Object |        | ❌        | "acquirerData" : {...}                                               |

## **Acquirer Data Parameter**
| Parameter | Type   | Length | Required | Description                                                                               |
|:----------|:-------|:-------|:---------|:------------------------------------------------------------------------------------------|
| rrn       | String |        | ❌        | a unique reference number that is provided by the Bank Partner when a refund is processed |

