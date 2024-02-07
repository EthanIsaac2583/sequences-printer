package kz.ruanjian;

import kz.ruanjian.sequence.IntegerSequence;

import java.util.Deque;

public class Producer implements Runnable {

    private final IntegerSequence sequence;
    private final Deque<String> stack;
    private final String prefix;

    public Producer(IntegerSequence sequence,
                    Deque<String> stack,
                    String prefix) {
        this.sequence = sequence;
        this.stack = stack;
        this.prefix = prefix;
    }

    @Override
    public void run() {

    }
}
