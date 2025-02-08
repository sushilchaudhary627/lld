package services;

import models.Order;
import models.OrderStatus;
import models.OrderType;
import models.Stock;
import models.User;
import repository.OrderRepository;
import services.*;

import java.util.Optional;

public class StockSellerService {
    private final StockService stockService;
    private final UserService userService;
    private final OrderRepository orderRepository;
    private final TransactionService transactionService;
    private final OrderMatchingService orderMatchingService;
    private final PortfolioService portfolioService;

    public StockSellerService(StockService stockService, UserService userService,
                              OrderRepository orderRepository, TransactionService transactionService,
                              OrderMatchingService orderMatchingService, PortfolioService portfolioService) {
        this.stockService = stockService;
        this.userService = userService;
        this.orderRepository = orderRepository;
        this.transactionService = transactionService;
        this.orderMatchingService = orderMatchingService;
        this.portfolioService = portfolioService;
    }

    public Order sellStocks(Integer stockId, Integer userId, Integer quantity, Double price) {
        User user = userService.getUserById(userId);
        Stock stock = stockService.getStockById(stockId);

        // ✅ Step 1: Validate user owns enough stock to sell
        if (!portfolioService.hasSufficientStocks(user, stock, quantity)) {
            throw new IllegalArgumentException("User does not have enough stocks to sell.");
        }
        Order sellOrder = new Order(user, stock, OrderType.SELLER, price, quantity);
        orderRepository.save(sellOrder); // Add to order book
        Optional<Order> matchingBuyOrder = orderMatchingService.matchSellOrder(sellOrder);
        if (matchingBuyOrder.isPresent()) {
            Order buyOrder = matchingBuyOrder.get();
            transactionService.processTransaction(sellOrder, buyOrder);
            sellOrder.setOrderStatus(OrderStatus.COMPLETED);
            buyOrder.setOrderStatus(OrderStatus.COMPLETED);

            orderRepository.updateOrder(sellOrder);
            orderRepository.updateOrder(buyOrder);
        }

        return sellOrder;
    }
}
