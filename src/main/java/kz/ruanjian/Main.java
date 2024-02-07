package kz.ruanjian;

import kz.ruanjian.logger.ConsoleLogger;
import kz.ruanjian.logger.Logger;
import kz.ruanjian.loopcontrol.CountLoopControl;
import kz.ruanjian.loopcontrol.DurationLoopControl;
import kz.ruanjian.sequence.ArithmeticIntegerSequence;
import kz.ruanjian.sequence.DelayedIntegerSequence;
import kz.ruanjian.sequence.IntegerSequence;
import kz.ruanjian.threaded.SafeRandom;
import kz.ruanjian.threaded.SafeSleeper;

import java.util.Deque;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {
        Deque<Integer> stack = new ConcurrentLinkedDeque<>();

        SafeSleeper sleeper = new SafeSleeper();
        Logger logger = new ConsoleLogger();
        CountLoopControl infiniteCountLoopControl = new CountLoopControl(-1);
        IntegerSequence delayedArithmeticThrees = new DelayedIntegerSequence(new SafeRandom(200, 300), sleeper, new ArithmeticIntegerSequence(3, 3));
        IntegerSequence delayedArithmeticFives = new DelayedIntegerSequence(new SafeRandom(2000, 2500), sleeper, new ArithmeticIntegerSequence(5, 5));

        new Thread(new Producer(stack, delayedArithmeticThrees, infiniteCountLoopControl, logger)).start();
        new Thread(new Producer(stack, delayedArithmeticFives, infiniteCountLoopControl, logger)).start();

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(new Printer(stack, new DurationLoopControl(new SafeRandom(200, 500).get()), logger), new SafeRandom(4, 8).get(), new SafeRandom(15, 20).get(), TimeUnit.SECONDS);
    }
}
