package kz.ruanjian.loopcontrol;

import java.util.concurrent.atomic.AtomicLong;

public class DurationLoopControl implements LoopControl {

    private final int duration;
    private final AtomicLong startMillis;

    public DurationLoopControl(int duration, long startMillis) {
        this.duration = duration;
        this.startMillis = new AtomicLong(startMillis);
    }

    public void setStartMillis(long startMillis) {
        this.startMillis.set(startMillis);
    }

    @Override
    public synchronized boolean canExecute() {
        return System.currentTimeMillis() - startMillis.get() <= duration;
    }
}
