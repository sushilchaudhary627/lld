import constants.SplitType;
import models.Expense;
import repo.UserRepo;
import repo.impl.ExpenseRepoImpl;
import repo.impl.UserRepoImpl;
import services.ExpenseService;
import services.SplitService;
import services.UserService;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        UserRepo userRepo = new UserRepoImpl();
        UserService userService = new UserService(new AtomicInteger(1), userRepo);
        userService.createUser("Sushil");
        userService.createUser("Abhy");
        userService.createUser("Aman");
        System.out.println(userRepo.findUserById(1).get());
        SplitService splitService = new SplitService(new AtomicInteger(1),userService);
        ExpenseService expenseService = new ExpenseService(new AtomicInteger(1),splitService, userService, new ExpenseRepoImpl());
        Expense expense = expenseService.createExpense("party", Map.of(1, 10.0,2,10.0,3,80.0), 1, 100.00, SplitType.PERCENTAGE);
            System.out.println(expense);

        System.out.println(userRepo.findUserById(1).get());
        System.out.println(userRepo.findUserById(2).get());
        System.out.println(userRepo.findUserById(3).get());
        expenseService.markPaid(1, 2);
        System.out.println(userRepo.findUserById(1).get());
        System.out.println(userRepo.findUserById(2).get());
        System.out.println(userRepo.findUserById(3).get());
        Expense expense1 = expenseService.createExpense("beer party", Map.of(1, 10.0,2,10.0,3,80.0), 1, 100.00, SplitType.EXACT);
        System.out.println(expense1);
    }
}
