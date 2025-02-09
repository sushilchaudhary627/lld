package repository;

import models.Transaction;

public interface TransactionRepository {
    public void save(Transaction transaction);
    public void update(Transaction transaction);
}
