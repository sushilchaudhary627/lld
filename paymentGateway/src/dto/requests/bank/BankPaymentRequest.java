package dto.requests.bank;

import constants.BankName;
import constants.PaymentMethod;

import java.math.BigDecimal;

public class BankPaymentRequest {
    private BankName bankName;
    private PaymentMethod paymentMethod;
    private Double amount;

    public BankPaymentRequest(BankName bankName,
                              PaymentMethod paymentMethod, Double amount) {
        this.bankName = bankName;
        this.paymentMethod = paymentMethod;
        this.amount = amount;
    }

    public BankPaymentRequest() {

    }

    public BankName getBankName() {
        return bankName;
    }

    public PaymentMethod getpaymentMethod() {
        return paymentMethod;
    }

    public Double getAmount() {
        return amount;
    }
}
