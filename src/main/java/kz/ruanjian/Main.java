package kz.ruanjian;

import kz.ruanjian.formatter.PrinterFormatter;
import kz.ruanjian.logger.ConsoleLogger;
import kz.ruanjian.loopcontrol.DurationLoopControl;
import kz.ruanjian.loopcontrol.InfiniteLoopControl;
import kz.ruanjian.sequence.ArithmeticSequence;
import kz.ruanjian.threaded.SafeRandom;
import kz.ruanjian.threaded.SafeSleeper;

import java.util.Deque;
import java.util.concurrent.ConcurrentLinkedDeque;

public class Main {

    public static void main(String[] args) {
        Deque<Integer> stack = new ConcurrentLinkedDeque<>();

        SafeSleeper sleeper = new SafeSleeper();
        InfiniteLoopControl infiniteLoopControl = new InfiniteLoopControl();

        Producer arithmeticThreesProducer = new Producer(stack, new ArithmeticSequence(3, 3));
        new Thread(new SleepingRunner(arithmeticThreesProducer, infiniteLoopControl, sleeper, new SafeRandom(500, 1000))).start();

        Producer arithmeticFivesProducer = new Producer(stack, new ArithmeticSequence(5, 5));
        new Thread(new SleepingRunner(arithmeticFivesProducer, infiniteLoopControl, sleeper, new SafeRandom(2000, 2500))).start();

        DurationLoopControl printerWorkDurationControl = new DurationLoopControl(new SafeRandom(2000, 4000));
        Printer printer = new Printer(stack, printerWorkDurationControl, new ConsoleLogger(), new PrinterFormatter(20));
        Thread printerThread = new Thread(new SleepingRunner(printer, infiniteLoopControl, sleeper, new SafeRandom(15000, 20000)));
        printerThread.setDaemon(true);
        printerThread.start();
    }
}
