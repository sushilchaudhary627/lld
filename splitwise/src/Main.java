import models.Expense;
import models.User;
import repository.ExpenseRepository;
import repository.UserRepository;
import repository.impl.ExpenseRepositoryImpl;
import repository.impl.UserRepositoryImpl;
import services.ExpenseService;
import services.UserService;

import java.util.Map;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        UserRepository userRepository = new UserRepositoryImpl();
        UserService userService = new UserService(userRepository);
        User user = userService.createUser("ABC", "9090909090");
        System.out.println(user);
        User user1 = userService.createUser("ABC", "9090909091");
        ExpenseRepository expenseRepository = new ExpenseRepositoryImpl();
        ExpenseService expenseService = new ExpenseService(expenseRepository, userService);
        Expense expense = expenseService.createExpense("DLF", user.getUserId(), 100.00, Map.of(user1.getUserId(), 50.00, user.getUserId(), 50.00), "equal");
        System.out.println(expense);
        expenseService.markPaidSplit(expense.getId(), 4);
        System.out.println(expense);
    }
}