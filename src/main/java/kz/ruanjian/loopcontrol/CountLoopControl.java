package kz.ruanjian.loopcontrol;

import java.util.concurrent.atomic.AtomicInteger;

public class CountLoopControl implements LoopControl {

    private final int maxLoops;
    private final AtomicInteger currentLoop;

    public CountLoopControl(int maxLoops) {
        this.maxLoops = maxLoops;
        this.currentLoop = new AtomicInteger(0);
    }

    @Override
    public boolean canExecute() {
        if (maxLoops < 0) {
            return true;
        }

        boolean isAllowed = currentLoop.get() < maxLoops;

        if (isAllowed) {
            currentLoop.getAndIncrement();
        }

        return isAllowed;
    }
}
