package kz.ruanjian;

import kz.ruanjian.formatter.PrinterFormatter;
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
        PrinterFormatter formatter = new PrinterFormatter(20);
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
