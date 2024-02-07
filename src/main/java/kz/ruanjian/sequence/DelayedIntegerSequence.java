package kz.ruanjian.sequence;

import kz.ruanjian.random.SafeRandom;

public class DelayedIntegerSequence implements IntegerSequence {

    private final SafeRandom random;
    private final IntegerSequence sequence;
    private final int delayMinMillis;
    private final int delayMaxMillis;

    public DelayedIntegerSequence(SafeRandom random,
                                  IntegerSequence sequence,
                                  int delayMinMillis,
                                  int delayMaxMillis) {
        this.random = random;
        this.sequence = sequence;
        this.delayMinMillis = delayMinMillis;
        this.delayMaxMillis = delayMaxMillis;
    }

    @Override
    public Integer generate() {
        trySleep();
        return sequence.generate();
    }

    private void trySleep() {
        try {
            Thread.sleep(random.get(delayMinMillis, delayMaxMillis));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
