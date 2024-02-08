package kz.ruanjian.runner;

import kz.ruanjian.PrintEvent;
import kz.ruanjian.Printer;
import kz.ruanjian.logger.Logger;
import kz.ruanjian.loopcontrol.DurationLoopControl;
import kz.ruanjian.runner.PrinterRunner;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Deque;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PrinterRunnerTest {

    @Mock
    Deque<Integer> stack;

    @Mock
    Printer printer;

    @InjectMocks
    PrinterRunner printerRunner;

    @Test
    void run_shouldNotPrint_whenStackHasNoItem() {
        doReturn(true).when(stack).isEmpty();

        printerRunner.run();

        verify(stack, never()).poll();
    }

    @Test
    void run_shouldPrint_whenStackHasItem() {
        doReturn(false).when(stack).isEmpty();
        doReturn(12).when(stack).poll();

        InOrder inOrder = inOrder(stack, printer);

        printerRunner.run();

        verify(stack).poll();

        inOrder.verify(printer).print(createPrintEvent("[STACK]", stack));
        inOrder.verify(stack).poll();
        inOrder.verify(printer).print(createPrintEvent("[Printed]", 12));
    }

    private PrintEvent createPrintEvent(String name, Object value) {
        return PrintEvent.builder()
                .dateTime(LocalDateTime.now())
                .name(name)
                .value(value)
                .build();
    }
}