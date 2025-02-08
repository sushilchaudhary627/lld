package services;

import models.*;
import repository.*;

import java.util.Optional;

public class StockBuyService {
    private final StockService stockService;
    private final UserService userService;
    private final OrderRepository orderRepository;
    private final TransactionService transactionService;
    private final OrderMatchingService orderMatchingService;

    public StockBuyService(StockService stockService, UserService userService,
                           OrderRepository orderRepository, TransactionService transactionService,
                           OrderMatchingService orderMatchingService) {
        this.stockService = stockService;
        this.userService = userService;
        this.orderRepository = orderRepository;
        this.transactionService = transactionService;
        this.orderMatchingService = orderMatchingService;
    }

    public Order buyStocks(Integer stockId, Integer userId, Integer quantity, Double price) {
        User user = userService.getUserById(userId);
        Stock stock = stockService.getStockById(stockId);

        double totalCost = quantity * price;
        if (user.getBalance() < totalCost) {
            throw new IllegalArgumentException("Insufficient balance.");
        }
        Order order = new Order(user, stock, OrderType.BUYER, price, quantity);
        orderRepository.save(order); // Add to order book
        processOrder(order);
        return order;
    }

    private boolean isStockAvailableToBuy(Order buyOrder){
         Stock stock = buyOrder.getStock();
         return stock.getStockQuantity()>=buyOrder.getStockQuantity() && stock.getPrice()<=buyOrder.getCostPerStock();
    }

    public Order processOrder(Order buyOrder){
        if(isStockAvailableToBuy(buyOrder)){
            transactionService.fulfillOrderFromSystemStock(buyOrder);
            buyOrder.setOrderStatus(OrderStatus.COMPLETED);
            stockService.updateStockQuantity(buyOrder.getStock().getStockId(), -buyOrder.getStockQuantity());
        }else{
            Optional<Order> matchingSellOrder = orderMatchingService.matchBuyOrder(buyOrder);
            if(matchingSellOrder.isPresent()){
                Order sellOrder = matchingSellOrder.get();

                transactionService.processTransaction(sellOrder, buyOrder);
                buyOrder.setOrderStatus(OrderStatus.COMPLETED);
                sellOrder.setOrderStatus(OrderStatus.COMPLETED);

                orderRepository.updateOrder(buyOrder);
                orderRepository.updateOrder(sellOrder);
            }
        }
        return buyOrder;
    }
}
