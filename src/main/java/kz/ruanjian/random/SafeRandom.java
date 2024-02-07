package kz.ruanjian.random;

import java.util.concurrent.ThreadLocalRandom;

public class SafeRandom {

    public int get(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max);
    }
}
