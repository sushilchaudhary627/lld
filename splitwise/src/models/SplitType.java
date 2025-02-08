package models;

public enum SplitType {
    EQUAL("equal"), PERCENTAGE("percentage"), EXACT("exact");
    String value;
    SplitType(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }

    public static SplitType fromString(String text){
        for(SplitType type:SplitType.values()){
            if(type.getValue().equals(text)){
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid split type found");
    }

}
