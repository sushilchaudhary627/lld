package constants;

public enum PaymentMethod {
    UPI("UPI");
    String value;
    PaymentMethod(String value){
        this.value = value;
    }

    String getValue(){
        return value;
    }

    public static PaymentMethod fromString(String methodName) {
        for(PaymentMethod paymentMethod:PaymentMethod.values()){
            if(paymentMethod.getValue().equals(methodName)) return  paymentMethod;
        }
        throw new IllegalArgumentException("Invalid payment method");
    }
}
