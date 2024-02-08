package kz.ruanjian.runner;

import kz.ruanjian.sequence.ArithmeticSequence;

import java.util.Deque;

public class ProducerRunner implements Runnable {

    private final Deque<Integer> stack;
    private final ArithmeticSequence sequence;

    public ProducerRunner(Deque<Integer> stack, ArithmeticSequence sequence) {
        this.stack = stack;
        this.sequence = sequence;
    }

    @Override
    public void run() {
        stack.push(sequence.generate());
    }
}
