package kz.ruanjian;

import kz.ruanjian.formatter.PrinterFormatter;
import kz.ruanjian.logger.ConsoleLogger;
import kz.ruanjian.loopcontrol.DurationLoopControl;
import kz.ruanjian.loopcontrol.InfiniteLoopControl;
import kz.ruanjian.runner.PrintWindowRunner;
import kz.ruanjian.runner.PrinterRunner;
import kz.ruanjian.runner.ProducerRunner;
import kz.ruanjian.runner.SleepAfterRunner;
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

        Runnable arithmeticThreesProducerRunner = new ProducerRunner(stack, new ArithmeticSequence(3, 3));
        new Thread(new SleepAfterRunner(arithmeticThreesProducerRunner, infiniteLoopControl, sleeper, new SafeRandom(500, 1000))).start();

        Runnable arithmeticFivesProducerRunner = new ProducerRunner(stack, new ArithmeticSequence(5, 5));
        new Thread(new SleepAfterRunner(arithmeticFivesProducerRunner, infiniteLoopControl, sleeper, new SafeRandom(2000, 2500))).start();

        Printer printer = new Printer(new PrinterFormatter(26, 13), new ConsoleLogger());
        Runnable printerRunner = new PrinterRunner(stack, printer);
        Runnable printWindowRunner = new PrintWindowRunner(printerRunner, new DurationLoopControl(new SafeRandom(2000, 4000)), printer);
        Thread printerThread = new Thread(new SleepAfterRunner(printWindowRunner, infiniteLoopControl, sleeper, new SafeRandom(15000, 20000)));
        printerThread.setDaemon(true);
        printerThread.start();
    }
}
