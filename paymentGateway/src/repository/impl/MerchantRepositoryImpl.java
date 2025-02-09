package repository.impl;

import models.Merchant;
import repository.MerchantRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MerchantRepositoryImpl implements MerchantRepository {
    Map<Integer, Merchant> merchantMap = new HashMap<>();
    @Override
    public void save(Merchant merchant) {
        merchantMap.put(merchant.getMerchantId(), merchant);
    }

    @Override
    public Optional<Merchant> getMerchantById(Integer id) {
        return Optional.ofNullable(merchantMap.get(id));
    }
}
