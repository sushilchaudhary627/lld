package routing;

import dto.requests.bank.BankPaymentRequest;
import dto.response.BankPaymentResponse;

public interface BankingIntegrationService {
    public BankPaymentResponse routePaymentRequest(BankPaymentRequest paymentRequest);
}
