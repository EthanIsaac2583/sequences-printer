package kz.ruanjian;

import kz.ruanjian.logger.Logger;
import kz.ruanjian.loopcontrol.DurationLoopControl;

import java.time.LocalDateTime;
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
        logger.log(LocalDateTime.now() + "          " + "------- [PRINT WINDOW] opened -------");

        durationLoopControl.fromNow();
        while (durationLoopControl.canExecute()) {
            if (!stack.isEmpty()) {
                logger.log(LocalDateTime.now() + " " + "STACK:   " + stack);
                Integer polled = stack.poll();
                logger.log(LocalDateTime.now() + " " + "Printed: " + polled);
            }
        }

        logger.log(LocalDateTime.now() + "          " + "------- [PRINT WINDOW] closed -------");
    }
}
