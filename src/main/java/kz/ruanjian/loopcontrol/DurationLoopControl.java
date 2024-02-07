package kz.ruanjian.loopcontrol;

import kz.ruanjian.threaded.SafeRandom;

import java.util.concurrent.atomic.AtomicLong;

public class DurationLoopControl implements LoopControl {

    private final int durationMinMillis;
    private final int durationMaxMillis;
    private final AtomicLong duration = new AtomicLong(0L);
    private final AtomicLong startMillis = new AtomicLong(0L);

    public DurationLoopControl(int durationMinMillis, int durationMaxMillis) {
        this.durationMinMillis = durationMinMillis;
        this.durationMaxMillis = durationMaxMillis;
    }

    public synchronized void fromMillis(long startMillis) {
        this.startMillis.set(startMillis);
        this.duration.set(new SafeRandom(durationMinMillis, durationMaxMillis).get());
    }

    @Override
    public boolean canExecute() {
        return System.currentTimeMillis() - startMillis.get() <= duration.get();
    }
}
