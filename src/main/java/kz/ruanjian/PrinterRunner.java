package kz.ruanjian;

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
            printer.print("[STACK]", stack);
            Integer polled = stack.poll();
            printer.print("[Printed]", polled);
        }
    }
}
