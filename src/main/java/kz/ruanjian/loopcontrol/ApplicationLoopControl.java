package kz.ruanjian.loopcontrol;

import java.util.concurrent.atomic.AtomicBoolean;

public class ApplicationLoopControl implements LoopControl {

    private AtomicBoolean atomicBoolean;

    public ApplicationLoopControl(boolean initialValue) {
        this.atomicBoolean = new AtomicBoolean(initialValue);
    }

    @Override
    public boolean canExecute() {
        return atomicBoolean.get();
    }

    public void stopExecution() {
        atomicBoolean.set(false);
    }
}
