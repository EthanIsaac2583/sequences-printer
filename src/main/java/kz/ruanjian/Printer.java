package kz.ruanjian;

import kz.ruanjian.formatter.PrinterFormatter;
import kz.ruanjian.logger.Logger;

import java.util.Deque;

public class Printer implements Runnable {

    private final Deque<Integer> stack;
    private final Logger logger;
    private final PrinterFormatter formatter;

    public Printer(Deque<Integer> stack,
                   Logger logger,
                   PrinterFormatter formatter) {
        this.stack = stack;
        this.logger = logger;
        this.formatter = formatter;
    }

    @Override
    public void run() {
        if (!stack.isEmpty()) {
            logger.log(formatter.timedMessage("[STACK]", stack));
            Integer polled = stack.poll();
            logger.log(formatter.timedMessage("[Printed]", polled));
        }
    }
}
