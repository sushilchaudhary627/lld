package util;

import java.util.Random;

public class PNRGenerator {
    static String alphabates = "ABCDEFGHIJK123456";
    public static String generate(){
        StringBuilder pnr = new StringBuilder();
        for(int i=0; i<6; i++){
            Random random = new Random();
            Integer pos  = random.nextInt(alphabates.length());
            pnr.append(String.valueOf(alphabates.charAt(pos)));
        }
        return pnr.toString();
    }
}
