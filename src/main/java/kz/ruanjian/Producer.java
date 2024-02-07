package kz.ruanjian;

import kz.ruanjian.logger.Logger;
import kz.ruanjian.sequence.ArithmeticSequence;

import java.util.Deque;

public class Producer implements Runnable {

    private final Deque<Integer> stack;
    private final ArithmeticSequence sequence;
    private final Logger logger;

    public Producer(Deque<Integer> stack,
                    ArithmeticSequence sequence,
                    Logger logger) {
        this.stack = stack;
        this.sequence = sequence;
        this.logger = logger;
    }

    @Override
    public void run() {
        logger.log("BEFORE: " + stack);
        stack.push(sequence.generate());
        logger.log("AFTER : " + stack);
    }
}
