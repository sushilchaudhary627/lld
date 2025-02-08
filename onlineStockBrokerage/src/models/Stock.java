package models;

public class Stock {
    private final Integer stockId;  // Unique stock identifier
    private final String stockSymbol; // e.g., AAPL, TSLA
    private final String companyName; // e.g., Apple Inc.
    private Integer stockQuantity; // Available shares
    private Double price; // Current stock price

    public Stock(Integer stockId, String stockSymbol, String companyName, Integer stockQuantity, Double price) {
        if (stockQuantity < 0 || price < 0) {
            throw new IllegalArgumentException("Stock quantity and price must be non-negative.");
        }
        this.stockId = stockId;
        this.stockSymbol = stockSymbol;
        this.companyName = companyName;
        this.stockQuantity = stockQuantity;
        this.price = price;
    }

    // Getters
    public Integer getStockId() {
        return stockId;
    }

    public String getStockSymbol() {
        return stockSymbol;
    }

    public String getCompanyName() {
        return companyName;
    }

    public Integer getStockQuantity() {
        return stockQuantity;
    }

    public Double getPrice() {
        return price;
    }

    public void updateStockQuantity(int change) {
        if (this.stockQuantity + change < 0) {
            throw new IllegalArgumentException("Insufficient stock quantity.");
        }
        this.stockQuantity += change;
    }


    public void updatePrice(Double newPrice) {
        if (newPrice < 0) {
            throw new IllegalArgumentException("Price must be non-negative.");
        }
        this.price = newPrice;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "stockId=" + stockId +
                ", stockSymbol='" + stockSymbol + '\'' +
                ", companyName='" + companyName + '\'' +
                ", stockQuantity=" + stockQuantity +
                ", price=" + price +
                '}';
    }
}
