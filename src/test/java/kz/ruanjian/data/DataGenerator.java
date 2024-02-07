package kz.ruanjian.data;

import java.util.Random;

public class DataGenerator {

    private final Random random = new Random();

    public int randomInt(int min, int max) {
        return random.nextInt(max - min) + min;
    }
}
