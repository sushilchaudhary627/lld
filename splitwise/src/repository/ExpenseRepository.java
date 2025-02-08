package repository;

import models.Expense;

import java.util.Optional;

public interface ExpenseRepository {
    public void save(Expense expense);
    public Optional<Expense> getExpenseById(Integer id);
    public void update(Expense expense);
}
