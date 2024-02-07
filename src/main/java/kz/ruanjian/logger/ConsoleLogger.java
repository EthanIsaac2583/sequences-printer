package kz.ruanjian.logger;

import kz.ruanjian.OncePerThread;

public class ConsoleLogger implements Logger, OncePerThread {

    @Override
    public void log(String value) {
        synchronized (System.out) {
            System.out.println(value);
        }
    }
}
