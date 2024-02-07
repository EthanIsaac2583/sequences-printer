package kz.ruanjian.util;

public class LoopControl {

    private final int maxLoops;
    private int currentLoop;

    public LoopControl(int maxLoops) {
        this.maxLoops = maxLoops;
    }

    boolean canExecute() {
        if (maxLoops < 0) {
            return true;
        }

        boolean result = currentLoop < maxLoops;
        currentLoop++;

        return result;
    }
}