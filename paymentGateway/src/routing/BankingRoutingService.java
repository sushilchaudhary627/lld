package routing;

import dto.requests.bank.BankPaymentRequest;
import dto.response.BankPaymentResponse;
import constants.BankName;
import routing.impl.HDFCBankingService;

import java.util.HashMap;
import java.util.Map;

public class BankingRoutingService {
    private final Map<BankName, BankingIntegrationService> bankServices;

    public BankingRoutingService() {
        this.bankServices = new HashMap<>();
        bankServices.put(BankName.HDFC, new HDFCBankingService());
    }

    public BankPaymentResponse routePaymentRequest(BankPaymentRequest bankPaymentRequest) {
        BankName bankName = bankPaymentRequest.getBankName();
        BankingIntegrationService bankingService = bankServices.get(bankName);
        if (bankingService == null) {
            throw new IllegalArgumentException("Unsupported bank: " + bankName);
        }
        return bankingService.routePaymentRequest(bankPaymentRequest);
    }
}
