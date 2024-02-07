package kz.ruanjian;

import kz.ruanjian.logger.Logger;
import kz.ruanjian.loopcontrol.CountLoopControl;
import kz.ruanjian.loopcontrol.DurationLoopControl;
import kz.ruanjian.threaded.SafeRandom;
import kz.ruanjian.threaded.SafeSleeper;

import java.util.Deque;

public class Printer implements Runnable {

    private final Deque<Integer> stack;
    private final DurationLoopControl durationLoopControl;
    private final CountLoopControl countLoopControl;
    private final SafeSleeper sleeper;
    private final Logger logger;

    public Printer(Deque<Integer> stack,
                   DurationLoopControl durationLoopControl,
                   CountLoopControl countLoopControl,
                   SafeSleeper sleeper,
                   Logger logger) {
        this.stack = stack;
        this.durationLoopControl = durationLoopControl;
        this.countLoopControl = countLoopControl;
        this.sleeper = sleeper;
        this.logger = logger;
    }

    @Override
    public void run() {
        while (countLoopControl.canExecute()) {
            sleeper.sleep(new SafeRandom(4000, 8000).get());

            logger.log("------- [PRINT WINDOW] opened -------");

            durationLoopControl.setStartMillis(System.currentTimeMillis());
            while (durationLoopControl.canExecute()) {
                Integer polled = stack.poll();

                if (polled != null) {
                    logger.log("Printed: " + polled);
                }
            }

            logger.log("------- [PRINT WINDOW] closed -------");
        }
    }
}
