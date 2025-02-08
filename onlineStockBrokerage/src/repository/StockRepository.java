package repository;

import models.Stock;

import java.util.Optional;

public interface StockRepository {
    public void save(Stock stock);
    public Optional<Stock> getStockById(Integer stockId);
    public void update(Stock stock);
}
