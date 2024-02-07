package kz.ruanjian.loopcontrol;

import kz.ruanjian.OncePerThread;

public class InfiniteLoopControl implements LoopControl, OncePerThread {

    @Override
    public boolean canExecute() {
        return true;
    }
}
