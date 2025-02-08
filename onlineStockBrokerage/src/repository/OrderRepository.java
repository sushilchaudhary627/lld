package repository;

import models.Order;

import java.util.List;
import java.util.Optional;

public interface OrderRepository {
    public void save(Order order);
    public List<Order> getPendingStockSellOrder(Integer stockId, Double costPerStock);

    void updateOrder(Order buyOrder);

    List<Order> getPendingStockBuyOrder(Integer stockId, Double costPerStock);
}
