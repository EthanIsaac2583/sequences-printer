package kz.ruanjian.runner;

import kz.ruanjian.PrintEvent;
import kz.ruanjian.Printer;
import kz.ruanjian.loopcontrol.DurationLoopControl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PrintWindowRunnerTest {

    @Mock
    Runnable runnable;

    @Mock
    DurationLoopControl loopControl;

    @Mock
    Printer printer;

    @InjectMocks
    PrintWindowRunner printWindowRunner;

    @Test
    void run_shouldNotCallChildRunner_whenLoopControlNotAllows() {
        doReturn(false).when(loopControl).canExecute();

        InOrder inOrder = inOrder(loopControl, printer);

        printWindowRunner.run();

        inOrder.verify(printer).print(createPrintEvent("[PRINTER]", "------- [PRINT WINDOW] opened -------"));
        inOrder.verify(loopControl).fromNow();
        inOrder.verify(printer).print(createPrintEvent("[PRINTER]", "------- [PRINT WINDOW] closed -------"));

        verify(runnable, never()).run();
    }

    @Test
    void run_shouldCallChildRunnerNTimes_whenLoopControlAllowedNTimes() {
        doReturn(true).doReturn(true).doReturn(false).when(loopControl).canExecute();

        InOrder inOrder = inOrder(runnable, loopControl, printer);

        printWindowRunner.run();

        inOrder.verify(printer).print(createPrintEvent("[PRINTER]", "------- [PRINT WINDOW] opened -------"));
        inOrder.verify(loopControl).fromNow();
        inOrder.verify(runnable).run();
        inOrder.verify(runnable).run();
        inOrder.verify(printer).print(createPrintEvent("[PRINTER]", "------- [PRINT WINDOW] closed -------"));

    }

    private PrintEvent createPrintEvent(String name, Object value) {
        return PrintEvent.builder()
                .dateTime(LocalDateTime.now())
                .name(name)
                .value(value)
                .build();
    }
}