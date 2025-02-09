package repository.impl;

import constants.BankName;
import models.Bank;
import repository.BankingRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class BankRepositoryImpl implements BankingRepository {
    Map<BankName, Bank> banks = new HashMap<>();
    @Override
    public Optional<Bank> getBankByName(BankName bankName) {
        return Optional.ofNullable(banks.get(bankName));
    }

    @Override
    public void save(Bank bank) {
        banks.put(bank.getBankName(), bank);
    }
}
