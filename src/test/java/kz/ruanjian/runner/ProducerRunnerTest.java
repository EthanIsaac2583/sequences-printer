package kz.ruanjian.runner;

import kz.ruanjian.data.DataGenerator;
import kz.ruanjian.logger.Logger;
import kz.ruanjian.sequence.ArithmeticSequence;
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
class ProducerRunnerTest {

    @Mock
    ConcurrentLinkedDeque<Integer> stack;

    @Mock
    ArithmeticSequence sequence;

    @InjectMocks
    ProducerRunner producerRunner;

    DataGenerator dataGenerator;

    @BeforeEach
    void setUp() {
        dataGenerator = new DataGenerator();
    }

    @Test
    void run_shouldDoAppropriateActions_whenInvoked() {
        int expected = dataGenerator.randomInt(100, 1_000_000);
        doReturn(expected).when(sequence).generate();

        producerRunner.run();

        verify(stack).push(expected);
    }
}