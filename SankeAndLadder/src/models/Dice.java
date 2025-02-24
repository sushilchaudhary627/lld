package models;

import java.util.Random;

public class Dice {
    private final Integer value;

    public Dice(Integer value) {
        this.value = value;
    }

    public Integer roll(){
        Random random = new Random();
        return random.nextInt(value);
    }
}
