package kz.ruanjian.loopcontrol;

public class DurationLoopControl implements LoopControl {

    private final int duration;
    private long startMillis;

    public DurationLoopControl(int duration, long startMillis) {
        this.duration = duration;
        this.startMillis = startMillis;
    }

    public void setStartMillis(long startMillis) {
        this.startMillis = startMillis;
    }

    @Override
    public boolean canExecute() {
        return System.currentTimeMillis() - startMillis <= duration;
    }
}
