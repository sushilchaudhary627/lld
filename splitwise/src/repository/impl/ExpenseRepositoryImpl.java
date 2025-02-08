package repository.impl;

import models.Expense;
import repository.ExpenseRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ExpenseRepositoryImpl implements ExpenseRepository {
    Map<Integer, Expense> expenseMap = new HashMap<>();
    @Override
    public void save(Expense expense) {
        expenseMap.put(expense.getId(), expense);
    }

    @Override
    public Optional<Expense> getExpenseById(Integer id) {
        return Optional.ofNullable(expenseMap.get(id));
    }

    @Override
    public void update(Expense expense) {

    }
}
