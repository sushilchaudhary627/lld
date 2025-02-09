package services;

import constants.PaymentMethod;
import models.Merchant;
import repository.MerchantRepository;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class MerchantService {
    private final MerchantRepository merchantRepository;
    private final AtomicInteger merchantIdGenerator;

    public MerchantService(MerchantRepository merchantRepository, AtomicInteger merchantIdGenerator) {
        this.merchantRepository = merchantRepository;
        this.merchantIdGenerator = merchantIdGenerator;
    }

    public Merchant onBoardMerchant(String merchantName, List<String> supportedPaymentMethods){
        Objects.requireNonNull(merchantName, "Merchant name should not be null.");
        if(merchantName.isEmpty()){
            throw new IllegalArgumentException("Merchant name should not be empty.");
        }
        Merchant  merchant = new Merchant(merchantIdGenerator.getAndIncrement(), merchantName);
        for(String paymentMethod:supportedPaymentMethods)
            merchant.addNewPaymentMethod(PaymentMethod.fromString(paymentMethod));
        merchantRepository.save(merchant);
        return merchant;
    }

    public Merchant getMerchantById(Integer merchantId) {
        return merchantRepository.getMerchantById(merchantId).orElseThrow(() -> new IllegalArgumentException("Merchant not found."));

    }
}
