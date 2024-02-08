package kz.ruanjian.logger;

import kz.ruanjian.OnePerThread;

public class ConsoleLogger implements Logger, OnePerThread {

    @Override
    public void log(String value) {
        synchronized (System.out) {
            System.out.println(value);
        }
    }
}
