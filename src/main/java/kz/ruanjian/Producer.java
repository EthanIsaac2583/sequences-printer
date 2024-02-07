package kz.ruanjian;

import kz.ruanjian.logger.Logger;
import kz.ruanjian.sequence.IntegerSequence;
import kz.ruanjian.loopcontrol.CountLoopControl;

import java.util.Deque;

public class Producer implements Runnable {

    private final Deque<Integer> stack;
    private final IntegerSequence sequence;
    private final Logger logger;

    public Producer(Deque<Integer> stack,
                    IntegerSequence sequence,
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
