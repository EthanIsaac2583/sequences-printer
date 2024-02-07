package kz.ruanjian;

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

        ArithmeticSequence arithmeticThrees = new ArithmeticSequence(3, 3);
        Producer arithmeticThreesProducer = new Producer(stack, arithmeticThrees, new ConsoleLogger());
        SafeRandom threesIdleMillis = new SafeRandom(200, 300);
        new Thread(new SleepingRunner(arithmeticThreesProducer, infiniteLoopControl, sleeper, threesIdleMillis)).start();

        ArithmeticSequence arithmeticFives = new ArithmeticSequence(5, 5);
        Producer arithmeticFivesProducer = new Producer(stack, arithmeticFives, new ConsoleLogger());
        SafeRandom fivesIdleMillis = new SafeRandom(2000, 2500);
        new Thread(new SleepingRunner(arithmeticFivesProducer, infiniteLoopControl, sleeper, fivesIdleMillis)).start();

        DurationLoopControl printerWorkDurationControl = new DurationLoopControl(200, 500);
        Printer printer = new Printer(stack, printerWorkDurationControl, new ConsoleLogger());
        SafeRandom printerIdleMillis = new SafeRandom(10000, 12000);
        new Thread(new SleepingRunner(printer, infiniteLoopControl, sleeper, printerIdleMillis)).start();
    }
}
