package kz.ruanjian;

import kz.ruanjian.formatter.PrinterFormatter;
import kz.ruanjian.logger.Logger;
import kz.ruanjian.loopcontrol.DurationLoopControl;

import java.util.Deque;

public class Printer implements Runnable {

    private final Deque<Integer> stack;
    private final DurationLoopControl durationLoopControl;
    private final Logger logger;
    private final PrinterFormatter formatter;

    public Printer(Deque<Integer> stack,
                   DurationLoopControl durationLoopControl,
                   Logger logger,
                   PrinterFormatter formatter) {
        this.stack = stack;
        this.durationLoopControl = durationLoopControl;
        this.logger = logger;
        this.formatter = formatter;
    }

    @Override
    public void run() {
        logger.log(formatter.timedMessage("[PRINTER]", "------- [PRINT WINDOW] opened -------"));
        Long startMillis = System.currentTimeMillis();

        durationLoopControl.fromNow();
        while (durationLoopControl.canExecute()) {
            if (!stack.isEmpty()) {
                logger.log(formatter.timedMessage("[STACK]", stack));
                Integer polled = stack.poll();
                logger.log(formatter.timedMessage("[Printed]", polled));
            }
        }

        Long endMillis = System.currentTimeMillis();
        logger.log(formatter.timedMessage("[PRINTER]: ", "------- [PRINT WINDOW] closed -------"));
        logger.log(formatter.timedMessage("[PRINTER]: ", "Print duration " + (endMillis - startMillis) + " millis"));
    }
}
