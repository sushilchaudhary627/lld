package models;

import java.util.Objects;

public enum Speciality {
    Cardiologist("Cardiologist"), Dermatologist("Dermatologist"), Orthopedic("Orthopedic"), GeneralPhysician("GeneralPhysician");
    private final  String value;
    Speciality(String val) {
        this.value = val;
    }

    String getValue(){
        return  value;
    }

    public static  Speciality fromString(String text){
        for(Speciality speciality: Speciality.values()){
            if(Objects.equals(speciality.getValue(), text)) return  speciality;
        }
        throw new IllegalArgumentException("Invalid speciality found");
    }

}
