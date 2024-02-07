package kz.ruanjian.sequence;

import java.util.concurrent.atomic.AtomicInteger;

public class ArithmeticIntegerSequence implements IntegerSequence {

    private final AtomicInteger atomicInteger;
    private final int increment;

    public ArithmeticIntegerSequence(int initialTerm, int increment) {
        atomicInteger = new AtomicInteger(initialTerm);
        this.increment = increment;
    }

    @Override
    public Integer generate() {
        return atomicInteger.getAndAdd(increment);
    }
}
