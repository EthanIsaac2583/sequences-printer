package kz.ruanjian.threaded;

import java.util.concurrent.ThreadLocalRandom;

public class SafeRandom {

    private final int min;
    private final int max;

    public SafeRandom(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public int get() {
        return ThreadLocalRandom.current().nextInt(min, max);
    }
}
