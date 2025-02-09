package repository;

import constants.BankName;
import models.Bank;

import java.util.Optional;

public interface BankingRepository {
    public Optional<Bank> getBankByName(BankName bankName);
    public void save(Bank bank);
}
