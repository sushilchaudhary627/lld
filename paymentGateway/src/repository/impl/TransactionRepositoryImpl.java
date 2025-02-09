package repository.impl;

import models.Transaction;
import repository.TransactionRepository;

import java.util.HashMap;
import java.util.Map;

public class TransactionRepositoryImpl implements TransactionRepository {
    Map<Integer, Transaction> transactionMap = new HashMap<>();
    @Override
    public void save(Transaction transaction) {
        transactionMap.put(transaction.getTransactionId(), transaction);
    }

    @Override
    public void update(Transaction transaction) {

    }
}
