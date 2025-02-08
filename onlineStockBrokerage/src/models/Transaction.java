package models;

import java.time.LocalDateTime;

public class Transaction {
    private static int idCounter = 1;  // Auto-incremented transaction ID
    private final Integer transactionId;
    private final Double amount;
    private TransactionStatus transactionStatus;
    private final Order order;
    private final LocalDateTime transactionTime;
    private TransactionType transactionType;

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public Transaction(Double amount, TransactionStatus transactionStatus, Order order) {
        if (amount < 0) {
            throw new IllegalArgumentException("Transaction amount must be non-negative.");
        }
        this.transactionId = idCounter++;
        this.amount = amount;
        this.transactionStatus = transactionStatus;
        this.order = order;
        this.transactionTime = LocalDateTime.now();
    }

    // Getters
    public Integer getTransactionId() {
        return transactionId;
    }

    public Double getAmount() {
        return amount;
    }

    public TransactionStatus getTransactionStatus() {
        return transactionStatus;
    }

    public Order getOrder() {
        return order;
    }

    public LocalDateTime getTransactionTime() {
        return transactionTime;
    }

    // Setter for updating transaction status
    public void setTransactionStatus(TransactionStatus transactionStatus) {
        this.transactionStatus = transactionStatus;
    }
}
