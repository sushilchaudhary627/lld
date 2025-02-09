package repository;

import models.Merchant;

import java.util.Optional;

public interface MerchantRepository {
    public void save(Merchant merchant);
    public Optional<Merchant> getMerchantById(Integer id);
}
