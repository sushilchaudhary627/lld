import constants.BankName;
import dto.requests.merchant.MerchantPaymentRequest;
import dto.requests.merchant.MerchantUPIPaymentRequest;
import models.Bank;
import models.Merchant;
import models.RoutingMetaData;
import repository.BankingRepository;
import repository.MerchantRepository;
import repository.RoutingMetaDataRepository;
import repository.TransactionRepository;
import repository.impl.BankRepositoryImpl;
import repository.impl.MerchantRepositoryImpl;
import repository.impl.RoutingMetaDataRepositoryImpl;
import repository.impl.TransactionRepositoryImpl;
import services.BankSelectionService;
import routing.BankingRoutingService;
import services.MerchantService;
import services.PaymentGatewayService;
import services.RoutingMetaDataService;
import services.TransactionService;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static constants.PaymentMethod.UPI;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        TransactionRepository transactionRepository = new TransactionRepositoryImpl();
        RoutingMetaDataRepository routingMetaDataRepository = new RoutingMetaDataRepositoryImpl();
        BankingRepository bankingRepository = new BankRepositoryImpl();
        RoutingMetaDataService routingMetaDataService = new RoutingMetaDataService(routingMetaDataRepository);
        MerchantRepository merchantRepository = new MerchantRepositoryImpl();
        MerchantService merchantService = new MerchantService(merchantRepository, new AtomicInteger(1));
        RoutingMetaData routingMetaData = routingMetaDataService.createRoutingMeta("hdfc", "UPI", 100.00);
        System.out.println(routingMetaData);
        Merchant merchant = merchantService.onBoardMerchant("ABBBAA", List.of("UPI"));
        System.out.println(merchant);
        TransactionService transactionService =  new TransactionService(transactionRepository, new AtomicInteger(1));
        BankingRoutingService bankingRoutingService = new BankingRoutingService();
        BankSelectionService bankingSelectionService = new BankSelectionService(routingMetaDataRepository);
        bankingRepository.save(new Bank(100, BankName.HDFC));
        PaymentGatewayService paymentGatewayService = new PaymentGatewayService(transactionService, bankingSelectionService, bankingRoutingService, merchantService, bankingRepository);
        MerchantPaymentRequest merchantPaymentRequest = new MerchantUPIPaymentRequest(merchant.getMerchantId(),100.00, UPI, "abc@paytm");
        paymentGatewayService.payment(merchantPaymentRequest);
    }
}