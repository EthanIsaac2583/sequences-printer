package kz.ruanjian;

import kz.ruanjian.logger.Logger;
import kz.ruanjian.sequence.IntegerSequence;
import kz.ruanjian.util.LoopControl;

import java.util.Deque;

public class Producer implements Runnable {

    private final Deque<String> stack;
    private final IntegerSequence sequence;
    private final LoopControl loopControl;
    private final Logger logger;
    private final String prefix;

    public Producer(Deque<String> stack,
                    IntegerSequence sequence,
                    LoopControl loopControl,
                    Logger logger,
                    String prefix) {
        this.stack = stack;
        this.sequence = sequence;
        this.loopControl = loopControl;
        this.logger = logger;
        this.prefix = prefix;
    }

    @Override
    public void run() {
        while (loopControl.canExecute()) {
            int number = sequence.generate();
            logger.log("BEFORE: " + stack);
            stack.push(prefix + number);
            logger.log("AFTER : " + stack);
        }
    }
}
