package repo.impl;

import models.Expense;
import repo.ExpenseRepo;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ExpenseRepoImpl implements ExpenseRepo {
    Map<Integer, Expense> expensesByIdMap = new HashMap<>();
    Map<String, Expense> expensesByNameMap = new HashMap<>();
    @Override
    public void save(Expense expense) {
        expensesByIdMap.put(expense.getExpenseId(), expense);
        expensesByNameMap.put(expense.getExpenseName(), expense);
    }

    @Override
    public void update(Expense expense) {

    }

    @Override
    public Optional<Expense> findExpenseById(Integer id) {
        return Optional.ofNullable(expensesByIdMap.get(id));
    }

    @Override
    public Optional<Expense> findExpenseByName(String name) {
        return Optional.ofNullable(expensesByNameMap.get(name));
    }
}
