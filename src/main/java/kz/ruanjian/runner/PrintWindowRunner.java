package kz.ruanjian.runner;

import kz.ruanjian.PrintEvent;
import kz.ruanjian.Printer;
import kz.ruanjian.loopcontrol.DurationLoopControl;

import java.time.LocalDateTime;

public class PrintWindowRunner implements Runnable {

    private final Runnable runnable;
    private final DurationLoopControl loopControl;
    private final Printer printer;

    public PrintWindowRunner(Runnable runnable,
                             DurationLoopControl loopControl,
                             Printer printer) {
        this.runnable = runnable;
        this.loopControl = loopControl;
        this.printer = printer;
    }

    @Override
    public void run() {
        printer.print(generateWindowOpenedEvent());

        loopControl.fromNow();
        while (loopControl.canExecute()) {
            if (runnable != null) {
                runnable.run();
            }
        }

        printer.print(generateWindowClosedEvent());
    }

    private PrintEvent generateWindowOpenedEvent() {
        return PrintEvent.builder()
                .dateTime(LocalDateTime.now())
                .name("[PRINTER]")
                .value("------- [PRINT WINDOW] opened -------")
                .build();
    }

    private PrintEvent generateWindowClosedEvent() {
        return PrintEvent.builder()
                .dateTime(LocalDateTime.now())
                .name("[PRINTER]")
                .value("------- [PRINT WINDOW] closed -------")
                .build();
    }
}
