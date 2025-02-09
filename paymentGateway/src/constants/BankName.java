package constants;

public enum BankName {
    HDFC("hdfc");
    String value;
    BankName(String value){
        this.value = value;
    }

    String getValue(){
        return  value;
    }
    public static BankName fromString(String name) {
        for(BankName bankName:BankName.values()){
            if(bankName.getValue().equals(name)) return bankName;
        }
        throw new IllegalArgumentException("Bank name Invalid.");
    }
}
