package kz.ruanjian.runner;

import kz.ruanjian.PrintEvent;
import kz.ruanjian.Printer;

import java.time.LocalDateTime;
import java.util.Deque;

public class PrinterRunner implements Runnable {

    private final Deque<Integer> stack;
    private final Printer printer;

    public PrinterRunner(Deque<Integer> stack, Printer printer) {
        this.stack = stack;
        this.printer = printer;
    }

    @Override
    public void run() {
        if (!stack.isEmpty()) {
            printer.print(generateStackEvent());
            Integer polled = stack.poll();
            printer.print(generatePrintedEvent(polled));
        }
    }

    private PrintEvent generateStackEvent() {
        return PrintEvent.builder()
                .dateTime(LocalDateTime.now())
                .name("[STACK]")
                .value(stack)
                .build();
    }

    private PrintEvent generatePrintedEvent(Integer polled) {
        return PrintEvent.builder()
                .dateTime(LocalDateTime.now())
                .name("[Printed]")
                .value(polled)
                .build();
    }
}
