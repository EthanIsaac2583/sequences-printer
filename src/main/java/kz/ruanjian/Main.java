package kz.ruanjian;

import kz.ruanjian.logger.ConsoleLogger;
import kz.ruanjian.logger.Logger;
import kz.ruanjian.loopcontrol.DurationLoopControl;
import kz.ruanjian.sequence.ArithmeticIntegerSequence;
import kz.ruanjian.sequence.IntegerSequence;
import kz.ruanjian.threaded.SafeRandom;
import kz.ruanjian.threaded.SafeSleeper;

import java.util.Deque;
import java.util.concurrent.ConcurrentLinkedDeque;

public class Main {

    public static void main(String[] args) {
        Deque<Integer> stack = new ConcurrentLinkedDeque<>();

        SafeSleeper sleeper = new SafeSleeper();
        Logger logger = new ConsoleLogger();

        IntegerSequence arithmeticThrees = new ArithmeticIntegerSequence(3, 3);
        new Thread(new SleepingRunner(new Producer(stack, arithmeticThrees, logger), sleeper, new SafeRandom(200, 300))).start();

        IntegerSequence arithmeticFives = new ArithmeticIntegerSequence(5, 5);
        new Thread(new SleepingRunner(new Producer(stack, arithmeticFives, logger), sleeper, new SafeRandom(2000, 2500))).start();

        DurationLoopControl printerWorkDurationControl = new DurationLoopControl(new SafeRandom(200, 500).get());
        new Thread(new SleepingRunner(new Printer(stack, printerWorkDurationControl, logger), sleeper, new SafeRandom(10000, 12000))).start();
    }
}
