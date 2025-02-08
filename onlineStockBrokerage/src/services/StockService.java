package services;

import models.Stock;
import observers.StockPriceObserver;
import repository.StockRepository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class StockService {
    private final StockRepository stockRepository;
    private final List<StockPriceObserver> priceObservers;
    private final AtomicInteger stockIdGenerator;

    public StockService(StockRepository stockRepository, List<StockPriceObserver> priceObservers) {
        this.stockRepository = stockRepository;
        this.priceObservers = priceObservers;
        this.stockIdGenerator = new AtomicInteger(1);  // Ensure thread safety
    }

    public Stock createStock(String stockSymbol, String companyName, Double price, Integer quantity) {
        validStockCreateRequest(stockSymbol, companyName, price, quantity);  // Use validation method
        Integer stockId = stockIdGenerator.getAndIncrement();
        Stock stock = new Stock(stockId, stockSymbol, companyName, quantity, price);
        stockRepository.save(stock);
        return stock;
    }

    public Stock updateStockPrice(Integer stockId, Double price) {
        Stock stock = stockRepository.getStockById(stockId)
                .orElseThrow(() -> new RuntimeException("Stock not found"));
        stock.updatePrice(price);
        stockRepository.update(stock);
        notifyObservers(stock);
        return stock;  // Return updated stock
    }

    public Stock updateStockQuantity(Integer stockId, Integer quantity) {
        Stock stock = stockRepository.getStockById(stockId)
                .orElseThrow(() -> new RuntimeException("Stock not found"));
        stock.updateStockQuantity(quantity);
        stockRepository.update(stock);
        return stock;  // Return updated stock
    }

    private void notifyObservers(Stock stock) {
        for (StockPriceObserver observer : priceObservers) {
            observer.update(stock);
        }
    }

    private void validStockCreateRequest(String stockSymbol, String companyName, Double price, Integer quantity) {
        if (stockSymbol == null || stockSymbol.isEmpty()) {
            throw new IllegalArgumentException("Stock symbol is required.");
        }

        if (companyName == null || companyName.isEmpty()) {
            throw new IllegalArgumentException("Company name is required.");
        }

        if (price == null || price <= 0) {
            throw new IllegalArgumentException("Stock price must be greater than zero.");
        }

        if (quantity == null || quantity <= 0) {
            throw new IllegalArgumentException("Stock quantity must be greater than zero.");
        }
    }
    public Stock getStockById(Integer stockId){
        return stockRepository.getStockById(stockId).orElseThrow(() -> new IllegalArgumentException("Stock id not valid"));
    }
}
