package dto.requests.merchant;

import constants.PaymentMethod;

import java.util.Objects;

public class MerchantUPIPaymentRequest extends MerchantPaymentRequest {
    private String upiId;

    public MerchantUPIPaymentRequest(Integer merchantId, Double amount, PaymentMethod paymentMethod, String upiId) {
        super(merchantId, amount, paymentMethod);
        this.upiId = upiId;
    }

    @Override
    public void validate() {
        Objects.requireNonNull(upiId, "UPI id is required");
        if(upiId.isEmpty()){
            throw new IllegalArgumentException("UPI ID should be present");
        }
    }

    public String getUpiId() {
        return upiId;
    }
}
