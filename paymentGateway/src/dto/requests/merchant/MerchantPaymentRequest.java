package dto.requests.merchant;

import constants.PaymentMethod;

public abstract class MerchantPaymentRequest {

    public Integer getMerchantId() {
        return merchantId;
    }

    public Double getAmount() {
        return amount;
    }

    public MerchantPaymentRequest(Integer merchantId, Double amount, PaymentMethod paymentMethod) {
        this.merchantId = merchantId;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
    }

    private Integer  merchantId;
    private Double amount;
    private PaymentMethod paymentMethod;

    public abstract void validate();

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

}

// different request structure we may need for different merchant payment method request.
