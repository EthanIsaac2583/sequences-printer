package kz.ruanjian;

import kz.ruanjian.loopcontrol.DurationLoopControl;

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
        printer.print("[PRINTER]", "------- [PRINT WINDOW] opened -------");

        loopControl.fromNow();
        while (loopControl.canExecute()) {
            if (runnable != null) {
                runnable.run();
            }
        }

        printer.print("[PRINTER]: ", "------- [PRINT WINDOW] closed -------");
    }
}
