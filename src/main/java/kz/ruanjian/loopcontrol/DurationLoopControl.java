package kz.ruanjian.loopcontrol;

import kz.ruanjian.OncePerThread;
import kz.ruanjian.threaded.SafeRandom;

public class DurationLoopControl implements LoopControl, OncePerThread {

    private final SafeRandom random;
    private long duration;
    private long startMillis;

    public DurationLoopControl(SafeRandom random) {
        this.random = random;
    }

    public void pointCurrentTime() {
        this.startMillis = System.currentTimeMillis();
        this.duration = random.get();
    }

    @Override
    public boolean canExecute() {
        return System.currentTimeMillis() - startMillis <= duration;
    }
}
