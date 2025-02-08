package repository;

import models.Portfolio;

import java.util.List;
import java.util.Optional;

public interface PortfolioRepository {
    void save(Portfolio portfolio);
    Optional<Portfolio> getPortfolio(Integer userId, Integer stockId);
    List<Portfolio> getPortfoliosByUser(Integer userId);
}
