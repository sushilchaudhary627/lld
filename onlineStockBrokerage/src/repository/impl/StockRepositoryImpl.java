package repository.impl;

import models.Stock;
import repository.StockRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class StockRepositoryImpl implements StockRepository {
    Map<Integer,Stock> stockmap = new HashMap<>();
    @Override
    public void save(Stock stock) {
        stockmap.put(stock.getStockId(), stock);
    }

    @Override
    public Optional<Stock> getStockById(Integer stockId) {
        return Optional.ofNullable(stockmap.get(stockId));
    }

    @Override
    public void update(Stock stock) {

    }
}
