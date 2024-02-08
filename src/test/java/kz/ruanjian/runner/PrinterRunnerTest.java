package kz.ruanjian.runner;

import kz.ruanjian.logger.Logger;
import kz.ruanjian.loopcontrol.DurationLoopControl;
import kz.ruanjian.runner.PrinterRunner;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Deque;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PrinterRunnerTest {

    @Mock
    Deque<Integer> stack;

    @Mock
    DurationLoopControl durationLoopControl;

    @Mock
    Logger logger;

    @InjectMocks
    PrinterRunner printerRunner;

    @Test
    void run_shouldDoAppropriateActions_whenCanExecuteAndHasStackValues() {
        doReturn(true).doReturn(true).doReturn(true).doReturn(false).when(durationLoopControl).canExecute();
        doReturn(12).doReturn(8).doReturn(4).when(stack).poll();

        InOrder inOrder = inOrder(logger, stack);

        printerRunner.run();

        inOrder.verify(logger).log("------- [PRINT WINDOW] opened -------");
        inOrder.verify(stack).poll();
        inOrder.verify(logger).log("Printed: " + 12);
        inOrder.verify(stack).poll();
        inOrder.verify(logger).log("Printed: " + 8);
        inOrder.verify(stack).poll();
        inOrder.verify(logger).log("Printed: " + 4);
        inOrder.verify(logger).log("------- [PRINT WINDOW] closed -------");
    }

    @Test
    void run_shouldDoAppropriateActions_whenCanExecuteAndHasLackStackValues() {
        doReturn(true).doReturn(true).doReturn(true).doReturn(false).when(durationLoopControl).canExecute();
        doReturn(100).when(stack).poll();

        InOrder inOrder = inOrder(logger, stack);

        printerRunner.run();

        inOrder.verify(logger).log("------- [PRINT WINDOW] opened -------");
        inOrder.verify(stack).poll();
        inOrder.verify(logger).log("Printed: " + 100);
        inOrder.verify(stack).poll();
        inOrder.verify(stack).poll();
        inOrder.verify(logger).log("------- [PRINT WINDOW] closed -------");
    }
}