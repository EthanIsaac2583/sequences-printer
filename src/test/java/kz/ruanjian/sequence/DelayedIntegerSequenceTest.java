package kz.ruanjian.sequence;

import kz.ruanjian.threaded.SafeRandom;
import kz.ruanjian.threaded.SafeSleeper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DelayedIntegerSequenceTest {

    @Mock
    SafeRandom random;

    @Mock
    SafeSleeper sleeper;

    @Mock
    IntegerSequence mockedSequence;

    @InjectMocks
    DelayedIntegerSequence delayedIntegerSequence;

    @Test
    void generate_shouldDoAppropriateActions_whenInvoked() {
        int expectedSleepMillis = 1234;
        int expected = 10;
        doReturn(expectedSleepMillis).when(random).get();
        doReturn(expected).when(mockedSequence).generate();

        int actual = delayedIntegerSequence.generate();

        assertEquals(expected, actual);
        verify(sleeper).sleep(expectedSleepMillis);
    }
}