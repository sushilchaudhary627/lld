import models.*;
import repository.*;
import repository.impl.*;
import services.*;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(String[] args) {
        // ✅ Initialize Repositories and Services
        UserRepository userRepository = new UserRepositoryImpl();
        AtomicInteger idGenerator = new AtomicInteger(1);
        UserService userService = new UserService(userRepository, idGenerator);

        StockRepository stockRepository = new StockRepositoryImpl();
        StockService stockService = new StockService(stockRepository, new ArrayList<>());

        OrderRepository orderRepository = new OrderRepositoryImpl();
        PortfolioRepository portfolioRepository = new PortfolioRepositoryImpl();
        PortfolioService portfolioService = new PortfolioService(portfolioRepository);
        TransactionRepository transactionRepository = new TransactionRepositoryImpl();
        OrderMatchingService orderMatchingService = new OrderMatchingService(orderRepository);
        TransactionService transactionService = new TransactionService(transactionRepository, portfolioService);

        StockBuyService stockBuyService = new StockBuyService(
                stockService, userService, orderRepository, transactionService, orderMatchingService
        );
        StockSellerService stockSellerService = new StockSellerService(
                stockService, userService, orderRepository, transactionService, orderMatchingService, portfolioService
        );

        // ✅ Test Case 1: Create Users
        User user1 = userService.createUser("Alice", "9000000001", 50000.00);
        User user2 = userService.createUser("Bob", "9000000002", 60000.00);
        assert user1 != null && user2 != null : "User creation failed";

        // ❌ Negative Test: Create User with Duplicate Mobile Number
        try {
            userService.createUser("Charlie", "9000000001", 70000.00);
            assert false : "Duplicate user allowed!";
        } catch (Exception e) {
            System.out.println("✔ Expected Error: " + e.getMessage());
        }

        // ✅ Test Case 2: Add Stocks
        Stock stock1 = stockService.createStock("AAPL", "Apple Inc.", 150.00, 100);
        Stock stock2 = stockService.createStock("TSLA", "Tesla Inc.", 700.00, 50);
        assert stock1 != null && stock2 != null : "Stock creation failed";

        // ❌ Negative Test: Add Stock with Zero Quantity
        try {
            stockService.createStock("MSFT", "Microsoft Corp.", 300.00, 0);
            assert false : "Stock with zero quantity allowed!";
        } catch (Exception e) {
            System.out.println("✔ Expected Error: " + e.getMessage());
        }

        // ✅ Test Case 3: Buy Stocks (Valid Case)
        Order buyOrder1 = stockBuyService.buyStocks(stock1.getStockId(), user1.getUserId(), 10, 155.00);
        assert buyOrder1 != null && buyOrder1.getOrderStatus() == OrderStatus.COMPLETED : "Buy order failed";

        // ❌ Negative Test: Buy More Than Available
        try {
            stockBuyService.buyStocks(stock1.getStockId(), user2.getUserId(), 200, 155.00);
            assert false : "Order should not be allowed for excess stock!";
        } catch (Exception e) {
            System.out.println("✔ Expected Error: " + e.getMessage());
        }

        // ❌ Negative Test: Buy with Insufficient Balance
        try {
            stockBuyService.buyStocks(stock2.getStockId(), user1.getUserId(), 50, 1000.00);
            assert false : "Order allowed with insufficient balance!";
        } catch (Exception e) {
            System.out.println("✔ Expected Error: " + e.getMessage());
        }

        // ✅ Test Case 4: Sell Stocks
        Order sellOrder1 = stockSellerService.sellStocks(stock1.getStockId(), user1.getUserId(), 5, 160.00);
        assert sellOrder1 != null && sellOrder1.getOrderStatus() == OrderStatus.PENDING : "Sell order failed";

        // ❌ Negative Test: Sell More Than Owned
        try {
            stockSellerService.sellStocks(stock1.getStockId(), user1.getUserId(), 50, 160.00);
            assert false : "Sell order should not be allowed for unowned stock!";
        } catch (Exception e) {
            System.out.println("✔ Expected Error: " + e.getMessage());
        }

        // ✅ Test Case 5: Order Matching (Buy matches existing Sell)
        Order buyOrder2 = stockBuyService.buyStocks(stock1.getStockId(), user2.getUserId(), 5, 160.00);
        assert buyOrder2 != null && buyOrder2.getOrderStatus() == OrderStatus.COMPLETED : "Order matching failed";

        // ✅ Test Case 6: Portfolio Check
        Portfolio portfolio1 = portfolioService.getPortfoliosByUser(user1.getUserId()).get(0);
        Portfolio portfolio2 = portfolioService.getPortfoliosByUser(user2.getUserId()).get(0);
        assert portfolio1.getQuantityOwned() == 5 : "Portfolio mismatch for User1";
        assert portfolio2.getQuantityOwned() == 5 : "Portfolio mismatch for User2";

        System.out.println("✔✔✔ ALL TEST CASES PASSED SUCCESSFULLY ✔✔✔");
    }
}
