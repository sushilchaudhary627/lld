package services;

import models.*;
import repository.PortfolioRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Queue;

public class PortfolioService {
    private final PortfolioRepository portfolioRepository;

    public PortfolioService(PortfolioRepository portfolioRepository) {
        this.portfolioRepository = portfolioRepository;
    }

    public void updatePortfolioAfterBuying(Stock stock, Double price, Integer quantity, User buyer) {
        Portfolio portfolio = portfolioRepository.getPortfolio(buyer.getUserId(), stock.getStockId())
                .orElseGet(() -> new Portfolio(buyer, stock, 0)); // Start with 0 quantity

        UserStock userStock = new UserStock(stock, price, quantity, buyer);
        portfolio.addUserStock(userStock);
        portfolio.setInvestedAmount(portfolio.getInvestedAmount() + price * quantity);
        portfolio.updateQuantity(quantity); // Increment quantity correctly
        System.out.println(portfolio);
        portfolioRepository.save(portfolio); // Ensure it's persisted
    }

    public void updatePortfolioAfterSelling(Stock stock, Double price, Integer quantity, User seller) {
        System.out.println("Seller: "+ seller);
        Portfolio portfolio = portfolioRepository.getPortfolio(seller.getUserId(), stock.getStockId())
                .orElseThrow(() -> new IllegalArgumentException("Portfolio does not exist"));

        if (portfolio.getQuantityOwned() < quantity) {
            throw new IllegalArgumentException("Not enough stocks to sell");
        }

        Queue<UserStock> userStocks = portfolio.getUserStocks();
        Double currentProfit = calculateProfit(userStocks, price, quantity);
        portfolio.setProfit(portfolio.getProfit() + currentProfit);
        portfolio.updateQuantity(-quantity);

        portfolioRepository.save(portfolio); // Ensure it's persisted
        System.out.println(portfolio);
    }

    private Double calculateProfit(Queue<UserStock> userStocks, Double price, Integer quantity) {
        double profit = 0.0;
        int remainingQuantity = quantity;

        while (!userStocks.isEmpty() && remainingQuantity > 0) {
            UserStock userStock = userStocks.peek();
            int sellQuantity = Math.min(userStock.getQuantity(), remainingQuantity);

            profit += sellQuantity * (price - userStock.getBuyingPricePerShare());
            remainingQuantity -= sellQuantity;
            userStock.setQuantity(userStock.getQuantity() - sellQuantity);

            if (userStock.getQuantity() == 0) {
                userStocks.poll();
            }
        }

        if (remainingQuantity > 0) {
            throw new IllegalStateException("Trying to sell more stocks than available");
        }

        return profit;
    }

    public boolean hasSufficientStocks(User user, Stock stock, Integer quantity) {
        return true;
    }
    public List<Portfolio> getPortfoliosByUser(Integer userId) {
        return portfolioRepository.getPortfoliosByUser(userId);
    }


}
