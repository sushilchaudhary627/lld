package repository.impl;

import models.Portfolio;
import repository.PortfolioRepository;

import java.util.*;

public class PortfolioRepositoryImpl implements PortfolioRepository {
    Map<Integer, Map<Integer, Portfolio>>portfolioMap = new HashMap<>();
    @Override
    public void save(Portfolio portfolio) {
        portfolioMap.computeIfAbsent(portfolio.getUser().getUserId(), k -> new HashMap<>())
                .put(portfolio.getStock().getStockId(), portfolio);
    }

    @Override
    public Optional<Portfolio> getPortfolio(Integer userId, Integer stockId) {
        System.out.println(userId + " stock id"+ stockId);
        if (!portfolioMap.containsKey(userId)) {
            return Optional.empty();
        }
        return Optional.ofNullable(portfolioMap.get(userId).get(stockId));
    }

    @Override
    public List<Portfolio> getPortfoliosByUser(Integer userId) {
        return new ArrayList<>(portfolioMap.getOrDefault(userId, new HashMap<>()).values());
    }

}
