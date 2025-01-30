# Inquiry Status V2 Direct Param 

## **Properties**

| **Property**     | **Value**                                               |
|:-------------|---------------------------------------------------------| 
| API Endpoint | /nicepay/direct/v2/inquiry                              |
| HTTP Method  | POST                                                    |
| Description  | Performs Inquiry Request to NICEPAY for Status Checking |
| Content-Type | application/json                                        |


## Request Header Parameter

| Parameter     | Type    | Length | Required  | Description       |
|:--------------|:--------|:-------|:--------- |:------------------|
| Content-Type  | String  |        | ✅        | application/json  |

## **Request Body Parameter**

| Parameter     | Type   | Length | Required | Description                                     | Example / Notes                |
|:--------------|:-------|:-------|:---------|:------------------------------------------------|:-------------------------------|
| timestamp     | String | 14     | ✅        | Request Timestamp (YYYYMMDDHH24MISS)            | 20250131155959                 |
| iMid          | String | 10     | ✅        | Merchant ID                                     | IONPAYTEST                     |
| merchantToken | String | 255    | ✅        | Merchant Token                                  | 6cfccfc0046773c1b89d8e98c...   |
| amt           | String | 12     | ✅        | Transaction Amount                              | 1000                           |
| referenceNo   | String | 40     | ✅        | Transaction ID (Reference Number from Merchant) | referenceNo1234                |
| tXid          | String | 30     | ✅        | Transaction ID                                  | IONPAYTEST01202501202059408369 |

## **Response Body Parameter**

| Parameter    | Type   | Length | Required | Description                                                        |
|:-------------|:-------|:-------|:---------|:-------------------------------------------------------------------|
| resultCd     | String | 4      | ✅        | [Result Code](https://docs.nicepay.co.id/nicepay-code-v1#sPc2x)    |
| resultMsg    | String | 255    | ✅        | [Result Message](https://docs.nicepay.co.id/nicepay-code-v1#sPc2x) |
| iMid         | String | 10     | ✅        | Merchant ID                                                        |
| tXid         | String | 30     | ✅        | Transaction ID                                                     |
| referenceNo  | String | 40     | ✅        | Transaction ID (Reference Number from Merchant)                    |
| payMethod    | String | 2      | ✅        | [Payment Method](https://docs.nicepay.co.id/nicepay-code-v1#lUEfQ) |
| amt          | String | 12     | ✅        | Transaction Amount                                                 | 
| status       | String |        | ✅        | [Transaction Status](https://docs.nicepay.co.id/nicepay-api-v2-status-inquiry-api-credit-card#5kyLpX5MpjSl96c8mhOik)                                             |
| reqDt        | String | 8      | ❌        | Request Date                                                       | 
| reqTm        | String | 6      | ❌        | Request Time                                                       | 
| currency     | String | 3      | ✅        | Currency                                                           |
| goodsNm      | String | 100    | ✅        | Goods Name                                                         |
| transDt      | String |        | ❌        | Transaction Date                                                   |
| transTm      | String |        | ❌        | Transaction time                                                   |
| billingNm    | String | 30     | ✅        | Billing Name                                                       |

## **Additional Response Parameter for Credit Card Status Inquiry**

| Parameter         | Type   | Length | Required | Description                                                                                                                                                                                                                       |
|:------------------|:-------|:-------|:---------|:----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| ccTransType       | String | 2      | ✅        | Credit Card Transaction Type                                                                                                                                                                                                      |
| cardNo            | String | 20     | ✅        | Masked Card Number                                                                                                                                                                                                                |
| issuBankCd        | String | 5      | ✅        | Issuer Card Bank Code                                                                                                                                                                                                             |
| preauthToken      | String |        | ✅        | Pre-Authorization Token                                                                                                                                                                                                           |
| cardExpYymm       | String | 4      | ✅        | Card Expiry Date                                                                                                                                                                                                                  |
| acquBankNm        | String |        | ✅        | Acquiring Bank Name                                                                                                                                                                                                               |
| instmntType       | String | 2      | ✅        | [Installment Type](https://docs.nicepay.co.id/nicepay-code-v1#EdIdH)                                                                                                                                                              | 
| instmntMon        | String | 2      | ✅        | Installment Month                                                                                                                                                                                                                 |
| issuBankNm        | String |        | ✅        | Issuer Card Bank Name                                                                                                                                                                                                             |
| acquBankCd        | String |        | ✅        | Acquiring Bank Code                                                                                                                                                                                                               |
| acquBankNm        | String |        | ✅        | Acquiring Bank Code                                                                                                                                                                                                               |
| acquirerData      | Object |        | ✅        | A dynamic array consisting of a unique reference number RRN that is provided by the Banking Partner when a refund is processed. This reference number can be used by the Customer to track the status of the refund with the Bank |
| latestFailHistory | Object |        | ✅        | Failed records are only displayed if the transaction fails and 1 Transaction may have more than 1 failed record if the merchant has a retry mechanism.                                                                            |
| authNo            | String | 10     | ✅        | Auth Number                                                                                                                                                                                                                       |
| preauthToken      | String | 255    | ✅        | Pre-Auth Token  (Needed for Capture Payment)                                                                                                                                                                                      |
| recurringToken    | String | 255    | ✅        | Recurring Token (Needed for Recurring Payment)                                                                                                                                                                                    |
| acquStatus        | String | 2      | ✅        | <br/>`1` : Normal <br/>`2` : Recurring <br/>`3` : Pre-Auth <br/>`4` : Capture                                                                                                                                                     |

### **Acquirer Data Parameter**
| Parameter | Type   | Length | Required | Description                                                                               |
|:----------|:-------|:-------|:---------|:------------------------------------------------------------------------------------------|
| rrn       | String |        | ❌        | a unique reference number that is provided by the Bank Partner when a refund is processed |

### **Latest Fail History Parameter**
| Parameter | Type   | Length | Required | Description                                                           |
|:----------|:-------|:-------|:---------|:----------------------------------------------------------------------|
| resultCd     | String | 4      | ✅        | [Result Code](https://docs.nicepay.co.id/nicepay-code-v1#sPc2x)       |
| resultMsg    | String | 255    | ✅        | [Result Message](https://docs.nicepay.co.id/nicepay-code-v1#sPc2x)    |
| transDt      | String |        | ❌        | Transaction Date                                                     |
| transTm      | String |        | ❌        | Transaction time                                                     |


## **Additional Response Parameter for Virtual Account Status Inquiry**

| Parameter    | Type   | Length | Required | Description                                                             |
|:-------------|:-------|:-------|:---------|:------------------------------------------------------------------------|
| bankCd       | String | 4      | ✅        | [Bank Code](https://docs.nicepay.co.id/nicepay-code-v2-bank-code)       | 
| vacctNo      | String | 20     | ✅        | VA Number                                                               |
| vacctValidDt | String | 8      | ✅        | VA Expiry Date (YYYYMMDD)                                               | 
| vacctValidTm | String | 6      | ✅        | VA Expiry Time (HH24MISS)                                               |
