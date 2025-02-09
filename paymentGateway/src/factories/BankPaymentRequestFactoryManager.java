package factories;

import constants.BankName;
import dto.requests.bank.BankPaymentRequest;
import dto.requests.merchant.MerchantPaymentRequest;
import factories.impl.HDFCBankPaymentRequestFactory;
import models.Bank;

import java.util.HashMap;
import java.util.Map;

public class BankPaymentRequestFactoryManager {
    private static final Map<BankName, BankPaymentRequestFactory> factoryMap = new HashMap<>();

    static {
        factoryMap.put(BankName.HDFC, new HDFCBankPaymentRequestFactory());
    }

    public static BankPaymentRequest transform(MerchantPaymentRequest merchantPaymentRequest, Bank bank) {
        BankPaymentRequestFactory factory = factoryMap.get(bank.getBankName());
        if (factory == null) {
            throw new IllegalArgumentException("No factory available for bank: " + bank.getBankName());
        }
        return factory.createBankPaymentRequest(merchantPaymentRequest);
    }
}
