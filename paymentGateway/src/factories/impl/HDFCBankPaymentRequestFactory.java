package factories.impl;

import dto.requests.bank.BankPaymentRequest;
import dto.requests.bank.hdfc.HDFCUPIPaymentRequest;
import dto.requests.merchant.MerchantPaymentRequest;
import factories.BankPaymentRequestFactory;

public class HDFCBankPaymentRequestFactory implements BankPaymentRequestFactory {
    @Override
    public BankPaymentRequest createBankPaymentRequest(MerchantPaymentRequest merchantPaymentRequest) {
        switch (merchantPaymentRequest.getPaymentMethod()) {
            case UPI:
                return new HDFCUPIPaymentRequest(merchantPaymentRequest.getAmount());
            default:
                throw new IllegalArgumentException("Unsupported payment method for HDFC: " + merchantPaymentRequest.getPaymentMethod());
        }
    }
}

// here we assumed that will be different request structure for different payment methods.
