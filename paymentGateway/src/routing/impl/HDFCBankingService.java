package routing.impl;

import dto.requests.bank.BankPaymentRequest;
import dto.response.BankPaymentResponse;
import routing.BankingIntegrationService;


public class HDFCBankingService implements BankingIntegrationService {
    @Override
    public BankPaymentResponse routePaymentRequest(BankPaymentRequest paymentRequest) {
        return new BankPaymentResponse();
    }
}
