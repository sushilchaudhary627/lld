package observers;

import models.Stock;

public interface StockPriceObserver {
    public void update(Stock stock);
}
