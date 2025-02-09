package factories;

import dto.requests.bank.BankPaymentRequest;
import dto.requests.merchant.MerchantPaymentRequest;

public interface BankPaymentRequestFactory {
    BankPaymentRequest createBankPaymentRequest(MerchantPaymentRequest merchantPaymentRequest);
}
