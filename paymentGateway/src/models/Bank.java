package models;

import constants.BankName;

import java.util.List;

public class Bank {
    private final Integer bankId;
    private BankName bankName;
    private List<Transaction> transactions;

    public Bank(Integer bankId, BankName bankName) {
        this.bankId = bankId;
        this.bankName = bankName;
    }

    public Integer getBankId() {
        return bankId;
    }

    public BankName getBankName() {
        return bankName;
    }
}
