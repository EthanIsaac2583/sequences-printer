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

        ProducerRunner arithmeticThreesProducerRunner = new ProducerRunner(stack, new ArithmeticSequence(3, 3));
        new Thread(new SleepingRunner(arithmeticThreesProducerRunner, infiniteLoopControl, sleeper, new SafeRandom(500, 1000))).start();

        ProducerRunner arithmeticFivesProducerRunner = new ProducerRunner(stack, new ArithmeticSequence(5, 5));
        new Thread(new SleepingRunner(arithmeticFivesProducerRunner, infiniteLoopControl, sleeper, new SafeRandom(2000, 2500))).start();

        PrinterFormatter printerFormatter = new PrinterFormatter(20);
        ConsoleLogger logger = new ConsoleLogger();
        PrinterRunner printerRunner = new PrinterRunner(stack, logger, printerFormatter);
        PrintWindowRunner printWindowRunner = new PrintWindowRunner(printerRunner, new DurationLoopControl(new SafeRandom(2000, 4000)), logger, printerFormatter);
        Thread printerThread = new Thread(new SleepingRunner(printWindowRunner, infiniteLoopControl, sleeper, new SafeRandom(15000, 20000)));
        printerThread.setDaemon(true);
        printerThread.start();
    }
}
