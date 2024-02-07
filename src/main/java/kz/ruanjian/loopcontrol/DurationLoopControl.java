package kz.ruanjian.loopcontrol;

import kz.ruanjian.OncePerThread;
import kz.ruanjian.threaded.SafeRandom;

public class DurationLoopControl implements LoopControl, OncePerThread {

    private final int durationMinMillis;
    private final int durationMaxMillis;
    private long duration;
    private long startMillis;

    public DurationLoopControl(int durationMinMillis, int durationMaxMillis) {
        this.durationMinMillis = durationMinMillis;
        this.durationMaxMillis = durationMaxMillis;
    }

    public void pointCurrentTime() {
        this.startMillis = System.currentTimeMillis();
        this.duration = new SafeRandom(durationMinMillis, durationMaxMillis).get();
    }

    @Override
    public boolean canExecute() {
        return System.currentTimeMillis() - startMillis <= duration;
    }
}
