package dto.requests.bank.hdfc;

import constants.BankName;
import constants.PaymentMethod;
import dto.requests.bank.BankPaymentRequest;

public class HDFCUPIPaymentRequest extends BankPaymentRequest {
    String upiId;

    public HDFCUPIPaymentRequest(Double amount) {
        super(BankName.HDFC, PaymentMethod.UPI, amount);
    }

    public HDFCUPIPaymentRequest() {
        super();
    }
}
