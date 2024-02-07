package kz.ruanjian.sequence;

import kz.ruanjian.threaded.SafeRandom;
import kz.ruanjian.threaded.SafeSleeper;

public class DelayedIntegerSequence implements IntegerSequence {

    private final SafeRandom random;
    private final SafeSleeper sleeper;
    private final IntegerSequence sequence;

    public DelayedIntegerSequence(SafeRandom random,
                                  SafeSleeper sleeper,
                                  IntegerSequence sequence) {
        this.random = random;
        this.sleeper = sleeper;
        this.sequence = sequence;
    }

    @Override
    public Integer generate() {
        sleeper.sleep(random.get());
        return sequence.generate();
    }
}
