package repository.impl;

import models.Order;
import models.OrderStatus;
import models.OrderType;
import repository.OrderRepository;

import java.util.*;
import java.util.stream.Collectors;

public class OrderRepositoryImpl implements OrderRepository {
    Map<Integer, List<Order>> stockOrders = new HashMap<>();
    Map<Integer, Order> orderById = new HashMap<>();

    @Override
    public void save(Order order) {
        stockOrders.putIfAbsent(order.getStock().getStockId(), new ArrayList<>());
        stockOrders.get(order.getStock().getStockId()).add(order);
        orderById.put(order.getOrderId(), order);
    }

    @Override
    public List<Order> getPendingStockSellOrder(Integer stockId, Double costPerStock) {
        return stockOrders.getOrDefault(stockId, new ArrayList<>())
                .stream()
                .filter(o -> o.getOrderType().equals(OrderType.SELLER)
                        && o.getCostPerStock() <= costPerStock
                        && o.getOrderStatus().equals(OrderStatus.PENDING))
                .sorted(Comparator.comparing(Order::getCostPerStock)  // Sort by lowest price
                .thenComparing(Order::getCreatedAt))          // If same price, oldest first
                .collect(Collectors.toList());
    }

    @Override
    public void updateOrder(Order buyOrder) {

    }

    @Override
    public List<Order> getPendingStockBuyOrder(Integer stockId, Double costPerStock) {
        return stockOrders.getOrDefault(stockId, new ArrayList<>())
                .stream()
                .filter(o -> o.getOrderType().equals(OrderType.BUYER)
                        && o.getCostPerStock() >= costPerStock
                        && o.getOrderStatus().equals(OrderStatus.PENDING))
                .sorted(Comparator.comparing(Order::getCostPerStock)  // Sort by lowest price
                        .thenComparing(Order::getCreatedAt))          // If same price, oldest first
                .collect(Collectors.toList());
    }
}
