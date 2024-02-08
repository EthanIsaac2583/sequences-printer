package kz.ruanjian;

import kz.ruanjian.formatter.PrinterFormatter;
import kz.ruanjian.logger.Logger;

public class Printer implements OnePerThread {

    private final PrinterFormatter formatter;
    private final Logger logger;

    public Printer(PrinterFormatter formatter, Logger logger) {
        this.formatter = formatter;
        this.logger = logger;
    }

    public void print(String prefix, Object value) {
        logger.log(formatter.timedMessage(prefix, value));
    }
}
