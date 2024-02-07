package kz.ruanjian.util;

public class CountLoopControl implements LoopControl {

    private final int maxLoops;
    private int currentLoop;

    public CountLoopControl(int maxLoops) {
        this.maxLoops = maxLoops;
    }

    @Override
    public boolean canExecute() {
        if (maxLoops < 0) {
            return true;
        }

        boolean isAllowed = currentLoop < maxLoops;

        if (isAllowed) {
            currentLoop++;
        }

        return isAllowed;
    }
}
