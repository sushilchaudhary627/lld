package services;

import models.*;
import repository.TransactionRepository;

public class TransactionService {
    private final TransactionRepository transactionRepository;
    private  final PortfolioService portfolioService;

    public TransactionService(TransactionRepository transactionRepository, PortfolioService portfolioService) {
        this.transactionRepository = transactionRepository;
        this.portfolioService = portfolioService;
    }

    public void processTransaction(Order sellOrder, Order buyOrder) {
        Double totalAmount = buyOrder.getCostPerStock() * buyOrder.getStockQuantity();
        User buyer = buyOrder.getUser();
        User seller = sellOrder.getUser();

        if (buyer.getBalance() < totalAmount) {
            throw new RuntimeException("Buyer doesn't have enough balance.");
        }

        try {
            deductBalance(buyOrder, totalAmount);
            creditBalance(sellOrder, totalAmount);
            portfolioService.updatePortfolioAfterBuying(buyOrder.getStock(), buyOrder.getCostPerStock(), buyOrder.getStockQuantity(), buyOrder.getUser());
            portfolioService.updatePortfolioAfterSelling(sellOrder.getStock(), buyOrder.getCostPerStock(), buyOrder.getStockQuantity(), sellOrder.getUser());
        } catch (Exception e) {
            // Rollback transactions in case of failure
            buyer.setBalance(buyer.getBalance() + totalAmount);
            seller.setBalance(seller.getBalance() - totalAmount);
            throw new RuntimeException("Transaction failed, rolling back.", e);
        }
    }


    private void deductBalance(Order buyOrder, Double amount) {
        User buyer = buyOrder.getUser();
        buyer.setBalance(buyer.getBalance() - amount);
        Transaction transaction = new Transaction(amount, TransactionStatus.COMPLETED, buyOrder);
        transaction.setTransactionType(TransactionType.DEDUCT);
        transactionRepository.save(transaction);
    }

    private void creditBalance(Order sellOrder, Double amount) {
        User seller = sellOrder.getUser();
        seller.setBalance(seller.getBalance() + amount);
        Transaction transaction = new Transaction(amount, TransactionStatus.COMPLETED, sellOrder);
        transaction.setTransactionType(TransactionType.CREDIT);
        transactionRepository.save(transaction);
    }

    public void fulfillOrderFromSystemStock(Order buyOrder){
        Double totalAmount = buyOrder.getCostPerStock() * buyOrder.getStockQuantity();
        User buyer = buyOrder.getUser();

        if (buyer.getBalance() < totalAmount) {
            throw new RuntimeException("Buyer doesn't have enough balance");
        }

        deductBalance(buyOrder, totalAmount);
        portfolioService.updatePortfolioAfterBuying(buyOrder.getStock(), buyOrder.getCostPerStock(), buyOrder.getStockQuantity(), buyOrder.getUser());
    }

}
