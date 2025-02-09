package models;

import constants.PaymentMethod;

import java.util.ArrayList;
import java.util.List;

public class Merchant {
    public Integer getMerchantId() {
        return merchantId;
    }

    private final Integer merchantId;
    private String name;
    private List<PaymentMethod> supportedPaymentMethods;
    public Merchant(Integer id, String name) {
        this.merchantId = id;
        this.name = name;
        this.supportedPaymentMethods = new ArrayList<>();
    }

    public String getName() {
        return name;
    }
    public void addNewPaymentMethod(PaymentMethod paymentMethod){
        supportedPaymentMethods.add(paymentMethod);
    }

    public List<PaymentMethod> getSupportedPaymentMethods() {
        return supportedPaymentMethods;
    }

    @Override
    public String toString() {
        return "Merchant{" +
                "merchantId=" + merchantId +
                ", name='" + name + '\'' +
                ", supportedPaymentMethods=" + supportedPaymentMethods +
                '}';
    }
}
