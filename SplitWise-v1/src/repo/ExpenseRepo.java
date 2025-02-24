package repo;

import models.Expense;

import java.util.Optional;

public interface ExpenseRepo {
    public void save(Expense expense);
    public void update(Expense expense);
    public Optional<Expense> findExpenseById(Integer id);
    public Optional<Expense> findExpenseByName(String name);
}
