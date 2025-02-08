package models;

public class UserStock {
    Stock stock;
    Double buyingPricePerShare;
    Integer quantity;
    User buyer;

    public UserStock(Stock stock, Double buyingPricePerShare, Integer quantity, User buyer) {
        this.stock = stock;
        this.buyingPricePerShare = buyingPricePerShare;
        this.quantity = quantity;
        this.buyer = buyer;
    }

    public Stock getStock() {
        return stock;
    }

    public Double getBuyingPricePerShare() {
        return buyingPricePerShare;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public User getBuyer() {
        return buyer;
    }
    public void setQuantity(int quantity){
        this.quantity = quantity;
    }
}
