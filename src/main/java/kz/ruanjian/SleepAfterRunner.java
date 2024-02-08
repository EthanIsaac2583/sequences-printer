package kz.ruanjian;

import kz.ruanjian.loopcontrol.LoopControl;
import kz.ruanjian.threaded.SafeRandom;
import kz.ruanjian.threaded.SafeSleeper;

public class SleepAfterRunner implements Runnable {

    private final Runnable runnable;
    private final LoopControl loopControl;
    private final SafeSleeper sleeper;
    private final SafeRandom random;

    public SleepAfterRunner(Runnable runnable,
                            LoopControl loopControl,
                            SafeSleeper sleeper,
                            SafeRandom random) {
        this.runnable = runnable;
        this.loopControl = loopControl;
        this.sleeper = sleeper;
        this.random = random;
    }

    @Override
    public void run() {
        while (loopControl.canExecute()) {
            if (runnable != null) {
                runnable.run();
            }
            sleeper.sleep(random.get());
        }
    }
}
