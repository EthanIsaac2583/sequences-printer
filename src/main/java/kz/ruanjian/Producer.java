package kz.ruanjian;

import kz.ruanjian.logger.Logger;
import kz.ruanjian.sequence.IntegerSequence;
import kz.ruanjian.loopcontrol.CountLoopControl;

import java.util.Deque;

public class Producer implements Runnable {

    private final Deque<Integer> stack;
    private final IntegerSequence sequence;
    private final CountLoopControl countLoopControl;
    private final Logger logger;

    public Producer(Deque<Integer> stack,
                    IntegerSequence sequence,
                    CountLoopControl countLoopControl,
                    Logger logger) {
        this.stack = stack;
        this.sequence = sequence;
        this.countLoopControl = countLoopControl;
        this.logger = logger;
    }

    @Override
    public void run() {
        while (countLoopControl.canExecute()) {
            logger.log("BEFORE: " + stack);
            stack.push(sequence.generate());
            logger.log("AFTER : " + stack);
        }
    }
}
