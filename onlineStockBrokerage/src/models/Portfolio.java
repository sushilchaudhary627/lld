package models;

import java.util.LinkedList;
import java.util.Queue;

public class Portfolio {
    private  User user;
    private  Stock stock;
    private Integer quantityOwned;
    private Double investedAmount;
    private Double profit;
    private  Queue<UserStock> userStocks;

    public Portfolio(User user, Stock stock, Integer quantityOwn) {
        this.user = user;
        this.stock = stock;
        this.quantityOwned = quantityOwn;
        this.investedAmount = 0.0;
        this.profit = 0.0;
        this.userStocks =new LinkedList<>();
    }

    public Portfolio() {

    }

    // Getters
    public User getUser() {
        return user;
    }

    public Stock getStock() {
        return stock;
    }

    public Integer getQuantityOwned() {
        return quantityOwned;
    }

    public Double getInvestedAmount() {
        return investedAmount;
    }

    public void setInvestedAmount(Double investedAmount) {
        this.investedAmount = investedAmount;
    }


    // Update stock quantity in the portfolio
    public void updateQuantity(int change) {
        if (this.quantityOwned + change < 0) {
            throw new IllegalArgumentException("Insufficient stock quantity in portfolio.");
        }
        this.quantityOwned += change;
    }

    public Double getProfit() {
        return profit;
    }

    public void setProfit(Double profit) {
        this.profit = profit;
    }

    public void addUserStock(UserStock userStock){
        userStocks.add(userStock);
    }

    public Queue<UserStock> getUserStocks(){
        return userStocks;
    }

    public Double getAverageBuyingPrice() {
        return (quantityOwned == 0) ? 0.0 : investedAmount / quantityOwned;
    }

    public Double getCurrentValue(Double latestStockPrice) {
        return quantityOwned * latestStockPrice;
    }

    public Double getRealizedProfit() {
        return profit;
    }

    public Double getUnrealizedProfit(Double latestStockPrice) {
        return getCurrentValue(latestStockPrice) - investedAmount;
    }

    @Override
    public String toString() {
        return "Portfolio{" +
                "user=" + user.getUserId() +
                ", stock=" + stock.getStockId() +
                ", quantityOwned=" + quantityOwned +
                ", investedAmount=" + investedAmount +
                ", profit=" + profit +
                '}';
    }
}
