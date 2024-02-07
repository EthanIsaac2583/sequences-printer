package kz.ruanjian;

import kz.ruanjian.data.DataGenerator;
import kz.ruanjian.logger.Logger;
import kz.ruanjian.sequence.IntegerSequence;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.concurrent.ConcurrentLinkedDeque;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ProducerTest {

    @Mock
    ConcurrentLinkedDeque<Integer> stack;

    @Mock
    IntegerSequence sequence;

    @Mock
    Logger logger;

    @InjectMocks
    Producer producer;

    DataGenerator dataGenerator;

    @BeforeEach
    void setUp() {
        dataGenerator = new DataGenerator();
    }

    @Test
    void run_shouldDoAppropriateActionsNTime_whenCorrespondingArgumentsPassed() {
        int expected = dataGenerator.randomInt(100, 1_000_000);
        doReturn(expected).when(sequence).generate();

        producer.run();

        verify(logger).log("BEFORE: " + stack);
        verify(stack).push(expected);
        verify(logger).log("AFTER : " + stack);
    }
}