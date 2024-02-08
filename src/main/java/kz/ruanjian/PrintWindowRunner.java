package kz.ruanjian;

import kz.ruanjian.formatter.PrinterFormatter;
import kz.ruanjian.logger.Logger;
import kz.ruanjian.loopcontrol.DurationLoopControl;

public class PrintWindowRunner implements Runnable {

    private final Runnable runnable;
    private final DurationLoopControl loopControl;
    private final Logger logger;
    private final PrinterFormatter formatter;

    public PrintWindowRunner(Runnable runnable,
                             DurationLoopControl loopControl,
                             Logger logger,
                             PrinterFormatter formatter) {
        this.runnable = runnable;
        this.loopControl = loopControl;
        this.logger = logger;
        this.formatter = formatter;
    }

    @Override
    public void run() {
        logger.log(formatter.timedMessage("[PRINTER]", "------- [PRINT WINDOW] opened -------"));

        loopControl.fromNow();
        while (loopControl.canExecute()) {
            if (runnable != null) {
                runnable.run();
            }
        }

        logger.log(formatter.timedMessage("[PRINTER]: ", "------- [PRINT WINDOW] closed -------"));
    }
}
