package kz.ruanjian.sequence;

import kz.ruanjian.threaded.SafeRandom;

public class DelayedIntegerSequence implements IntegerSequence {

    private final SafeRandom random;
    private final IntegerSequence sequence;

    public DelayedIntegerSequence(SafeRandom random, IntegerSequence sequence) {
        this.random = random;
        this.sequence = sequence;
    }

    @Override
    public Integer generate() {
        trySleep();
        return sequence.generate();
    }

    private void trySleep() {
        try {
            Thread.sleep(random.get());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
