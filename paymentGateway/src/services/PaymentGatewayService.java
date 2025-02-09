package services;

import constants.BankName;
import dto.requests.bank.BankPaymentRequest;
import dto.requests.merchant.MerchantPaymentRequest;
import dto.response.BankPaymentResponse;
import factories.BankPaymentRequestFactoryManager;
import models.Bank;
import models.Merchant;
import models.Transaction;
import repository.BankingRepository;
import services.BankSelectionService;
import routing.BankingRoutingService;

import java.util.Optional;

public class PaymentGatewayService {
    private final TransactionService transactionService;
    private final BankSelectionService bankSelectionService;
    private final BankingRoutingService bankingRoutingService;
    private final MerchantService merchantService;
    private final BankingRepository bankingRepository;
    public PaymentGatewayService(TransactionService transactionService, BankSelectionService bankSelectionService, BankingRoutingService bankingRoutingService, MerchantService merchantService, BankingRepository bankingRepository) {
        this.transactionService = transactionService;
        this.bankSelectionService = bankSelectionService;
        this.bankingRoutingService = bankingRoutingService;
        this.merchantService = merchantService;
        this.bankingRepository = bankingRepository;
    }

    public void payment(MerchantPaymentRequest merchantPaymentRequest) {
        merchantPaymentRequest.validate();
        Merchant merchant = merchantService.getMerchantById(merchantPaymentRequest.getMerchantId());
        System.out.println("Merchant found ..." + merchant);
        Transaction transaction = transactionService.createTransaction(merchantPaymentRequest);
        Optional<BankName> selectedBank = bankSelectionService.selectBank(merchantPaymentRequest.getPaymentMethod());
        if (selectedBank.isEmpty()) {
            throw new IllegalStateException("No bank available for the selected payment method: "
                    + merchantPaymentRequest.getPaymentMethod());
        }
        BankPaymentRequest bankPaymentRequest= BankPaymentRequestFactoryManager.transform(merchantPaymentRequest, bankingRepository.getBankByName(selectedBank.get()).orElseThrow());
        BankPaymentResponse response = bankingRoutingService.routePaymentRequest(bankPaymentRequest);
        transaction.setPaymentStatus(response.getPaymentStatus());
        transactionService.updateTransactionStatus(transaction);
        System.out.println(transaction);
    }
}
