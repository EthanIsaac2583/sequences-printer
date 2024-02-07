package kz.ruanjian;

import kz.ruanjian.logger.Logger;
import kz.ruanjian.sequence.IntegerSequence;
import kz.ruanjian.util.LoopControl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.concurrent.ConcurrentLinkedDeque;

import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ProducerTest {

    @Mock
    ConcurrentLinkedDeque<Integer> stack;

    @Mock
    IntegerSequence sequence;

    @Mock
    LoopControl loopControl;

    @Mock
    Logger logger;

    @InjectMocks
    Producer producer;

    @Test
    void run_shouldDoAppropriateActionsNTime_whenCorrespondingArgumentsPassed() {
        doReturn(true).doReturn(true).doReturn(true).doReturn(false).when(loopControl).canExecute();
        doReturn(4).doReturn(8).doReturn(12).when(sequence).generate();

        producer.run();

        verify(stack).push(4);
        verify(stack).push(8);
        verify(stack).push(12);
        verify(loopControl, atLeast(3)).canExecute();
        verify(logger, times(3)).log("BEFORE: " + stack);
        verify(logger, times(3)).log("AFTER : " + stack);
    }
}