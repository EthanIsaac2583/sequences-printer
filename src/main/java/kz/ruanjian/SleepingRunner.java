package kz.ruanjian;

import kz.ruanjian.threaded.SafeRandom;
import kz.ruanjian.threaded.SafeSleeper;

public class SleepingRunner implements Runnable {

    private final Runnable runnable;
    private final SafeSleeper sleeper;
    private final SafeRandom random;

    public SleepingRunner(Runnable runnable,
                          SafeSleeper sleeper,
                          SafeRandom random) {
        this.runnable = runnable;
        this.sleeper = sleeper;
        this.random = random;
    }

    @Override
    public void run() {
        while (true) {
            sleeper.sleep(random.get());
            if (runnable != null) {
                runnable.run();
            }
        }
    }
}
