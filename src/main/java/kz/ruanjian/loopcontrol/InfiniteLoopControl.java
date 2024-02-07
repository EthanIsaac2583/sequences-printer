package kz.ruanjian.loopcontrol;

public class InfiniteLoopControl implements LoopControl {

    @Override
    public boolean canExecute() {
        return true;
    }
}
