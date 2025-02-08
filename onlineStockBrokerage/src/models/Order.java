package models;

import java.time.LocalDateTime;

public class Order {
    private final Integer orderId;
    private final User user;
    private final Stock stock;
    private final OrderType orderType;  // BUY or SELL
    private final Double costPerStock;
    private final LocalDateTime createdAt;
    private OrderStatus orderStatus;
    private final Integer stockQuantity;

    private static int idCounter = 1;

    public Order(User user, Stock stock, OrderType orderType, Double costPerStock, Integer stockQuantity) {
        this.orderId = idCounter++;
        this.user = user;
        this.stock = stock;
        this.orderType = orderType;
        this.costPerStock = costPerStock;
        this.stockQuantity = stockQuantity;
        this.createdAt = LocalDateTime.now();
        this.orderStatus = OrderStatus.PENDING;
    }


    public Integer getOrderId() { return orderId; }
    public User getUser() { return user; }
    public Stock getStock() { return stock; }
    public OrderType getOrderType() { return orderType; }
    public Double getCostPerStock() { return costPerStock; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public OrderStatus getOrderStatus() { return orderStatus; }
    public Integer getStockQuantity() { return stockQuantity; }


    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", user=" + user +
                ", stock=" + stock +
                ", orderType=" + orderType +
                ", costPerStock=" + costPerStock +
                ", createdAt=" + createdAt +
                ", orderStatus=" + orderStatus +
                ", stockQuantity=" + stockQuantity +
                '}';
    }
}
