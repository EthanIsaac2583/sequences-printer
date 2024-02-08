package kz.ruanjian;

import kz.ruanjian.sequence.ArithmeticSequence;

import java.util.Deque;

public class Producer implements Runnable {

    private final Deque<Integer> stack;
    private final ArithmeticSequence sequence;

    public Producer(Deque<Integer> stack, ArithmeticSequence sequence) {
        this.stack = stack;
        this.sequence = sequence;
    }

    @Override
    public void run() {
        stack.push(sequence.generate());
    }
}
