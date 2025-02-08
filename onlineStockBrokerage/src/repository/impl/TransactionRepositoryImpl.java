package repository.impl;

import models.Transaction;
import repository.TransactionRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TransactionRepositoryImpl implements TransactionRepository {
    Map<Integer, List<Transaction>> transactionsById = new HashMap<>();
    @Override
    public void save(Transaction transaction) {
          transactionsById.computeIfAbsent(transaction.getOrder().getUser().getUserId(), k -> new ArrayList<>()).add(transaction);
    }
}
