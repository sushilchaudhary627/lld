package services;
import java.util.*;
import models.Order;
import models.OrderStatus;
import models.Stock;
import repository.OrderRepository;

public class OrderMatchingService {
    private final OrderRepository orderRepository;

    public OrderMatchingService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
    public Optional<Order> matchBuyOrder(Order buyOrder) {
        Stock stock = buyOrder.getStock();
        // Here, it could be possible for one buy order to be fulfilled by multiple sell orders at different prices.
        // However, for simplicity, we enforce a strict one-to-one mapping: one buy order matches exactly one sell order.

        Optional<Order> matchingSellOrder = orderRepository.getPendingStockSellOrder(stock.getStockId(), buyOrder.getCostPerStock())
                .stream()
                .filter((Order sellOrder) -> sellOrder.getStockQuantity()>=buyOrder.getStockQuantity())
                .findFirst();

        return matchingSellOrder;
    }

    public Optional<Order> matchSellOrder(Order sellOrder) {
        Stock stock = sellOrder.getStock();
        // Here, it could be possible for one buy order to be fulfilled by multiple sell orders at different prices.
        // However, for simplicity, we enforce a strict one-to-one mapping: one buy order matches exactly one sell order.

        Optional<Order> matchingBuyOrder = orderRepository.getPendingStockBuyOrder(stock.getStockId(), sellOrder.getCostPerStock())
                .stream()
                .filter((Order buyOrder) -> sellOrder.getStockQuantity()>=buyOrder.getStockQuantity())
                .findFirst();

        return matchingBuyOrder;
    }
}
