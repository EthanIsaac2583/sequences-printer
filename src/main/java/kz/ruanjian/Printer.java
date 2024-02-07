package kz.ruanjian;

import kz.ruanjian.logger.Logger;
import kz.ruanjian.loopcontrol.DurationLoopControl;

import java.util.Deque;

public class Printer implements Runnable {

    private final Deque<Integer> stack;
    private final DurationLoopControl loopControl;
    private final Logger logger;

    public Printer(Deque<Integer> stack, DurationLoopControl loopControl, Logger logger) {
        this.stack = stack;
        this.loopControl = loopControl;
        this.logger = logger;
    }

    @Override
    public void run() {
        loopControl.setStartMillis(System.currentTimeMillis());
        logger.log("------- [PRINT WINDOW] opened -------");
        while (loopControl.canExecute()) {
            Integer polled = stack.poll();

            if (polled != null) {
                logger.log("Printed: " + polled);
            }
        }
        logger.log("------- [PRINT WINDOW] closed -------");
    }
}
