package models;
import java.util.Random;
public class Dice {
    public Integer roll() {
        Random random = new Random();
        return random.nextInt(6) + 1;
    }
}
