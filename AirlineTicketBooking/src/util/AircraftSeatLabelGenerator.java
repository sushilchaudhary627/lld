package util;

import java.util.ArrayList;
import java.util.List;

public class AircraftSeatLabelGenerator {

    public static  List<String>generate(int capacity){
        List<String> labels = new ArrayList<>();
        for(char c='A'; c<='Z'; c++) {
            for (int k = 0; k < 6; k++) {
                labels.add(String.valueOf(c) + String.valueOf(k));
            }
        }
        return labels;
    }
}
