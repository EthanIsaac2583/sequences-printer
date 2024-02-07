package kz.ruanjian;

import kz.ruanjian.logger.Logger;
import kz.ruanjian.loopcontrol.DurationLoopControl;

import java.util.Deque;

public class Printer implements Runnable {

    private final Deque<Integer> stack;
    private final DurationLoopControl durationLoopControl;
    private final Logger logger;

    public Printer(Deque<Integer> stack,
                   DurationLoopControl durationLoopControl,
                   Logger logger) {
        this.stack = stack;
        this.durationLoopControl = durationLoopControl;
        this.logger = logger;
    }

    @Override
    public void run() {
        logger.log("------- [PRINT WINDOW] opened -------");

        durationLoopControl.pointCurrentTime();
        while (durationLoopControl.canExecute()) {
            Integer polled = stack.poll();

            if (polled != null) {
                logger.log("Printed: " + polled);
            }
        }

        logger.log("------- [PRINT WINDOW] closed -------");
    }
}
