package kz.ruanjian;

import kz.ruanjian.formatter.PrinterFormatter;
import kz.ruanjian.logger.ConsoleLogger;
import kz.ruanjian.loopcontrol.DurationLoopControl;
import kz.ruanjian.loopcontrol.ApplicationLoopControl;
import kz.ruanjian.runner.PrintWindowRunner;
import kz.ruanjian.runner.PrinterRunner;
import kz.ruanjian.runner.ProducerRunner;
import kz.ruanjian.runner.SleepAfterRunner;
import kz.ruanjian.sequence.ArithmeticSequence;
import kz.ruanjian.threaded.SafeRandom;
import kz.ruanjian.threaded.SafeSleeper;

import java.util.Deque;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {
        Deque<Integer> stack = new ConcurrentLinkedDeque<>();

        SafeSleeper sleeper = new SafeSleeper();
        ApplicationLoopControl applicationLoopControl = new ApplicationLoopControl(true);

        ExecutorService producersService = Executors.newFixedThreadPool(2);

        Runnable arithmeticThreesProducerRunner = new ProducerRunner(stack, new ArithmeticSequence(3, 3));
        producersService.execute(new SleepAfterRunner(arithmeticThreesProducerRunner, applicationLoopControl, sleeper, new SafeRandom(500, 1000)));

        Runnable arithmeticFivesProducerRunner = new ProducerRunner(stack, new ArithmeticSequence(5, 5));
        producersService.execute(new SleepAfterRunner(arithmeticFivesProducerRunner, applicationLoopControl, sleeper, new SafeRandom(2000, 2500)));

        ExecutorService printerService = Executors.newFixedThreadPool(1, Main::createDaemonThread);

        Printer printer = new Printer(new PrinterFormatter(26, 13), new ConsoleLogger());
        Runnable printerRunner = new PrinterRunner(stack, printer);
        Runnable printWindowRunner = new PrintWindowRunner(printerRunner, new DurationLoopControl(new SafeRandom(2000, 4000)), printer);
        printerService.execute(new SleepAfterRunner(printWindowRunner, applicationLoopControl, sleeper, new SafeRandom(15000, 20000)));

        producersService.shutdown();
        printerService.shutdown();
    }

    private static Thread createDaemonThread(Runnable runnable) {
        Thread thread = Executors.defaultThreadFactory().newThread(runnable);
        thread.setDaemon(true);
        return thread;
    }
}
