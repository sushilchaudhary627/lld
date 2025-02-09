package dto.response;

import constants.PaymentStatus;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class BankPaymentResponse {
    public PaymentStatus getPaymentStatus() {
        return ThreadLocalRandom.current().nextBoolean() ? PaymentStatus.SUCCESS : PaymentStatus.FAILED;
    }

    public Integer getTransactionId() {
        return 1;
    }
}
