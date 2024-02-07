package kz.ruanjian.sequence;

import java.util.concurrent.atomic.AtomicInteger;

public class ArithmeticSequence {

    private final AtomicInteger atomicInteger;
    private final int increment;

    public ArithmeticSequence(int initialTerm, int increment) {
        atomicInteger = new AtomicInteger(initialTerm);
        this.increment = increment;
    }

    public Integer generate() {
        return atomicInteger.getAndAdd(increment);
    }
}
