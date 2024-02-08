package kz.ruanjian.loopcontrol;

import kz.ruanjian.OnePerThread;
import kz.ruanjian.threaded.SafeRandom;

public class DurationLoopControl implements LoopControl, OnePerThread {

    private final SafeRandom random;
    private long duration;
    private long startMillis;

    public DurationLoopControl(SafeRandom random) {
        this.random = random;
    }

    public void fromNow() {
        this.startMillis = System.currentTimeMillis();
        this.duration = random.get();
    }

    @Override
    public boolean canExecute() {
        return System.currentTimeMillis() - startMillis <= duration;
    }
}
