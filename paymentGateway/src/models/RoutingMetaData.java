package models;

import constants.BankName;
import constants.PaymentMethod;

public class RoutingMetaData {
    public RoutingMetaData(Double percentage, BankName bankName, PaymentMethod paymentMethod) {
        this.percentage = percentage;
        this.bankName = bankName;
        this.paymentMethod = paymentMethod;
    }

    Double percentage;
    BankName bankName;
    PaymentMethod paymentMethod;

    public Double getPercentage() {
        return percentage;
    }

    public BankName getBankName() {
        return bankName;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }

    @Override
    public String toString() {
        return String.format("Routing Data → Payment Method: %s | Bank: %s | Percentage: %.2f%%",
                paymentMethod != null ? paymentMethod : "N/A",
                bankName != null ? bankName : "N/A",
                percentage != null ? percentage : 0.0);
    }

}
