package repository;

import models.Transaction;

public interface TransactionRepository {
    void save(Transaction transaction);
}
