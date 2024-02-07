package kz.ruanjian.sequence;

import kz.ruanjian.threaded.SafeRandom;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DelayedIntegerSequenceTest {

    @Mock
    SafeRandom random;

    @Mock
    IntegerSequence mockedSequence;

    @InjectMocks
    DelayedIntegerSequence delayedIntegerSequence;

    @Test
    void generate() {

    }
}