package kz.ruanjian.logger;

import kz.ruanjian.OnePerThread;

public class ConsoleLogger implements Logger, OnePerThread {

    @Override
    public void log(String value) {
        System.out.println(value);
    }
}
