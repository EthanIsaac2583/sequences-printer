package kz.ruanjian.loopcontrol;

import kz.ruanjian.threaded.SafeRandom;

import java.util.concurrent.atomic.AtomicLong;

public class DurationLoopControl implements LoopControl {

    private final int durationMinMillis;
    private final int durationMaxMillis;
    private long duration;
    private AtomicLong startMillis;

    public DurationLoopControl(int durationMinMillis, int durationMaxMillis) {
        this.durationMinMillis = durationMinMillis;
        this.durationMaxMillis = durationMaxMillis;
        setStateValues(durationMinMillis, durationMaxMillis);
    }

    public void setStartMillis(long startMillis) {
        this.startMillis.set(startMillis);
    }

    public void reset() {
        setStateValues(this.durationMinMillis, this.durationMaxMillis);
    }

    @Override
    public synchronized boolean canExecute() {
        return System.currentTimeMillis() - startMillis.get() <= duration;
    }

    private void setStateValues(int durationMinMillis, int durationMaxMillis) {
        this.duration = new SafeRandom(durationMinMillis, durationMaxMillis).get();
        this.startMillis = new AtomicLong(0L);
    }
}
